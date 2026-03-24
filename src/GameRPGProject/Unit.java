package GameRPGProject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Unit implements Damageable {
    public String name;
    public String jobClass;
    public int maxHp, currentHp;
    public int damage, defense;
    public boolean isAlive = true;
    public boolean isStunned = false;
    public boolean isCursed = false;

    public int x, y;
    public BufferedImage image;
    public Color fallbackColor;

    public Unit(String name, String jobClass, int hp, int def, int dmg, String imgPath, Color color) {
        this.name = name; this.jobClass = jobClass; this.maxHp = hp;
        this.currentHp = hp; this.defense = def; this.damage = dmg;
        this.fallbackColor = color;
        try {
            this.image = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            this.image = null; // วาดสี่เหลี่ยมแทนถ้าไม่มีรูป
        }
    }

    // Overloading: รับดาเมจปกติ
    @Override
    public void takeDamage(int rawDamage) {
        takeDamage(rawDamage, false);
    }

    // Overloading: รับดาเมจแบบเจาะเกราะได้
    @Override
    public void takeDamage(int rawDamage, boolean ignoreDefense) {
        if (!isAlive) return;
        if (isCursed) rawDamage = (int)(rawDamage * 1.5);

        int defToUse = ignoreDefense ? 0 : defense;
        int actualDamage = Math.max(0, rawDamage - defToUse);
        this.currentHp -= actualDamage;

        if (this.currentHp <= 0) {
            this.currentHp = 0;
            this.isAlive = false;
            this.isStunned = false;
            this.isCursed = false;
        }
    }

    public void heal(int amount) {
        if (!isAlive) return;
        this.currentHp += amount;
        if (this.currentHp > maxHp) this.currentHp = maxHp;
    }

    @Override
    public boolean isAlive() { return isAlive; }
    @Override
    public String getName() { return name; }

    // Abstract Method ให้คลาสลูกไปเขียนต่อ
    public abstract String getUnitType();
}