package fr.mrcoq.antikeconomy.economy;

import fr.mrcoq.antikeconomy.AntikEconomy;
import org.bukkit.Bukkit;

import java.util.Comparator;
import java.util.Optional;

public class Salarie {

    public static void sendSalaries() {
        AntikEconomy antikEconomy = AntikEconomy.getInstance();

        Bukkit.getOnlinePlayers().forEach(player -> {
            Optional<String> permissionOptional = player.getEffectivePermissions().stream().filter(perm -> perm.getPermission().contains("antikeconomy.salary.")).map(perm -> perm.getPermission().replace("antikeconomy.salary.", "")).max(Comparator.comparingDouble(Double::parseDouble));

            if(!permissionOptional.isPresent()) {
                return;
            }

            double salarie = Double.parseDouble(permissionOptional.get());
            double balance = antikEconomy.playerAccounts.get(player.getUniqueId());
            antikEconomy.playerAccounts.put(player.getUniqueId(), balance + salarie);
            player.sendMessage(EconomyImplementer.EconomyConf.SALARY.getValue().replace("%%money%%", format(salarie)));
        });
    }

    public static void autoSend() {
        Bukkit.getScheduler().runTaskTimer(AntikEconomy.getInstance(), () -> Salarie.sendSalaries(), 20L*60*20, 20L*60*20);
    }

    public static String format(double amount) {
        return EconomyImplementer.EconomyConf.COLOR.getValue() + amount + EconomyImplementer.EconomyConf.SUFFIX.getValue();
    }
}
