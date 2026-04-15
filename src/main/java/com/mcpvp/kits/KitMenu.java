package com.mcpvp.kits;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.server.MinecraftServer;

public class KitMenu {

    public static void openGUI(ServerPlayer player) {
        SimpleContainer container = new SimpleContainer(9);
        
        container.setItem(0, createIcon(Items.DIAMOND_SWORD, "§b§lSword Kit"));
        container.setItem(1, createIcon(Items.DIAMOND_AXE, "§3§lAxe Kit"));
        container.setItem(2, createIcon(Items.MACE, "§c§lMace Kit"));
        container.setItem(3, createIcon(Items.GOLDEN_APPLE, "§e§lUHC Kit"));
        container.setItem(4, createIcon(Items.NETHERITE_CHESTPLATE, "§8§lNetherite OP Kit"));
        container.setItem(5, createIcon(Items.SPLASH_POTION, "§d§lPot Kit"));
        container.setItem(6, createIcon(Items.ELYTRA, "§a§lSMP Kit"));
        container.setItem(7, createIcon(Items.END_CRYSTAL, "§5§lCrystal PvP Kit"));

        player.openMenu(new net.minecraft.world.MenuProvider() {
            @Override
            public Component getDisplayName() {
                return Component.literal("Select a Kit");
            }

            @Override
            public net.minecraft.world.inventory.AbstractContainerMenu createMenu(int id, net.minecraft.world.entity.player.Inventory inv, net.minecraft.world.entity.player.Player p) {
                return new ChestMenu(MenuType.GENERIC_9x1, id, inv, container, 1) {
                    @Override
                    public void clicked(int slotId, int button, ClickType clickType, net.minecraft.world.entity.player.Player p) {
                        if (slotId >= 0 && slotId < 9) {
                            handleKitSelection((ServerPlayer) p, slotId);
                        }
                    }
                };
            }
        });
    }

    private static ItemStack createIcon(net.minecraft.world.item.Item item, String name) {
        ItemStack stack = new ItemStack(item);
        stack.set(net.minecraft.core.component.DataComponents.CUSTOM_NAME, Component.literal(name));
        return stack;
    }

    private static void handleKitSelection(ServerPlayer player, int slot) {
        player.closeContainer();
        player.getInventory().clearContent();
        
        // Ensure commands execute cleanly
        MinecraftServer server = player.getServer();
        if (server == null) return;
        String pName = player.getName().getString();

        if (slot == 0) equipKitViaCommands(server, pName, "Sword");
        else if (slot == 1) equipKitViaCommands(server, pName, "Axe");
        else if (slot == 2) equipKitViaCommands(server, pName, "Mace");
        else if (slot == 3) equipKitViaCommands(server, pName, "UHC");
        else if (slot == 4) equipKitViaCommands(server, pName, "Netherite OP");
        else if (slot == 5) equipKitViaCommands(server, pName, "Pot");
        else if (slot == 6) equipKitViaCommands(server, pName, "SMP");
        else if (slot == 7) equipKitViaCommands(server, pName, "Crystal PvP");
    }

