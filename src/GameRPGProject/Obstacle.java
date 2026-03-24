package GameRPGProject;
import java.awt.Color;

public class Obstacle extends Unit {
    public Obstacle(String name, int hp, String imgPath, Color color) {
        // วัตถุไม่มีดาเมจ ป้องกัน 0
        super(name, "Object", hp, 0, 0, imgPath, color);
    }

    @Override
    public String getUnitType() {
        return "Obstacle";
    }

    // Overriding: วัตถุไม่ติดคริติคอล ไม่ติดคำสาป รับดาเมจตรงๆ
    @Override
    public void takeDamage(int rawDamage, boolean ignoreDefense) {
        if (!isAlive) return;
        this.currentHp -= rawDamage;
        if (this.currentHp <= 0) {
            this.currentHp = 0;
            this.isAlive = false;
        }
    }
}