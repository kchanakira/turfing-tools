package shiro.turfingtools.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shiro.turfingtools.TurfingTools;

import javax.annotation.Nonnull;

public class ItemTiller extends ItemSpade {

    public ItemTiller(ToolMaterial material, String unlocalizedName)
    {
        super(material);

        setRegistryName(new ResourceLocation(TurfingTools.MODID, unlocalizedName));
        setUnlocalizedName(unlocalizedName);
        setMaxStackSize(1);
        setCreativeTab(CreativeTabs.TOOLS);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, @Nonnull World world, BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing side, float hitX, float hitY, float hitZ)
    {
        ItemStack stack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos, side, stack))
            return EnumActionResult.PASS;
        else
        {
            Block block = world.getBlockState(pos).getBlock();

            if (block == Blocks.DIRT || block == Blocks.GRASS || block == Blocks.GRASS_PATH || block == Blocks.FARMLAND || block == Blocks.MYCELIUM)
            {
                if (block == Blocks.DIRT && world.getBlockState(pos).getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT)
                {
                    return EnumActionResult.PASS;
                }

                if (world.isRemote) {
                    world.playSound(player, pos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);

                    return EnumActionResult.SUCCESS;
                }
                else
                {

                    // Turn the block into dirt
                    AxisAlignedBB blockBB = block.getBlockState().getBaseState().getCollisionBoundingBox(world, pos);
                    AxisAlignedBB diffBB = new AxisAlignedBB(0, blockBB.maxY, 0, 1.0D, 1.0D, 1.0D);
                    AxisAlignedBB offsetBB = diffBB.offset(pos);

                    world.setBlockState(pos, Blocks.DIRT.getDefaultState());

                    for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(null, offsetBB))
                    {
                        double yOffset = Math.min(offsetBB.maxY - offsetBB.minY, offsetBB.maxY - entity.getEntityBoundingBox().minY);

                        entity.setPositionAndUpdate(entity.posX, entity.posY + yOffset + 0.001D, entity.posZ);
                    }

                    stack.damageItem(1, player);

                    return EnumActionResult.SUCCESS;
                }
            }

            return EnumActionResult.PASS;
        }
    }
}