    // Since exact 1.21.1 component data is massive and complex, the most robust server-side 
    // method without hardcoding a registry parser is safely executing /item and /give.
    private static void equipKitViaCommands(MinecraftServer server, String p, String kitType) {
        CommandExecute(server, "clear " + p);
        
        if (kitType.equals("Crystal PvP")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with netherite_helmet[enchantments={protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.chest with netherite_chestplate[enchantments={protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.legs with netherite_leggings[enchantments={blast_protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.feet with netherite_boots[enchantments={blast_protection:4,feather_falling:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " weapon.offhand with totem_of_undying");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with netherite_sword[enchantments={sharpness:5,sweeping_edge:3,unbreaking:3,mending:1,knockback:1}]");
            CommandExecute(server, "give " + p + " respawn_anchor 64");
            CommandExecute(server, "give " + p + " glowstone 64");
            CommandExecute(server, "give " + p + " end_crystal 64");
            CommandExecute(server, "give " + p + " obsidian 64");
            CommandExecute(server, "give " + p + " ender_pearl 16");
            CommandExecute(server, "give " + p + " golden_apple 64");
            CommandExecute(server, "give " + p + " totem_of_undying 1");
            CommandExecute(server, "give " + p + " shield[enchantments={mending:1,unbreaking:3}]");
            for(int i=0; i<8; i++) CommandExecute(server, "give " + p + " totem_of_undying 1");
        } 
        else if (kitType.equals("Mace")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with netherite_helmet[enchantments={protection:4}]");
            CommandExecute(server, "item replace entity " + p + " armor.chest with netherite_chestplate[enchantments={protection:4}]");
            CommandExecute(server, "item replace entity " + p + " armor.legs with netherite_leggings[enchantments={protection:4}]");
            CommandExecute(server, "item replace entity " + p + " armor.feet with netherite_boots[enchantments={protection:4,feather_falling:4}]");
            CommandExecute(server, "item replace entity " + p + " weapon.offhand with totem_of_undying");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with netherite_sword[enchantments={sharpness:5,unbreaking:3}]");
            CommandExecute(server, "give " + p + " netherite_axe[enchantments={sharpness:5,unbreaking:3}]");
            CommandExecute(server, "give " + p + " ender_pearl 16");
            CommandExecute(server, "give " + p + " golden_apple 64");
            CommandExecute(server, "give " + p + " wind_charge 64");
            CommandExecute(server, "give " + p + " elytra");
            CommandExecute(server, "give " + p + " mace[enchantments={breach:4,unbreaking:3}]");
            CommandExecute(server, "give " + p + " mace[enchantments={density:5,wind_burst:1,unbreaking:3}]");
            CommandExecute(server, "give " + p + " shield[enchantments={mending:1,unbreaking:3}]");
        }
        else if (kitType.equals("Sword")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with diamond_helmet[enchantments={protection:4,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.chest with diamond_chestplate[enchantments={protection:4,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.legs with diamond_leggings[enchantments={protection:3,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.feet with diamond_boots[enchantments={protection:3,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with diamond_sword[enchantments={unbreaking:3}]");
        }
        else if (kitType.equals("Axe")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with diamond_helmet");
            CommandExecute(server, "item replace entity " + p + " armor.chest with diamond_chestplate");
            CommandExecute(server, "item replace entity " + p + " armor.legs with diamond_leggings");
            CommandExecute(server, "item replace entity " + p + " armor.feet with diamond_boots");
            CommandExecute(server, "item replace entity " + p + " weapon.offhand with shield");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with diamond_sword");
            CommandExecute(server, "give " + p + " diamond_axe");
            CommandExecute(server, "give " + p + " bow");
            CommandExecute(server, "give " + p + " crossbow");
            CommandExecute(server, "give " + p + " arrow 6");
        }
        else if (kitType.equals("SMP")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with netherite_helmet[enchantments={protection:4,unbreaking:3,mending:1,respiration:3,aqua_affinity:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.chest with netherite_chestplate[enchantments={protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.legs with netherite_leggings[enchantments={protection:4,unbreaking:3,mending:1,swift_sneak:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.feet with netherite_boots[enchantments={protection:4,unbreaking:3,mending:1,feather_falling:4,depth_strider:3,soul_speed:3}]");
            CommandExecute(server, "item replace entity " + p + " weapon.offhand with shield[enchantments={mending:1,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with netherite_sword[enchantments={sharpness:5,sweeping_edge:3,fire_aspect:2,unbreaking:3,knockback:1}]");
            CommandExecute(server, "give " + p + " ender_pearl 16");
            CommandExecute(server, "give " + p + " golden_apple 64");
            CommandExecute(server, "give " + p + " netherite_axe[enchantments={sharpness:5,efficiency:5,unbreaking:3}]");
            CommandExecute(server, "give " + p + " experience_bottle 64");
            CommandExecute(server, "give " + p + " totem_of_undying 1");
        }
        else if (kitType.equals("Netherite OP")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with netherite_helmet[enchantments={protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.chest with netherite_chestplate[enchantments={protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.legs with netherite_leggings[enchantments={protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " armor.feet with netherite_boots[enchantments={protection:4,unbreaking:3,mending:1}]");
            CommandExecute(server, "item replace entity " + p + " weapon.offhand with golden_apple 64");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with netherite_sword[enchantments={sharpness:5,sweeping_edge:3,unbreaking:3}]");
            for(int i=0; i<5; i++) CommandExecute(server, "give " + p + " splash_potion[potion_contents={potion:'strong_healing'}]");
            CommandExecute(server, "give " + p + " splash_potion[potion_contents={potion:'strong_strength'}]");
            CommandExecute(server, "give " + p + " splash_potion[potion_contents={potion:'strong_swiftness'}]");
            CommandExecute(server, "give " + p + " totem_of_undying 1");
        }
        else if (kitType.equals("Pot")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with diamond_helmet[enchantments={protection:4,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.chest with diamond_chestplate[enchantments={protection:4,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.legs with diamond_leggings[enchantments={protection:4,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.feet with diamond_boots[enchantments={protection:4,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with diamond_sword[enchantments={sharpness:5,sweeping_edge:3,unbreaking:3}]");
            CommandExecute(server, "item replace entity " + p + " weapon.offhand with cooked_beef 5");
            for(int i=0; i<15; i++) CommandExecute(server, "give " + p + " splash_potion[potion_contents={potion:'strong_healing'}]");
            CommandExecute(server, "give " + p + " splash_potion[potion_contents={potion:'strong_strength'}]");
            CommandExecute(server, "give " + p + " splash_potion[potion_contents={potion:'strong_swiftness'}]");
        }
        else if (kitType.equals("UHC")) {
            CommandExecute(server, "item replace entity " + p + " armor.head with diamond_helmet[enchantments={protection:3}]");
            CommandExecute(server, "item replace entity " + p + " armor.chest with diamond_chestplate[enchantments={protection:2}]");
            CommandExecute(server, "item replace entity " + p + " armor.legs with diamond_leggings[enchantments={protection:2}]");
            CommandExecute(server, "item replace entity " + p + " armor.feet with diamond_boots[enchantments={protection:3}]");
            CommandExecute(server, "item replace entity " + p + " weapon.offhand with shield");
            CommandExecute(server, "item replace entity " + p + " hotbar.0 with diamond_sword[enchantments={sharpness:3}]");
            CommandExecute(server, "give " + p + " diamond_axe[enchantments={efficiency:3}]");
            CommandExecute(server, "give " + p + " golden_apple 8");
            CommandExecute(server, "give " + p + " oak_planks 64");
            CommandExecute(server, "give " + p + " water_bucket");
            CommandExecute(server, "give " + p + " lava_bucket");
            CommandExecute(server, "give " + p + " crossbow[enchantments={piercing:1}]");
            CommandExecute(server, "give " + p + " cobweb 8");
        }
    }

    private static void CommandExecute(MinecraftServer server, String cmd) {
        server.getCommands().performPrefixedCommand(server.createCommandSourceStack(), cmd);
    }
}
