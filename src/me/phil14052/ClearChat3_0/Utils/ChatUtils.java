package me.phil14052.ClearChat3_0.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_10_R1;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_11_R1;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_12_R1;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_9_R1;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_v1_9_R2;
import me.phil14052.ClearChat3_0.Utils.JSON.JsonSender_vOther;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

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
	
	public static String prepareMessage(Player p, String string, boolean color){
		PluginDescriptionFile pluginyml = plugin.getDescription();
		if(plugin.isPlaceholderAPIEnabled()){
			string = PlaceholderAPI.setPlaceholders(p, string);
		}
		string = replacePlaceholder(string, "{player_name}", p.getName());
		string = replacePlaceholder(string, "{player_displayname}", p.getDisplayName());
		string = replacePlaceholder(string, "{plugin_name}", pluginyml.getName());
		string = replacePlaceholder(string, "{plugin_version}", pluginyml.getVersion());
		if(color){
			string = color(string);
		}
		return string;
	}
	public static String prepareMessage(CommandSender sender, String string, boolean color){
		if(sender instanceof Player){
			 Player p = (Player) sender;
			 return prepareMessage(p, string, color);
		}
		PluginDescriptionFile pluginyml = plugin.getDescription();
		string = replacePlaceholder(string, "{player_name}", "Console");
		string = replacePlaceholder(string, "{player_displayname}", "Console");
		string = replacePlaceholder(string, "{plugin_name}", pluginyml.getName());
		string = replacePlaceholder(string, "{plugin_version}", pluginyml.getVersion());
		if(color){
			string = color(string);
		}
		return string;
	}
	public static void send(Player p, String string, boolean color){
		string = prepareMessage(p, string, color);
		p.sendMessage(string);
	}
	public static void send(CommandSender sender, String string, boolean color){
		string = prepareMessage(sender, string, color);
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
		if(version.startsWith("v1_12_R1")){
			js = new JsonSender_v1_12_R1();
		}
		else if(version.startsWith("v1_11_R1")){
			js = new JsonSender_v1_11_R1();
		}
		else if(version.startsWith("v1_10")){
			js = new JsonSender_v1_10_R1();
		}
		else if(version.startsWith("v1_9_R2")){
			js = new JsonSender_v1_9_R2();
		}
		else if(version.startsWith("v1_9_R1")){
			js = new JsonSender_v1_9_R1();
		}
		else {
			js = new JsonSender_vOther();
		}
		return js;
	}
	public static void broadcastMessage(String message, boolean withConsole){
		if(withConsole){
			Bukkit.broadcastMessage(message);
			return;
		}else{
			for(Player p : Bukkit.getOnlinePlayers()){
				p.sendMessage(message);
			}
		}
	}
	
	public static String getLastColor(String string, String dchar){
		String colorcode = null;
		
		String[] splits = string.split(dchar);
		for(String str : splits){
			if(str.startsWith("4")){
				colorcode = "§4";
			}else if(str.startsWith("c")){
				colorcode = "§c";
			}else if(str.startsWith("6")){
				colorcode = "§c";
			}else if(str.startsWith("e")){
				colorcode = "§e";
			}else if(str.startsWith("2")){
				colorcode = "§2";
			}else if(str.startsWith("a")){
				colorcode = "§a";
			}else if(str.startsWith("b")){
				colorcode = "§b";
			}else if(str.startsWith("3")){
				colorcode = "§3";
			}else if(str.startsWith("1")){
				colorcode = "§1";
			}else if(str.startsWith("9")){
				colorcode = "§9";
			}else if(str.startsWith("d")){
				colorcode = "§d";
			}else if(str.startsWith("5")){
				colorcode = "§5";
			}else if(str.startsWith("f")){
				colorcode = "§f";
			}else if(str.startsWith("7")){
				colorcode = "§7";
			}else if(str.startsWith("8")){
				colorcode = "§8";
			}else if(str.startsWith("0")){
				colorcode = "§0";
			}else if(str.startsWith("l")){
				colorcode = colorcode + "§l";
			}else if(str.startsWith("n")){
				colorcode = colorcode + "§n";
			}else if(str.startsWith("o")){
				colorcode = colorcode + "§o";
			}else if(str.startsWith("k")){
				colorcode = colorcode + "§k";
			}else if(str.startsWith("m")){
				colorcode = colorcode + "§m";
			}else if(str.startsWith("r")){
				colorcode = "§r";
			}
		}
		
		return colorcode;
	}
}
