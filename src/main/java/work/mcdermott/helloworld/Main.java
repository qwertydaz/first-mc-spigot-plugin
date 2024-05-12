package work.mcdermott.helloworld;

import org.bukkit.plugin.java.JavaPlugin;
import work.mcdermott.helloworld.commands.HelloCommand;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new HelloCommand(this);
    }

}
