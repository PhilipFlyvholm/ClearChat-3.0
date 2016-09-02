package me.phil14052.ClearChat3_0.Commands;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Files.Lang;
import me.phil14052.ClearChat3_0.Managers.PermissionManager;
import me.phil14052.ClearChat3_0.Utils.ChatUtils;
import me.phil14052.ClearChat3_0.Utils.FastUtils;
import mkremins.fanciful.FancyMessage;

import org.bukkit.Bukkit;
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
			@SuppressWarnings("unused")
			FancyMessage fm = new FancyMessage();
			return true;
		}
		if(args[0].equalsIgnoreCase("help")){
			//Show help
			return true;
		}else if(args[0].equalsIgnoreCase("global")){
			if(FastUtils.isGreaterThan(args.length, 1)){
				if(args[1].equalsIgnoreCase("-s")){
					api.clearChatGlobal(plugin.getConfig().getInt("clear.global.lines"),  "none");
					return true;
				}else if(args[1].equalsIgnoreCase("-a")){
					String message = ChatUtils.prepareMessage(sender, Lang.PREFIX.toString() + Lang.PLAYER_GLOBAL_CLEAR_ANONYMOUS.toString());
					api.clearChatGlobal(plugin.getConfig().getInt("clear.global.lines"),  message);
					return true;
				}else{
					ChatUtils.send(sender, Lang.INVALID_ARGS.toString());
					return false;
				}
			}else{
				String message = ChatUtils.prepareMessage(sender, Lang.PREFIX.toString() + Lang.PLAYER_GLOBAL_CLEAR_DEFAULT.toString());
				api.clearChatGlobal(plugin.getConfig().getInt("clear.global.lines"),  message);
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
				if(args[1].equalsIgnoreCase("-s")){
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
						Bukkit.broadcastMessage(Lang.PREFIX.toString() + Lang.PLAYER_GLOBAL_CHAT_DISABLED);	
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
