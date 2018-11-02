package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import de.einar.ecs.components.EnemyComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.SpriteComponent;
import de.einar.input.GameInputProcessor;
import de.einar.util.PositionConverter;

/**
 * Takes care of processing the player input.
 */
public class InitializeEnemyMovementSystem extends IteratingSystem {

	@Wire
	ComponentMapper<SpriteComponent> spriteMapper;
	@Wire
	ComponentMapper<PhysicsComponent> physicsMapper;
	@Wire
	ComponentMapper<EnemyComponent> enemyMapper;
	private GameInputProcessor inputProcessor;

	public InitializeEnemyMovementSystem(GameInputProcessor inputProcessor) {
		super(Aspect.all(EnemyComponent.class));
		this.inputProcessor = inputProcessor;
	}

	@Override
	protected void process(int id) {
		PhysicsComponent phy = physicsMapper.get(id);
		EnemyComponent enemyComponent = enemyMapper.get(id);
		SpriteComponent spriteComp = spriteMapper.get(id);

		//if (spriteComp.getPosX() < ) {
			phy.setVel(PositionConverter.toPhysicUnits(new Vector2(-enemyComponent.getSpeed(), 0)));
		//}
	}
}
