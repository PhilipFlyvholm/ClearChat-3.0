package me.phil14052.ClearChat3_0.API;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.phil14052.ClearChat3_0.API.CustomEvents.ChatClearEvent;

public class CCAPI {

	private JavaPlugin plugin = null;
	
	public CCAPI(JavaPlugin plugin, boolean b){
		if(plugin == null){
			Bukkit.getLogger().warning("ClearChat API: Plugin is null");
			Bukkit.getLogger().warning("ClearChat API: A plugin did not connect correct to the ClearChat api.");
			Bukkit.getLogger().warning("ClearChat API: If you think this was a problem with the ClearChat api please contact 'phil14052' on spigot.");
			return;
		}else{
			this.plugin = plugin;	
			if(!b){
				Bukkit.getLogger().info("ClearChat API: Succesfully connected " + plugin.getName() + " with the ClearChat api.");
			}
		}
	}
	
	public void ClearChatGlobalt(){
		this.ClearChatGlobal(100 , "none");
	}
	
	public void ClearChatGlobal(int lines){
		this.ClearChatGlobal(lines, "none");
	}
	public void ClearChatGlobal(String message){
		this.ClearChatGlobal(100, message);
	}
	
	public void ClearChatGlobal(int lines, String message){
		List<Player> players = new ArrayList<Player>();
		players.addAll(Bukkit.getOnlinePlayers());
		boolean withMessage = true;
		if(message.isEmpty() || message == "none" || message == "" || message == " "){
			withMessage = false;
		}
		ChatClearEvent event = new ChatClearEvent(ClearType.PERSONAL, players, withMessage, lines);
	    Bukkit.getPluginManager().callEvent(event);
	    if(!event.isCancelled()){
			for(int i=0; i<lines; i++){
				Bukkit.broadcastMessage("");
			}
			if(withMessage){
				Bukkit.broadcastMessage(message);
			}
	    }
	}
	
	public void ClearChatPersonal(Player p){
		this.ClearChatPersonal(p, 100 , "none");
	}
	
	public void ClearChatPersonal(Player p, int lines){
		this.ClearChatPersonal(p, lines, "none");
	}
	public void ClearChatPersonal(Player p, String message){
		this.ClearChatPersonal(p, 100, message);
	}
	
	public void ClearChatPersonal(Player p, int lines, String message){
		if(p == null || !p.isOnline()){
			Bukkit.getLogger().warning("ClearChat API: Player not found");
			Bukkit.getLogger().warning("ClearChat API: Player given was not online or found when #ClearChatPersonal was executed.");
			Bukkit.getLogger().warning("ClearChat API: Please contact the dev of " + plugin.getName());
		}
		List<Player> players = new ArrayList<Player>();
		players.add(p);
		boolean withMessage = true;
		if(message.isEmpty() || message == "none" || message == "" || message == " "){
			withMessage = false;
		}
		ChatClearEvent event = new ChatClearEvent(ClearType.PERSONAL, players, withMessage, lines);
	    Bukkit.getPluginManager().callEvent(event);
	    if(!event.isCancelled()){
			for(int i=0; i<lines; i++){
				p.sendMessage("");
			}
			if(withMessage){
				p.sendMessage(message);
			}
	    }
		
	}
	
	
}
