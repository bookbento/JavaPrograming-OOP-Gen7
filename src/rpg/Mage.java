package rpg;

public class Mage extends Character {
    public Mage(String name) {
        super(name, 95, 22, 2);
    }

    @Override
    public int calculateAttackDamage() {
        int base = attackPower + randomVariance(-2, 8);
        if (randomVariance(1, 100) <= 25) {
            System.out.println("Magic surge! Bonus spell damage activated.");
            base += 8;
        }
        return Math.max(1, base);
    }

    @Override
    public String getClassType() {
        return "Mage";
    }
}
