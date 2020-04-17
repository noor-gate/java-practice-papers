package videogame;

public abstract class Entity {
	
	protected String name;
	protected int lifePoints = 0;

	public Entity(String name, int lifePoints) {
		assert(lifePoints >= 0);
		this.name = name;
		this.lifePoints = lifePoints;
	}

	public final boolean isAlive() {
		return lifePoints > 0;
	}
	
	public final int applySpell(SpellCaster spellCaster) {
		return propagateDamage(spellCaster.getStrength());
	}
	
	protected abstract int propagateDamage(int damageAmount);

	protected abstract int propagateDamage(int damageAmount, int totalDamage);

	public abstract int minimumStrikeToDestroy();

	protected abstract int minimumStrikeToDestroy(int maxStrike, int level);
}
