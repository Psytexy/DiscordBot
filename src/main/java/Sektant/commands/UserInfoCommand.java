package Sektant.commands;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

import java.awt.*;
import java.util.stream.Collectors;
public class UserInfoCommand implements MessageCreateListener{
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().startsWith("!userinfo")) {
            User u;
            if(event.getMessage().getMentionedUsers().size() > 0){
                u = event.getMessage().getMentionedUsers().get(0);
            }else{
                u = event.getMessage().getAuthor().asUser().get();
            }
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("User Info")
                    .addField("Display Name", u.getDisplayName(event.getServer().get()), true)
                    .addField("Name + Discriminator", u.getDiscriminatedName(), true)
                    .addField("User Id", u.getIdAsString(), true)
                    .setColor(new Color(0, 0, 0))
                    .setAuthor(u);
            embed.addField("Online Status", u.getStatus().getStatusString(), true);
            embed.addField("Connected Clients", u.getCurrentClients().toString());

            embed.addInlineField("Activities", u.getActivities().isEmpty() ? "none"
                    : u.getActivities().stream().map(Activity::getName).collect(Collectors.joining(", ")));

            event.getMessage().getServer()
                    .ifPresent(server -> embed.addField("Server Admin", server.isAdmin(u) ? "yes" : "no", true));
            event.getChannel().sendMessage(embed)
                    .exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
        }
    }}
