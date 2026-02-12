package rpg.lab01;

public class Character {
    protected String name;
    protected int level;
    protected int healthPoints;
    protected int maxHealthPoints;
    protected Weapon weapon;
    protected String characterClass;

    public Character(String name, int level, int maxHealthPoints, Weapon weapon, String characterClass) {
        this.name = name;
        this.level = level;
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.weapon = weapon;
        this.characterClass = characterClass;
    }

    public int attack() {
        int levelBonus = level * 2;
        int damage = weapon.getBaseDamage() + levelBonus;

        System.out.println("\n--- " + characterClass + "Turn ---" );
        System.out.println(name + " (" + characterClass + ") attacks with " + weapon.getName() + "!");
        System.out.println("⚔️ Attack Damage: " + damage +
                " (Weapon Base: " + weapon.getBaseDamage() +
                " + Level Bonus: " + levelBonus + ")");
        return damage;
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;
        if (healthPoints < 0) healthPoints = 0;

        System.out.println("\n--- " + characterClass + "Take damage ---" );
        System.out.println(name + " takes " + damage +
                " damage! Current HP: " +
                healthPoints + "/" + maxHealthPoints);
    }

    public void levelUp() {
        level++;
        maxHealthPoints += 10;
        healthPoints = maxHealthPoints;

        System.out.println("\n --- " + name + " leveled up to Level " + level + "!" + " ---");
        System.out.println(" 📈 Max Health increased to " + maxHealthPoints + " (full heal applied)");
    }

    public void displayCharacterDetails() {
        System.out.println("\n" + "--- " + name.toUpperCase() + " ---");
        System.out.println("Class: " + characterClass);
        System.out.println("Level: " + level);
        System.out.println("Health Points: " + healthPoints + "/" + maxHealthPoints);
        System.out.println("Weapon: " + weapon);
    }
}
