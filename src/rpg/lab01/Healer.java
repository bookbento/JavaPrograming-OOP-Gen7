package rpg.lab01;

public class Healer extends Character {
    private int baseHealingPower;

    public Healer(String name, int level, int maxHealthPoints,
                  Weapon weapon, int baseHealingPower) {
        super(name, level, maxHealthPoints, weapon, "Healer");
        this.baseHealingPower = baseHealingPower;
    }

    public int getHealingPower() {
        return baseHealingPower + (level * 3);
    }

    public void heal() {
        int healAmount = getHealingPower();

        System.out.println(name + " (Healer) casts a healing spell!");
        System.out.println(" ✨ Healing Amount: " + healAmount +
                " (Base: " + baseHealingPower +
                " + Level Bonus: " + (level * 3) + ")");

        healthPoints = Math.min(healthPoints + healAmount, maxHealthPoints + 80);

        System.out.println(" 💚 Restored " + healAmount +
                " HP! Current HP: " +
                healthPoints + "/" + (maxHealthPoints + 80));
    }

    public void healAlly(Character ally) {
        int healAmount = getHealingPower();

        System.out.println(name + " (Healer) casts healing spell on " + ally.name + "!");
        System.out.println(" ✨ Healing Amount: " + healAmount);

        ally.healthPoints = Math.min(
                ally.healthPoints + healAmount,
                ally.maxHealthPoints
        );

        System.out.println(" 💚 Restored " + healAmount +
                " HP to " + ally.name +
                "! Their HP: " + ally.healthPoints);
    }

    @Override
    public void displayCharacterDetails() {
        System.out.println("\n--- " + name.toUpperCase() + " ---");
        System.out.println("  Class: " + characterClass);
        System.out.println("  Level: " + level);
        System.out.println("  Health Points: " + healthPoints + "/" + maxHealthPoints);
        System.out.println("    Weapon: " + weapon);
        System.out.println("  Healing Power: " + getHealingPower());
    }
}