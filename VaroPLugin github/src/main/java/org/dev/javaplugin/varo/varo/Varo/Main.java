package org.dev.javaplugin.varo.varo.Varo;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.dev.javaplugin.varo.varo.Varo.mysql.DatabaseManager;
import org.dev.javaplugin.varo.varo.Varo.mysql.Table;
import sun.tools.jconsole.Tab;

public final class Main extends JavaPlugin {
    public static DatabaseManager db;
    public static FileConfiguration configuration = null;
    public static Main instance;
    Table user = new Table("MCNAME TEXT, DCID TEXT, ISALIVE TEXT");
    Table teams = new Table("NAME TEXT, MCUSER1 TEXT, MCUSER2 TEXT");
    Table strikes = new Table("MCNAME TEXT, STRIKES TEXT,LASTSEEN TEXT");
    @Override
    public void onEnable() {
        instance = this;
        configuration = this.getConfig();
        if (!configuration.contains("mysql.host")){
            configuration.set("mysql.host", "null");
            configuration.set("mysql.port", "null");
            configuration.set("mysql.user", "null");
            configuration.set("mysql.database", "null");
            configuration.set("mysql.password", "null");
        }
        this.saveConfig();
        db = new DatabaseManager(configuration.getString("mysql.host"), configuration.getString("mysql.port"), configuration.getString("mysql.user"), configuration.getString("mysql.database"), configuration.getString("mysql.password"));


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
