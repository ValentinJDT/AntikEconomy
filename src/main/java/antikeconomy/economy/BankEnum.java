package antikeconomy.economy;

public enum BankEnum {
    GLADIATEUR("Compte courant", false, 0.4, 5000),
    PLEBEIEN("Compte courant populaire", false, 0.5, 5000),
    BATISSEUR("Plan d'épargne logement", false,0.9, 10000),
    EDILE("Plan d'épargne logement", false,0.95, 12000),
    MARCHAND("Compte courant à terme", false, 0.7, 12000),
    PATRICIEN("Plan d’épargne logement", false, 0.975, 16000),
    TRIBUN("Compte d’épargne", true, 0.8, 12000),
    QUESTEUR("Compté d’épargne populaire", true, 0.85, 18000),
    DECEMVIR("Compte d’épargne bancaire", true, 0.9, 20000);

    private String name;
    private boolean buyable;
    private double percent;
    private double bankingLimit;

    BankEnum(String name, boolean buyable, double percent, int bankingLimit) {
        this.name = name;
        this.buyable = buyable;
        this.percent = percent;
        this.bankingLimit = bankingLimit;
    }

    public String getName() {
        return name;
    }

    public boolean isBuyable() {
        return buyable;
    }

    public double getBankingLimit() {
        return bankingLimit;
    }

    public double getPercent() {
        return percent;
    }

    public double calcul(double value) {
        return (1+this.percent)*value;
    }
}
