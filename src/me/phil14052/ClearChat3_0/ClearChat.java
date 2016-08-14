package me.phil14052.ClearChat3_0;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Commands.MainCommand;
import me.phil14052.ClearChat3_0.Events.PlayerEvents;
import me.phil14052.ClearChat3_0.Files.ConfigUpdater;

public class ClearChat extends JavaPlugin{

	private static ClearChat plugin = null;
	public CCAPI papi = null;
	private static List<JavaPlugin> connectedPlugins = new ArrayList<JavaPlugin>();
	@Override
	public void onEnable(){
		plugin = this;
		new ConfigUpdater();
		saveConfig();
		papi = getApi(plugin);
	    PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerEvents(), this);
		plugin.getCommand("clearchat").setExecutor(new MainCommand());
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public static ClearChat getInstance(){
		return plugin;
	}

	public static CCAPI getApi(JavaPlugin plugin){
		if(plugin != null && connectedPlugins.contains(plugin)){
			CCAPI api = new CCAPI(plugin, true);
			return api;
		}else{
			connectedPlugins.add(plugin);
			CCAPI api = new CCAPI(plugin, false);
			return api;
		}
	}

}
