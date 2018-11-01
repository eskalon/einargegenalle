package de.einar.ecs.systems;

import java.util.LinkedList;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.physics.box2d.World;

import de.einar.ecs.components.PhysicsComponent;

/**
 * Takes care of translating entity changes (addition/removal) to the physics
 * engine.
 */
public class EntityPhysicsHandlerSystem extends EntityProcessingSystem {

	private LinkedList<PhysicsComponent> componentsOfRemovedEntities = new LinkedList<PhysicsComponent>();
	private World physicsWorld;

	public EntityPhysicsHandlerSystem(World physicsWorld) {
		super(Aspect.all(PhysicsComponent.class));
		this.physicsWorld = physicsWorld;
	}

	@Override
	public void inserted(Entity e) {
		PhysicsComponent physicsComponent = e
				.getComponent(PhysicsComponent.class);
		physicsComponent.getBody().setActive(true);
	}

	@Override
	public void removed(Entity e) {
		PhysicsComponent physicsComponent = e
				.getComponent(PhysicsComponent.class);
		if (physicsComponent == null)
			return;

		componentsOfRemovedEntities.add(physicsComponent);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void begin() {
		for (PhysicsComponent p : componentsOfRemovedEntities) {
			p.getBody().setActive(false);
			p.getBody().setUserData(null);
			physicsWorld.destroyBody(p.getBody());
		}
		componentsOfRemovedEntities.clear();
	}

	@Override
	protected void process(Entity e) {
	}

}
