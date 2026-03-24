package rpg;

public class HealthPotion extends Item {
    private final int healAmount;

    public HealthPotion() {
        this(35);
    }

    public HealthPotion(int healAmount) {
        super("Health Potion");
        this.healAmount = healAmount;
    }

    @Override
    public void use(Character user) {
        System.out.println(user.getName() + " uses " + itemName + ".");
        user.heal(healAmount);
    }
}
