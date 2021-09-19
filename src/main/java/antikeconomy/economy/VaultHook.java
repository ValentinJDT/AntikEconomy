package antikeconomy.economy;

import antikeconomy.AntikEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;

public class VaultHook {

    private AntikEconomy antikBase = AntikEconomy.getInstance();

    private Economy provider;

    public void hook() {
        provider = antikBase.economyImplementer;
        Bukkit.getServicesManager().register(Economy.class, this.provider, this.antikBase, ServicePriority.Normal);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "VaultAPI hooked into " + ChatColor.AQUA + antikBase.getName());
    }

    public void unhook() {
        Bukkit.getServicesManager().unregister(Economy.class, this.provider);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "VaultAPI unhooked from " + ChatColor.AQUA + antikBase.getName());
    }
}
