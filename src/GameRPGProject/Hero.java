package GameRPGProject;
import java.awt.Color;

public class Hero extends Unit {
    public Hero(String name, String jobClass, int hp, int def, int dmg, String imgPath, Color color) {
        super(name, jobClass, hp, def, dmg, imgPath, color);
    }

    @Override
    public String getUnitType() {
        return "Hero";
    }
}