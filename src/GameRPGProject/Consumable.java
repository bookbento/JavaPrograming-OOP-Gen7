package GameRPGProject;

public interface Consumable {
    void use(Unit target);
    String getItemName();
}