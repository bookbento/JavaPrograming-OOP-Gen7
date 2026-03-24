package GameRPGProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SevenKnightsGame extends JFrame {
    private BattlePanel battlePanel;
    private JTextArea logArea;
    private JPanel controlPanel;
    private JPanel menuPanel;

    private List<Unit> playerTeam = new ArrayList<>();
    private List<Unit> enemyTeam = new ArrayList<>();

    private Random rand = new Random();
    private boolean playerTurn = true;
    private int potions = 3; // จำนวนไอเท็มที่มี

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SevenKnightsGame().setVisible(true);
        });
    }

    public SevenKnightsGame() {
        setTitle("Java RPG: Advanced OOP Edition");
        setSize(950, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initUnits();

        battlePanel = new BattlePanel(playerTeam, enemyTeam);
        add(battlePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        logArea = new JTextArea(8, 50);
        logArea.setEditable(false);
        bottomPanel.add(new JScrollPane(logArea), BorderLayout.NORTH);

        // แผงปุ่มสกิล
        controlPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        createSkillButtons();
        bottomPanel.add(controlPanel, BorderLayout.CENTER);

        // แผงปุ่มเมนูระบบ (ไอเท็ม, Save, Load)
        menuPanel = new JPanel(new FlowLayout());
        createMenuButtons();
        bottomPanel.add(menuPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
        log("⚔️ Battle Start! Choose your action.");
    }

    private void initUnits() {
        // ใช้ Polymorphism โหลดเข้า List
        playerTeam.add(new Hero("Arthur", "Knight", 2000, 50, 80, "assets/knight.png", Color.BLUE));
        playerTeam.get(0).x = 250; playerTeam.get(0).y = 250;
        playerTeam.add(new Hero("Loki", "Assassin", 1200, 20, 150, "assets/assassin.png", Color.DARK_GRAY));
        playerTeam.get(1).x = 150; playerTeam.get(1).y = 250;
        playerTeam.add(new Hero("Merlin", "Sorcerer", 900, 0, 200, "assets/sorcerer.png", Color.MAGENTA));
        playerTeam.get(2).x = 50; playerTeam.get(2).y = 100;
        playerTeam.add(new Hero("Hades", "Necro", 900, 0, 120, "assets/necromancer.png", new Color(102, 0, 102)));
        playerTeam.get(3).x = 50; playerTeam.get(3).y = 250;
        playerTeam.add(new Hero("Elara", "Healer", 1000, 10, 50, "assets/healer.png", Color.GREEN));
        playerTeam.get(4).x = 50; playerTeam.get(4).y = 400;

        enemyTeam.add(new Monster("Minotaur", "Tank", 1500, 60, 100, "assets/minotaur.png", new Color(139, 69, 19)));
        enemyTeam.get(0).x = 600; enemyTeam.get(0).y = 200;
        enemyTeam.add(new Monster("Cyclops", "Tank", 2500, 30, 90, "assets/cyclops.png", new Color(160, 82, 45)));
        enemyTeam.get(1).x = 600; enemyTeam.get(1).y = 350;
        enemyTeam.add(new Monster("Medusa", "Mage", 1100, 20, 130, "assets/medusa.png", new Color(0, 128, 128)));
        enemyTeam.get(2).x = 750; enemyTeam.get(2).y = 100;
        enemyTeam.add(new Monster("Chimera", "Beast", 1300, 0, 180, "assets/chimera.png", Color.ORANGE));
        enemyTeam.get(3).x = 750; enemyTeam.get(3).y = 250;

        enemyTeam.add(new Obstacle("Hydra", 800, "assets/hydra.png", Color.GRAY));
        enemyTeam.get(4).x = 750; enemyTeam.get(4).y = 400;
    }

    private void createSkillButtons() {
        String[] labels = {"Knight: Stun", "Assassin: Backstab", "Sorcerer: Meteor", "Necro: Curse", "Healer: Heal"};
        for (int i = 0; i < labels.length; i++) {
            JButton btn = new JButton(labels[i]);
            final int index = i;
            btn.addActionListener(e -> executePlayerTurn(index));
            controlPanel.add(btn);
        }
    }

    private void createMenuButtons() {
        JButton btnPotion = new JButton("🍎 Use Potion (" + potions + " left)");
        btnPotion.addActionListener(e -> {
            if (potions > 0 && playerTurn) {
                Unit lowest = getLowestHpAlly();
                if(lowest != null) {
                    new HealthPotion().use(lowest);
                    potions--;
                    btnPotion.setText("🍎 Use Potion (" + potions + " left)");
                    showEffect(lowest.x, lowest.y, Color.GREEN, "+500 HP");
                    log("🧪 Used Potion on " + lowest.name);
                    playerTurn = false; toggleButtons(false); endPlayerTurn();
                }
            }
        });

        JButton btnSave = new JButton("💾 Save Game");
        btnSave.addActionListener(e -> {
            try { SaveManager.saveGame(playerTeam, enemyTeam); log("✅ Game Saved!"); JOptionPane.showMessageDialog(this, "Save Successful!"); }
            catch (Exception ex) { log("❌ Save Failed: " + ex.getMessage()); }
        });

        JButton btnLoad = new JButton("📂 Load Game");
        btnLoad.addActionListener(e -> {
            try {
                SaveManager.loadGame(playerTeam, enemyTeam);
                log("✅ Game Loaded!"); battlePanel.repaint(); JOptionPane.showMessageDialog(this, "Load Successful!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Load Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        menuPanel.add(btnPotion);
        menuPanel.add(btnSave);
        menuPanel.add(btnLoad);
    }

    private void log(String msg) {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void executePlayerTurn(int skillIndex) {
        if (!playerTurn) return;
        Unit actor = playerTeam.get(skillIndex);

        if (!actor.isAlive) { log("🚫 " + actor.name + " is dead!"); return; }
        if (actor.isStunned) { log("😵 " + actor.name + " is stunned!"); actor.isStunned = false; endPlayerTurn(); return; }

        playerTurn = false; toggleButtons(false);

        if (skillIndex == 0) { // Knight
            List<Unit> targets = enemyTeam.stream().filter(u -> u.isAlive && (u.name.equals("Minotaur") || u.name.equals("Cyclops"))).collect(Collectors.toList());
            if (targets.isEmpty()) targets = getAliveEnemies();
            if (!targets.isEmpty()) {
                Unit t = targets.get(rand.nextInt(targets.size()));
                log("🛡️ Knight Stuns " + t.name);
                showEffect(t.x, t.y, Color.YELLOW, "STUN!");
                t.takeDamage(actor.damage); t.isStunned = true;
            }
        }
        else if (skillIndex == 1) { // Assassin (เจาะเกราะ)
            List<Unit> back = enemyTeam.stream().filter(u -> u.isAlive && (!u.name.equals("Minotaur") && !u.name.equals("Cyclops"))).collect(Collectors.toList());
            if (back.isEmpty()) back = getAliveEnemies();
            if (!back.isEmpty()) {
                Unit t = back.get(rand.nextInt(back.size()));
                log("🗡️ Assassin Backstabs " + t.name);
                showEffect(t.x, t.y, Color.RED, "PIERCE!");
                t.takeDamage(actor.damage * 2, true); // Overloading: true = เจาะเกราะ
            }
        }
        else if (skillIndex == 2) { // Sorcerer
            List<Unit> targets = getAliveEnemies();
            log("🔥 Sorcerer attacks 2 targets!");
            for(int c=0; c<2 && !targets.isEmpty(); c++) {
                Unit t = targets.remove(rand.nextInt(targets.size()));
                showEffect(t.x, t.y, Color.MAGENTA, "BOOM!");
                t.takeDamage(actor.damage);
            }
        }
        else if (skillIndex == 3) { // Necro
            log("💀 Necromancer Curses everyone!");
            for(Unit e : getAliveEnemies()) { e.isCursed = true; showEffect(e.x, e.y, new Color(102,0,102), "CURSE"); }
        }
        else if (skillIndex == 4) { // Healer
            Unit lowest = getLowestHpAlly();
            if(lowest != null) {
                log("✝  Healer heals " + lowest.name);
                showEffect(lowest.x, lowest.y, Color.GREEN, "HEAL");
                lowest.heal(300 + actor.damage);
            }
        }

        battlePanel.repaint();
        Timer t = new Timer(1500, e -> executeEnemyTurn()); t.setRepeats(false); t.start();
    }

    private void endPlayerTurn() {
        Timer t = new Timer(1000, e -> executeEnemyTurn()); t.setRepeats(false); t.start();
    }

    private void executeEnemyTurn() {
        checkWin();
        if(getAliveEnemies().isEmpty()) return;

        // ฝั่งมอนสเตอร์โจมตี (ตัด Obstacle ออกเพราะโจมตีไม่ได้)
        List<Unit> actingEnemies = enemyTeam.stream().filter(u -> u.isAlive && u instanceof Monster).collect(Collectors.toList());
        if(actingEnemies.isEmpty()) { playerTurn = true; toggleButtons(true); return; }

        Unit enemy = actingEnemies.get(rand.nextInt(actingEnemies.size()));
        log("\n👹 Enemy Turn: " + enemy.name);

        if (enemy.isStunned) { enemy.isStunned = false; log("...is Stunned!"); }
        else {
            if (enemy.name.equals("Minotaur")) { enemy.defense += 20; showEffect(enemy.x, enemy.y, Color.GRAY, "DEF UP"); }
            else if (enemy.name.equals("Cyclops")) { enemy.heal(300); showEffect(enemy.x, enemy.y, Color.GREEN, "+HP"); }
            else if (enemy.name.equals("Medusa") || enemy.name.equals("Chimera")) {
                List<Unit> alive = getAlivePlayers();
                if(!alive.isEmpty()) {
                    Unit t = alive.get(rand.nextInt(alive.size()));
                    t.takeDamage(enemy.damage);
                    showEffect(t.x, t.y, Color.RED, "DMG");
                }
            }
        }
        battlePanel.repaint(); checkWin();
        playerTurn = true; toggleButtons(true);
    }

    private Unit getLowestHpAlly() {
        Unit lowest = null; double minPct = 1.0;
        for(Unit u : playerTeam) {
            if(u.isAlive) { double pct = (double)u.currentHp/u.maxHp; if(pct < minPct) { minPct = pct; lowest = u; } }
        }
        return lowest;
    }

    private List<Unit> getAliveEnemies() { return enemyTeam.stream().filter(u -> u.isAlive).collect(Collectors.toList()); }
    private List<Unit> getAlivePlayers() { return playerTeam.stream().filter(u -> u.isAlive).collect(Collectors.toList()); }

    private void checkWin() {
        if (getAliveEnemies().isEmpty()) { JOptionPane.showMessageDialog(this, "Victory!"); System.exit(0); }
        if (getAlivePlayers().isEmpty()) { JOptionPane.showMessageDialog(this, "Defeat!"); System.exit(0); }
    }

    private void toggleButtons(boolean b) {
        for(Component c : controlPanel.getComponents()) c.setEnabled(b);
        menuPanel.getComponent(0).setEnabled(b); // ปุ่ม Potion
    }
    private void showEffect(int x, int y, Color c, String t) { battlePanel.addEffect(new VisualEffect(x, y, c, t)); }
}