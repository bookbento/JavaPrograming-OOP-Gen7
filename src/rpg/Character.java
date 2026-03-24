package rpg;

import java.util.Random;

public abstract class Character implements Attackable {
    private static final Random RANDOM = new Random();

    protected String name;
    protected int maxHp;
    protected int currentHp;
    protected int attackPower;
    protected int defense;
    protected int level;
    protected int experience;

    public Character(String name, int maxHp, int attackPower, int defense) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.level = 1;
        this.experience = 0;
    }

    public abstract int calculateAttackDamage();

    public abstract String getClassType();

    public void attack(Attackable target) {
        attack(target, 0);
    }

    // Method overloading: same method name with bonus parameter
    public void attack(Attackable target, int bonusDamage) {
        int damage = Math.max(1, calculateAttackDamage() + bonusDamage);
        System.out.println(name + " attacks " + target.getName() + " for " + damage + " damage.");
        target.takeDamage(damage);
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(1, damage - defense);
        currentHp = Math.max(0, currentHp - reducedDamage);
        System.out.println(name + " takes " + reducedDamage + " damage. (" + currentHp + "/" + maxHp + " HP)");
    }

    public void heal(int amount) {
        int oldHp = currentHp;
        currentHp = Math.min(maxHp, currentHp + amount);
        int healed = currentHp - oldHp;
        System.out.println(name + " recovers " + healed + " HP. (" + currentHp + "/" + maxHp + " HP)");
    }

    @Override
    public boolean isAlive() {
        return currentHp > 0;
    }

    public void gainExperience(int amount) {
        experience += amount;
        int nextLevelThreshold = level * 50;
        while (experience >= nextLevelThreshold) {
            experience -= nextLevelThreshold;
            level++;
            maxHp += 10;
            attackPower += 2;
            defense += 1;
            currentHp = maxHp;
            System.out.println(name + " leveled up! Now level " + level + ".");
            nextLevelThreshold = level * 50;
        }
    }

    protected int randomVariance(int minInclusive, int maxInclusive) {
        return RANDOM.nextInt(maxInclusive - minInclusive + 1) + minInclusive;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getDefense() {
        return defense;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = Math.max(0, Math.min(currentHp, maxHp));
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = Math.max(1, maxHp);
        if (currentHp > this.maxHp) {
            currentHp = this.maxHp;
        }
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = Math.max(1, attackPower);
    }

    public void setDefense(int defense) {
        this.defense = Math.max(0, defense);
    }

    public void setLevel(int level) {
        this.level = Math.max(1, level);
    }

    public void setExperience(int experience) {
        this.experience = Math.max(0, experience);
    }
}
