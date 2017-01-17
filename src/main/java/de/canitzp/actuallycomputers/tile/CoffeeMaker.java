package de.canitzp.actuallycomputers.tile;

import de.canitzp.actuallycomputers.ACDriver;
import de.canitzp.actuallycomputers.ManagedTileEnvironment;
import de.ellpeck.actuallyadditions.mod.items.InitItems;
import de.ellpeck.actuallyadditions.mod.items.metalists.TheMiscItems;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityCoffeeMachine;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityCompost;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidRegistry;

import static de.ellpeck.actuallyadditions.mod.tile.TileEntityCoffeeMachine.*;

/**
 * @author canitzp
 */
public class CoffeeMaker extends ACDriver<TileEntityCoffeeMachine>{

    public CoffeeMaker(){
        super(TileEntityCoffeeMachine.class);
    }

    @Override
    public ManagedTileEnvironment<TileEntityCoffeeMachine> create(TileEntityCoffeeMachine tile, EnumFacing side){
        return new Env(tile, side, tile.name);
    }

    private static boolean canMakeCoffee(TileEntityCoffeeMachine tile){
        return StackUtil.isValid(tile.slots[SLOT_INPUT]) && tile.slots[SLOT_INPUT].getItem() == InitItems.itemMisc && tile.slots[SLOT_INPUT].getItemDamage() == TheMiscItems.CUP.ordinal() && !StackUtil.isValid(tile.slots[SLOT_OUTPUT]) && tile.coffeeCacheAmount >= CACHE_USE && tile.tank.getFluid() != null && tile.tank.getFluid().getFluid() == FluidRegistry.WATER && tile.tank.getFluidAmount() >= WATER_USE;
    }

    public static final class Env extends ManagedTileEnvironment<TileEntityCoffeeMachine>{
        public Env(TileEntityCoffeeMachine tile, EnumFacing side, String name){
            super(tile, side, name);
        }

        @Callback(doc = "function():boolean; Returns true if it was successfully started. Starts the coffee maker if it isn't already running.")
        public Object[] startCoffee(Context context, Arguments arguments){
            if(tile.brewTime <= 0 && canMakeCoffee(tile)){
                tile.brew();
                return new Object[]{true};
            }
            return new Object[]{false};
        }

    }

}
