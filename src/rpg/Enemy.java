package rpg;

public class Enemy extends Character {
    private final int experienceReward;

    public Enemy(String name, int maxHp, int attackPower, int defense, int experienceReward) {
        super(name, maxHp, attackPower, defense);
        this.experienceReward = experienceReward;
    }

    @Override
    public int calculateAttackDamage() {
        return Math.max(1, attackPower + randomVariance(-1, 3));
    }

    @Override
    public String getClassType() {
        return "Enemy";
    }

    public int getExperienceReward() {
        return experienceReward;
    }
}
