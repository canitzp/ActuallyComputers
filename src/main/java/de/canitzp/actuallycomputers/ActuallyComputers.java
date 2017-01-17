package de.canitzp.actuallycomputers;

import de.canitzp.actuallycomputers.tile.*;
import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * @author canitzp
 */
@Mod(modid = ActuallyComputers.MODID, name = ActuallyComputers.MODNAME, version = ActuallyComputers.MODVERSION, dependencies = ActuallyComputers.DEPENDENCIES)
public class ActuallyComputers {

    public static final String MODID = "actuallycomputers";
    public static final String MODNAME = "ActuallyComputers";
    public static final String MODVERSION = "@Version@";
    public static final String DEPENDENCIES = "required-after:OpenComputers@[1.6,);required-after:actuallyadditions";

    @Optional.Method(modid = "OpenComputers")
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){
        Driver.add(new Compost());
        Driver.add(new TileBase());
        Driver.add(new Feeder());
        Driver.add(new Phantomface());
        Driver.add(new XPSolidifier());
        Driver.add(new SmileyCloud());
        Driver.add(new CoffeeMaker());
    }

}
