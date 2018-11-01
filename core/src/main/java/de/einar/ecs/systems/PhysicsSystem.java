package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Processes the physics.
 */
public class PhysicsSystem extends BaseEntitySystem {

	private World physicsWorld;

	public PhysicsSystem(World physicsWorld) {
		super(Aspect.exclude());
		this.physicsWorld = physicsWorld;
	}

	@Override
	protected void processSystem() {
		physicsWorld.step(this.world.getDelta(), 10, 10);
	}

}
