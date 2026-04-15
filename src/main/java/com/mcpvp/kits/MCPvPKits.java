package com.mcpvp.kits;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("mcpvpkits")
public class MCPvPKits {
    public MCPvPKits() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onCommandsRegister(RegisterCommandsEvent event) {
        KitCommand.register(event.getDispatcher());
    }
}
