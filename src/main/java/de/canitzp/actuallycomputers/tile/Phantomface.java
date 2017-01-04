package de.canitzp.actuallycomputers.tile;

import de.canitzp.actuallycomputers.ACDriver;
import de.canitzp.actuallycomputers.ManagedTileEnvironment;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityPhantomface;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

/**
 * @author canitzp
 */
public class Phantomface extends ACDriver<TileEntityPhantomface>{

    public Phantomface(){
        super(TileEntityPhantomface.class);
    }

    @Override
    public ManagedTileEnvironment<TileEntityPhantomface> create(TileEntityPhantomface tile, EnumFacing side){
        return new Env(tile, side, tile.name);
    }

    public static final class Env extends ManagedTileEnvironment<TileEntityPhantomface>{
        public Env(TileEntityPhantomface tile, EnumFacing side, String name){
            super(tile, side, name);
        }

        @Callback(doc = "function():number,number,number; Returns the position of the bounded block.")
        public Object[] getBoundPosition(Context context, Arguments args){
            BlockPos pos = tile.getBoundPosition();
            return pos != null ? new Object[]{pos.getX(), pos.getY(), pos.getZ()} : new Object[]{null};
        }

        @Callback(doc = "function():string; Returns the name of the Phantomface type")
        public Object[] getType(Context context, Arguments args){
            return new Object[]{tile.type.name().toLowerCase()};
        }

        @Callback(doc = "function(x:number, y:number, z:number):boolean; Set the bounded Position, returns true if it was successful.")
        public Object[] setBoundedPosition(Context context, Arguments args){
            if(args.count() == 3 && args.isInteger(0) && args.isInteger(1) && args.isInteger(2)){
                BlockPos pos = new BlockPos(args.checkInteger(0), args.checkInteger(1), args.checkInteger(2));
                tile.setBoundPosition(pos);
                tile.sendUpdate();
                return new Object[]{true};
            }
            return new Object[]{false};
        }
    }
}
