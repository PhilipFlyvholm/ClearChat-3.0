package me.phil14052.ClearChat3_0.API.CustomEvents;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GlobalChatClearEvent extends Event implements Cancellable{
	
	private List<Player> players;
	private boolean isCancelled = false;
	private static final HandlerList handlers = new HandlerList();
	private boolean withMessage = false;  
	private int lines = 100;
	/**
	 * Player list can be null
	 * */
	public GlobalChatClearEvent(List<Player> players, boolean withMessage, int lines) {
	    this.isCancelled = false;
	    this.players = players;
	    this.withMessage = withMessage;
	    this.lines = lines;
	}
	 
	/**
	 * Player list can be null
	 * */
	public List<Player> getPlayers() {
	    return this.players;
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
	
	public boolean isWithMessage(){
		return withMessage;
	}

	/**
	 * @return the lines
	 */
	public int getLines() {
		return lines;
	}

	/**
	 * @param lines the lines to set
	 */
	public void setLines(int lines) {
		this.lines = lines;
	}
}
