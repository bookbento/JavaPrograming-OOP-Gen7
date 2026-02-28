package RpgLab03;

public class Healer extends Character {

    private int baseHealingPower;

    public Healer(String name, int level, int maxHealthPoints, int damage, int defense, Weapon weapon, int baseHealingPower) {

        super(name, level, maxHealthPoints,
                damage, defense, weapon, "Healer");

        this.baseHealingPower = baseHealingPower;
    }

    public int getHealingPower() {
        return baseHealingPower + (level * 3);
    }

    public void heal() {

        int healAmount = getHealingPower();

        System.out.println("✨ " + name + " casts a healing spell!");
        System.out.println("💚 Healing Amount: " + healAmount +
                " (Base: " + baseHealingPower +
                " + Level Bonus: " + (level * 3) + ")");

        healthPoints = Math.min(
                healthPoints + healAmount,
                maxHealthPoints + 80);

        System.out.println("🌿 Restored " + healAmount +
                " HP! Current HP: "
                + healthPoints + "/" + (maxHealthPoints + 80));
    }

    public void healAlly(Character ally) {

        int healAmount = getHealingPower();

        System.out.println("✨ " + name +
                " casts healing spell on " + ally.name + "!");
        System.out.println("💚 Healing Amount: " + healAmount);

        ally.healthPoints = Math.min(
                ally.healthPoints + healAmount,
                ally.maxHealthPoints);

        System.out.println("🌿 Restored " + healAmount +
                " HP to " + ally.name +
                "! Their HP: " + ally.healthPoints);
    }

    @Override
    public void displayCharacterDetails() {
        super.displayCharacterDetails();
        System.out.println("Healing Power: " + getHealingPower());
    }
}
