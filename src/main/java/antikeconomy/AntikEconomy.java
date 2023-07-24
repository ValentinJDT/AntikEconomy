package antikeconomy;

import antikeconomy.economy.EconomyImplementer;
import antikeconomy.economy.Salarie;
import antikeconomy.economy.VaultHook;
import antikeconomy.sql.BalancesInterface;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class AntikEconomy extends JavaPlugin {

    private static AntikEconomy instance;

    public HashMap<UUID, Double> playerAccounts = new HashMap<>();
    public HashMap<UUID, Double> playerBanks = new HashMap<>();
    public EconomyImplementer economyImplementer;
    private VaultHook vaultHook;

    private BalancesInterface balancesInterface;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        economyImplementer = new EconomyImplementer();
        balancesInterface = new BalancesInterface("Balances");

        vaultHook = new VaultHook();
        vaultHook.hook();

        initEconomy();
        saveDefaultConfig();
        Salarie.autoSend();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        save();
        vaultHook.unhook();
    }

    public static AntikEconomy getInstance() {
        return instance;
    }

    private void initEconomy() {
        playerAccounts = balancesInterface.getBalancesDB();
        autoSave();
    }

    private void autoSave() {
        Bukkit.getScheduler().runTaskTimer(this, this::save, 20L*60*30, 20L*60*30);
    }

    public void save() {
        balancesInterface.saveBalances(playerAccounts);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Balances saved !");
    }
}
