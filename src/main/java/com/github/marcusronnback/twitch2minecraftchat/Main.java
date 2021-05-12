package com.github.marcusronnback.twitch2minecraftchat;
import com.github.marcusronnback.twitch2minecraftchat.commands.CommandIrcBotStart;
import com.github.marcusronnback.twitch2minecraftchat.CommandIrcBotStop;
import com.github.marcusronnback.twitch2minecraftchat.CommandIrcBotToggleChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class IrcMain extends JavaPlugin {
    FileConfiguration _config;
    @Override
    public void onEnable() {
        _config = this.getConfig();
        _config.options().copyDefaults(true);
        saveConfig();
        try {
            System.out.println("Loading plugin IRC BOT...");
            System.out.println("Generating items list ...");
            System.out.println("Verifing config set");
            verifyConfigSet();
            BotManager.getInstance().setConfig(_config);
            this.getCommand("start").setExecutor(new CommandIrcBotStart());
            this.getCommand("stop").setExecutor(new CommandIrcBotStop());
            this.getCommand("togglechat").setExecutor(new CommandIrcBotToggleChat());
        }catch (Exception e){
            e.printStackTrace();
            this.setEnabled(false);
        }
    }

    private void verifyConfigSet() throws  Exception{
        boolean isChannelEmpty = _config.getString("twitch.channel").isEmpty();
        boolean isUserNameEmpty = _config.getString("twitch.username").isEmpty();
        boolean isOauthPwdEmpty = _config.getString("twitch.oauth_pwd").isEmpty();
        if(isChannelEmpty || isUserNameEmpty || isOauthPwdEmpty){
            throw new Exception("You need to set up your plugin config file");
        }
    }

    @Override
    public void onDisable() {
        BotManager.getInstance().stop();
    }
}
