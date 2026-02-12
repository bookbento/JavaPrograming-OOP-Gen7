package RpgLab02;

public class Character {

    protected String name;
    protected int level;
    protected int healthPoints;
    protected int maxHealthPoints;
    protected int damage;
    protected int defense;
    protected Weapon weapon;
    protected String characterClass;

    public Character(String name, int level, int maxHealthPoints,
                     int damage, int defense,
                     Weapon weapon, String characterClass) {

        this.name = name;
        this.level = level;
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.damage = damage;
        this.defense = defense;
        this.weapon = weapon;
        this.characterClass = characterClass;
    }

    // ================= ⚔ ATTACK =================
    public void attack(Character target) {

        int rawDamage = damage + weapon.getBaseDamage();

        System.out.println("⚔️ " + name + " (" + characterClass + ") attacks "
                + target.name + " with " + weapon.getName() + "!");
        System.out.println("🔥 Raw Attack Damage: " + rawDamage);

        target.takeDamage(rawDamage);
    }

    // ================= 🛡 TAKE DAMAGE =================
    public void takeDamage(int rawDamage) {

        System.out.println("🛡 " + name + "'s Defense: " + defense +
                " (reduces damage by " + defense + ")");

        int actualDamage = Math.max(0, rawDamage - defense);

        System.out.println("💥 Actual Damage Taken: " + actualDamage);

        healthPoints -= actualDamage;

        if (healthPoints < 0) {
            healthPoints = 0;
        }

        System.out.println("❤️ " + name + "'s HP: "
                + healthPoints + "/" + maxHealthPoints);
    }

    // ================= STATUS =================
    public boolean isAlive() {
        return healthPoints > 0;
    }

    public String getStatus() {
        return isAlive() ? "Active 🟢" : "Fainted 💀";
    }

    // ================= LEVEL UP =================
    public void levelUp() {
        level++;
        maxHealthPoints += 10;
        healthPoints = maxHealthPoints;

        System.out.println("⬆️ " + name + " leveled up to Level " + level + "!");
        System.out.println("✨ Max Health increased to "
                + maxHealthPoints + " (full heal applied)");
    }

    // ================= DISPLAY =================
    public void displayCharacterDetails() {

        System.out.println("\n--- " + name.toUpperCase() + " ---");
        System.out.println("Class: " + characterClass);
        System.out.println("Status: " + getStatus());
        System.out.println("Level: " + level);
        System.out.println("Health Points: " + healthPoints + "/" + maxHealthPoints);
        System.out.println("Damage: " + damage);
        System.out.println("Defense: " + defense);
        System.out.println("Weapon: " + weapon);
    }
}
