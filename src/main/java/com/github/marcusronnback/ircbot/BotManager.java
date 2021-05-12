package com.github.marcusronnback.ircbot;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jibble.pircbot.PircBot;
import java.util.HashMap;
import java.util.Random;


public class BotManager extends PircBot {

    private static BotManager _instance = null;
    private  HashMap<String,Long> _chatTimeout;
    private int CHAT_TIMEOUT;
    private boolean _chatEnabled = false;
    FileConfiguration _config = null;

    BotManager(){
        _chatTimeout = new HashMap<>();
    }

    public static  BotManager getInstance(){
        if(_instance == null){
            _instance = new BotManager();
        }
        return  _instance;
    }

    public  void setConfig(FileConfiguration config){
        _config = config;
        try {
            _chatEnabled = _config.getBoolean("chat.enable");
            CHAT_TIMEOUT = _config.getInt("chat.timeout");
            this.setName(_config.getString("twitch.username"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public  boolean toggleChat(){
        _chatEnabled = !_chatEnabled;
        _chatTimeout.clear();
        return  _chatEnabled;
    }

    public  boolean isChatEnabled(){
        return  _chatEnabled;
    }

    public  String getCurrentChannel(){
        return  _config.getString("twitch.channel");
    }

    private  void _connect() throws  Exception{
            if(_config == null) throw new Exception("Couldn't load configuration file");
            String pwd = _config.getString("twitch.oauth_pwd","");
            if(pwd.isEmpty()) throw new Exception("twitch oauth_pwd is empty");
            this.connect(
                    "irc.twitch.tv",
                    6667,
                     pwd
            );
            this.joinChannel("#"+_config.getString("twitch.channel"));
    }

    public  boolean start() throws Exception{
            if(this.isConnected()) {
                return false;
            }else{
                _connect();
            return true;
            }
    }

    private  void clearTimeouts(){
        _chatTimeout.clear();
    }

    public  boolean stop(){
        if(this.isConnected()) {
            this.disconnect();
            this.clearTimeouts();
          return true;
        }else{
         return  false;
        }
    }

    private boolean canSendChatMessage(String sender){
        if(!_chatTimeout.containsKey(sender)) return  true;
        long time = _chatTimeout.get(sender);
        return  ( System.currentTimeMillis() - time ) > CHAT_TIMEOUT;
    }

    public void onMessage(String channel, String sender,
                          String login, String hostname, String message) {
        if(!this.isChatEnabled()) return;
        if(!sender.equalsIgnoreCase(this.getName()) &&
                !sender.toLowerCase().contains("bot")){
            if(!canSendChatMessage(sender))
                return;
            ChatUtils.twitchChatToMinecraft(sender,message);
            _chatTimeout.put(sender,System.currentTimeMillis());
        }
    }

}
