package me.phil14052.ClearChat3_0.Managers;

import java.util.Arrays;
import java.util.List;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.GUI.ClickAction;
import me.phil14052.ClearChat3_0.GUI.CustomHolder;
import me.phil14052.ClearChat3_0.GUI.Icon;
import me.phil14052.ClearChat3_0.Utils.ItemLib;
import me.phil14052.ClearChat3_0.Utils.XMaterial;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;

public class GUIManager {

	private static GUIManager instance = null;
	private static ClearChat plugin = ClearChat.getInstance();
	@SuppressWarnings("unused")
	private PluginDescriptionFile pluginYml = plugin.getDescription();
	private CCAPI api = plugin.papi;
	//private PermissionManager pm = new PermissionManager();
	private final ItemStack backgroundItem = new ItemLib(XMaterial.GLASS_PANE.parseMaterial(), 1, (short) 0, " ").create();
	
	public class MainGUI{
		private CustomHolder ch = new CustomHolder(27, "§3§lClearChat Menu §8- §6Home");	
		private Player player;
		public MainGUI(Player p){
			player = p;
			for(int i = 0; i < 27; i++){
				Icon icon = new Icon(backgroundItem);
				if(i == 11){
					List<String> clearLore = Arrays.asList("§6Clear the global or personal chat");
					ItemStack item = new ItemLib(XMaterial.TNT.parseMaterial(), 1, (short)0, "§3Clear Chat", clearLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							GUIManager.getInstance().new ClearGUI(p).open();
						}
						
					});
				}else if(i == 13){
					List<String> muteLore = Arrays.asList("§6Mute/unmute the global or personal chat");
					ItemStack item = new ItemLib(XMaterial.NOTE_BLOCK.parseMaterial(), 1, (short)0, "§3Mute Chat", muteLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {

							GUIManager.getInstance().new MuteGUI(p).open();
						}
						
					});
				}else if(i == 15){
					List<String> exitLore = Arrays.asList("§6Close the menu");
					ItemStack item = new ItemLib(XMaterial.BARRIER.parseMaterial(), 1, (short)0, "§cCLOSE", exitLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							p.closeInventory();
						}
						
					});
				}
				ch.setIcon(i, icon);
			}
		}
		public void open(){
			Inventory inventory = ch.getInventory();
			player.openInventory(inventory);
		}
		
	}
	
	public class ClearGUI{
		private CustomHolder ch = new CustomHolder(27, "§3§lClearChat Menu §8- §6Clear chat");	
		private Player player;
		public ClearGUI(Player p){
			player = p;
			for(int i = 0; i < 27; i++){
				Icon icon = new Icon(backgroundItem);
				if(i == 10){
					List<String> personalLore = Arrays.asList("§6Clears your personal chat");
					ItemStack item = new ItemLib(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short)3, "§3Personal clear", personalLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							p.performCommand("cc personal");
							p.closeInventory();
						}
						
					});
				}
				if(i == 11){
					List<String> personalLore = Arrays.asList("§6Clears your personal chat without messages");
					ItemStack item = new ItemLib(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short)3, "§3Silent personal clear", personalLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							p.performCommand("cc personal -m");
							p.closeInventory();
						}
						
					});
				}else if(i == 13){
					List<String> globalLore = Arrays.asList("§6Clears the global chat");
					ItemStack item = new ItemLib(XMaterial.ZOMBIE_HEAD.parseMaterial(), 1, (short)2, "§3Global clear", globalLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							p.performCommand("cc global");
							p.closeInventory();
						}
						
					});
				}else if(i == 14){
					List<String> globalLore = Arrays.asList("§6Clears the global chat anonymously");
					ItemStack item = new ItemLib(XMaterial.ZOMBIE_HEAD.parseMaterial(), 1, (short)2, "§3Anonymous global clear", globalLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							p.performCommand("cc global -a");
							p.closeInventory();
						}
						
					});
				}else if(i == 15){
					List<String> globalLore = Arrays.asList("§6Clears the global chat without a message");
					ItemStack item = new ItemLib(XMaterial.ZOMBIE_HEAD.parseMaterial(), 1, (short)2, "§3Silent global clear", globalLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							p.performCommand("cc global -s");
							p.closeInventory();
						}
						
					});
				}else if(i == 26){
					List<String> backLore = Arrays.asList("§6Go back to the home menu");
					ItemStack item = new ItemLib(XMaterial.BARRIER.parseMaterial(), 1, (short)0, "§cBACK", backLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							GUIManager.getInstance().new MainGUI(p).open();
						}
						
					});
				}
				ch.setIcon(i, icon);
			}
		}
		public void open(){
			Inventory inventory = ch.getInventory();
			player.openInventory(inventory);
		}
		
	}
	
	public class MuteGUI{
		private CustomHolder ch = new CustomHolder(27, "§3§lClearChat Menu §8- §6Home");	
		private Player player;
		public MuteGUI(Player p){
			player = p;
			for(int i = 0; i < 27; i++){
				Icon icon = new Icon(backgroundItem);
				if(i == 12){
					ItemStack item;
					if(api.isPlayerChatDisabled(p)){
						List<String> personalLore = Arrays.asList("§6Unmute your personal chat");
						ItemLib il = new ItemLib(XMaterial.REDSTONE.parseMaterial(true));
						il.setDisplayName("§3Personal unmute");
						il.setLore(personalLore);
						item = il.create();
					}else{
						List<String> personalLore = Arrays.asList("§6Mute your personal chat");
						ItemLib il = new ItemLib(XMaterial.SLIME_BALL.parseMaterial(true));
						il.setDisplayName("§3Personal mute");
						il.setLore(personalLore);
						item = il.create();
					}
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {

							p.performCommand("cc mutechat personal");
							GUIManager.getInstance().new MuteGUI(p).open();
						}
						
					});
				}else if(i == 14){
					ItemStack item;
					if(api.isGlobalChatMuted()){
						List<String> globalLore = Arrays.asList("§6Unmute the global chat");
						ItemLib il = new ItemLib(XMaterial.REDSTONE.parseMaterial(true));
						il.setDisplayName("§3Global unmute");
						il.setLore(globalLore);
						item = il.create();
					}else{
						List<String> globalLore = Arrays.asList("§6Mute the global chat");
						ItemLib il = new ItemLib(XMaterial.SLIME_BALL.parseMaterial(true));
						il.setDisplayName("§3Global mute");
						il.setLore(globalLore);
						item = il.create();
					}
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							p.performCommand("cc mutechat global");
							GUIManager.getInstance().new MuteGUI(p).open();
						}
						
					});
				}else if(i == 26){
					List<String> exitLore = Arrays.asList("§6Go back to the home menu");
					ItemStack item = new ItemLib(XMaterial.BARRIER.parseMaterial(), 1, (short)0, "§cBACK", exitLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							GUIManager.getInstance().new MainGUI(p).open();
						}
						
					});
				}
				ch.setIcon(i, icon);
			}
		}
		public void open(){
			Inventory inventory = ch.getInventory();
			player.openInventory(inventory);
		}
		
	}
	
	public static GUIManager getInstance(){
		if(instance == null){
			instance = new GUIManager();
		}
		return instance;
	}
	
}
