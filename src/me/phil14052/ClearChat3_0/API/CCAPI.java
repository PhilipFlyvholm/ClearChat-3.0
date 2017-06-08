package me.phil14052.ClearChat3_0.API;

import java.util.ArrayList;
import java.util.List;

import me.phil14052.ClearChat3_0.API.CustomEvents.GlobalChatClearEvent;
import me.phil14052.ClearChat3_0.API.CustomEvents.PersonalChatClearEvent;
import me.phil14052.ClearChat3_0.Managers.MuteManager;
import me.phil14052.ClearChat3_0.Managers.PermissionManager;
import me.phil14052.ClearChat3_0.Utils.ChatUtils;
import me.phil14052.ClearChat3_0.Utils.FastUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CCAPI {

	private JavaPlugin plugin = null;
	private MuteManager mm;
	private boolean isAutoClearEnabled = false;
	private int autoClearInterval = 0;
	private PermissionManager pm = null;
	
	/**
	 * Get the CCAPI instance
	 * @param plugin = Your JavaPlugin instance
	 * @param b = With message (Does not work with all plugins) Use true.
	 */
	public CCAPI(JavaPlugin plugin, boolean b){
		if(plugin == null){
			Bukkit.getLogger().warning("ClearChat API: Plugin is null");
			Bukkit.getLogger().warning("ClearChat API: A plugin did not connect correct to the clearChat api.");
			Bukkit.getLogger().warning("ClearChat API: If you think this was a problem with the clearChat api please contact 'phil14052' on spigot.");
			return;
		}else{
			this.plugin = plugin;
			this.mm = MuteManager.getInstance();
			this.pm = new PermissionManager();
			if(!b && !(plugin.getName().startsWith("ClearChat") && plugin.getDescription().getAuthors().contains("phil14052"))){
				Bukkit.getLogger().info("ClearChat API: Succesfully connected " + plugin.getName() + " with the clearChat api.");
			}
		}
	}
	
	/**
	 * Clear the global chat
	 */
	public void clearChatGlobal(){
		this.clearChatGlobal(false, 100 , "none", false);
	}

	
	/**
	 * Clear the global chat
	 * @param inGamePlayersOnly = Boolean (should it only clear in game players - Does not clear console)
	 */
	public void clearChatGlobal(boolean inGamePlayersOnly){
		this.clearChatGlobal(inGamePlayersOnly, 100 , "none", false);
	}
	
	
	/**
	 * Clear the global chat
	 * @param lines = How many lines should be cleared (100 is default)
	 */
	public void clearChatGlobal(int lines){
		this.clearChatGlobal(false, lines, "none", false);
	}
	
	/**
	 * Clear the global chat
	 * @param inGamePlayersOnly = Boolean (should it only clear in game players - Does not clear console)
	 * @param lines = How many lines should be cleared (100 is default)
	 */
	public void clearChatGlobal(boolean inGamePlayersOnly, int lines){
		this.clearChatGlobal(inGamePlayersOnly, lines, "none", false);
	}
	
	/**
	 * Clear the global chat
	 * @param inGamePlayersOnly = Boolean (should it only clear in game players - Does not clear console)
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
	public void clearChatGlobal(boolean inGamePlayersOnly, String message){
		this.clearChatGlobal(inGamePlayersOnly, 100, message, false);
	}

	/**
	 * Clear the global chat
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
	public void clearChatGlobal(String message){
		this.clearChatGlobal(false, 100, message, false);
	}
	
	/**
	 * Clear the global chat
	 * @param inGamePlayersOnly = Boolean (should it only clear in game players - Does not clear console)
	 * @param lines = How many lines should be cleared (100 is default)
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
	public void clearChatGlobal(int lines, String message){
		this.clearChatGlobal(false, lines, message, false);
	}
	
	/**
	 * Clear the global chat
	 * @param lines = How many lines should be cleared (100 is default)
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
	public void clearChatGlobal(boolean inGamePlayersOnly, int lines, String message){
		this.clearChatGlobal(inGamePlayersOnly, lines, message, false);
	}
	
	/**
	 * Clear the global chat
	 * @param lines = How many lines should be cleared (100 is default)
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
	public void clearChatGlobal(boolean inGamePlayersOnly, int lines, String message, boolean sendToAdmins){
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
				if(!sendToAdmins){
					for(Player p : Bukkit.getOnlinePlayers()){
						if(!pm.hasPermission(p, "clearchat.global.bypass", false)){
							p.sendMessage(" ");
						}
					}
				}else{
					ChatUtils.broadcastMessage(" ", !inGamePlayersOnly);
				}
			}
			if(withMessage){
				ChatUtils.broadcastMessage(message, !inGamePlayersOnly);
			}
	    }
	}
	

	/**
	 * Clear the personal chat
	 * @param p = The player which chat should be cleared
	 */
	public void clearChatPersonal(Player p){
		this.clearChatPersonal(p, 100 , "none");
	}
	

	/**
	 * Clear the personal chat
	 * @param p = The player which chat should be cleared
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
	public void clearChatPersonal(Player p, String message){
		this.clearChatPersonal(p, 100, message);
	}
	
	/**
	 * Clear the personal chat
	 * @param p = The player which chat should be cleared
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
	public void clearChatPersonal(Player p, int lines){
		this.clearChatPersonal(p, lines, "none");
	}
	
	/**
	 * Clear the personal chat
	 * @param p = The player which chat should be cleared
	 * @param lines = How many lines should be cleared (100 is default)
	 * @param message = The message that will be sent after clear (Use "none" for no message)
	 */
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

	
	/**
	 * Toggle global chats mute
	 */
	public void toggleGlobalMute(){
		if(isGlobalChatMuted()){
			this.unMuteGlobalChat();
		}else{
			this.muteGlobalChat();
		}
	}
	
	
	/**
	 * Mute global chat
	 */
	public void muteGlobalChat(){
		mm.setDisableGlobalChat(true);
	}
	
	/**
	 * Unmute global chat
	 */
	public void unMuteGlobalChat(){
		mm.setDisableGlobalChat(false);
	}
	
	
	/**
	 * @return true if global chat is muted
	 */
	public boolean isGlobalChatMuted(){
		return mm.isGlobalChatDisabled();
	}

	
	/**
	 * Toggle a players chat (Disable a player from receiving messages)
	 * @param p = Player
	 */
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
	
	/**
	 * Disable a player from receiving messages
	 * @param p = Player
	 */
	public void disablePlayerChat(Player p){
		if(p == null || !p.isOnline()){
			Bukkit.getLogger().warning("ClearChat API: Player not found");
			Bukkit.getLogger().warning("ClearChat API: Player given was not online or found when #disablePlayerChat was executed.");
			Bukkit.getLogger().warning("ClearChat API: Please contact the dev of " + plugin.getName());
			return;
		}
		mm.setDisablePlayerChat(p, true);
	}
	
	/**
	 * Enable a player from receiving messages
	 * @param p = Player
	 */
	public void enablePlayerChat(Player p){if(p == null || !p.isOnline()){
		Bukkit.getLogger().warning("ClearChat API: Player not found");
		Bukkit.getLogger().warning("ClearChat API: Player given was not online or found when #enablePlayerChat was executed.");
		Bukkit.getLogger().warning("ClearChat API: Please contact the dev of " + plugin.getName());
		return;
	}
		mm.setDisablePlayerChat(p,false);
	}
	
	/**
	 * Check if a player can receive messages
	 * @return if a players is disabled from receiving messages
	 * @param p = Player
	 */
	public boolean isPlayerChatDisabled(Player p){
		return mm.isPlayerChatDisabled(p);
	}
	
	
	/**
	 * Get interval between the chat is cleared automatically 
	 * @return Interval for AutoClear
	 */
	public int getAutoClearInterval(){
		return this.autoClearInterval;
	}
	
	
	/**
	 * Get interval between the chat is cleared automatically 
	 * @param interval = Interval for AutoClear
	 */
	public void setAutoClearInterval(int interval){
		this.autoClearInterval = interval;
	}
	
	
	/**
	 * Toggle AutoClear
	 */
	public void toggleAutoClear(){
		if(this.isAutoClearEnabled){
			this.setAutoClearEnabled(false);
		}else{
			this.setAutoClearEnabled(true);
		}
	}

	/**
	 * Check if AutoClear is enabled
	 * @return true if autoclear is enabled
	 */
	public boolean isAutoClearEnabled() {
		return isAutoClearEnabled;
	}

	/**
	 * Set if AutoClear is enabled
	 * @param isAutoClearEnabled = boolean
	 */
	public void setAutoClearEnabled(boolean isAutoClearEnabled) {
		this.isAutoClearEnabled = isAutoClearEnabled;
		
	}
}
