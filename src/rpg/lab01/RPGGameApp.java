package rpg.lab01;

public class RPGGameApp {
    public static void main(String[] args) {

        System.out.println("[STEP 1] Creating Weapons...");
        Weapon swordWeapon = new Weapon("Sword", "Melee", 25, "Slash Attack");
        Weapon staffWeapon = new Weapon("Staff", "Magic", 20, "Spell Cast");
        Weapon staffHealer = new Weapon("Healing Staff", "Magic", 15, "Healing");

        System.out.println("[STEP 2] Creating Characters...");
        Character warrior = new Character("Arthur", 10, 1500, swordWeapon, "Warrior");
        Character mage = new Character("Merlin", 12, 800, staffWeapon, "Mage");
        Healer healer = new Healer("Elara", 9, 1000, staffHealer, 30);

        System.out.println("[STEP 3] Initial Character Status:");
        warrior.displayCharacterDetails();
        mage.displayCharacterDetails();
        healer.displayCharacterDetails();

        System.out.println("\n[STEP 4] Testing attack() Method:");
        int warriorDamage = warrior.attack();
        int mageDamage = mage.attack();

        System.out.println("\n[STEP 5] Simulating Combat Scenario:");
        mage.takeDamage(warriorDamage);
        warrior.takeDamage(mageDamage);

        System.out.println("\n[STEP 6] Testing levelUp() Method:");
        warrior.levelUp();
        mage.levelUp();

        System.out.println("\n[STEP 7] Testing Healer Special Abilities:\n");

        System.out.println("--- Healer heals themselves ---");
        healer.displayCharacterDetails();
        healer.heal();

        System.out.println("\n--- Healer heals the Warrior ---");
        healer.healAlly(warrior);

    }
}
