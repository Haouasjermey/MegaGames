package fr.mega.megagames.commands;

import fr.mega.megagames.ItemBuilder;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandDuel implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender Sender, Command cmd, String msg, String[] strings) {
        if (Sender instanceof Player) {

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


        return false;
    }




    private Inventory Duel(Player p, String targetName){

        Inventory inv = Bukkit.createInventory(p, 27, "§cDuel "+targetName);
        inv.setItem(12, new ItemBuilder(Material.SHEARS).setName("Shifumi").addEnchant(Enchantment.ARROW_DAMAGE, 1).hideAllAttributes().toItemStack());


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

