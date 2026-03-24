package GameRPGProject;

public interface Damageable {
    void takeDamage(int rawDamage);
    void takeDamage(int rawDamage, boolean ignoreDefense); // Overloading
    boolean isAlive();
    String getName();
}