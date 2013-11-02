package io.github.fourohfour.wolvesvspigs;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class GameStateChangeEvent extends Event implements Cancellable{
	private static final HandlerList handlers = new HandlerList();
	public String state;

	
    public GameStateChangeEvent() {
        state = Globals.globalvars.get("gamestage").toString();
    }
	

    public String getState() {
    	return state;
    }
	
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}

	@Override
	public boolean isCancelled() {
		return true;
	}

	@Override
	public void setCancelled(boolean arg0) {
		// TODO Auto-generated method stub
		
	}
}
