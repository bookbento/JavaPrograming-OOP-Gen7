package GameRPGProject;

import java.awt.*;

public class VisualEffect {
    public int x, y;
    public Color color;
    public String text;
    public int life = 30; // ระยะเวลาแสดงผล (frames)

    public VisualEffect(int x, int y, Color c, String text) {
        this.x = x;
        this.y = y;
        this.color = c;
        this.text = text;
    }

    public boolean isExpired() {
        life--;
        y--; // ลอยขึ้น
        return life <= 0;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString(text, x + 10, y + 30);
    }
}