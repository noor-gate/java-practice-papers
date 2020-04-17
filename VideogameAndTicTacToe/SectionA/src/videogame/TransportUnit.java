package videogame;

import java.util.HashSet;
import java.util.Set;

public class TransportUnit extends Entity {

    private Set<Entity> transporting;

    public TransportUnit(String name, int lifePoints) {
        super(name, lifePoints);
        transporting = new HashSet<Entity>();
    }

    public void add(Entity e) {
        transporting.add(e);
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

        for (Entity e : transporting) {
            totalDamage += e.propagateDamage(damageAmount / 2, totalDamage);
        }

        return totalDamage;
    }

    @Override
    public int minimumStrikeToDestroy() {
        return minimumStrikeToDestroy(0, 0);
    }

    @Override
    protected int minimumStrikeToDestroy(int maxStrike, int level) {
        maxStrike = Math.max(maxStrike, (int) (lifePoints * Math.pow(2, level)));
        for (Entity e : transporting) {
            maxStrike = Math.max(maxStrike, e.minimumStrikeToDestroy(maxStrike, level++));
        }
        return maxStrike;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name + "(" + lifePoints + ")" + " transporting: [");
        String pre = "";
        for (Entity e : transporting) {
            sb.append(pre);
            pre = ", ";
            sb.append(e.toString());
        }
        sb.append("]");
        return sb.toString();
    }
}

// TODO: complete this class as part of Section A Question 4
