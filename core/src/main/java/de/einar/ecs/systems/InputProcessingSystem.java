package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;

import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PlayerComponent;
import de.einar.input.GameInputProcessor;

/**
 * Takes care of processing the player input.
 */
public class InputProcessingSystem extends IteratingSystem {
	@Wire
	ComponentMapper<PhysicsComponent> physicsMapper;
	private GameInputProcessor inputProcessor;

	public InputProcessingSystem(GameInputProcessor inputProcessor) {
		super(Aspect.all(PlayerComponent.class));
		this.inputProcessor = inputProcessor;
	}

	@Override
	protected void process(int id) {
		PhysicsComponent phy = physicsMapper.get(id);

		// TODO
		// phy.setVel(inputProcessor.getDir(), PlayerStats.VELOCITY * (1 -
		// (armor.weight + velocityModifier)));
	}
}
