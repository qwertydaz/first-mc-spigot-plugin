package work.mcdermott.helloworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import work.mcdermott.helloworld.Main;

import java.util.Objects;

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
        if (!senderIsPermitted(sender)) {
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage("Hello World!");

        return true;
    }

    private boolean senderIsPermitted(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command.");
            return false;
        }
        if (!sender.hasPermission("hello.use")) {
            sender.sendMessage("You do not have permission to execute this command.");
            return false;
        }

        return true;
    }

}
