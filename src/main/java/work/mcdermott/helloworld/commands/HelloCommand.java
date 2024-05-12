package work.mcdermott.helloworld.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import work.mcdermott.helloworld.Main;

public class HelloCommand implements CommandExecutor {

    private Main plugin;

    public HelloCommand(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("hello").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!senderIsPermitted(sender)) {
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage("hi!");

        return true;
    }

    private boolean senderIsPermitted(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command!");
            return false;
        }
        if (!sender.hasPermission("hello.use")) {
            sender.sendMessage("You do not have permission to execute this command!");
            return false;
        }

        return true;
    }

}
