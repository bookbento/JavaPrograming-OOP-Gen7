package RpgLab02;

public class RPGGameApp {

    public static void main(String[] args) {

        System.out.println("======================================================================");
        System.out.println("SCENARIO 2: RPG GAME CHARACTER - DEMONSTRATION");
        System.out.println("======================================================================");

        System.out.println("[STEP 1] Creating Weapons...");
        Weapon swordWeapon = new Weapon("Sword", "Melee", 25, "Slash Attack");
        Weapon staffWeapon = new Weapon("Staff", "Magic", 20, "Spell Cast");
        Weapon staffHealer = new Weapon("Healing Staff", "Magic", 15, "Healing");

        System.out.println("[STEP 2] Creating Characters...");
        Character warrior = new Character("Arthur", 10, 1500,
                20, 10, swordWeapon, "Warrior");

        Character mage = new Character("Merlin", 12, 800,
                15, 5, staffWeapon, "Mage");

        Healer healer = new Healer("Elara", 9, 1000,
                10, 8, staffHealer, 30);

        System.out.println("[STEP 3] Initial Character Status:");
        warrior.displayCharacterDetails();
        mage.displayCharacterDetails();
        healer.displayCharacterDetails();

        System.out.println("[STEP 4] Testing attack() Method:");

        System.out.println("--- Warrior's Turn ---");
        warrior.attack(mage);

        System.out.println("--- Mage's Turn ---");
        mage.attack(warrior);

        System.out.println("[STEP 5] Status After Exchanges:");
        warrior.displayCharacterDetails();
        mage.displayCharacterDetails();

        System.out.println("[STEP 6] Testing levelUp() Method:");

        System.out.println("--- Arthur levels up! ---");
        warrior.levelUp();

        System.out.println("--- Merlin levels up! ---");
        mage.levelUp();

        System.out.println("[STEP 7] Testing Healer Special Abilities:");

        System.out.println("--- Healer heals themselves ---");
        healer.displayCharacterDetails();
        healer.heal();

        System.out.println("--- Healer heals the Warrior ---");
        healer.healAlly(warrior);
    }
}
