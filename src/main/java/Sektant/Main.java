package Sektant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import Sektant.commands.CopyAvatarCommand;
import Sektant.commands.UserInfoCommand;
import io.github.cdimascio.dotenv.Dotenv;
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        FallbackLoggerConfiguration.setDebug(true);
            Dotenv dotenv = Dotenv.load();
            String token = dotenv.get("TOKEN");
            DiscordApi api = new DiscordApiBuilder()
                    .setToken(token)
                    .setAllIntents()
                    .login()
                    .join();



        // Print the invite url of your bot
        System.out.println("You can invite the bot by using the following url: " + api.createBotInvite());
            api.addMessageCreateListener(new CopyAvatarCommand());
            api.addMessageCreateListener(new UserInfoCommand());
            api.addServerJoinListener(event -> logger.info("Joined server " + event.getServer().getName()));
            api.addServerLeaveListener(event -> logger.info("Left server " + event.getServer().getName()));
    }

}

