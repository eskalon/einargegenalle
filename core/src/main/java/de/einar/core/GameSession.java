package de.einar.core;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.einar.collisions.GameContactListener;
import de.einar.ecs.systems.InputProcessingSystem;
import de.einar.ecs.systems.SpriteRenderSystem;
import de.einar.input.GameInputProcessor;

/**
 * This class handles all the basic game stuff.
 */
public class GameSession {
	private com.artemis.World entityWorld;
	private com.badlogic.gdx.physics.box2d.World physicsWorld;

	private SpriteRenderSystem spriteRenderSystem;

	/**
	 * Creates a new game session.
	 * 
	 * @param inputListener
	 */
	public GameSession(GameInputProcessor inputListener, SpriteBatch batch) {
		// ECS
		spriteRenderSystem = new SpriteRenderSystem(batch);
		WorldConfiguration config = new WorldConfigurationBuilder()
				.withPassive(1, spriteRenderSystem)
				.with(new InputProcessingSystem(inputListener)).build();
		this.entityWorld = new com.artemis.World(config);

		// PHYSICS
		this.physicsWorld = new com.badlogic.gdx.physics.box2d.World(
				new Vector2(0, 0), true);
		this.physicsWorld.setAutoClearForces(false);
		this.physicsWorld.setContactListener(new GameContactListener());

		// TODO das Spiel aufsetzen, d.h. alle Entities zum ECS hinzufügen
	}

	/**
	 * Updates the game session.
	 * 
	 * @param delta
	 */
	public void update(float delta) {
		entityWorld.setDelta(delta);
		entityWorld.process();
	}

	public void render(float delta) {
		spriteRenderSystem.process();
	}

	public void renderDebug() {
		// debugRenderSystem.render(physicsWorld,
		// debugCamera.combined.scl(PositionConverter.toPixels(1)));
	}

	public void dispose() {
		entityWorld.dispose();
		physicsWorld.dispose();
	}

	/**
	 * @return The ECS's world.
	 */
	public World getEntityWorld() {
		return entityWorld;
	}

}
