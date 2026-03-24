package rpg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final FileManager fileManager = new FileManager("player.txt");
    private final List<Usable> inventory = new ArrayList<>();
    private final List<Attackable> targets = new ArrayList<>();

    private Character player;
    private boolean running = true;

    public void start() {
        System.out.println("=== TEXT RPG ===");
        while (running) {
            showMainMenu();
            int choice = readInt("Choose option: ", 1, 3);
            if (choice == 1) {
                createCharacterFlow();
                gameLoop();
            } else if (choice == 2) {
                if (loadGameFlow()) {
                    gameLoop();
                }
            } else {
                running = false;
                System.out.println("Goodbye!");
            }
        }
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("1) New Game");
        System.out.println("2) Load Game");
        System.out.println("3) Exit");
    }

    private void createCharacterFlow() {
        System.out.print("Enter your character name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Hero";
        }

        System.out.println("Choose class:");
        System.out.println("1) Warrior (high HP)");
        System.out.println("2) Mage (high damage)");
        int classChoice = readInt("Class: ", 1, 2);

        if (classChoice == 1) {
            player = new Warrior(name);
        } else {
            player = new Mage(name);
        }

        inventory.clear();
        inventory.add(new HealthPotion());
        inventory.add(new HealthPotion());
        initializeTargets();
    }

    private boolean loadGameFlow() {
        SaveData data = fileManager.loadGame();
        if (data == null || data.playerClass == null || data.playerName == null) {
            return false;
        }

        if ("Warrior".equalsIgnoreCase(data.playerClass)) {
            player = new Warrior(data.playerName);
        } else {
            player = new Mage(data.playerName);
        }
        if (data.maxHp > 0) {
            player.setMaxHp(data.maxHp);
        }
        if (data.attackPower > 0) {
            player.setAttackPower(data.attackPower);
        }
        if (data.defense >= 0) {
            player.setDefense(data.defense);
        }
        player.setCurrentHp(data.currentHp);
        player.setLevel(data.level);
        player.setExperience(data.experience);

        inventory.clear();
        for (int i = 0; i < Math.max(0, data.potionCount); i++) {
            inventory.add(new HealthPotion());
        }

        initializeTargets();
        return true;
    }

    private void initializeTargets() {
        targets.clear();
        targets.add(generateRandomEnemy(1));
        targets.add(generateRandomEnemy(2));
        targets.add(new Chest("Old Chest", 25));
    }

    private Enemy generateRandomEnemy(int difficulty) {
        String[] names = {"Goblin", "Skeleton", "Slime", "Wolf"};
        String name = names[random.nextInt(names.length)] + " Lv" + difficulty;
        int hp = 45 + random.nextInt(20) + (difficulty * 10);
        int atk = 10 + random.nextInt(4) + difficulty;
        int def = 1 + random.nextInt(3);
        int exp = 20 + (difficulty * 10);
        return new Enemy(name, hp, atk, def, exp);
    }

    private void gameLoop() {
        boolean playing = true;
        while (playing && player != null && player.isAlive()) {
            if (allEnemiesDefeated()) {
                System.out.println("All enemies defeated. You win!");
                break;
            }

            showActionMenu();
            int action = readInt("Choose action: ", 1, 6);
            switch (action) {
                case 1:
                    handleAttack();
                    break;
                case 2:
                    handleUseItem();
                    break;
                case 3:
                    showStatus();
                    break;
                case 4:
                    fileManager.saveGame(player, countPotions());
                    break;
                case 5:
                    loadGameFlow();
                    break;
                case 6:
                    playing = false;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    break;
            }
        }

        if (player != null && !player.isAlive()) {
            System.out.println("You were defeated. Game over.");
        }
    }

    private void showActionMenu() {
        System.out.println();
        System.out.println("1) Attack");
        System.out.println("2) Use Item");
        System.out.println("3) View Status");
        System.out.println("4) Save Game");
        System.out.println("5) Load Game");
        System.out.println("6) Exit to Main Menu");
    }

    private void handleAttack() {
        List<Attackable> aliveTargets = new ArrayList<>();
        for (Attackable target : targets) {
            if (target.isAlive()) {
                aliveTargets.add(target);
            }
        }
        if (aliveTargets.isEmpty()) {
            System.out.println("No targets left.");
            return;
        }

        System.out.println("Select target:");
        for (int i = 0; i < aliveTargets.size(); i++) {
            Attackable t = aliveTargets.get(i);
            System.out.println((i + 1) + ") " + t.getName() + " (HP/Durability: " + t.getCurrentHp() + ")");
        }

        int targetChoice = readInt("Target: ", 1, aliveTargets.size());
        Attackable target = aliveTargets.get(targetChoice - 1);

        if (player instanceof Warrior) {
            player.attack(target, 2);
        } else {
            player.attack(target);
        }

        if (target instanceof Chest) {
            Chest chest = (Chest) target;
            if (chest.isOpened()) {
                inventory.add(new HealthPotion());
            }
            return;
        }

        Enemy enemy = (Enemy) target;
        if (!enemy.isAlive()) {
            System.out.println(enemy.getName() + " was defeated!");
            player.gainExperience(enemy.getExperienceReward());
            return;
        }

        enemy.attack(player);
    }

    private void handleUseItem() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("Inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i + 1) + ") " + inventory.get(i).getItemName());
        }

        int choice = readInt("Use item number: ", 1, inventory.size());
        Usable item = inventory.remove(choice - 1);
        item.use(player);
    }

    private void showStatus() {
        System.out.println();
        System.out.println("--- PLAYER STATUS ---");
        System.out.println("Name: " + player.getName());
        System.out.println("Class: " + player.getClassType());
        System.out.println("HP: " + player.getCurrentHp() + "/" + player.getMaxHp());
        System.out.println("Attack: " + player.getAttackPower());
        System.out.println("Defense: " + player.getDefense());
        System.out.println("Level: " + player.getLevel());
        System.out.println("Exp: " + player.getExperience());
        System.out.println("Health Potions: " + countPotions());
        System.out.println("---------------------");
    }

    private int countPotions() {
        int count = 0;
        for (Usable item : inventory) {
            if (item instanceof HealthPotion) {
                count++;
            }
        }
        return count;
    }

    private boolean allEnemiesDefeated() {
        for (Attackable t : targets) {
            if (t instanceof Enemy && t.isAlive()) {
                return false;
            }
        }
        return true;
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
