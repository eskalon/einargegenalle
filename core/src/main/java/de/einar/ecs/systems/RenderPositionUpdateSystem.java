package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.SpriteComponent;
import de.einar.util.PositionConverter;

/**
 * Syncs the position in the {@linkplain PhysicsComponent physics engine} to the
 * {@linkplain SpriteComponent render} position.
 */
public class RenderPositionUpdateSystem extends IteratingSystem {

	@Wire
	ComponentMapper<SpriteComponent> spriteMapper;
	@Wire
	ComponentMapper<PhysicsComponent> physicsMapper;

	public RenderPositionUpdateSystem() {
		super(Aspect.all(SpriteComponent.class, PhysicsComponent.class));
	}

	@Override
	protected void process(int id) {
		Vector2 pos = physicsMapper.get(id).getPos();
		SpriteComponent renderPositionComponent = spriteMapper.get(id);
		renderPositionComponent.setPosX(PositionConverter.toPixels(pos.x));
		renderPositionComponent.setPosY(PositionConverter.toPixels(pos.y));
	}
}
