package de.einar.collisions;

import com.artemis.Entity;
import com.badlogic.gdx.physics.box2d.Contact;
import com.google.common.eventbus.EventBus;
import de.einar.ecs.components.PlayerComponent;
import de.einar.ecs.components.PowerUpComponent;
import de.einar.events.PowerUpEvent;

public class PowerUpCollisionListener implements CollisionListener {

	private EventBus bus;

	public PowerUpCollisionListener(EventBus bus) {
		this.bus = bus;
	}

	@Override
	public void beginContact(Contact contact, Entity powerUp, Entity target) {
		target.getComponent(PlayerComponent.class).currentPowerUpType = powerUp.getComponent(PowerUpComponent.class).type;
		target.getComponent(PlayerComponent.class).pickUpTime = System.currentTimeMillis();
		switch (target.getComponent(PlayerComponent.class).currentPowerUpType) {
			case HELM:

				break;
			case JUMP:

				break;
			case SPEED:

				break;
		}

		bus.post(new PowerUpEvent());
	}
}
