package com.nesaak.hidenbt;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class WindowItemListener extends PacketAdapter {

    HideNBTPlugin plugin;

    public WindowItemListener(HideNBTPlugin plugin) {
        super(plugin, PacketType.Play.Server.WINDOW_ITEMS);
        this.plugin = plugin;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        StructureModifier<List<ItemStack>> modifier = event.getPacket().getItemListModifier();
        modifier.write(0, modifier.read(0).stream().map(item -> plugin.removeNBT(item)).collect(Collectors.toList()));
    }
}
