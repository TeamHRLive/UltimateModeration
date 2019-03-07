package com.songoda.ultimatemoderation.gui;

import com.songoda.ultimatemoderation.UltimateModeration;
import com.songoda.ultimatemoderation.utils.gui.AbstractGUI;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class GUIPlayer extends AbstractGUI {

    private final UltimateModeration plugin;

    private final OfflinePlayer toModerate;

    public GUIPlayer(UltimateModeration plugin, OfflinePlayer toModerate, Player player) {
        super(player);
        this.plugin = plugin;
        this.toModerate = toModerate;

        init(plugin.getLocale().getMessage("gui.player.title", toModerate.getName()), 54);
    }

    @Override
    protected void constructGUI() {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = ((SkullMeta) head.getItemMeta());
        meta.setOwningPlayer(toModerate);
        head.setItemMeta(meta);

        createButton(13, head, "&7&l" + toModerate.getName());

        createButton(8, Material.OAK_DOOR, plugin.getLocale().getMessage("gui.general.back"));

        createButton(28, Material.ANVIL, plugin.getLocale().getMessage("gui.player.punish"));
        createButton(30, Material.CHEST, plugin.getLocale().getMessage("gui.player.tickets"));
        createButton(32, Material.DIAMOND_SWORD, plugin.getLocale().getMessage("gui.player.punishments"));
        createButton(34, Material.MAP, plugin.getLocale().getMessage("gui.player.notes"));
        createButton(40, Material.DIAMOND_CHESTPLATE, plugin.getLocale().getMessage("gui.player.moderate"));
    }

    @Override
    protected void registerClickables() {
        registerClickable(8, ((player1, inventory1, cursor, slot, type) ->
                new GUIPlayers(plugin, player1)));

        registerClickable(28, ((player1, inventory1, cursor, slot, type) ->
                new GUIPunish(plugin, toModerate, null, player1)));

        registerClickable(30, ((player1, inventory1, cursor, slot, type) ->
                new GUITicketManager(plugin, toModerate, player1)));

        registerClickable(32, ((player1, inventory1, cursor, slot, type) ->
                new GUIPunishments(plugin, toModerate, player1)));

        registerClickable(34, ((player1, inventory1, cursor, slot, type) ->
                new GUINotesManager(plugin, toModerate, player1)));

        registerClickable(40, ((player1, inventory1, cursor, slot, type) ->
                new GUIModerate(plugin, toModerate, player1)));
    }

    @Override
    protected void registerOnCloses() {

    }
}