package me.phil14052.ClearChat3_0.Managers;

import java.util.Arrays;
import java.util.List;

import me.phil14052.ClearChat3_0.ClearChat;
import me.phil14052.ClearChat3_0.API.CCAPI;
import me.phil14052.ClearChat3_0.Files.Lang;
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
	private PermissionManager pm = new PermissionManager();
	private final ItemStack backgroundItem = new ItemLib(XMaterial.GLASS_PANE.parseMaterial(), 1, (short) 0, " ").create();
	private final ItemStack noPermissionItem = new ItemLib(XMaterial.BARRIER.parseMaterial(), 1, (short) 0, Lang.GUI_NO_PERMISSION_TITLE.toString(), Arrays.asList(Lang.GUI_NO_PERMISSION_LORE.toString())).create();
	
	public class MainGUI{
		private CustomHolder ch = new CustomHolder(27, Lang.GUI_PREFIX.toString() + Lang.GUI_HOME_TITLE.toString(),Lang.GUI_HOME_TITLE.toString());	
		private Player player;
		public MainGUI(Player p){
			player = p;
			for(int i = 0; i < 27; i++){
				Icon icon = new Icon(backgroundItem);
				if(i == 11){
					List<String> clearLore = Arrays.asList(Lang.GUI_CLEAR_BUTTON_LORE.toString());
					ItemStack item = new ItemLib(XMaterial.TNT.parseMaterial(), 1, (short)0, Lang.GUI_CLEAR_BUTTON_TITLE.toString(), clearLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {
							GUIManager.getInstance().new ClearGUI(p).open();
						}
						
					});
				}else if(i == 13){
					List<String> muteLore = Arrays.asList(Lang.GUI_MUTE_BUTTON_LORE.toString());
					ItemStack item = new ItemLib(XMaterial.NOTE_BLOCK.parseMaterial(), 1, (short)0, Lang.GUI_MUTE_BUTTON_TITLE.toString(), muteLore).create();
					icon = new Icon(item);
					icon.addClickAction(new ClickAction(){

						@Override
						public void execute(Player p) {

							GUIManager.getInstance().new MuteGUI(p).open();
						}
						
					});
				}else if(i == 15){
					List<String> exitLore = Arrays.asList(Lang.GUI_CLOSE_BUTTON_LORE.toString());
					ItemStack item = new ItemLib(XMaterial.BARRIER.parseMaterial(), 1, (short)0, Lang.GUI_CLOSE_BUTTON_TITLE.toString(), exitLore).create();
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
		private CustomHolder ch = new CustomHolder(27, Lang.GUI_PREFIX.toString() + Lang.GUI_CLEAR_MENU_TITLE.toString(), Lang.GUI_CLEAR_MENU_TITLE.toString());	
		private Player player;
		public ClearGUI(Player p){
			player = p;
			for(int i = 0; i < 27; i++){
				Icon icon = new Icon(backgroundItem);
				if(i == 10){
					if(!pm.hasPermission(p, "clearchat.commands.personal", false)){
						icon = new Icon(noPermissionItem);
					}else{
						List<String> personalLore = Arrays.asList(Lang.GUI_CLEAR_PERSONAL_BUTTON_LORE.toString());
						ItemStack item = new ItemLib(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short)3, Lang.GUI_CLEAR_PERSONAL_BUTTON_TITLE.toString(), personalLore).create();
						icon = new Icon(item);
						icon.addClickAction(new ClickAction(){

							@Override
							public void execute(Player p) {
								p.performCommand("cc personal");
								p.closeInventory();
							}
							
						});	
					}
				}
				if(i == 11){
					if(!pm.hasPermission(p, "clearchat.commands.personal", false)){
						icon = new Icon(noPermissionItem);
					}else{
						List<String> personalLore = Arrays.asList(Lang.GUI_CLEAR_PERSONAL_SILENT_BUTTON_LORE.toString());
						ItemStack item = new ItemLib(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short)3, Lang.GUI_CLEAR_PERSONAL_SILENT_BUTTON_TITLE.toString(), personalLore).create();
						icon = new Icon(item);
						icon.addClickAction(new ClickAction(){
	
							@Override
							public void execute(Player p) {
								p.performCommand("cc personal -m");
								p.closeInventory();
							}
							
						});
					}
				}else if(i == 13){
					if(!pm.hasPermission(p, "clearchat.commands.global", false)){
						icon = new Icon(noPermissionItem);
					}else{
						List<String> globalLore = Arrays.asList(Lang.GUI_CLEAR_GLOBAL_BUTTON_LORE.toString());
						ItemStack item = new ItemLib(XMaterial.ZOMBIE_HEAD.parseMaterial(), 1, (short)2, Lang.GUI_CLEAR_GLOBAL_BUTTON_TITLE.toString(), globalLore).create();
						icon = new Icon(item);
						icon.addClickAction(new ClickAction(){
	
							@Override
							public void execute(Player p) {
								p.performCommand("cc global");
								p.closeInventory();
							}
							
						});
					}
				}else if(i == 14){
					if(!pm.hasPermission(p, "clearchat.commands.global.anonymous", false)){
						icon = new Icon(noPermissionItem);
					}else{
						List<String> globalLore = Arrays.asList(Lang.GUI_CLEAR_GLOBAL_ANON_BUTTON_LORE.toString());
						ItemStack item = new ItemLib(XMaterial.ZOMBIE_HEAD.parseMaterial(), 1, (short)2, Lang.GUI_CLEAR_GLOBAL_ANON_BUTTON_TITLE.toString(), globalLore).create();
						icon = new Icon(item);
						icon.addClickAction(new ClickAction(){
	
							@Override
							public void execute(Player p) {
								p.performCommand("cc global -a");
								p.closeInventory();
							}
							
						});
					}
				}else if(i == 15){
					if(!pm.hasPermission(p, "clearchat.commands.global.silent", false)){
						icon = new Icon(noPermissionItem);
					}else{
						List<String> globalLore = Arrays.asList(Lang.GUI_CLEAR_GLOBAL_SILENT_BUTTON_LORE.toString());
						ItemStack item = new ItemLib(XMaterial.ZOMBIE_HEAD.parseMaterial(), 1, (short)2, Lang.GUI_CLEAR_GLOBAL_SILENT_BUTTON_TITLE.toString(), globalLore).create();
						icon = new Icon(item);
						icon.addClickAction(new ClickAction(){
	
							@Override
							public void execute(Player p) {
								p.performCommand("cc global -s");
								p.closeInventory();
							}
							
						});
					}
				}else if(i == 26){
					List<String> backLore = Arrays.asList(Lang.GUI_BACK_BUTTON_LORE.toString());
					ItemStack item = new ItemLib(XMaterial.BARRIER.parseMaterial(), 1, (short)0, Lang.GUI_BACK_BUTTON_TITLE.toString(), backLore).create();
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
		private CustomHolder ch = new CustomHolder(27, Lang.GUI_PREFIX.toString() + Lang.GUI_MUTE_MENU_TITLE.toString(), Lang.GUI_MUTE_MENU_TITLE.toString());	
		private Player player;
		public MuteGUI(Player p){
			player = p;
			for(int i = 0; i < 27; i++){
				Icon icon = new Icon(backgroundItem);
				if(i == 12){
					if(!pm.hasPermission(p, "clearchat.commands.mutechat", false) || !pm.hasPermission(p, "clearchat.commands.mutechat.personal", false)){
						icon = new Icon(noPermissionItem);
					}else{
						ItemStack item;
						if(api.isPlayerChatDisabled(p)){
							List<String> personalLore = Arrays.asList(Lang.GUI_MUTE_PERSONAL_BUTTON_UNMUTE_LORE.toString());
							ItemLib il = new ItemLib(XMaterial.REDSTONE.parseMaterial(true));
							il.setDisplayName(Lang.GUI_MUTE_PERSONAL_BUTTON_UNMUTE_TITLE.toString());
							il.setLore(personalLore);
							item = il.create();
						}else{
							List<String> personalLore = Arrays.asList(Lang.GUI_MUTE_PERSONAL_BUTTON_MUTE_LORE.toString());
							ItemLib il = new ItemLib(XMaterial.SLIME_BALL.parseMaterial(true));
							il.setDisplayName(Lang.GUI_MUTE_PERSONAL_BUTTON_MUTE_TITLE.toString());
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
					}
				}else if(i == 14){
					if(!pm.hasPermission(p, "clearchat.commands.mutechat", false) || !pm.hasPermission(p, "clearchat.commands.mutechat.global", false)){
						icon = new Icon(noPermissionItem);
					}else{
						ItemStack item;
						if(api.isGlobalChatMuted()){
							List<String> globalLore = Arrays.asList(Lang.GUI_MUTE_GLOBAL_BUTTON_UNMUTE_LORE.toString());
							ItemLib il = new ItemLib(XMaterial.REDSTONE.parseMaterial(true));
							il.setDisplayName(Lang.GUI_MUTE_GLOBAL_BUTTON_UNMUTE_TITLE.toString());
							il.setLore(globalLore);
							item = il.create();
						}else{
							List<String> globalLore = Arrays.asList(Lang.GUI_MUTE_GLOBAL_BUTTON_MUTE_LORE.toString());
							ItemLib il = new ItemLib(XMaterial.SLIME_BALL.parseMaterial(true));
							il.setDisplayName(Lang.GUI_MUTE_GLOBAL_BUTTON_MUTE_TITLE.toString());
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
					}
				}else if(i == 26){
					List<String> exitLore = Arrays.asList(Lang.GUI_BACK_BUTTON_LORE.toString());
					ItemStack item = new ItemLib(XMaterial.BARRIER.parseMaterial(), 1, (short)0, Lang.GUI_BACK_BUTTON_TITLE.toString(), exitLore).create();
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
