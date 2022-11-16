package org.dev.javaplugin.varo.varo.Varo.dccommands;

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
            OptionMapping matedcid = event.getOption("matedcid");
            OptionMapping teamname = event.getOption("teamname");


            
        }

    }
    @Override
    public void onGuildReady(GuildReadyEvent event){
        List<CommandData> commandData = new ArrayList<>();
        OptionData optionData = new OptionData(OptionType.USER, "user", "verify a user");
        commandData.add(Commands.slash("verify", "verify a member").addOptions(optionData));
        event.getGuild().updateCommands().addCommands(commandData).queue();

    }
}










}
