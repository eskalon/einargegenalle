package de.einar.collisions;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;

public interface CollisionListener {

	/**
	 * Called when the entity {@code self} comes in contact with the entity
	 * {@code otherEntity}.
	 * 
	 * @param contact
	 * @param self
	 *            The entity whose listener this is.
	 * @param otherEntity
	 */
	public default void beginContact(Contact contact, Entity self,
			Entity otherEntity) {
	}

	/**
	 * Called when the entity {@code self} ends contact with the entity
	 * {@code otherEntity}.
	 * 
	 * @param contact
	 * @param self
	 *            The entity whose listener this is.
	 * @param otherEntity
	 */
	public default void endContact(Contact contact, Entity self,
			Entity otherEntity) {
	}

}
