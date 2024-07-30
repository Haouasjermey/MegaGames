package fr.mega.megagames.commands;

import com.google.common.escape.ArrayBasedUnicodeEscaper;
import fr.mega.megagames.ItemBuilder;
import fr.mega.megagames.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class ShifumiAccept implements CommandExecutor{

    HashMap<UUID, UUID> Hashmap = new HashMap<>();
    private int Time = 10;
    private boolean SenderClick = false;
    private boolean FirstChooseIsStone = false;
    private boolean FirstChooseIsPaper = false;
    private boolean FirstChooseIsCisor = false;


    @Override
    public boolean onCommand(CommandSender Sender, Command msg, String s, String[] strings) {
        if (Sender instanceof Player) {
            if (strings.length == 1) {
                Player player = Bukkit.getPlayer(strings[0]);
                if (player != null) {
                    Sender.sendMessage("Vous venez d'§aaccepter §rla demande de duel de §c" + player.getName());
                    player.sendMessage(Sender.getName() + "viens d'§aaccepter §rvotre demande de duel");
                    player.openInventory(Shifumi(player));
                    ((Player) Sender).openInventory(Shifumi((Player) Sender));
                    Hashmap.put(((Player) Sender).getUniqueId(), player.getUniqueId());
                }
            }
        }
        return false;
    }

    private Inventory Shifumi(Player p){
        Inventory inv = Bukkit.createInventory(p, 9,"Shifumi");
        inv.setItem(3, new ItemBuilder(Material.STONE).setName("§7Pierre").toItemStack());
        inv.setItem(5, new ItemBuilder(Material.PAPER).setName("§rFeuille").toItemStack());
        inv.setItem(7, new ItemBuilder(Material.SHEARS).setName("§cCiseau").toItemStack());

        return inv;
    }
    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event){
        if(event.getClickedInventory().getTitle().equals("Shifumi")){
            new BukkitRunnable(){

                @Override
                public void run() {
                    if (!SenderClick && Time >= 0){
                        Time --;
                    }
                }
            }.runTaskTimer(Main.getPlugin(Main.class), 0,20);
        }
    }



}
