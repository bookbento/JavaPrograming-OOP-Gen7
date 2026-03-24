package GameRPGProject;

import java.io.*;
import java.util.List;

public class SaveManager {
    private static final String FILE_PATH = "savegame.csv";

    // บันทึกเกม
    public static void saveGame(List<Unit> players, List<Unit> enemies) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        writer.write("Type,Name,CurrentHP\n");
        for (Unit u : players) writer.write("Player," + u.name + "," + u.currentHp + "\n");
        for (Unit u : enemies) writer.write("Enemy," + u.name + "," + u.currentHp + "\n");
        writer.close();
    }

    // โหลดเกม + Input Validation
    public static void loadGame(List<Unit> players, List<Unit> enemies) throws Exception {
        File file = new File(FILE_PATH);
        if (!file.exists()) throw new FileNotFoundException("No save file found.");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); // ข้าม Header

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length != 3) continue; // ข้ามบรรทัดที่ข้อมูลไม่ครบ

            String type = data[0];
            String name = data[1];
            int hp;

            // Validation: ป้องกันไฟล์โดนแก้เป็นตัวหนังสือ
            try {
                hp = Integer.parseInt(data[2]);
            } catch (NumberFormatException e) {
                continue;
            }

            // อัปเดต HP ให้ตรงกับชื่อ
            List<Unit> targetList = type.equals("Player") ? players : enemies;
            for (Unit u : targetList) {
                if (u.name.equals(name)) {
                    u.currentHp = hp;
                    u.isAlive = (hp > 0);
                    break;
                }
            }
        }
        reader.close();
    }
}