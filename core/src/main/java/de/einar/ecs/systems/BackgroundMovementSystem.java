package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;

import de.einar.core.GameSession;
import de.einar.ecs.components.BackgroundComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.SpriteComponent;

/**
 * Syncs the position in the {@linkplain PhysicsComponent physics engine} to the
 * {@linkplain SpriteComponent render} position.
 */
public class BackgroundMovementSystem extends IteratingSystem {

	@Wire
	ComponentMapper<SpriteComponent> spriteMapper;

	public BackgroundMovementSystem() {
		super(Aspect.all(SpriteComponent.class, BackgroundComponent.class));
	}

	@Override
	protected void process(int id) {
		SpriteComponent spriteComponent = spriteMapper.get(id);

		spriteComponent.setPosX(spriteComponent.getPosX() - (int) (world.delta * GameSession.backgroundSpeed));
		// spriteComponent.setPosY(spriteComponent.getPosY());
	}
}
