package de.canitzp.actuallycomputers.tile;

import de.canitzp.actuallycomputers.ACDriver;
import de.canitzp.actuallycomputers.ManagedTileEnvironment;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityBase;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

/**
 * @author canitzp
 */
public class TileBase extends ACDriver<TileEntityBase>{

    public TileBase(){
        super(TileEntityBase.class);
    }

    @Override
    public ManagedTileEnvironment<TileEntityBase> create(TileEntityBase tile, EnumFacing side){
        return new Env(tile, side, tile.name);
    }

    private static boolean isFluidTile(TileEntityBase tile, EnumFacing side){
        return tile.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side) && tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side) != null;
    }

    private static IFluidHandler getFluidHandler(TileEntityBase tile, EnumFacing side){
        return tile.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side);
    }

    public static final class Env extends ManagedTileEnvironment<TileEntityBase>{
        public Env(TileEntityBase tile, EnumFacing side, String name){
            super(tile, side, name);
        }

        @Override
        public int priority(){
            return 2;
        }

        /*
                    Redstone mode
                 */
        @Callback(doc = "function():string; Returns the redstone mode")
        public Object[] getRedstoneMode(Context context, Arguments args){
            return new Object[]{tile.isPulseMode ? "pulse" : "deactivated"};
        }

        @Callback(doc = "function(mode:string):boolean; Sets the redstone mode; Returns true if it was successfully")
        public Object[] setRedstoneMode(Context context, Arguments args){
            if(args.isString(0)){
                String mode = args.checkString(0);
                if("deactivated".equals(mode)){
                    tile.isPulseMode = false;
                    tile.sendUpdate();
                    return new Object[]{true};
                } else if("pulse".equals(mode)){
                    tile.isPulseMode = true;
                    tile.sendUpdate();
                    return new Object[]{true};
                }
            }
            return new Object[]{false};
        }

        /*
            Fluid storage
         */
        @Callback(doc = "function():boolean; Returns true if the TileEntity is a Fluid Storage")
        public Object[] isFluidContainer(Context context, Arguments arguments){
            return new Object[]{isFluidTile(tile, side)};
        }

        @Callback(doc = "function():number; Returns the available tanks of the Fluid Storage. Make sure that you check this before!")
        public Object[] getTankAmount(Context context, Arguments arguments){
            return new Object[]{isFluidTile(tile, side) ? getFluidHandler(tile, side).getTankProperties().length : 0};
        }

        @Callback(doc = "function(tank:number):string; Returns the name of the stored Fluid inside the specified tank")
        public Object[] getFluidType(Context context, Arguments args){
            if(args.count() == 1 && args.isInteger(0)){
                int tank = args.checkInteger(0);
                IFluidTankProperties[] props = getFluidHandler(tile, side).getTankProperties();
                return new Object[]{props.length > tank ? props[tank].getContents() != null ? props[tank].getContents().getFluid().getName() : null : null};
            }
            return new Object[]{null};
        }

        @Callback(doc = "function(tank:number):number; Returns the current amount of the stored Fluid")
        public Object[] getFluidAmount(Context context, Arguments args){
            if(args.count() == 1 && args.isInteger(0)){
                int tank = args.checkInteger(0);
                IFluidTankProperties[] props = getFluidHandler(tile, side).getTankProperties();
                return new Object[]{props.length > tank ? props[tank].getContents() != null ? props[tank].getContents().amount : 0 : 0};
            }
            return new Object[]{0};
        }

        @Callback(doc = "function(tank:number):number; Returns the capacity of the Fluid tank")
        public Object[] getTankCapacity(Context context, Arguments args){
            if(args.count() == 1 && args.isInteger(0)){
                int tank = args.checkInteger(0);
                IFluidTankProperties[] props = getFluidHandler(tile, side).getTankProperties();
                return new Object[]{props.length > tank ? props[tank].getCapacity() : 0};
            }
            return new Object[]{0};
        }
    }

}
