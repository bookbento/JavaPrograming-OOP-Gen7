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

    private List<Unit> playerTeam = new ArrayList<>();
    private List<Unit> enemyTeam = new ArrayList<>();

    private Random rand = new Random();
    private boolean playerTurn = true;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SevenKnightsGame window = new SevenKnightsGame();
            window.setVisible(true);
        });
    }

    public SevenKnightsGame() {
        setTitle("Java RPG: 5 Prime Battle");
        setSize(900, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initUnits(); // สร้างตัวละคร

        // ส่ง List ตัวละครไปให้ BattlePanel วาด
        battlePanel = new BattlePanel(playerTeam, enemyTeam);
        add(battlePanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        logArea = new JTextArea(8, 50);
        logArea.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(logArea);
        bottomPanel.add(scrollLog, BorderLayout.NORTH);

        controlPanel = new JPanel(new GridLayout(1, 5, 5, 5));
        createSkillButtons();
        bottomPanel.add(controlPanel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);
        log("⚔️ Battle Start! Choose your action.");
    }

    private void initUnits() {
        // --- PLAYER TEAM ---
        Unit knight = new Unit("Arthur", "Knight", 2000, 50, 80, "assets/knight.png", Color.BLUE);
        knight.x = 250; knight.y = 250;

        Unit assassin = new Unit("Loki", "Assassin", 1200, 20, 150, "assets/assassin.png", Color.DARK_GRAY);
        assassin.x = 150; assassin.y = 250;

        Unit sorcerer = new Unit("Merlin", "Sorcerer", 900, 0, 200, "assets/sorcerer.png", Color.MAGENTA);
        sorcerer.x = 50; sorcerer.y = 100;

        Unit necro = new Unit("Hades", "Necro", 900, 0, 120, "assets/necromancer.png", new Color(102, 0, 102));
        necro.x = 50; necro.y = 250;

        Unit healer = new Unit("Elara", "Healer", 1000, 10, 50, "assets/healer.png", Color.GREEN);
        healer.x = 50; healer.y = 400;

        playerTeam.add(knight);
        playerTeam.add(assassin);
        playerTeam.add(sorcerer);
        playerTeam.add(necro);
        playerTeam.add(healer);

        // --- ENEMY TEAM ---
        Unit mino = new Unit("Minotaur", "Tank", 1500, 60, 100, "assets/minotaur.png", new Color(139, 69, 19));
        mino.x = 600; mino.y = 200;

        Unit cyclops = new Unit("Cyclops", "Tank", 2500, 30, 90, "assets/cyclops.png", new Color(160, 82, 45));
        cyclops.x = 600; cyclops.y = 350;

        Unit medusa = new Unit("Medusa", "Mage", 1100, 20, 130, "assets/medusa.png", new Color(0, 128, 128));
        medusa.x = 750; medusa.y = 100;

        Unit chimera = new Unit("Chimera", "Beast", 1300, 0, 180, "assets/chimera.png", Color.ORANGE);
        chimera.x = 750; chimera.y = 250;

        Unit hydra = new Unit("Hydra", "Beast", 1600, 0, 140, "assets/hydra.png", new Color(34, 139, 34));
        hydra.x = 750; hydra.y = 400;

        enemyTeam.add(mino);
        enemyTeam.add(cyclops);
        enemyTeam.add(medusa);
        enemyTeam.add(chimera);
        enemyTeam.add(hydra);
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

    private void log(String msg) {
        logArea.append(msg + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    // --- LOGIC PARTS (ย่อลงเล็กน้อย Logic เหมือนเดิม) ---
    private void executePlayerTurn(int skillIndex) {
        if (!playerTurn) return;
        Unit actor = playerTeam.get(skillIndex);

        if (!actor.isAlive) { log("🚫 " + actor.name + " is dead!"); return; }
        if (actor.isStunned) {
            log("😵 " + actor.name + " is stunned!");
            actor.isStunned = false; endPlayerTurn(); return;
        }

        playerTurn = false;
        toggleButtons(false);

        // 1. Knight: Stun Minotaur/Cyclops
        if (skillIndex == 0) {
            List<Unit> targets = enemyTeam.stream().filter(u -> u.isAlive && (u.name.equals("Minotaur") || u.name.equals("Cyclops"))).collect(Collectors.toList());
            if (targets.isEmpty()) targets = getAliveEnemies();
            if (!targets.isEmpty()) {
                Unit t = targets.get(rand.nextInt(targets.size()));
                log("🛡️ Knight Stuns " + t.name);
                showEffect(t.x, t.y, Color.YELLOW, "STUN!");
                t.takeDamage(actor.damage);
                t.isStunned = true;
            }
        }
        // 2. Assassin: Backrow
        else if (skillIndex == 1) {
            List<Unit> back = enemyTeam.stream().filter(u -> u.isAlive && (u.name.equals("Medusa") || u.name.equals("Chimera") || u.name.equals("Hydra"))).collect(Collectors.toList());
            if (back.isEmpty()) back = getAliveEnemies();
            if (!back.isEmpty()) {
                Unit t = back.get(rand.nextInt(back.size()));
                log("🗡️ Assassin Crits " + t.name);
                showEffect(t.x, t.y, Color.RED, "CRIT!");
                t.takeDamage(actor.damage * 2);
            }
        }
        // 3. Sorcerer: 2 Targets
        else if (skillIndex == 2) {
            List<Unit> targets = getAliveEnemies();
            log("🔥 Sorcerer attacks 2 enemies!");
            int c = 0;
            while(c < 2 && !targets.isEmpty()) {
                Unit t = targets.remove(rand.nextInt(targets.size()));
                showEffect(t.x, t.y, Color.MAGENTA, "BOOM!");
                t.takeDamage(actor.damage);
                c++;
            }
        }
        // 4. Necro: Curse All
        else if (skillIndex == 3) {
            log("💀 Necromancer Curses everyone!");
            for(Unit e : getAliveEnemies()) {
                e.isCursed = true;
                showEffect(e.x, e.y, new Color(102,0,102), "CURSE");
            }
        }
        // 5. Healer: Heal Lowest
        else if (skillIndex == 4) {
            Unit lowest = null;
            double minPct = 1.0;
            for(Unit u : playerTeam) {
                if(u.isAlive) {
                    double pct = (double)u.currentHp/u.maxHp;
                    if(pct < minPct) { minPct = pct; lowest = u; }
                }
            }
            if(lowest != null) {
                log("💚 Healer heals " + lowest.name);
                showEffect(lowest.x, lowest.y, Color.GREEN, "HEAL");
                lowest.heal(300 + actor.damage);
            }
        }

        battlePanel.repaint();
        Timer t = new Timer(1500, e -> executeEnemyTurn());
        t.setRepeats(false); t.start();
    }

    private void endPlayerTurn() {
        Timer t = new Timer(1000, e -> executeEnemyTurn());
        t.setRepeats(false); t.start();
    }

    private void executeEnemyTurn() {
        checkWin();
        if(getAliveEnemies().isEmpty()) return;

        log("\n👿 Enemy Turn...");
        Unit enemy = getAliveEnemies().get(rand.nextInt(getAliveEnemies().size()));

        if (enemy.isStunned) {
            log(enemy.name + " is stunned!");
            enemy.isStunned = false;
        } else {
            String n = enemy.name;
            if (n.equals("Minotaur")) {
                log("🐂 Minotaur roars! (Defense Up)");
                enemy.defense += 20;
                showEffect(enemy.x, enemy.y, Color.GRAY, "DEF UP");
            }
            else if (n.equals("Cyclops")) {
                log("👁️ Cyclops regenerates! (HP Up)");
                enemy.heal(300); showEffect(enemy.x, enemy.y, Color.GREEN, "+HP");
            }
            else if (n.equals("Medusa")) {
                log("🐍 Medusa glares! (Random Stun)");
                List<Unit> alive = getAlivePlayers();
                if(!alive.isEmpty()) {
                    Unit t = alive.get(rand.nextInt(alive.size()));
                    t.isStunned = true; t.takeDamage(enemy.damage);
                    showEffect(t.x, t.y, Color.YELLOW, "STONE"); log("Medusa stuns " + t.name);
                }
            }
            else if (n.equals("Chimera")) {
                log("🦁 Chimera Savage Bite! (High Dmg)");
                List<Unit> alive = getAlivePlayers();
                if(!alive.isEmpty()) {
                    Unit t = alive.get(rand.nextInt(alive.size()));
                    t.takeDamage((int)(enemy.damage * 2.5));
                    showEffect(t.x, t.y, Color.RED, "BITE"); log("Chimera Bites " + t.name);
                }
            }
            else if (n.equals("Hydra")) {
                log("🐲 Hydra Triple Breath! (3 Targets)");
                List<Unit> alive = getAlivePlayers();
                int c=0;
                while(c<3 && !alive.isEmpty()) {
                    Unit t = alive.remove(rand.nextInt(alive.size()));
                    t.takeDamage(enemy.damage);
                    showEffect(t.x, t.y, Color.MAGENTA, "POISON");
                    c++;
                }
                log("Hydra attacks 3 targets");
            }
        }

        battlePanel.repaint();
        checkWin();
        playerTurn = true;
        toggleButtons(true);
    }

    private List<Unit> getAliveEnemies() { return enemyTeam.stream().filter(u -> u.isAlive).collect(Collectors.toList()); }
    private List<Unit> getAlivePlayers() { return playerTeam.stream().filter(u -> u.isAlive).collect(Collectors.toList()); }

    private void checkWin() {
        if (getAliveEnemies().isEmpty()) { JOptionPane.showMessageDialog(this, "Victory!"); System.exit(0); }
        if (getAlivePlayers().isEmpty()) { JOptionPane.showMessageDialog(this, "Defeat!"); System.exit(0); }
    }

    private void toggleButtons(boolean b) { for(Component c : controlPanel.getComponents()) c.setEnabled(b); }
    private void showEffect(int x, int y, Color c, String t) { battlePanel.addEffect(new VisualEffect(x, y, c, t)); }
}
