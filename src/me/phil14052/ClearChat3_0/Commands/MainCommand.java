package me.phil14052.ClearChat3_0.Commands;

import java.util.HashMap;
import java.util.List;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Files.Lang;
import me.phil14052.ClearChat3_0.Managers.AutoClearManager;
import me.phil14052.ClearChat3_0.Managers.PermissionManager;
import me.phil14052.ClearChat3_0.Utils.ChatUtils;
import me.phil14052.ClearChat3_0.Utils.FastUtils;
import mkremins.fanciful.FancyMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class MainCommand implements CommandExecutor{

	private static ClearChat plugin = ClearChat.getInstance();
	@SuppressWarnings("unused")
	private PluginDescriptionFile pluginYml = plugin.getDescription();
	private CCAPI api = plugin.papi;
	private PermissionManager pm = new PermissionManager();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(FastUtils.isLessThan(args.length, 1)){
			List<String> info = Lang.PLAYER_PLUGIN_INFO.toStringList();
			String ppart = "";
			for(String line : info){
				line = ChatUtils.prepareMessage(sender, line);
				//MAXS 1 %run_command_ per line
				if(line.contains("%run_command_")){
					String[] strings = line.split("%run_command_");
					String command = strings[1];
					command = command.substring(command.indexOf("_") + 1);
					command = command.substring(0, command.indexOf("%"));
					String l = strings[1];
					l = l.replaceAll(command + "%", "");
					FancyMessage fm = new FancyMessage(strings[0]);
					if(ppart != "" && ChatUtils.getLastColor(ppart, "§") != null){
						command = ChatUtils.getLastColor(ppart, "§") + command;
					}
					fm.then(command)
					.command(ChatColor.stripColor(command))
					.tooltip("§cClick here to run " + ChatColor.stripColor(command))
					.then(l);
					ChatUtils.getJsonSender().sendJson(sender, fm);
					
				}else if(line.contains("%suggest_command_")){
					
					String[] strings = line.split("%suggest_command_");
					String command = strings[1];
					command = command.substring(command.indexOf("_") + 1);
					command = command.substring(0, command.indexOf("%"));
					String l = strings[1];
					l = l.replaceAll(command + "%", "");
					FancyMessage fm = new FancyMessage(strings[0]);
					if(!line.startsWith("%suggest_command_") && ppart != "" && ChatUtils.getLastColor(ppart, "§") != null){
						command = ChatUtils.getLastColor(ppart, "§") + command;
					}
					fm.then(command)
					.suggest(ChatColor.stripColor(command))
					.tooltip("§c" + ChatColor.stripColor(command))
					.then(l);
					ChatUtils.getJsonSender().sendJson(sender, fm);
					
				}else{
					String[] splits = line.split("http");
					int part = 0;
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					for(String split : splits){
						if(split.startsWith("s://") || split.startsWith("://")){
							if(split.endsWith("?") || split.endsWith("!") || split.endsWith(".") || split.endsWith("<") || split.endsWith(">")){
								plugin.getLogger().info("A link may not end with a symbol: " + split);
							}else{
								map.put(split, part);
							}
						}
						part++;
					}
					FancyMessage fm = new FancyMessage("");
					part = 0;
					for(String split : splits){
						if(map.containsKey(split) && map.get(split) == part){
							if(ppart != "" && ChatUtils.getLastColor(ppart, "§") != null){
								split = ChatUtils.getLastColor(ppart, "§") + "http" + split;
							}else{
								split = "http" + split;	
							}
							fm.then(split)
							.link(ChatColor.stripColor(split))
							.tooltip("§cClick to open link");
						}else{
							fm.then(split);
						}
						ppart = split;
						part++;
					}
					ChatUtils.getJsonSender().sendJson(sender, fm);
				}
				ppart = line;
			}
			
			return true;
		}
		if(args[0].equalsIgnoreCase("help")){
			List<String> info = Lang.PLAYER_PLUGIN_HELP.toStringList();
			String ppart = "";
			for(String line : info){
				line = ChatUtils.prepareMessage(sender, line);
				//MAXS 1 %run_command_ per line
				if(line.contains("%run_command_")){
					
					String[] strings = line.split("%run_command_");
					String command = strings[1];
					command = command.substring(command.indexOf("_") + 1);
					command = command.substring(0, command.indexOf("%"));
					String l = strings[1];
					l = l.replaceAll(command + "%", "");
					FancyMessage fm = new FancyMessage(strings[0]);
					if(ppart != "" && ChatUtils.getLastColor(ppart, "§") != null && ChatUtils.getLastColor(strings[0], "§") == null){
						command = ChatUtils.getLastColor(ppart, "§") + command;
					}else if(ppart != "" && ChatUtils.getLastColor(strings[0], "§") != null){
						command = ChatUtils.getLastColor(strings[0], "§") + command;
					}
					fm.then(command)
					.command(ChatColor.stripColor(command))
					.tooltip("§cClick here to run " + ChatColor.stripColor(command))
					.then(l);
					ChatUtils.getJsonSender().sendJson(sender, fm);
					
				}else if(line.contains("%suggest_command_")){
					
					String[] strings = line.split("%suggest_command_");
					String command = strings[1];
					command = command.substring(command.indexOf("_") + 1);
					command = command.substring(0, command.indexOf("%"));
					String l = strings[1];
					l = l.replaceAll(command + "%", "");
					FancyMessage fm = new FancyMessage(strings[0]);
					if(ppart != "" && ChatUtils.getLastColor(ppart, "§") != null && ChatUtils.getLastColor(strings[0], "§") == null){
						command = ChatUtils.getLastColor(ppart, "§") + command;
					}else if(ppart != "" && ChatUtils.getLastColor(strings[0], "§") != null){
						command = ChatUtils.getLastColor(strings[0], "§") + command;
					}
					fm.then(command)
					.suggest(ChatColor.stripColor(command))
					.tooltip("§c" + ChatColor.stripColor(command))
					.then(l);
					ChatUtils.getJsonSender().sendJson(sender, fm);
					
				}else{
					String[] splits = line.split("http");
					int part = 0;
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					for(String split : splits){
						if(split.startsWith("s://") || split.startsWith("://")){
							if(split.endsWith("?") || split.endsWith("!") || split.endsWith(".") || split.endsWith("<") || split.endsWith(">")){
								plugin.getLogger().info("A link may not end with a symbol: " + split);
							}else{
								map.put(split, part);
							}
						}
						part++;
					}
					FancyMessage fm = new FancyMessage("");
					part = 0;
					for(String split : splits){
						if(map.containsKey(split) && map.get(split) == part){
							if(ppart != "" && ChatUtils.getLastColor(ppart, "§") != null){
								split = ChatUtils.getLastColor(ppart, "§") + "http" + split;
							}else{
								split = "http" + split;	
							}
							fm.then(split)
							.link(ChatColor.stripColor(split))
							.tooltip("§cClick to open link");
						}else{
							fm.then(split);
						}
						ppart = split;
						part++;
					}
					ChatUtils.getJsonSender().sendJson(sender, fm);
				}
				ppart = line;
			}
			
			return true;
		}else if(args[0].equalsIgnoreCase("global")){
			boolean inGamePlayersOnly = plugin.getConfig().getBoolean("clear.global.ingammeplayersonly");
			if(FastUtils.isGreaterThan(args.length, 1)){
				if(args[1].equalsIgnoreCase("-s")){
					api.clearChatGlobal(inGamePlayersOnly, plugin.getConfig().getInt("clear.global.lines"),  "none");
					return true;
				}else if(args[1].equalsIgnoreCase("-a")){
					String message = ChatUtils.prepareMessage(sender, Lang.PREFIX.toString() + Lang.PLAYER_GLOBAL_CLEAR_ANONYMOUS.toString());
					api.clearChatGlobal(inGamePlayersOnly, plugin.getConfig().getInt("clear.global.lines"),  message);
					return true;
				}else{
					ChatUtils.send(sender, Lang.INVALID_ARGS.toString());
					return false;
				}
			}else{
				String message = ChatUtils.prepareMessage(sender, Lang.PREFIX.toString() + Lang.PLAYER_GLOBAL_CLEAR_DEFAULT.toString());
				api.clearChatGlobal(inGamePlayersOnly, plugin.getConfig().getInt("clear.global.lines"),  message);
				return true;
			}			
		}else if(args[0].equalsIgnoreCase("personal")){
			
			if(!(sender instanceof Player)){
				ChatUtils.send(sender, Lang.PLAYER_ONLY.toString());
				
				return false;
			}
			Player p = (Player) sender;
			
			String message = ChatUtils.prepareMessage(p, Lang.PREFIX.toString() + Lang.PLAYER_PERSONAL_CLEAR_DEFAULT.toString());
			if(FastUtils.isGreaterThan(args.length, 1)){
				if(args[1].equalsIgnoreCase("-m")){
					api.clearChatPersonal(p, plugin.getConfig().getInt("clear.personal.lines"), "none");
					return true;
				}else{
					ChatUtils.send(sender, Lang.INVALID_ARGS.toString());
					return false;
				}
			}else{
				api.clearChatPersonal(p, plugin.getConfig().getInt("clear.personal.lines"), message);
				return true;
			}
			
		}else if(args[0].equalsIgnoreCase("mutechat")){
			
			if(FastUtils.isGreaterThan(args.length, 1)){
				if(args[1].equalsIgnoreCase("global")){
					api.toggleGlobalMute();
					if(api.isGlobalChatMuted()){
						sender.sendMessage(Lang.PREFIX.toString() + Lang.PLAYER_GLOBAL_MUTE_ON);
						Bukkit.broadcastMessage(Lang.PREFIX.toString() + Lang.BROADCAST_GLOBAL_MUTE_ON);	
					}else{
						sender.sendMessage(Lang.PREFIX.toString() + Lang.PLAYER_GLOBAL_MUTE_OFF);
						Bukkit.broadcastMessage(Lang.PREFIX.toString() + Lang.BROADCAST_GLOBAL_MUTE_OFF);	
					}
					return true;
				}else if(args[1].equalsIgnoreCase("personal")){
					if(FastUtils.isGreaterThan(args.length, 2)){
						String playername = args[2];
						@SuppressWarnings("deprecation")
						Player p = Bukkit.getPlayer(playername);
						if(p == null || !p.isOnline()){
							String message = ChatUtils.color(Lang.FAILED_PREFIX.toString() + Lang.FAILED_PLAYER_NOT_FOUND.toString());
							message = message.replace("{player_name}", playername);
							message = message.replace("{player_displayname}", playername);
							sender.sendMessage(message);
							return false;
						}
						api.togglePlayerChat(p);
						if(api.isPlayerChatDisabled(p)){
							ChatUtils.send(p, Lang.PREFIX.toString() +  Lang.PLAYER_PERSONAL_MUTE_OFF.toString());
							return true;
						}else{
							ChatUtils.send(p, Lang.PREFIX.toString() + Lang.PLAYER_PERSONAL_MUTE_ON.toString());
							return true;
						}
					}else{
						if(!(sender instanceof Player)){
							ChatUtils.send(sender, Lang.PLAYER_ONLY.toString());
							ChatUtils.send(sender, Lang.USAGE_PREFIX.toString() + Lang.USAGE_MUTE_PERSONAL_CONSOLE.toString());
							return false;
						}
						Player p = (Player) sender;
						api.togglePlayerChat(p);
						if(api.isPlayerChatDisabled(p)){
							ChatUtils.send(sender, Lang.PREFIX.toString() +  Lang.PLAYER_PERSONAL_MUTE_ON.toString());
							return true;
						}else{
							ChatUtils.send(sender, Lang.PREFIX.toString() + Lang.PLAYER_PERSONAL_MUTE_OFF.toString());
							return true;
						}
					}
				}
			}else{
				ChatUtils.send(sender, Lang.USAGE_PREFIX.toString() + Lang.USAGE_MUTE.toString());
				return false;
			}
			return false;
		}else if(args[0].equalsIgnoreCase("reload")){
			if(pm.hasPermission(sender, "clearchat.reload", true)){
				double time = System.currentTimeMillis();
				plugin.reloadConfig();
				plugin.lang.reload();
				api.setAutoClearEnabled(plugin.getConfig().getBoolean("clear.autoclear.enabled"));
				int hours = plugin.getConfig().getInt("clear.autoclear.time.hours");
				int minutes = plugin.getConfig().getInt("clear.autoclear.time.minutes");
				int seconds = plugin.getConfig().getInt("clear.autoclear.time.seconds");
				minutes = minutes*60;
				hours = (hours*60)*60;
				seconds = seconds+minutes+hours;
				api.setAutoClearInterval(seconds);
				AutoClearManager.getInstance().restart();
				double time2 = System.currentTimeMillis();
				double time3 = (time2-time)/1000;
				String message = Lang.PREFIX.toString() + Lang.RELOAD_SUCCES.toString();
				message = message.replaceAll("%time%", String.valueOf(time3));
				sender.sendMessage(message);
				return true;
			}
			return false;
		}else{
			ChatUtils.send(sender, Lang.INVALID_ARGS.toString());
			return false;
		}
		
	}

}
