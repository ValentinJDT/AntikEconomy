package antikeconomy.economy;

import antikeconomy.AntikEconomy;
import org.bukkit.Bukkit;

import java.util.*;

public class Bank {

    private String name;
    private Map<BankEnum, Double> banks = new HashMap<>();

    public Bank(String playerName) {
        this.name = playerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<BankEnum, Double> getBanks() {
        return banks;
    }

    public void setBanks(Map<BankEnum, Double> banks) {
        this.banks = banks;
    }

    public boolean addBank(BankEnum bankEnum) {
        if(!this.banks.containsKey(bankEnum)) {
            this.banks.put(bankEnum, 0d);
            return true;
        }
        return false;
    }

    public boolean setBankValue(BankEnum bankEnum, double value) {
        if(this.banks.containsKey(bankEnum)) {
            this.banks.put(bankEnum, value);
            return true;
        }
        return false;
    }
}
