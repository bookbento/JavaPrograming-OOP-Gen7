package GameRPGProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BattlePanel extends JPanel {
    private List<Unit> playerTeam;
    private List<Unit> enemyTeam;
    private List<VisualEffect> effects = new ArrayList<>();

    public BattlePanel(List<Unit> playerTeam, List<Unit> enemyTeam) {
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        setBackground(new Color(30, 30, 30)); // พื้นหลังสีมืด

        // Loop Animation
        new Timer(16, e -> updateEffects()).start();
    }

    public void addEffect(VisualEffect ve) {
        effects.add(ve);
    }

    private void updateEffects() {
        effects.removeIf(VisualEffect::isExpired);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Unit u : playerTeam) drawUnit(g, u);
        for (Unit u : enemyTeam) drawUnit(g, u);
        for (VisualEffect ve : effects) ve.draw(g);
    }

    private void drawUnit(Graphics g, Unit u) {
        if (!u.isAlive) {
            g.setColor(Color.DARK_GRAY);
            g.drawString("💀", u.x + 20, u.y + 30);
            return;
        }

        if (u.image != null) {
            g.drawImage(u.image, u.x, u.y, 60, 60, null);
        } else {
            g.setColor(u.fallbackColor);
            g.fillRect(u.x, u.y, 60, 60);
            g.setColor(Color.WHITE);
            g.drawRect(u.x, u.y, 60, 60);
            g.drawString(u.name.substring(0, Math.min(3, u.name.length())), u.x+15, u.y+35);
        }

        // Status Icons
        if(u.isStunned) g.drawString("💫", u.x, u.y - 5);
        if(u.isCursed) g.drawString("💀", u.x + 50, u.y - 5);

        // HP Bar
        g.setColor(Color.RED);
        g.fillRect(u.x, u.y - 15, 60, 5);
        g.setColor(Color.GREEN);
        int hpWidth = (int) ((double) u.currentHp / u.maxHp * 60);
        g.fillRect(u.x, u.y - 15, hpWidth, 5);

        // Name
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString(u.name, u.x, u.y + 75);
        g.drawString(u.currentHp + "/" + u.maxHp, u.x, u.y + 85);
    }
}
