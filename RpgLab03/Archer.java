package RpgLab03;

public class Archer extends Character {

    private int accuracy;

    public Archer(String name, int level, int maxHealthPoints, int damage, int defense, int accuracy, Weapon weapon) {

        super(name, level, maxHealthPoints,
                damage, defense, weapon, "Archer");

        this.accuracy = accuracy;
    }

    @Override
    public void attack(Character target) {

        System.out.println("🏹 " + name + " fires a precise arrow!");

        int bonus = accuracy / 5;
        int totalDamage = damage + weapon.getBaseDamage() + bonus;

        target.receiveDamage(totalDamage);
    }
    public void displayCharacterDetails() {
        super.displayCharacterDetails();
        System.out.println("Accuracy: " + accuracy + "%");
    }
}
