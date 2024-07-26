package fr.mega.megagames.commands;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CommandDuel implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String msg, String[] strings) {
        if (Sender instanceof Player){
            if (msg.equalsIgnoreCase("duel")) {
                if (strings[0] != null) {
                    Player player = Bukkit.getPlayer(strings[0]);
                    if (player != null) {
                        //if (player != Sender) {
                            ((Player) Sender).openInventory(Duel((Player) Sender, player.getName()));
                        //} else  {
                        //Sender.sendMessage("§aVous ne pouvez pas vous défiez vous même !");
                    }
                }
            }

            if (msg.equalsIgnoreCase("accept")){
                if (strings[0] != null) {
                    Player player = Bukkit.getPlayer(strings[0]);
                    if (player != null) {
                        Sender.sendMessage("Vous venez d'§aaccepter §rla demande de duel de §c"+player.getName());
                        player.sendMessage(Sender.getName()+"viens d'§aaccepter §rvotre demande de duel");
                    }
                }
            }
            if (msg.equalsIgnoreCase("refuser")){
                if (strings[0] != null) {
                    Player player = Bukkit.getPlayer(strings[0]);
                    if (player != null) {
                        Sender.sendMessage("Vous venez de §crefuser §rla demande de duel de §c"+player.getName());
                        player.sendMessage(Sender.getName()+"viens de §crefuser §rvotre demande de duel");
                    }
                }
            }
        }
        return false;
    }

    private Inventory Duel(Player p, String targetName){

        Inventory inv = Bukkit.createInventory(p, 27, "§cDuel "+targetName);
        inv.setItem(12, new ItemStack(Material.SHEARS));

        return inv;
    }

    @EventHandler
    private void OnInventoryClick(InventoryClickEvent event){
        if (event.getInventory().getName().contains("§cDuel ")){
            String targetName = event.getView().getTitle().substring(7);
            if (event.getSlot() == 12){
                Player p1 = Bukkit.getPlayer(targetName);
                if (p1 != null) {
                    TextComponent textaccept = new TextComponent("§aACCEPTER");
                    textaccept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/accept "+event.getWhoClicked()));
                    TextComponent textdeni = new TextComponent("§c   REFUSER");
                    textdeni.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/refuser "+event.getWhoClicked()));
                    TextComponent text = new TextComponent(event.getWhoClicked().getName()+" vous a défié dans un shifumi.\n\n");
                    text.addExtra(textaccept);
                    text.addExtra(textdeni);
                    p1.spigot().sendMessage(text);
                }
            }
        }
    }





}

