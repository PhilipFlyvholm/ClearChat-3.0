package me.phil14052.ClearChat3_0.API.CustomEvents;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.phil14052.ClearChat3_0.API.ClearType;

public class ChatClearEvent extends Event implements Cancellable{
	
	private List<Player> players;
	private boolean isCancelled = false;
	private static final HandlerList handlers = new HandlerList();
	private ClearType clearType;
	private boolean withMessage = false;  
	private int lines = 100;
	/**
	 * Player list can be null
	 * */
	public ChatClearEvent(ClearType ct, List<Player> players, boolean withMessage, int lines) {
	    this.isCancelled = false;
	    this.players = players;
	    this.clearType = ct;
	    this.withMessage = withMessage;
	    this.lines = lines;
	}
	 
	/**
	 * Player list can be null
	 * */
	public List<Player> getPlayers() {
	    return this.players;
	}
	/**
	 * Use to get the first player or the player when using personal clear.
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
	public ClearType getClearType() {
		return clearType;
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
