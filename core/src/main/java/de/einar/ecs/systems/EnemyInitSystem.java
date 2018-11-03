package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.google.common.eventbus.EventBus;

import de.damios.gamedev.misc.RandomUtils;
import de.einar.ecs.components.EnemyComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.events.JamSoundEvent;
import de.einar.util.PositionConverter;

/**
 * Makes the cars move at their real speed as soon as they are seen by the
 * player.
 */
public class EnemyInitSystem extends IteratingSystem {

	public Body bounds;
	private EventBus bus;

	@Wire
	ComponentMapper<PhysicsComponent> physicsMapper;
	@Wire
	ComponentMapper<EnemyComponent> enemyMapper;

	public EnemyInitSystem(EventBus bus) {
		super(Aspect.all(EnemyComponent.class, PhysicsComponent.class));
		this.bus = bus;
	}

	@Override
	protected void process(int id) {
		PhysicsComponent phy = physicsMapper.get(id);
		int posEnemyX = PositionConverter.toPixels(phy.getPos().x);

		EnemyComponent ene = enemyMapper.get(id);

		if (!ene.isStarted() && posEnemyX < 1281) {
			ene.setStarted(true);
			phy.setVel(PositionConverter.toPhysicUnits(new Vector2(ene.getSpeed(), 0)));

			if (RandomUtils.rollTheDice(4)) {
				bus.post(new JamSoundEvent());
			}
		}
	}
}
