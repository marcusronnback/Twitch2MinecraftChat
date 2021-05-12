package com.github.marcusronnback.twitch2minecraftchat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChatUtils {
    private   static  String _colors[] = {"black","dark_blue","dark_green","dark_aqua","dark_red",
            "dark_purple","gold","gray","dark_gray","blue","green","aqua","light_purple","yellow"};
    private static HashMap<String,String> user_colors = new HashMap<String,String>();        
    private static Random _random = new Random();

    public  static void info(CommandSender sender,String message){
        sender.sendMessage(ChatColor.AQUA + "[INFO] " + ChatColor.WHITE + message);
    }
    public  static void log(CommandSender sender,String message){
        sender.sendMessage(ChatColor.GREEN + "[OK] " + ChatColor.WHITE + message);
    }
    public  static void error(CommandSender sender,String message){
        sender.sendMessage(ChatColor.RED + "[ERROR] " + ChatColor.WHITE + message);
    }
    public  static void warn(CommandSender sender,String message){
        sender.sendMessage(ChatColor.YELLOW + "[WARN] " + ChatColor.WHITE + message);
    }

    public  static  void twitchChatToMinecraft(String user, String message, String prefix){
        //dispatch async event
        Bukkit.getServer().getScheduler()
            .scheduleSyncDelayedTask(Main.getPlugin(Main.class), new Runnable() {
            public void run() {
                String color;
                if (user_colors.containsKey(user)){
                    color = user_colors.get(user);
                } else {
                    int colorIndex = _random.nextInt(_colors.length);
                    color = _colors[colorIndex];
                    user_colors.put(user, color);
                }
                String cmd = String.format("tellraw @a [\"\",{\"text\":\"%s \",\"color\":\"light_purple\"},{\"text\":\"%s:\",\"color\":\"%s\"},{\"text\":\" %s\"}]",
                        prefix, user, color, message);
                System.out.println(cmd);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),cmd);
            }
        });
    }
}
