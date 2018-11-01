package de.einar.collisions;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.einar.ecs.components.PhysicsComponent;

/**
 * Distributes contact events to the respective {@linkplain CollisionListener
 * listeners} that are registered within the {@link PhysicsComponent} of an
 * entity.
 */
public class GameContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Entity a = (Entity) contact.getFixtureA().getBody().getUserData();
		Entity b = (Entity) contact.getFixtureB().getBody().getUserData();

		notifyBeginContactListener(contact, a, b);
	}

	private void notifyBeginContactListener(Contact contact, Entity a,
			Entity b) {
		CollisionListener c1 = getCollisionListenerOfEntity(a);
		CollisionListener c2 = getCollisionListenerOfEntity(b);

		if (c1 != null) {
			c1.beginContact(contact, a, b);
		}

		if (c2 != null) {
			c2.beginContact(contact, b, a);
		}
	}

	@Override
	public void endContact(Contact contact) {
		Entity a = (Entity) contact.getFixtureA().getBody().getUserData();
		Entity b = (Entity) contact.getFixtureB().getBody().getUserData();

		notifyEndContactListener(contact, a, b);
	}

	private void notifyEndContactListener(Contact contact, Entity a, Entity b) {
		CollisionListener c1 = getCollisionListenerOfEntity(a);
		CollisionListener c2 = getCollisionListenerOfEntity(b);

		if (c1 != null) {
			c1.endContact(contact, a, b);
		}

		if (c2 != null) {
			c2.endContact(contact, b, a);
		}
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
	}

	private CollisionListener getCollisionListenerOfEntity(Entity e) {
		if (e == null)
			return null;

		PhysicsComponent physicsComponent = e
				.getComponent(PhysicsComponent.class);
		if (physicsComponent == null)
			return null;

		return physicsComponent.getCollisionListener();
	}

}
