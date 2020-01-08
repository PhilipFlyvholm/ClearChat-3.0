package me.phil14052.ClearChat3_0.Events;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Files.Lang;
import me.phil14052.ClearChat3_0.Managers.PermissionManager;
import me.phil14052.ClearChat3_0.Utils.ChatUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class MuteEvents implements Listener{
	
	private static ClearChat plugin = ClearChat.getInstance();
	private static CCAPI api = plugin.papi;
	private static PermissionManager pm = new PermissionManager();
	
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		if(api.isGlobalChatMuted() && !pm.hasPermission(p, "clearchat.mutechat.bypass", false)){
			e.setCancelled(true);
			ChatUtils.send(p, Lang.PLAYER_GLOBAL_CHAT_DISABLED.toString(), true);
		}else if(api.isPlayerChatDisabled(p)){
			e.setCancelled(true);
			ChatUtils.send(p, Lang.FAILED_PREFIX.toString() + Lang.FAILED_REENABLE_CHAT_TO_CHAT.toString(), true);
		}else if(!pm.hasPermission(p, "clearchat.mutechat.bypass" , false)){
			
			for(Player op : Bukkit.getOnlinePlayers()){
				if(api.isPlayerChatDisabled(op)){
					if(e.getMessage().contains(op.getName())){
						String message = Lang.PLAYER_CHAT_DISABLED.toString().replace("{player_name}", op.getName()).replace("{player_displayname}", op.getDisplayName());
						ChatUtils.send(p, message, true);
					}
					e.getRecipients().remove(op);
				}
			}
			
		}
	}
	
	
}
