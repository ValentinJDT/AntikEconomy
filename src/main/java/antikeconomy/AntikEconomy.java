package antikeconomy;

import antikeconomy.economy.EconomyImplementer;
import antikeconomy.economy.VaultHook;
import antikeconomy.sql.BalancesInterface;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class AntikEconomy extends JavaPlugin {

    private static AntikEconomy instance;

    public HashMap<UUID, Double> playerBank = new HashMap<>();
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

        for(EconomyImplementer.EconomyConf conf : EconomyImplementer.EconomyConf.values()) {
            System.out.println("----> " + conf.getValue().replace("§", "&"));
        }
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
        playerBank = balancesInterface.getBalancesDB();
        autoSave();
    }

    private void autoSave() {
        Bukkit.getScheduler().runTaskTimer(this, this::save, 20L*60*30, 20L*60*30);
    }

    public void save() {
        balancesInterface.saveBalances(playerBank);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Balances saved");
    }
}
