package de.canitzp.actuallycomputers.tile;

import de.canitzp.actuallycomputers.ACDriver;
import de.canitzp.actuallycomputers.ManagedTileEnvironment;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityFeeder;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

/**
 * @author canitzp
 */
public class Feeder extends ACDriver<TileEntityFeeder>{

    public Feeder(){
        super(TileEntityFeeder.class);
    }

    @Override
    public ManagedTileEnvironment<TileEntityFeeder> create(TileEntityFeeder tile, EnumFacing side){
        return new Env(tile, side, tile.name);
    }

    public static final class Env extends ManagedTileEnvironment<TileEntityFeeder>{
        public Env(TileEntityFeeder tile, EnumFacing side, String name){
            super(tile, side, name);
        }
        @Callback(doc = "function():number; Returns the timer variable the feeder use to pause.")
        public Object[] getCurrentTimer(Context context, Arguments args){
            return new Object[]{tile.currentTimer};
        }

        @Callback(doc = "function():number; Amount of animals to feed in range")
        public Object[] getAnimalsInRange(Context context, Arguments args){
            return new Object[]{tile.currentAnimalAmount};
        }
    }
}
