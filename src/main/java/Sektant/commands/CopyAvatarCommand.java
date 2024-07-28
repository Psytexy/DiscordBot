package Sektant.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class CopyAvatarCommand implements MessageCreateListener {
  @Override
  public void onMessageCreate(MessageCreateEvent event) {

    if (event.getMessageContent().equalsIgnoreCase("!copyAvatar")) {

      if (!event.getMessage().getAuthor().isBotOwner()) {
        event.getChannel().sendMessage("You are not allowed to use this command!");
        return;
      }

      event
          .getApi()
          .updateAvatar(event.getMessage().getAuthor().getAvatar()) // Update the avatar
          .thenRun(
              () ->
                  event
                      .getChannel()
                      .sendMessage(
                          "Ok, I'm now using your avatar :-)")) // Send the user a message if the
                                                                // update was successful
          .exceptionally(
              throwable -> {
                event.getChannel().sendMessage("Something went wrong: " + throwable.getMessage());
                return null;
              });
    }
  }
}
