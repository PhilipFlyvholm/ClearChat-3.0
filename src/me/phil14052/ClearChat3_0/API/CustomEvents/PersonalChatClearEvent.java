package me.phil14052.ClearChat3_0.API.CustomEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PersonalChatClearEvent extends Event implements Cancellable{
	
	private Player player;
	private boolean isCancelled = false;
	private static final HandlerList handlers = new HandlerList();
	private boolean withMessage = false;  
	private int lines = 100;
	/**
	 * Player list can be null
	 * */
	public PersonalChatClearEvent(Player player, boolean withMessage, int lines) {
	    this.isCancelled = false;
	    this.player = player;
	    this.withMessage = withMessage;
	    this.lines = lines;
	}
	 
	
	
	/**
	 * @return Returns the player who's chat got cleared.
	 */
	public Player getPlayer(){
		return this.player;
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
