package me.phil14052.ClearChat3_0.API;

import java.util.ArrayList;
import java.util.List;

import me.phil14052.ClearChat3_0.API.CustomEvents.GlobalChatClearEvent;
import me.phil14052.ClearChat3_0.API.CustomEvents.PersonalChatClearEvent;
import me.phil14052.ClearChat3_0.Managers.MuteManager;
import me.phil14052.ClearChat3_0.Utils.FastUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CCAPI {

	private JavaPlugin plugin = null;
	private MuteManager mm;
	
	public CCAPI(JavaPlugin plugin, boolean b){
		if(plugin == null){
			Bukkit.getLogger().warning("ClearChat API: Plugin is null");
			Bukkit.getLogger().warning("ClearChat API: A plugin did not connect correct to the clearChat api.");
			Bukkit.getLogger().warning("ClearChat API: If you think this was a problem with the clearChat api please contact 'phil14052' on spigot.");
			return;
		}else{
			this.plugin = plugin;
			this.mm = MuteManager.getInstance();
			if(!b && !(plugin.getName().startsWith("ClearChat") && plugin.getDescription().getAuthors().contains("phil14052"))){
				Bukkit.getLogger().info("ClearChat API: Succesfully connected " + plugin.getName() + " with the clearChat api.");
			}
		}
	}
	
	public void clearChatGlobalt(){
		this.clearChatGlobal(100 , "none");
	}
	
	public void clearChatGlobal(int lines){
		this.clearChatGlobal(lines, "none");
	}
	public void clearChatGlobal(String message){
		this.clearChatGlobal(100, message);
	}
	
	public void clearChatGlobal(int lines, String message){
		List<Player> players = new ArrayList<Player>();
		players.addAll(Bukkit.getOnlinePlayers());
		boolean withMessage = true;
		if(message.isEmpty() || message == "none" || message == "" || message == " "){
			withMessage = false;
		}
		GlobalChatClearEvent event = new GlobalChatClearEvent(players, withMessage, lines);
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
	
	public void clearChatPersonal(Player p){
		this.clearChatPersonal(p, 100 , "none");
	}
	
	public void clearChatPersonal(Player p, int lines){
		this.clearChatPersonal(p, lines, "none");
	}
	public void clearChatPersonal(Player p, String message){
		this.clearChatPersonal(p, 100, message);
	}
	
	public void clearChatPersonal(Player p, int lines, String message){
		if(p == null || !p.isOnline()){
			Bukkit.getLogger().warning("ClearChat API: Player not found");
			Bukkit.getLogger().warning("ClearChat API: Player given was not online or found when #clearChatPersonal was executed.");
			Bukkit.getLogger().warning("ClearChat API: Please contact the dev of " + plugin.getName());
		}
		boolean withMessage = true;
		if(message.isEmpty() || message == "none" || message == "" || message == " "){
			withMessage = false;
		}
		if(FastUtils.isLessThan(lines, 1)){
			lines = 100;
		}
		PersonalChatClearEvent event = new PersonalChatClearEvent(p, withMessage, lines);
	    Bukkit.getPluginManager().callEvent(event);
	    if(!event.isCancelled()){
			for(int i=0; i<lines; i++){
				p.sendMessage(" ");
			}
			if(withMessage){
				p.sendMessage(message);
			}
	    }
		
	}
	
	public void toggleGlobalMute(){
		if(isGlobalChatMuted()){
			this.unMuteGlobalChat();
		}else{
			this.muteGlobalChat();
		}
	}
	
	public void muteGlobalChat(){
		mm.setDisableGlobalChat(true);
	}
	public void unMuteGlobalChat(){
		mm.setDisableGlobalChat(false);
	}
	
	public boolean isGlobalChatMuted(){
		return mm.isGlobalChatDisabled();
	}

	public void togglePlayerChat(Player p){
		if(p == null || !p.isOnline()){
			Bukkit.getLogger().warning("ClearChat API: Player not found");
			Bukkit.getLogger().warning("ClearChat API: Player given was not online or found when #togglePlayerChat was executed.");
			Bukkit.getLogger().warning("ClearChat API: Please contact the dev of " + plugin.getName());
			return;
		}
		if(isPlayerChatDisabled(p)){
			this.enablePlayerChat(p);
		}else{
			this.disablePlayerChat(p);
		}
	}
	
	public void disablePlayerChat(Player p){
		if(p == null || !p.isOnline()){
			Bukkit.getLogger().warning("ClearChat API: Player not found");
			Bukkit.getLogger().warning("ClearChat API: Player given was not online or found when #disablePlayerChat was executed.");
			Bukkit.getLogger().warning("ClearChat API: Please contact the dev of " + plugin.getName());
			return;
		}
		mm.setDisablePlayerChat(p, true);
	}
	public void enablePlayerChat(Player p){if(p == null || !p.isOnline()){
		Bukkit.getLogger().warning("ClearChat API: Player not found");
		Bukkit.getLogger().warning("ClearChat API: Player given was not online or found when #enablePlayerChat was executed.");
		Bukkit.getLogger().warning("ClearChat API: Please contact the dev of " + plugin.getName());
		return;
	}
		mm.setDisablePlayerChat(p,false);
	}
	
	public boolean isPlayerChatDisabled(Player p){
		return mm.isPlayerChatDisabled(p);
	}
	
	
}
