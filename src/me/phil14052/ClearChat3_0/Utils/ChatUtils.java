package me.phil14052.ClearChat3_0.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.phil14052.ClearChat3_0.ClearChat;

public class ChatUtils {

	private static ClearChat plugin = ClearChat.getInstance();
	
	public static String getString(String path){
		return plugin.getConfig().getString(path);
	}
	
	public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static String prepareMessage(Player p, String string){
		string = color(string);
		string = string.replace("{player_name}", p.getName());
		string = string.replace("{player_displayname}", p.getDisplayName());
		return string;
	}
	
	public static void send(Player p, String string){
		string = prepareMessage(p, string);
		p.sendMessage(string);
	}
	
	
}
