package de.canitzp.actuallycomputers.tile;

import de.canitzp.actuallycomputers.ACDriver;
import de.canitzp.actuallycomputers.ManagedTileEnvironment;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityCompost;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

/**
 * @author canitzp
 */
public class Compost extends ACDriver<TileEntityCompost>{

    public Compost(){
        super(TileEntityCompost.class);
    }

    @Override
    public ManagedTileEnvironment<TileEntityCompost> create(TileEntityCompost tile, EnumFacing side){
        return new Env(tile, side, tile.name);
    }

    public static final class Env extends ManagedTileEnvironment<TileEntityCompost>{
        public Env(TileEntityCompost tile, EnumFacing side, String name){
            super(tile, side, name);
        }
        @Callback(doc = "function():number; Returns the time the conversion has done. 3000 is max")
        public Object[] getConversionTime(Context context, Arguments arguments){
            return new Object[]{tile.conversionTime};
        }
    }

}
