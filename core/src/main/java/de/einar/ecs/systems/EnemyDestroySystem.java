package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;

import de.einar.ecs.components.PhysicsComponent;
import de.einar.util.PositionConverter;

/**
 * Removes enemies outside of the playable area.
 */
public class EnemyDestroySystem extends IteratingSystem {

	public Body bounds;

	@Wire
	ComponentMapper<PhysicsComponent> physicsMapper;

	public EnemyDestroySystem() {
		super(Aspect.all(PhysicsComponent.class));
	}

	@Override
	protected void process(int id) {
		PhysicsComponent phy = physicsMapper.get(id);
		int posEnemyX = PositionConverter.toPixels(phy.getPos().x);

		if (posEnemyX < -500) {
			world.delete(id);
		}
	}
}
