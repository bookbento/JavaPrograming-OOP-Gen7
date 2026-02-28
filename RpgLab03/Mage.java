package RpgLab03;

public class Mage extends Character {

    private int mana;

    public Mage(String name, int level, int maxHealthPoints,int damage, int defense,int mana, Weapon weapon) {

        super(name, level, maxHealthPoints,damage, defense, weapon, "Mage");

        this.mana = mana;
    }

    @Override
    public void attack(Character target) {

        if (mana >= 20) {

            int spellDamage = damage + weapon.getBaseDamage() + 30;

            System.out.println("✨ " + name + " casts MAGIC MISSILE!");
            System.out.println("🔮 Spell Damage: " + spellDamage);

            mana -= 20;

            target.receiveDamage(spellDamage);

            System.out.println("🔋 Remaining Mana: " + mana);

        } else {
            System.out.println("⚠️ Not enough mana! Basic attack instead.");
            super.attack(target);
        }
    }
    public void displayCharacterDetails() {
        super.displayCharacterDetails();
        System.out.println("Mana: " + mana  + "/" + mana );
    }
}
