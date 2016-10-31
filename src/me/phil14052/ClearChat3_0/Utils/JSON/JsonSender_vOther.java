package me.phil14052.ClearChat3_0.Utils.JSON;

import mkremins.fanciful.FancyMessage;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JsonSender_vOther implements JsonSender{

	public void sendJson(CommandSender sender, FancyMessage fm) {
		String msg = fm.toOldMessageFormat();
		sender.sendMessage(msg);
	}
	
	public void sendJson(Player player, FancyMessage fm) {
		String msg = fm.toOldMessageFormat();
		player.sendMessage(msg);
	}

	
}
