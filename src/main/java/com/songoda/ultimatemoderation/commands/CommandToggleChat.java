package com.songoda.ultimatemoderation.commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.locale.Message;
import com.songoda.ultimatemoderation.UltimateModeration;
import com.songoda.ultimatemoderation.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandToggleChat extends AbstractCommand {

    private UltimateModeration instance;

    /*
     * Chat is enabled by default ;)
     */
    private boolean toggled = true;

    public CommandToggleChat(UltimateModeration instance) {
        super(CommandType.PLAYER_ONLY, "togglechat");
        this.instance = instance;
    }

    @Override
    protected ReturnType runCommand(CommandSender sender, String... args) {
        toggled = !toggled;

        Message message = toggled ? instance.getLocale().getMessage("command.togglechat.toggledOn")
                : instance.getLocale().getMessage("command.togglechat.toggledOff");

        ChatListener.setChatToggled(toggled);

        for (Player player : Bukkit.getOnlinePlayers()) {

            message.sendPrefixedMessage(player);

            if (!player.hasPermission(getPermissionNode() + ".bypass"))
                continue;

            instance.getLocale().getMessage("command.togglechat.bypass").sendMessage(player);
        }

        if (!(sender instanceof Player))
            message.sendPrefixedMessage(sender);

        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender sender, String... args) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "um.togglechat";
    }

    @Override
    public String getSyntax() {
        return "/ToggleChat";
    }

    @Override
    public String getDescription() {
        return "Toggle chat for the entire server";
    }
}
