package antikeconomy.economy;

import antikeconomy.AntikEconomy;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class EconomyImplementer implements Economy {

    private static final AntikEconomy antikEconomy = AntikEconomy.getInstance();
    private static final FileConfiguration config = antikEconomy.getConfig();

    public enum EconomyConf {
        COLOR(config.getString("currency.color")),
        PLURAL(config.getString("currency.plural")),
        SINGULAR(config.getString("currency.singular")),
        SUFFIX(config.getString("currency.suffix")),
        DEFAULT_VALUE("" + config.getDouble("currency.default-value"));

        private String value;
        EconomyConf(String value) {
            this.value = value.replace("&", "§");
        }

        public String getValue() {
            return value;
        }
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return AntikEconomy.class.getName();
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return EconomyConf.COLOR.value + amount + EconomyConf.SUFFIX.value;
    }

    @Override
    public String currencyNamePlural() {
        return EconomyConf.PLURAL.value;
    }

    @Override
    public String currencyNameSingular() {
        return EconomyConf.SINGULAR.value;
    }

    @Override
    public boolean hasAccount(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        return antikEconomy.playerBank.containsKey(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();

        return antikEconomy.playerBank.containsKey(uuid);
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        return antikEconomy.playerBank.containsKey(uuid);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        UUID uuid = player.getUniqueId();

        return antikEconomy.playerBank.containsKey(uuid);
    }

    @Override
    public double getBalance(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid);
    }

    @Override
    public double getBalance(String playerName, String world) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid);
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid);
    }

    @Override
    public boolean has(String playerName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid) >= amount;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid) >= amount;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid) >= amount;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        UUID uuid = player.getUniqueId();
        return antikEconomy.playerBank.get(uuid) >= amount;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance - amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance - amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance - amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance - amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance + amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance + amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance + amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        UUID uuid = player.getUniqueId();

        double oldBalance = antikEconomy.playerBank.get(uuid);
        antikEconomy.playerBank.put(uuid, oldBalance + amount);
        return new EconomyResponse(amount, antikEconomy.playerBank.get(uuid), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        if(!antikEconomy.playerBank.containsKey(uuid)) {
            antikEconomy.playerBank.put(uuid, 50d);
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        UUID uuid = player.getUniqueId();

        if(!antikEconomy.playerBank.containsKey(uuid)) {
            double money = Double.parseDouble(EconomyConf.DEFAULT_VALUE.getValue());
            antikEconomy.playerBank.put(uuid, money);
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        Player player = Bukkit.getPlayer(playerName);
        UUID uuid = player.getUniqueId();

        if(!antikEconomy.playerBank.containsKey(uuid) || worldName.equals("reset")) {
            double money = Double.parseDouble(EconomyConf.DEFAULT_VALUE.getValue());
            antikEconomy.playerBank.put(uuid, money);
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        UUID uuid = player.getUniqueId();

        if(!antikEconomy.playerBank.containsKey(uuid) || worldName.equals("reset")) {
            double money = Double.parseDouble(EconomyConf.DEFAULT_VALUE.getValue());
            antikEconomy.playerBank.put(uuid, money);
            return true;
        }
        return false;
    }
}
