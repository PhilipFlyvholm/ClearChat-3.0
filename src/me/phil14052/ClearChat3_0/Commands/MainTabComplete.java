/**
 * ClearChat-3_0 By @author Philip Flyvholm
 * MainTabComplete.java
 */
package me.phil14052.ClearChat3_0.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.Managers.PermissionManager;

/**
 * @author Philip
 *
 */
public class MainTabComplete implements TabCompleter{
	private static ClearChat plugin = ClearChat.getInstance();
	private PermissionManager pm = new PermissionManager();
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("clearchat") || args.length < 1 || !(sender instanceof Player)) return null;
		if(args.length < 2) {
			List<String> subCommands = new ArrayList<>();
			if(!plugin.getConfig().getBoolean("other.helpmenu.needpermission") 
					|| pm.hasPermission(sender, plugin.getConfig().getString("other.helpmenu.permission"), false)) subCommands.add("help");
			if(pm.hasPermission(sender, "clearchat.commands.global", false)) subCommands.add("global");
			if(pm.hasPermission(sender, "clearchat.commands.personal", false)) subCommands.add("personal");
			if(pm.hasPermission(sender, "clearchat.commands.mutechat", false)) subCommands.add("mutechat");
			if(pm.hasPermission(sender, "clearchat.reload", false)) subCommands.add("reload");
			if(plugin.getConfig().getBoolean("GUI.needPermission") && pm.hasPermission(sender, plugin.getConfig().getString("GUI.permission"), false)) subCommands.add("gui");
			return subCommands;	
		} else if(args.length < 3 && args[0].equalsIgnoreCase("global")) {
			List<String> subArgs = new ArrayList<>();
			if(pm.hasPermission(sender, "clearchat.commands.global.silent", false)) subArgs.add("-s");
			if(pm.hasPermission(sender, "clearchat.commands.global.anonymous", false)) subArgs.add("-a");
			return subArgs;	
		} else if(args.length < 3 && args[0].equalsIgnoreCase("personal")) {
			List<String> subArgs = new ArrayList<>();
			subArgs.add("-m");
			return subArgs;	
		} else if(args.length < 3 && args[0].equalsIgnoreCase("mutechat")) {
			List<String> subArgs = new ArrayList<>();
			if(pm.hasPermission(sender, "clearchat.commands.mutechat.global", false)) subArgs.add("global");
			if(pm.hasPermission(sender, "clearchat.commands.mutechat.personal", false)) subArgs.add("personal");
			return subArgs;	
		} else if(args.length < 4 && args[0].equalsIgnoreCase("mutechat") && args[1].equalsIgnoreCase("personal")) {
			List<String> subArgs = new ArrayList<>();
			if(pm.hasPermission(sender, "clearchat.commands.mutechat.personal", false) && pm.hasPermission(sender, "clearchat.commands.mutechat.personal.other", false)) {
				for(Player p : Bukkit.getOnlinePlayers()) {
					subArgs.add(p.getName());
				}
			}
			return subArgs;	
		}
	
		
		return new ArrayList<>();
	}
}
