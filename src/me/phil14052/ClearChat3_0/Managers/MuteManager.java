package me.phil14052.ClearChat3_0.Managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class MuteManager {

	private static MuteManager instance = null;
	private List<Player> personalMute;
	private boolean globalMute;
	
	public MuteManager(){
		personalMute = new ArrayList<Player>();
		setDisableGlobalChat(false);
	}
	
	public boolean isGlobalChatDisabled() {
		return globalMute;
	}
	
	public void setDisableGlobalChat(boolean globalMute) {
		this.globalMute = globalMute;
	}
	
	public boolean isPlayerChatDisabled(Player p){
		return personalMute.contains(p);
	}
	
	public void setDisablePlayerChat(Player p, Boolean value){
		if(value){
			if(!personalMute.contains(p)) personalMute.add(p);
		}else{
			if(personalMute.contains(p)) personalMute.remove(p);
		}
	}
	
	
	public static MuteManager getInstance(){
		if(instance == null){
			instance = new MuteManager();
		}
		return instance;
	}
	
}
