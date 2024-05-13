package work.mcdermott.helloworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import work.mcdermott.helloworld.Main;

import java.util.Objects;

import static work.mcdermott.helloworld.constant.HelloWorldConsts.HELLO_MESSAGE;
import static work.mcdermott.helloworld.constant.HelloWorldConsts.NO_PERMISSION;
import static work.mcdermott.helloworld.constant.HelloWorldConsts.PLAYER_ONLY;

public class HelloCommand implements CommandExecutor {

	public HelloCommand(Main plugin) {
        PluginCommand helloCommand = plugin.getCommand("hello");

        if (Objects.isNull(helloCommand)) {
            plugin.getLogger().warning("Could not register command.");
            return;
        }

        helloCommand.setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label,
                             @NotNull String[] args) {
        if (!senderIsPermitted(sender)) return false;

        Player player = (Player) sender;

        String playerName = player.getDisplayName();
        String message = String.format(HELLO_MESSAGE, playerName);
        player.sendMessage(message);

        return true;
    }

    private boolean senderIsPermitted(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(PLAYER_ONLY);
            return false;
        }
        if (!sender.hasPermission("hello.use")) {
            sender.sendMessage(NO_PERMISSION);
            return false;
        }

        return true;
    }

}
