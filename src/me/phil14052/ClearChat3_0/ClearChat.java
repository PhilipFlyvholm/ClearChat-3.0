package me.phil14052.ClearChat3_0;

import org.bukkit.plugin.java.JavaPlugin;

public class ClearChat extends JavaPlugin{

	private static ClearChat plugin = null;
	
	@Override
	public void onEnable(){
		plugin = this;
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public static ClearChat getInstance(){
		return plugin;
	}
	
}
