package com.mcpvp.kits;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class KitCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("kits")
            .executes(context -> {
                ServerPlayer player = context.getSource().getPlayerOrException();
                KitMenu.openGUI(player);
                return 1;
            })
        );
    }
}
