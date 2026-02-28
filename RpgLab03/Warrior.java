package RpgLab03;

public class Warrior extends Character {

    private int armorValue;

    public Warrior(String name, int level, int maxHealthPoints, int damage, int defense, int armorValue, Weapon weapon) {

        super(name, level, maxHealthPoints,damage, defense, weapon, "Warrior");

        this.armorValue = armorValue;
    }

    @Override
    public void attack(Character target) {

        int boostedDamage = (int)((damage + weapon.getBaseDamage()) * 1.5);

        System.out.println("🗡️ " + name + " unleashes a POWER STRIKE!");
        System.out.println("🔥 Boosted Damage (1.5x): " + boostedDamage);

        target.receiveDamage(boostedDamage);
    }

    @Override
    public void receiveDamage(int rawDamage) {

        int reducedDamage = Math.max(0, rawDamage - armorValue);

        System.out.println("🛡️ " + name + "'s Armor absorbs " + armorValue + " damage!");

        super.receiveDamage(reducedDamage);
    }
    public void displayCharacterDetails() {
        super.displayCharacterDetails();
        System.out.println("Armor Value: " + armorValue);
    }
}
