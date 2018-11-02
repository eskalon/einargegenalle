package de.einar.core;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.einar.collisions.GameContactListener;
import de.einar.ecs.systems.DebugPhysicsRenderSystem;
import de.einar.ecs.systems.EntityPhysicsHandlerSystem;
import de.einar.ecs.systems.InputProcessingSystem;
import de.einar.ecs.systems.PhysicsSystem;
import de.einar.ecs.systems.RenderPositionUpdateSystem;
import de.einar.ecs.systems.SpriteRenderSystem;
import de.einar.input.GameInputProcessor;

/**
 * This class handles all the basic game stuff.
 */
public class GameSession {
	private com.artemis.World entityWorld;
	private com.badlogic.gdx.physics.box2d.World physicsWorld;

	private SpriteRenderSystem spriteRenderSystem;
	private DebugPhysicsRenderSystem debugRenderSystem;

	/**
	 * Creates a new game session.
	 * 
	 * @param inputListener
	 */
	public GameSession(GameInputProcessor inputListener, SpriteBatch batch, OrthographicCamera camera) {
		// PHYSICS
		this.physicsWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -98.1F), true);
		this.physicsWorld.setAutoClearForces(false);
		this.physicsWorld.setContactListener(new GameContactListener());

		// ECS
		spriteRenderSystem = new SpriteRenderSystem(batch);
		debugRenderSystem = new DebugPhysicsRenderSystem(camera, batch, physicsWorld);
		WorldConfiguration config = new WorldConfigurationBuilder()
				/* Render */
				.withPassive(1, spriteRenderSystem).withPassive(1, debugRenderSystem)
				/* Physics */
				.with(new EntityPhysicsHandlerSystem(physicsWorld)).with(new PhysicsSystem(physicsWorld))
				.with(new RenderPositionUpdateSystem())
				/* Misc */
				.with(new InputProcessingSystem(inputListener)).build();
		this.entityWorld = new com.artemis.World(config);

		// Generate world
		WorldGenerator gen = new WorldGenerator();
		gen.generate(entityWorld, physicsWorld);
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
		debugRenderSystem.process();
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
