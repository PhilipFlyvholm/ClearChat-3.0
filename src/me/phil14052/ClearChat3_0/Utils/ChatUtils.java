package me.phil14052.ClearChat3_0.Utils;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_10_R1;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_9_R1;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_9_R2;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatUtils {

	private static ClearChat plugin = ClearChat.getInstance();
	private static JsonSender js = null;
	
	
	public static String getString(String path){
		return plugin.getConfig().getString(path);
	}
	
	public static String color(String string){
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static String replacePlaceholder(String string, String placeholder, String replacement){
		if(placeholder.startsWith("{")){
			string = string.replace(placeholder, replacement);
		}else{
			string = string.replaceAll(placeholder, replacement);
		}
		return string;
	}
	
	public static String prepareMessage(Player p, String string){
		string = replacePlaceholder(string, "{player_name}", p.getName());
		string = replacePlaceholder(string, "{player_displayname}", p.getDisplayName());
		string = color(string);
		return string;
	}
	public static String prepareMessage(CommandSender sender, String string){
		if(sender instanceof Player){
			 Player p = (Player) sender;
			 return prepareMessage(p, string);
		}
		string = replacePlaceholder(string, "{player_name}", "Console");
		string = replacePlaceholder(string, "{player_displayname}", "Console");
		string = color(string);
		return string;
	}
	public static void send(Player p, String string){
		string = prepareMessage(p, string);
		p.sendMessage(string);
	}
	public static void send(CommandSender sender, String string){
		string = prepareMessage(sender, string);
		sender.sendMessage(string);
	}
	
	public static JsonSender getJsonSender(){
		if(js != null) return js;
		String version = "";
		try {
		     version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException ex) {
		ex.printStackTrace();
		}
		if(version.startsWith("v1_10")){
			js = new JsonSender_v1_10_R1();
		}
		if(version.startsWith("v1_9_R2")){
			js = new JsonSender_v1_9_R2();
		}
		if(version.startsWith("v1_9_R1")){
			js = new JsonSender_v1_9_R1();
		}
		return js;
	}
	
}
