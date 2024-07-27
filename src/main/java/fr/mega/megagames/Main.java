package fr.mega.megagames;

import fr.mega.megagames.commands.CommandDuel;
import fr.mega.megagames.commands.ShifumiAccept;
import fr.mega.megagames.commands.ShifumiDeny;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("Le plugin vient de démmarer");
        getCommand("duel").setExecutor(new CommandDuel());
        getCommand("accept").setExecutor(new ShifumiAccept());
        getCommand("refuser").setExecutor(new ShifumiDeny());
        getServer().getPluginManager().registerEvents(new CommandDuel(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Le system vient de s'éteindre");
    }

    @EventHandler
    public void OnInteract(PlayerInteractEvent event){}


}
