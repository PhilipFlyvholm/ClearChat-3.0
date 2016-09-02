package me.phil14052.ClearChat3_0.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Utils.ChatUtils;

public class PlayerEvents implements Listener{

	private ClearChat plugin = ClearChat.getInstance();
	private CCAPI api = plugin.papi;
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(plugin.getConfig().getBoolean("login.clearOnLogin")){
			int lines = plugin.getConfig().getInt("login.lines");
			if(plugin.getConfig().getBoolean("login.message.withMessage")){
				String message = plugin.getConfig().getString("login.message.message");
				message = ChatUtils.prepareMessage(p, message);
				api.clearChatPersonal(p, lines, message);
			}else{
				api.clearChatPersonal(p, lines);
			}
		}
	}
	
}
