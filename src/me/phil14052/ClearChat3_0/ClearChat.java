package me.phil14052.ClearChat3_0;

import java.util.ArrayList;
import java.util.List;

import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Commands.MainCommand;
import me.phil14052.ClearChat3_0.Events.MuteEvents;
import me.phil14052.ClearChat3_0.Events.PlayerEvents;
import me.phil14052.ClearChat3_0.Files.ConfigUpdater;
import me.phil14052.ClearChat3_0.Files.Files;
import me.phil14052.ClearChat3_0.Files.Lang;
import me.phil14052.ClearChat3_0.Files.LangFileUpdater;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ClearChat extends JavaPlugin{

	private static ClearChat plugin = null;
	public CCAPI papi = null;
	private static List<JavaPlugin> connectedPlugins = new ArrayList<JavaPlugin>();
	public Files lang;
	
	@Override
	public void onEnable(){
		plugin = this;
		lang = new Files(this, "lang.yml");
		new LangFileUpdater(plugin);
		Lang.setFile(lang);
		new ConfigUpdater();
		saveConfig();
		papi = getApi(plugin);
		registerEvents();
		plugin.getCommand("clearchat").setExecutor(new MainCommand());
	}
	
	@Override
	public void onDisable(){
		plugin = null;
	}
	
	public static ClearChat getInstance(){
		return plugin;
	}

	private void registerEvents(){
	    PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerEvents(), this);
		pm.registerEvents(new MuteEvents(), this);
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
