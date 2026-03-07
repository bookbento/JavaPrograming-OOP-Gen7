package GameRPGProject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Unit {
    public String name;
    public String jobClass;
    public int maxHp, currentHp;
    public int damage, defense;
    public boolean isAlive = true;

    // Status Effects
    public boolean isStunned = false;
    public boolean isCursed = false;

    // GUI Position & Image
    public int x, y;

    public BufferedImage image;
    public Color fallbackColor;

    public Unit(String name, String jobClass, int hp, int def, int dmg, String imgPath, Color color) {
        this.name = name;
        this.jobClass = jobClass;
        this.maxHp = hp;
        this.currentHp = hp;
        this.defense = def;
        this.damage = dmg;
        this.fallbackColor = color;

        try {
            this.image = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            this.image = null;
        }
    }

    public void takeDamage(int rawDmg) {
        if (!isAlive) return;

        if (isCursed) {
            rawDmg = (int)(rawDmg * 1.5);
        }

        int actualDmg = Math.max(0, rawDmg - defense);
        this.currentHp -= actualDmg;

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
}