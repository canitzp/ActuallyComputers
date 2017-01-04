package de.canitzp.actuallycomputers;

import de.ellpeck.actuallyadditions.mod.tile.TileEntityBase;
import li.cil.oc.api.Network;
import li.cil.oc.api.driver.NamedBlock;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;
import net.minecraft.util.EnumFacing;

/**
 * @author canitzp
 */
public class ManagedTileEnvironment<T extends TileEntityBase> extends ManagedEnvironment implements NamedBlock {

    protected String name;
    protected T tile;
    protected EnumFacing side;

    public ManagedTileEnvironment(T tile, EnumFacing side, String name){
        this.name = name;
        this.tile = tile;
        this.side = side;
        this.setNode(Network.newNode(this, Visibility.Network).withComponent(name, Visibility.Network).create());
    }

    @Override
    public String preferredName() {
        return this.name;
    }

    @Override
    public int priority() {
        return 3;
    }

}
