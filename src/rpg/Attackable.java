package rpg;

public interface Attackable {
    void takeDamage(int damage);
    boolean isAlive();
    String getName();
    int getCurrentHp();
}
