package org.dev.javaplugin.varo.varo.Varo;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.sharding.DefaultShardManager;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.dev.javaplugin.varo.varo.Varo.mysql.DatabaseManager;
import org.dev.javaplugin.varo.varo.Varo.mysql.Table;
import sun.tools.jconsole.Tab;



public final class Main extends JavaPlugin {
    public static DatabaseManager db;
    public static FileConfiguration configuration = null;
    public static Main instance;
    public static Table user = new Table("MCNAME TEXT, DCID TEXT, ISALIVE TEXT");
    public static Table teams = new Table("NAME TEXT, MCUSER1 TEXT, MCUSER2 TEXT");
    public static Table strikes = new Table("MCNAME TEXT, STRIKES TEXT,LASTSEEN TEXT");
    public static ShardManager bot;
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
            configuration.set("bottoken", "null");
            configuration.set("Guildid", "null");
            configuration.set("serverip", "null");
        }
        this.saveConfig();
        db = new DatabaseManager(configuration.getString("mysql.host"), configuration.getString("mysql.port"), configuration.getString("mysql.user"), configuration.getString("mysql.database"), configuration.getString("mysql.password"));
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(configuration.getString("bottoken"));
        builder.setActivity(Activity.listening("VARO"));
        bot = builder.build();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static void sendPrivateMessage(User user, MessageEmbed embed) {

        user.openPrivateChannel().queue((channel) ->
        {
            channel.sendMessageEmbeds(embed).queue();
        });
    }
}
