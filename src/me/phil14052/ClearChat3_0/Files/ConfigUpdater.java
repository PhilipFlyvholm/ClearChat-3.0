package me.phil14052.ClearChat3_0.Files;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;

import me.phil14052.ClearChat3_0.ClearChat;

public class ConfigUpdater extends YamlConfiguration {
	
	private ClearChat plugin = ClearChat.getInstance();
	
	public ConfigUpdater() {
		PluginDescriptionFile pluginYml = plugin.getDescription();
		@SuppressWarnings("unused")
		String[] motdList = { "&5Welcome back on %newline% %ServerName%",
				"&cYou are so %newline% &a&lAWESOME" };
		plugin.getConfig()
				.options()
				.header("ClearChat! Version: "
						+ pluginYml.getVersion()
						+ " By Phil14052");
		plugin.getConfig().options().copyHeader();
		plugin.getConfig().addDefault("Debugmode", false);
		plugin.getConfig().addDefault("login.clearOnLogin", true);
		plugin.getConfig().addDefault("login.lines", 100);
		plugin.getConfig().addDefault("login.message.withMessage", true);
		plugin.getConfig().addDefault("login.message.message", "&3Your chat was cleared automatically on join.");
		plugin.getConfig().addDefault("clear.global.lines", 100);
		plugin.getConfig().addDefault("clear.global.ingammeplayersonly", true);
		plugin.getConfig().addDefault("clear.personal.lines", 100);
		plugin.getConfig().addDefault("clear.autoclear.enabled", true);
		plugin.getConfig().addDefault("clear.autoclear.lines", 100);
		plugin.getConfig().addDefault("clear.autoclear.time.hours", 0);
		plugin.getConfig().addDefault("clear.autoclear.time.minutes", 5);
		plugin.getConfig().addDefault("clear.autoclear.time.seconds", 0);
		plugin.getConfig().addDefault("clear.autoclear.ingammeplayersonly", true);
		plugin.getConfig().addDefault("clear.autoclear.message.withMessage", true);
		plugin.getConfig().addDefault("clear.autoclear.message.message", "&8The chat was cleared automatically.");
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveDefaultConfig();
	}

}
