package videogame;

public class Magician extends Entity implements SpellCaster {

    public Magician(String name, int lifePoints) {
        super(name, lifePoints);
    }

    @Override
    protected int propagateDamage(int damageAmount) {
        return propagateDamage(damageAmount, damageAmount);
    }

    @Override
    protected int propagateDamage(int damageAmount, int totalDamage) {
        if (damageAmount < 0) {
            throw new IllegalArgumentException();
        }
        lifePoints -= damageAmount;
        if (lifePoints < 0) {
            lifePoints = 0;
        }
        return totalDamage;
    }

    @Override
    public int minimumStrikeToDestroy() {
        return lifePoints;
    }

    @Override
    protected int minimumStrikeToDestroy(int maxStrike, int level) {
        return Math.max(maxStrike, (int) (lifePoints * Math.pow(2, level)));
    }

    @Override
    public int getStrength() {
        return lifePoints * 2;
    }

    @Override
    public String toString() {
        return name + "(" + lifePoints + ")";
    }
}
