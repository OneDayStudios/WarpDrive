package cr0s.warpdrive.block.energy;

import cr0s.warpdrive.block.BlockAbstractContainer;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnanReactorLaser extends BlockAbstractContainer {
	
	@SideOnly(Side.CLIENT)
	static IIcon[] iconBuffer;
	
	public BlockEnanReactorLaser() {
		super(Material.iron);
		setResistance(60.0F * 5 / 3);
		setBlockName("warpdrive.energy.EnanReactorLaser");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new TileEntityEnanReactorLaser();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister) {
		iconBuffer = new IIcon[3];
		iconBuffer[0] = iconRegister.registerIcon("warpdrive:energy/enanReactorLaserTopBottom");
		iconBuffer[1] = iconRegister.registerIcon("warpdrive:energy/enanReactorLaserSides");
		iconBuffer[2] = iconRegister.registerIcon("warpdrive:energy/enanReactorLaserActive");
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
		final int metadata  = blockAccess.getBlockMetadata(x, y, z);
		if (side == 0 || side == 1) {
			return iconBuffer[0];
		}
		
		final int facing = metadata >= 8 ? metadata & 7 : 6;
		if (side == facing) {
			return iconBuffer[2];
		}
		
		return iconBuffer[1];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int metadata) {
		if (side == 0 || side == 1) {
			return iconBuffer[0];
		}
		
		if (side == 4) {
			return iconBuffer[2];
		}
		
		return iconBuffer[1];
	}
	
	@Override
	public byte getTier(final ItemStack itemStack) {
		return 3;
	}
}