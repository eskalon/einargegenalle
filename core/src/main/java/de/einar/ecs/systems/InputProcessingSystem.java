package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PlayerComponent;
import de.einar.input.GameInputProcessor;
import de.einar.util.PositionConverter;

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

		if (inputProcessor.isSpacePressed()
				&& Math.abs(phy.getVel().y) < 0.000001) {
			phy.applyImpulseToCenter(
					new Vector2(0, PositionConverter.toPhysicUnits(510)));
		}
	}
}
