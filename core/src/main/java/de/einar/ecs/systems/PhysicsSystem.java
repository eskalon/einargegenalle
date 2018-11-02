package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.systems.IntervalSystem;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Processes the physics.
 */
public class PhysicsSystem extends IntervalSystem {

	private World physicsWorld;

	public PhysicsSystem(World physicsWorld) {
		super(Aspect.exclude(), 1 / 60f);
		this.physicsWorld = physicsWorld;
	}

	@Override
	protected void processSystem() {
		physicsWorld.step(1 / 60f, 10, 10);
	}

}
