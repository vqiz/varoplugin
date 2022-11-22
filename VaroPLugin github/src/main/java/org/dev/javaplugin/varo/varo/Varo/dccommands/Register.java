package org.dev.javaplugin.varo.varo.Varo.dccommands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.dev.javaplugin.varo.varo.Varo.Main;

import java.util.ArrayList;
import java.util.List;

public class Register extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        String command = event.getName();
        User user = event.getUser();
        Member member = event.getMember();
        if (command.equals("register")) {
            OptionMapping minecraftname = event.getOption("selfmcname");
            OptionMapping matemcname = event.getOption("matemcname");
            OptionMapping teamname = event.getOption("teamname");
            if (!Main.teams.dataexist("NAME", teamname.getAsString()) && !Main.teams.dataexist("MCUSER1", minecraftname.getAsString()) && !Main.teams.dataexist("MCUSER2", matemcname.getAsString())){
                Main.teams.insert();

            }


        }
        if(command.equals("verify")){
            OptionMapping mcname = event.getOption("mcuser");
            if (!Main.user.dataexist("MCNAME", mcname.getAsString()) && !Main.user.dataexist("DCID", user.getId())){
                Main.user.insert("'"+ mcname.getAsString() + "'" + ",'" + user.getId() +"','true'");
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("🙌 | Wilkommen");
                builder.setDescription("Du bist nun registiert und hasst zugriff auf alle momentan relevanten channel im discord , solltest du bei unserem tunier mitspielen wollen, registriere dich mit dem command /register auf dem discord mit deinem mate bedenke dabei das alle angaben correct sein müssen ansonsten wirst du nicht gewhitelisted");
                builder.setFooter("✔ | Verify");
                Main.sendPrivateMessage(user, builder.build());

            }else {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("❌ | Fehler");
                builder.setDescription("Du scheinst bereits registriert zu sein oder falsche angaben gemacht zu haben !");
                builder.setFooter("✔ | Verify");
                Main.sendPrivateMessage(user, builder.build());

            }


        }

    }
    @Override
    public void onGuildReady(GuildReadyEvent event){
        List<CommandData> commandData = new ArrayList<>();
        OptionData optionData = new OptionData(OptionType.STRING, "mcuser", "verify a user");
        commandData.add(Commands.slash("verify", "verify a member").addOptions(optionData));
        event.getGuild().updateCommands().addCommands(commandData).queue();

    }
}











