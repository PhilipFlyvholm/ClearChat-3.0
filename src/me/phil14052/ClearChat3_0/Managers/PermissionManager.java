package me.phil14052.ClearChat3_0.Managers;

import me.phil14052.ClearChat3_0.Files.Lang;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionManager {
	public boolean hasPermission(Player p, String permission, boolean withMessage){			
		String permissionClone = permission;
		if(permission.contains(".")){
			int index = permission.lastIndexOf(".");
			permissionClone = permissionClone.replace(permissionClone.substring(index), "");
		}
		if(p.hasPermission(permissionClone + ".*")) return true;
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
			if(hasPermission(p, permission, withMessage)){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
}
