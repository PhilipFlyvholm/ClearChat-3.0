package me.phil14052.ClearChat3_0.Managers;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.API.CCAPI;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class AutoClearManager {

	private static ClearChat plugin = ClearChat.getInstance();
	private CCAPI api = plugin.papi;
	private static AutoClearManager instance = null;
	private BukkitTask runnable = null;
	private int interval = 0;
	
	
	public AutoClearManager(){
		api.setAutoClearEnabled(plugin.getConfig().getBoolean("clear.autoclear.enabled"));
		int time = 0;
		int hours = plugin.getConfig().getInt("clear.autoclear.time.hours");
		int minutes = plugin.getConfig().getInt("clear.autoclear.time.minutes");
		int seconds = plugin.getConfig().getInt("clear.autoclear.time.seconds");
		minutes = minutes*60;
		hours = (hours*60)*60;
		time = seconds+minutes+hours;
		api.setAutoClearInterval(time);
		start();
	}
	
	private void start(){
		this.interval = api.getAutoClearInterval();
		runnable = new BukkitRunnable(){

			@Override
			public void run() {
				if(api.isAutoClearEnabled()){
					String message = "none";
					if(plugin.getConfig().getBoolean("clear.autoclear.message.withMessag")) message = plugin.getConfig().getString("clear.autoclear.message.message");
					api.clearChatGlobal(plugin.getConfig().getBoolean("clear.autoclear.ingammeplayersonly"), plugin.getConfig().getInt("clear.autoclear.lines"), message);
					if(api.getAutoClearInterval() != interval){
						stop();
						start();
					}					
				}
			}
			
		}.runTaskTimer(plugin, interval*20, interval*20);
	}	
	public void stop(){
		this.runnable.cancel();
		this.runnable = null;
	
	}
	public boolean isRunning(){
		if(runnable == null) return false;
		else return true;
	}
	public void restart(){
		if(runnable != null){
			stop();
		}
		start();
	}
	
	public static AutoClearManager getInstance(){
		if(instance == null) instance = new AutoClearManager();
		return instance;
	}
}
