package Sektant.commands;

import java.awt.*;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

public class RuleMessage implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        // Check if the message content equals "!userInfo"
        if (event.getMessageContent().equalsIgnoreCase("!rules")) {
            MessageAuthor author = event.getMessage().getAuthor();
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("User Info")
                    .addField("<:V_Blank:1267015822090440817>", "aasdasd")
                    .addInlineField("<:V_Blank:1267015822090440817>", "doodaad")
                    .addInlineField("<:V_Blank:1267015822090440817>", "test")
                    .setColor(new Color(43, 45, 49));
            author.asUser().ifPresent(user -> {
                embed.addInlineField("<:V_Blank:1267015822090440817>", "test");
                embed.addField("<:V_Blank:1267015822090440817>", "test");
                embed.addInlineField("<:V_Blank:1267015822090440817>", "<:V_Blank:1267015822090440817>");
            });
            // Send the embed. It logs every exception, besides missing permissions (you are not allowed to send message in the channel)
            event.getChannel().sendMessage(embed)
                    .exceptionally(ExceptionLogger.get(MissingPermissionsException.class));


        }

    }

}



















