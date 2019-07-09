package me.phil14052.ClearChat3_0.Files;
 
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
 
/**
* An enum for requesting strings from the language file.
* Made by gomeow.
* Lang added by phil14052.
* @author gomeow
*/

public enum Lang {
	
    PREFIX("prefix", "&8[&3ClearChat&8]"),
    INVALID_ARGS("invalid-args", "&cInvalid args!"),
    PLAYER_ONLY("player-only", "Sorry but that can only be run by a player!"),
    NO_PERMS("no-permissions", "&cYou don't have permission for that!"),
    RELOAD_SUCCES("reload-succes", "&3Reloaded the plugin in %time% seconds."),
    USAGE_PREFIX("usage.prefix", "&4&lUsage:&c "),
    USAGE_MUTE("usage.mute", "/clearchat mutechat (global, personal) [player - (Can only be used with personal)]"),
    USAGE_MUTE_PERSONAL_CONSOLE("usage.personalmute.console", "/clearchat mutechat personal (player)"),
    FAILED_PREFIX("failed.prefix", "&4&lFailed:&c "),
    FAILED_PLAYER_NOT_FOUND("failed.player-not-found", "{player_name} was not found."),
    FAILED_REENABLE_CHAT_TO_CHAT("failed.reenable-chat", "You need to enable your chat to use the chat."),
    PLAYER_PLUGIN_INFO("player.plugin-info", "ARRAYLIST: &8&l&m--------------------- ,   , &3{plugin_name} - Version {plugin_version} by Phil14052"
    		+ " , &8If you want help do: &3%run_command_/clearchat help%"
    		+ " , &8Or visit the plugin thread: &3http://goo.gl/5MA6Zl"
    		+ " ,  "
    		+ " , &8&l&m---------------------"),
    PLAYER_PERSONAL_MUTE_ON("player.mute.personal.on", "&aYour chat is now muted"),
    PLAYER_PERSONAL_MUTE_OFF("player.mute.personal.off", "&cYour chat is no longer muted"),
    PLAYER_GLOBAL_CLEAR_DEFAULT("player.clear.global.default", "The chat was cleared by &3{player_name}&8."),
    PLAYER_GLOBAL_CLEAR_ANONYMOUS("player.clear.global.anonymous", "The chat was cleared."),
    PLAYER_PERSONAL_CLEAR_DEFAULT("player.clear.personal.default", "Your chat was cleared."),
    PLAYER_GLOBAL_CHAT_DISABLED("player.globalChat.disabled", "&c&lThe chat is disabled."),
    PLAYER_GLOBAL_MUTE_ON("player.mute.global.on", "&aYou have now muted the chat."),
    PLAYER_GLOBAL_MUTE_OFF("player.mute.global.off", "&cYou have now unmuted the chat."),
    PLAYER_CHAT_DISABLED("player.players-chat-disabled","&c{player_name}'s&8 chat is currently disabled. So &ihe/she&8 did not see your message."),
    PLAYER_PLUGIN_HELP("player.plugin-help", "ARRAYLIST: &8&l&m--------------------- ,   , &3{plugin_name} - &8Help"
    		+ " , &3%suggest_command_/clearchat%&8 - Main command"
    		+ " , &3%suggest_command_/clearchat help% &8- Shows this"
    		+ " , &3%suggest_command_/clearchat gui% &8- Shows a clearchat menu"
    		+ " , &3%suggest_command_/clearchat global%&3  [-a,-s]&8 - Main command"
    		+ " , &3%suggest_command_/clearchat personal%&3 [-m]&8 - Main command"
    		+ " , &3%suggest_command_/clearchat mutechat global%&8 - Main command"
    		+ " , &3%suggest_command_/clearchat mutechat personal%&3 [player]&8 - Main command"
    		+ " , &3%suggest_command_/clearchat reload%&8 - Reload the config and lang file."
    		+ " ,  "
    		+ " , &8&l&m---------------------"),
    BROADCAST_GLOBAL_MUTE_ON("broadcast.mute.global.on", "&cThe chat is now muted"),
    BROADCAST_GLOBAL_MUTE_OFF("broadcast.mute.global.off", "&aThe chat is no longer muted"),
    MAIN_COLOR("main-color","&8"),
    SECONDARY_COLOR("secondary-color", "&3"),
    GUI_PREFIX("gui.prefix", "&3&lClearChat Menu &8- "),
    GUI_HOME_TITLE("gui.home.title", "&6Home"),
    GUI_CLEAR_BUTTON_LORE("gui.home.clear-button.lore", "&6Clear the global or personal chat"),
    GUI_CLEAR_BUTTON_TITLE("gui.home.clear-button.title", "&3Clear Chat"),
    GUI_MUTE_BUTTON_LORE("gui.home.mute-button.lore", "&6Mute/unmute the global or personal chat"),
    GUI_MUTE_BUTTON_TITLE("gui.home.mute-button.title", "&3Mute Chat"),
    GUI_CLOSE_BUTTON_LORE("gui.home.close-button.lore", "&6Close the menu"),
    GUI_CLOSE_BUTTON_TITLE("gui.home.close-button.title", "&cCLOSE"),
    GUI_CLEAR_MENU_TITLE("gui.clear.title", "&6Clear chat"),
    GUI_CLEAR_PERSONAL_BUTTON_LORE("gui.clear.personal-button.lore", "&6Clears your personal chat"),
    GUI_CLEAR_PERSONAL_BUTTON_TITLE("gui.clear.personal-button.title", "&3Personal clear"),
    GUI_CLEAR_PERSONAL_SILENT_BUTTON_LORE("gui.clear.personal-silent-button.lore", "&6Clears your personal chat a message"),
    GUI_CLEAR_PERSONAL_SILENT_BUTTON_TITLE("gui.clear.personal-silent-button.title", "&3Silent personal clear"),
    GUI_CLEAR_GLOBAL_BUTTON_LORE("gui.clear.global-button.lore", "&6Clears the global chat"),
    GUI_CLEAR_GLOBAL_BUTTON_TITLE("gui.clear.global-button.title", "&3Global clear"),
    GUI_CLEAR_GLOBAL_SILENT_BUTTON_LORE("gui.clear.global-silent-button.lore", "&6Clears your global chat a message"),
    GUI_CLEAR_GLOBAL_SILENT_BUTTON_TITLE("gui.clear.global-silent-button.title", "&3Silent global clear"),
    GUI_CLEAR_GLOBAL_ANON_BUTTON_LORE("gui.clear.global-anonymous-button.lore", "&6Clears your global chat a message anonymously"),
    GUI_CLEAR_GLOBAL_ANON_BUTTON_TITLE("gui.clear.global-anonymous-button.title", "&3Anonymous global clear"),
    GUI_BACK_BUTTON_LORE("gui.back-button.lore", "&6Go back to the home menu"),
    GUI_BACK_BUTTON_TITLE("gui.back-button.title", "&cBACK"),
    GUI_MUTE_MENU_TITLE("gui.mute.title", "&6Mute chat"),
    GUI_MUTE_PERSONAL_BUTTON_UNMUTE_TITLE("gui.mute.personal-unmute.title", "&3Personal unmute"),
    GUI_MUTE_PERSONAL_BUTTON_MUTE_TITLE("gui.mute.personal-mute.title", "&3Personal mute"),
    GUI_MUTE_PERSONAL_BUTTON_UNMUTE_LORE("gui.mute.personal-unmute.lore", "&6Unmute your personal chat"),
    GUI_MUTE_PERSONAL_BUTTON_MUTE_LORE("gui.mute.personal-mute.lore", "&6Mute your personal chat"),
    GUI_MUTE_GLOBAL_BUTTON_UNMUTE_TITLE("gui.mute.global-unmute.title", "&3Globla unmute"),
    GUI_MUTE_GLOBAL_BUTTON_MUTE_TITLE("gui.mute.global-mute.title", "&3Global mute"),
    GUI_MUTE_GLOBAL_BUTTON_UNMUTE_LORE("gui.mute.global-unmute.lore", "&6Unmute the global chat"),
    GUI_MUTE_GLOBAL_BUTTON_MUTE_LORE("gui.mute.global-mute.lore", "&6Mute the global chat"),
    GUI_NO_PERMISSION_LORE("gui.no-permission.lore", "&cYou do not have the right permissions to use this button"),
    GUI_NO_PERMISSION_TITLE("gui.no-permission.title", "&cNo permission");;
    
    
    private String path;
    private String def;
    private static YamlConfiguration LANG;

    /**
    * Lang enum constructor.
    * @param path The string path.
    * @param start The default string.
    */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }
 
    /**
    * Set the {@code YamlConfiguration} to use.
    * @param config The config to set.
    */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }
 
    @Override
    public String toString() {
        if (this == PREFIX)
            return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path)) + " ";
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path));
    }
    
    public List<String> toStringList(){
    	List<String> s = LANG.getStringList(this.path);
    	List<String> colored_s = new ArrayList<String>();
    	for(String string : s){
    		colored_s.add(ChatColor.translateAlternateColorCodes('&', string));
    	}
    	return colored_s;
    }
    
    /**
    * Get the default value of the path.
    * @return The default value of the path.
    */
    public String getDefault() {
        return this.def;
    }
 
    /**
    * Get the path to the string.
    * @return The path to the string.
    */
    public String getPath() {
        return this.path;
    }
}