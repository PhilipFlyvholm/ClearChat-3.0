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
    PLAYER_TEAM_INFO("player.team-info", "ARRAYLIST: &8&l&m--------------------- ,   , &3&lTeam name: &b%team_name% , &3&lTeam owner: &b%team_owner%"
    		+ " , &3&lMembers: , &b%team_members%"
    		+ " , &3&lAlliances: , &b%team_allies%"
    		+ " , &3&lEnemies: , &b%team_enemies%"
    		+ " , &3&lLevel: &b%team_level%"
    		+ " , &3&lXp: &b%team_xp%"
    		+ " , &3&lInvite only: &b%invite_only%"
    		+ " ,  "
    		+ " , &8&l&m---------------------"),
    PLAYER_PERSONAL_MUTE_ON("player.mute.personal.on", "&aYour chat is now muted"),
    PLAYER_PERSONAL_MUTE_OFF("player.mute.personal.off", "&cYour chat is no longer muted"),
    PLAYER_GLOBAL_CLEAR_DEFAULT("player.clear.global.default", "The chat was cleared by &3{player_name}&8."),
    PLAYER_GLOBAL_CLEAR_ANONYMOUS("player.clear.global.anonymous", "The chat was cleared."),
    PLAYER_PERSONAL_CLEAR_DEFAULT("player.clear.personal.default", "Your chat was cleared."),
    PLAYER_GLOBAL_CHAT_DISABLED("player.globalChat.disabled", "&c&lThe chat is disabled.");
    
    
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