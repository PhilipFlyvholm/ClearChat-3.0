package me.phil14052.ClearChat3_0.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length < 1){
			//Help menu or something
			return true;
		}
		if(args[0].equalsIgnoreCase("help")){
			//Show help
		}
		else if(args[0].equalsIgnoreCase("global")){
			if(args.length > 2){
				if(args[1].equalsIgnoreCase("-s")){
					//Silent clear
				}else if(args[1].equalsIgnoreCase("-a")){
					//global clear anonymous
				}
			}else{
				//global clear
			}
			return true;
			
		}else if(args[0].equalsIgnoreCase("personal")){
			if(args.length > 2){
				if(args[1].equalsIgnoreCase("-s")){
					//Silent clear
				}else{
					//Personal clear
				}
			}else{
				//Personal clear
			}
			return true;
			
		}else if(args[0].equalsIgnoreCase("mutechat")){
			//Mute
			if(args.length > 2){
				if(args[1].equalsIgnoreCase("global")){
					//Mute global
					return true;
				}else if(args[1].equalsIgnoreCase("personal")){
					if(args.length > 3){
						String playername = args[2];
						@SuppressWarnings("deprecation")
						Player p = Bukkit.getPlayer(playername);
						if(!p.isOnline()){
							return false;
						}
						//Mute p(Player) chat 
					}else{
						//Mute senders chat
					}
				}
				return true;
			}else{
				//Mute help
			}
		}else if(args[0].equalsIgnoreCase("reload")){
			//reload
			return true;
		}
		
		return false;
	}

}
