package com.github.marcusronnback.twitch2minecraftchat;

import com.github.marcusronnback.twitch2minecraftchat.BotManager;
import com.github.marcusronnback.twitch2minecraftchat.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandIrcBotToggleChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length > 0) return  false;
        boolean _chatEnabled = BotManager.getInstance().toggleChat();
        if(_chatEnabled){
            ChatUtils.log(sender,"Twitch Chat is now enabled");
        }else {
            ChatUtils.log(sender,"Twitch Chat is now disabled");
        }
        return true;
    }
}
