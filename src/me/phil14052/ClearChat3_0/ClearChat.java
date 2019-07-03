package me.phil14052.ClearChat3_0;

import java.util.ArrayList;
import java.util.List;

import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Commands.MainCommand;
import me.phil14052.ClearChat3_0.Events.InventoryEvents;
import me.phil14052.ClearChat3_0.Events.MuteEvents;
import me.phil14052.ClearChat3_0.Events.PlayerEvents;
import me.phil14052.ClearChat3_0.Files.ConfigUpdater;
import me.phil14052.ClearChat3_0.Files.Files;
import me.phil14052.ClearChat3_0.Files.Lang;
import me.phil14052.ClearChat3_0.Files.LangFileUpdater;
import me.phil14052.ClearChat3_0.Managers.AutoClearManager;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearChat extends JavaPlugin{

	private static ClearChat plugin = null;
	public CCAPI papi = null;
	private static List<JavaPlugin> connectedPlugins = new ArrayList<JavaPlugin>();
	public Files lang;
	private boolean isPlaceholderAPIEnabled = false;
	@Override
	public void onEnable(){
		plugin = this;
		double time = System.currentTimeMillis();
		new ConfigUpdater();
		saveConfig();
		this.debug("Enabling ClearChat");
		this.debug("The config is now setup");
		lang = new Files(this, "lang.yml");
		new LangFileUpdater(plugin);
		Lang.setFile(lang);
		this.debug("Lang is now setup");
		papi = getApi(plugin);
		this.debug("Api is now setup");
		registerEvents();
		plugin.getCommand("clearchat").setExecutor(new MainCommand());
		AutoClearManager.getInstance();
		this.debug("Registed events and commands");
		this.debug("Checking for PlaceholderAPI");
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			isPlaceholderAPIEnabled = true;
			this.debug("Found PlaceholderAPI");
		}else{
			this.debug("PlaceholderAPI not found. Skipping it.");
		}
		double time2 = System.currentTimeMillis();
		double time3 = (time2-time)/1000;
		this.debug("Took " + String.valueOf(time3) + " seconds to setup ClearChat 3.0");
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public static ClearChat getInstance(){
		return plugin;
	}

	public void debug(String message){
		if(plugin.getConfig().getBoolean("Debugmode")){
			Bukkit.getConsoleSender().sendMessage(("&8[&3&lClearChat&8]: &c&lDebug &8-&7 " + message).replaceAll("&", "\u00A7"));
		}
	}
	
	public boolean isPlaceholderAPIEnabled(){
		return isPlaceholderAPIEnabled;
	}
	
	private void registerEvents(){
	    PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerEvents(), this);
		pm.registerEvents(new MuteEvents(), this);
		pm.registerEvents(new InventoryEvents(), this);
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
