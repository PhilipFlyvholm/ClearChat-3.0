package me.phil14052.ClearChat3_0.API.CustomEvents;

import java.util.List;

import me.phil14052.ClearChat3_0.API.ChatToggleType;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChatToggleEvent extends Event implements Cancellable{
	
	private List<Player> players;
	private boolean isCancelled = false;
	private static final HandlerList handlers = new HandlerList();
	private ChatToggleType chatToggleType;
	/**
	 * Player list can be null
	 * */
	public ChatToggleEvent(ChatToggleType ctt, List<Player> players) {
	    this.isCancelled = false;
	    this.players = players;
	    this.chatToggleType = ctt;
	}
	 
	/**
	 * Player list can be null
	 * */
	public List<Player> getPlayers() {
	    return this.players;
	}
	/**
	 * @return Use to get the first player or the player when using personal clear.
	 */
	public Player getFirstPlayer(){
		return this.players.get(0);
	}
	
	@Override
	public boolean isCancelled() {
	    return isCancelled;
	}
	 
	@Override
	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	 
	 
	@Override
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}

	/**
	 * @return the clearType
	 */
	public ChatToggleType getChatToggleType() {
		return chatToggleType;
	}
	

}
