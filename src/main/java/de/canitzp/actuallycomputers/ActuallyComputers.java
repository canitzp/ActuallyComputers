package de.canitzp.actuallycomputers;

import de.canitzp.actuallycomputers.tile.*;
import li.cil.oc.api.Driver;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/**
 * @author canitzp
 */
@Mod(modid = ActuallyComputers.MODID, name = ActuallyComputers.MODNAME, version = ActuallyComputers.MODVERSION, dependencies = ActuallyComputers.DEPENDENCIES, acceptedMinecraftVersions = ActuallyComputers.MINECRAFT_VERSIONS)
public class ActuallyComputers {

    public static final String MODID = "actuallycomputers";
    public static final String MODNAME = "ActuallyComputers";
    public static final String MODVERSION = "@Version@";
    public static final String DEPENDENCIES = "required-after:opencomputers@[1.7,);required-after:actuallyadditions";
    public static final String MINECRAFT_VERSIONS = "1.12.2";

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
