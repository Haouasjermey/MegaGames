package fr.mega.megagames;

import fr.mega.megagames.commands.CommandDuel;
import fr.mega.megagames.commands.CommandDuelAccept;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.fusesource.jansi.AnsiConsole;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Le plugin vient de démmarer");
        getCommand("duel").setExecutor(new CommandDuel());
        getCommand("accept").setExecutor(new CommandDuelAccept());
        getServer().getPluginManager().registerEvents(new CommandDuel(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Le system vient de s'éteindre");
    }

    @EventHandler
    public void OnInteract(PlayerInteractEvent event){}


}
