package fr.mrcoq.antikeconomy.sql;

import java.util.*;
import java.util.List;

public class BalancesInterface extends SQLite {

    public BalancesInterface(String dbName) {
        super(dbName);
    }

    public HashMap<UUID, Double> getBalancesDB() {
        HashMap<UUID, Double> balances = new HashMap<>();
        List<Map<String, String>> balancesDB = fetchAll("SELECT * FROM Balances");

        for(Map<String, String> balance : balancesDB) {
            UUID uuid = UUID.fromString(balance.get("uuid"));
            Double money = Double.parseDouble(balance.get("money"));
            
            balances.put(uuid, money);
        }

        return balances;
    }

    public void saveBalances(HashMap<UUID, Double> balancesDB) {
        String insertString = "INSERT INTO Balances (`id`, `uuid`, `money`) VALUES ";

        List<String> values = new ArrayList<>();
        List<String> updates = new ArrayList<>();

        List<Map<String, String>> list = fetchAll("SELECT * FROM Balances");

        balancesDB.forEach((uuid, money) -> {

            if(list.stream().anyMatch(map -> map.get("uuid").equals(uuid.toString()))) {
                updates.add(String.format("UPDATE Balances SET money = %s WHERE uuid = '%s'", money, uuid));
            } else {
                values.add(String.format("(NULL, '%s', %s)", uuid, money));
            }

        });

        insertString += String.join(", ", values);

        if(values.size() > 0) {
            execute(insertString);
        }

        if(updates.size() > 0) {
            updates.forEach((value) -> SQLite.execute(value));
        }
    }

}
