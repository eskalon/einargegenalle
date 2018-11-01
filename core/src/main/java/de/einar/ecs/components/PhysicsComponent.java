package de.einar.ecs.components;

import com.artemis.Component;
import com.artemis.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import de.einar.collisions.CollisionListener;

public class PhysicsComponent extends Component {

	private Body body;
	private CollisionListener collisionListener;

	public PhysicsComponent() {
		// default public constructor
	}

	public PhysicsComponent(Body body) {
		this(body, null);
	}

	public PhysicsComponent(Body body, CollisionListener collisionListener) {
		this.body = body;
		this.collisionListener = collisionListener;
	}

	public static Body createBody(World physicsWorld, BodyType bt, float posX,
			float posY, Vector2 velocity, Entity e, FixtureDef... defs) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = bt;
		bodyDef.position.set(posX, posY);
		Body body = physicsWorld.createBody(bodyDef);
		body.setUserData(e);

		for (FixtureDef f : defs) {
			body.createFixture(f);
		}

		if (velocity != null)
			body.setLinearVelocity(velocity);

		body.setFixedRotation(true);

		return body;
	}

	public CollisionListener getCollisionListener() {
		return collisionListener;
	}

	public void stopMovement() {
		body.setLinearVelocity(0, 0);
	}

	/**
	 * Sets the velocity of the entity.
	 * 
	 * @param dir
	 *            The direction (is automatically normalized).
	 * @param scalar
	 *            The actual speed.
	 */
	public void setVel(Vector2 dir, float scalar) {
		body.setLinearVelocity(dir.nor().scl(scalar));
	}

	/**
	 * This applies a force to the center of the entities body.
	 * 
	 * @param force
	 *            the force vector, usually in Newtons (N).
	 */
	public void applyForce(Vector2 force) {
		body.applyForceToCenter(force, true);
	}

	/**
	 * This applies a linear impulse to the center of the entities body.
	 * 
	 * @param impulse
	 *            The impulse vector, usually in N-seconds or kg-m/s.
	 */
	public void applyImpulseToCenter(Vector2 impulse) {
		body.applyLinearImpulse(impulse, body.getLocalCenter(), true);
	}

	/**
	 * @return A copy of the position vector.
	 */
	public Vector2 getPos() {
		return body.getPosition().cpy();
	}

	/**
	 * @return A copy of the velocity vector.
	 */
	public Vector2 getVel() {
		return body.getLinearVelocity().cpy();
	}

	public Body getBody() {
		return body;
	}

	public static class Category {
		public static final short BOUNDARY = 1;
	}

	public static class Mask {
		public static final short BOUNDARY = (short) 0xFFFF;
		/*
		 * public static final short PLAYER = Category.BOUNDARY |
		 * Category.PLAYER | Category.BAG | Category.ENEMY | Category.BULLET;
		 */
	}

}
