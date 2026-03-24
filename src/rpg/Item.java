package rpg;

public abstract class Item implements Usable {
    protected final String itemName;

    public Item(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public String getItemName() {
        return itemName;
    }
}
