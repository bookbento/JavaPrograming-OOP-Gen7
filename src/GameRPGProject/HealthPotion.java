package GameRPGProject;

public class HealthPotion implements Consumable {
    private int healAmount = 500;

    @Override
    public void use(Unit target) {
        target.heal(healAmount);
    }

    @Override
    public String getItemName() {
        return "Health Potion (+500 HP)";
    }
}