package rpg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    private final File saveFile;

    public FileManager(String filePath) {
        this.saveFile = new File(filePath);
    }

    public void saveGame(Character player, int potionCount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            writer.write("name=" + player.getName());
            writer.newLine();
            writer.write("class=" + player.getClassType());
            writer.newLine();
            writer.write("currentHp=" + player.getCurrentHp());
            writer.newLine();
            writer.write("maxHp=" + player.getMaxHp());
            writer.newLine();
            writer.write("attackPower=" + player.getAttackPower());
            writer.newLine();
            writer.write("defense=" + player.getDefense());
            writer.newLine();
            writer.write("level=" + player.getLevel());
            writer.newLine();
            writer.write("experience=" + player.getExperience());
            writer.newLine();
            writer.write("potionCount=" + potionCount);
            writer.newLine();
            System.out.println("Game saved to " + saveFile.getPath());
        } catch (IOException e) {
            System.out.println("Failed to save game: " + e.getMessage());
        }
    }

    public SaveData loadGame() {
        if (!saveFile.exists()) {
            System.out.println("No save file found.");
            return null;
        }

        SaveData data = new SaveData();
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length != 2) {
                    continue;
                }
                String key = parts[0].trim();
                String value = parts[1].trim();
                parseField(data, key, value);
            }
            System.out.println("Game loaded from " + saveFile.getPath());
            return data;
        } catch (IOException | NumberFormatException e) {
            System.out.println("Failed to load game: " + e.getMessage());
            return null;
        }
    }

    private void parseField(SaveData data, String key, String value) {
        switch (key) {
            case "name":
                data.playerName = value;
                break;
            case "class":
                data.playerClass = value;
                break;
            case "currentHp":
                data.currentHp = Integer.parseInt(value);
                break;
            case "level":
                data.level = Integer.parseInt(value);
                break;
            case "maxHp":
                data.maxHp = Integer.parseInt(value);
                break;
            case "attackPower":
                data.attackPower = Integer.parseInt(value);
                break;
            case "defense":
                data.defense = Integer.parseInt(value);
                break;
            case "experience":
                data.experience = Integer.parseInt(value);
                break;
            case "potionCount":
                data.potionCount = Integer.parseInt(value);
                break;
            default:
                break;
        }
    }
}
