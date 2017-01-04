package de.canitzp.actuallycomputers.tile;

import de.canitzp.actuallycomputers.ACDriver;
import de.canitzp.actuallycomputers.ManagedTileEnvironment;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityXPSolidifier;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

/**
 * @author canitzp
 */
public class XPSolidifier extends ACDriver<TileEntityXPSolidifier>{

    public XPSolidifier(){
        super(TileEntityXPSolidifier.class);
    }

    @Override
    public ManagedTileEnvironment<TileEntityXPSolidifier> create(TileEntityXPSolidifier tile, EnumFacing side){
        return new Env(tile, side, tile.name);
    }

    public static final class Env extends ManagedTileEnvironment<TileEntityXPSolidifier>{
        public Env(TileEntityXPSolidifier tile, EnumFacing side, String name){
            super(tile, side, name);
        }

        @Callback(doc = "function():number; Returns the amount of stored XP")
        public Object[] getXPAmount(Context context, Arguments args){
            return new Object[]{tile.amount};
        }
    }
}
