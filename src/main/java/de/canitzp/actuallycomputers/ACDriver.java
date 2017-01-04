package de.canitzp.actuallycomputers;

import de.ellpeck.actuallyadditions.mod.tile.TileEntityBase;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverSidedTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * @author canitzp
 */
public abstract class ACDriver<T extends TileEntityBase> extends DriverSidedTileEntity{

    private Class<T> clazz;

    public ACDriver(Class<T> clazz){
        this.clazz = clazz;
    }

    @Override
    public Class<?> getTileEntityClass(){
        return clazz;
    }

    @Override
    public ManagedEnvironment createEnvironment(World world, BlockPos pos, EnumFacing side){
        return create((T) world.getTileEntity(pos), side);
    }

    public abstract ManagedTileEnvironment<T> create(T tile, EnumFacing side);

}
