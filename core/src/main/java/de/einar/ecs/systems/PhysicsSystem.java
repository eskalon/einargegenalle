package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IntervalIteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import de.einar.ecs.components.LerpComponent;
import de.einar.ecs.components.PhysicsComponent;

/**
 * Processes the physics.
 */
public class PhysicsSystem extends IntervalIteratingSystem {

	public static final float interval = 1 / 75f;

	@Wire
	ComponentMapper<LerpComponent> lerpMapper;
	@Wire
	ComponentMapper<PhysicsComponent> physicsMapper;

	private World physicsWorld;

	public PhysicsSystem(World physicsWorld) {
		super(Aspect.all(LerpComponent.class, PhysicsComponent.class),
				interval);
		this.physicsWorld = physicsWorld;
	}

	@Override
	protected void begin() {
		physicsWorld.step(interval, 10, 10);
	}

	@Override
	protected void process(int id) {
		LerpComponent lerpC = lerpMapper.get(id);
		Vector2 pos = physicsMapper.get(id).getPos();

		lerpC.targetPosX = pos.x;
		lerpC.targetPosY = pos.y;
		lerpC.intervall = 0;
	}

}
