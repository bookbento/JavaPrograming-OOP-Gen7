package rpg;

public class Chest implements Attackable {
    private final String name;
    private int durability;
    private boolean opened;

    public Chest(String name, int durability) {
        this.name = name;
        this.durability = durability;
        this.opened = false;
    }

    @Override
    public void takeDamage(int damage) {
        if (!isAlive()) {
            System.out.println(name + " is already broken.");
            return;
        }
        durability = Math.max(0, durability - damage);
        System.out.println(name + " durability is now " + durability + ".");
        if (durability == 0 && !opened) {
            opened = true;
            System.out.println(name + " breaks open! You found a Health Potion.");
        }
    }

    @Override
    public boolean isAlive() {
        return durability > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCurrentHp() {
        return durability;
    }

    public boolean isOpened() {
        return opened;
    }
}
