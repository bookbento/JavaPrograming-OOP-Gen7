package rpg;

public class Warrior extends Character {
    public Warrior(String name) {
        super(name, 140, 18, 5);
    }

    @Override
    public int calculateAttackDamage() {
        return attackPower + randomVariance(0, 5);
    }

    @Override
    public String getClassType() {
        return "Warrior";
    }
}
