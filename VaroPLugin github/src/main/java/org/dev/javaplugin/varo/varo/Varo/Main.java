package org.dev.javaplugin.varo.varo.Varo;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static FileConfiguration configuration = null;
    @Override
    public void onEnable() {
        configuration = this.getConfig();
        if (!configuration.contains("mysql.host")){
            configuration.set("mysql.host", "null");
            configuration.set("mysql.port", "null");
            configuration.set("mysql.user", "null");
            configuration.set("mysql.database", "null");
            configuration.set("mysql.password", "null");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
