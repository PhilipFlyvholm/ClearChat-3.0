package me.phil14052.ClearChat3_0.Utils.JSON;

import mkremins.fanciful.FancyMessage;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;

import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class JsonSender_v1_9_R2 implements JsonSender{

	public void sendJson(CommandSender sender, FancyMessage fm) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			this.sendJson(p, fm);
			return;
		}else{
			String msg = fm.toOldMessageFormat();
			sender.sendMessage(msg);
		}
	}
	
	public void sendJson(Player player, FancyMessage fm) {
		String json = fm.toJSONString();

		PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(ChatSerializer.a(json));

		((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetPlayOutChat);
	}

	
}
