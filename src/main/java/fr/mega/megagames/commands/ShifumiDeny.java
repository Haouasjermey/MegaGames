package fr.mega.megagames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShifumiDeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender Sender, Command command, String s, String[] strings) {
        if (Sender instanceof Player) {
            if (strings.length == 1) {
                Player player = Bukkit.getPlayer(strings[0]);
                if (player != null) {
                    Sender.sendMessage("Vous venez de §crefuser §rla demande de duel de §c"+player.getName());
                    player.sendMessage(Sender.getName()+"viens de §crefuser §rvotre demande de duel");
                }
            }
        }
        return false;
    }
}
