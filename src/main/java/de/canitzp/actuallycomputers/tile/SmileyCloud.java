package de.canitzp.actuallycomputers.tile;

import de.canitzp.actuallycomputers.ACDriver;
import de.canitzp.actuallycomputers.ManagedTileEnvironment;
import de.ellpeck.actuallyadditions.mod.tile.TileEntitySmileyCloud;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;

/**
 * @author canitzp
 */
public class SmileyCloud extends ACDriver<TileEntitySmileyCloud>{

    public SmileyCloud(){
        super(TileEntitySmileyCloud.class);
    }

    @Override
    public ManagedTileEnvironment<TileEntitySmileyCloud> create(TileEntitySmileyCloud tile, EnumFacing side){
        return new Env(tile, side, "smileycloud");
    }

    public static final class Env extends ManagedTileEnvironment<TileEntitySmileyCloud>{
        public Env(TileEntitySmileyCloud tile, EnumFacing side, String name){
            super(tile, side, name);
        }

        @Callback(doc = "function():string; Returns the current name of the SmileyCloud")
        public Object[] getName(Context context, Arguments args){
            return new Object[]{tile.name};
        }

        @Callback(doc = "function(name:string):boolean; Set the name, returns true if it was successful.")
        public Object[] setName(Context context, Arguments args){
            if(args.count() == 1 && args.isString(0)){
                tile.name = args.checkString(0);
                return new Object[]{true};
            }
            return new Object[]{false};
        }
    }
}
