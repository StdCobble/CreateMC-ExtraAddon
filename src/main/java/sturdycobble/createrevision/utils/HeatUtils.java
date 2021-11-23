package sturdycobble.createrevision.utils;

import sturdycobble.createrevision.api.heat.CapabilityHeat;
import sturdycobble.createrevision.api.heat.HeatContainer;
import sturdycobble.createrevision.contents.heat.HeatPipeBlock;
import sturdycobble.createrevision.contents.heat.HeatPipeTileEntity;
import sturdycobble.createrevision.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.Direction.AxisDirection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public final class HeatUtils {

    public static boolean isPipeNode(HeatPipeTileEntity te) {
        BlockPos pos = te.getBlockPos();
        World world = te.getLevel();
        BlockState state = te.getBlockState();

        if (state.getBlock() != ModBlocks.HEAT_PIPE.get())
            return true;

        int filledDirectionCount = 0;
        int connectedAxisCount = 0;

        for (Axis axis : Axis.values()) {
            Direction d1 = Direction.get(AxisDirection.POSITIVE, axis);
            Direction d2 = Direction.get(AxisDirection.NEGATIVE, axis);
            TileEntity te1 = world.getBlockEntity(pos.relative(d1));
            TileEntity te2 = world.getBlockEntity(pos.relative(d2));
            boolean isValid1 = te1 == null ? false : te1.getCapability(CapabilityHeat.HEAT_CAPABILITY, d2).isPresent();
            boolean isValid2 = te2 == null ? false : te2.getCapability(CapabilityHeat.HEAT_CAPABILITY, d1).isPresent();
            boolean isConnected1 = state.getValue(HeatPipeBlock.PROPERTY_BY_DIRECTION.get(d1));
            boolean isConnected2 = state.getValue(HeatPipeBlock.PROPERTY_BY_DIRECTION.get(d2));
            if (isConnected1 || isConnected2) {
                connectedAxisCount++;
                if (isValid1 && isConnected1)
                    filledDirectionCount++;
                if (isValid2 && isConnected2)
                    filledDirectionCount++;
            }
        }
        return filledDirectionCount < 2 || connectedAxisCount > 1;
    }

    public static LazyOptional<HeatContainer> findNeighborNodes(TileEntity te, Direction side) {
        World world = te.getLevel();
        BlockPos pos = te.getBlockPos();

        BlockPos.Mutable mpos = new BlockPos.Mutable();

        int count = 0;
        mpos.set(pos);
        while (count < 32) {
            count++;
            mpos.move(side);
            TileEntity mTE = world.getBlockEntity(mpos);
            if (mTE != null) {
                LazyOptional<HeatContainer> nodeHeatContainer = mTE.getCapability(CapabilityHeat.HEAT_CAPABILITY, side.getOpposite());
                if (mTE instanceof HeatPipeTileEntity) {
                    if (!((HeatPipeTileEntity) mTE).isNode())
                        continue;
                }
                return nodeHeatContainer;
            }
            return LazyOptional.empty();
        }
        return LazyOptional.empty();
    }

}