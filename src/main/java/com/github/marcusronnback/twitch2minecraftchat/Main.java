package com.github.marcusronnback.twitch2minecraftchat;
import com.github.marcusronnback.twitch2minecraftchat.CommandT2mcBotStart;
import com.github.marcusronnback.twitch2minecraftchat.CommandT2mcBotStop;
import com.github.marcusronnback.twitch2minecraftchat.CommandT2mcBotToggleChat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    FileConfiguration _config;
    @Override
    public void onEnable() {
        _config = this.getConfig();
        _config.options().copyDefaults(true);
        saveConfig();
        try {
            System.out.println("Loading plugin Twitch2MinecraftChat...");
            System.out.println("Generating items list ...");
            System.out.println("Verifing config set");
            verifyConfigSet();
            BotManager.getInstance().setConfig(_config);
            this.getCommand("start").setExecutor(new CommandT2mcBotStart());
            this.getCommand("stop").setExecutor(new CommandT2mcBotStop());
            this.getCommand("togglechat").setExecutor(new CommandT2mcBotToggleChat());
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
