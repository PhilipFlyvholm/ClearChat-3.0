package me.phil14052.ClearChat3_0.Managers;

import me.phil14052.ClearChat3_0.Files.Lang;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionManager {
	public boolean hasPermisson(Player p, String permission, boolean withMessage){
		if(p.hasPermission("clearchat.*")){
			return true;
		}
		else if(p.hasPermission(permission)){
			return true;
		}
		else{
			if(withMessage == true){
				p.sendMessage(Lang.PREFIX.toString() + Lang.NO_PERMS.toString());
			}
			return false;	
		}
	}
	
	public boolean hasPermission(CommandSender sender, String permission, boolean withMessage){
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(hasPermisson(p, permission, withMessage)){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}
