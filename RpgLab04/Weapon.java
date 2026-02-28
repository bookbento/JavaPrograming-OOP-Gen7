package RpgLab04;

public class Weapon {
    private String name;
    private String type;
    private int baseDamage;
    private String ability;

    public Weapon(String name, String type, int baseDamage, String ability) {
        this.name = name;
        this.type = type;
        this.baseDamage = baseDamage;
        this.ability = ability;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (Type: " + type + ", Damage: " + baseDamage + ", Ability: " + ability + ")";
    }
}
