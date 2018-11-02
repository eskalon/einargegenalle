package de.einar.core;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import de.einar.collisions.GameContactListener;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.factories.PropsFactory;
import de.einar.ecs.systems.DebugPhysicsRenderSystem;
import de.einar.ecs.systems.EnemyInitSystem;
import de.einar.ecs.systems.EntityPhysicsHandlerSystem;
import de.einar.ecs.systems.InputProcessingSystem;
import de.einar.ecs.systems.PhysicsSystem;
import de.einar.ecs.systems.RenderPositionUpdateSystem;
import de.einar.ecs.systems.SpriteRenderSystem;
import de.einar.ecs.systems.WinSystem;
import de.einar.events.GrannyContatcEvent;
import de.einar.events.PlayerDeathEvent;
import de.einar.input.GameInputProcessor;
import de.einar.util.PositionConverter;

/**
 * This class handles all the basic game stuff.
 */
public class GameSession {
	public static int backgroundSpeed = 160;
	public static int worldSpeed = 405;
	public static int carSpeed = 510;
	public Body bounds;

	private com.artemis.World entityWorld;
	private com.badlogic.gdx.physics.box2d.World physicsWorld;

	private SpriteRenderSystem spriteRenderSystem;
	private DebugPhysicsRenderSystem debugRenderSystem;
	private PhysicsSystem physicsSystem;

	/**
	 * Creates a new game session.
	 * 
	 * @param inputListener
	 */
	public GameSession(GameInputProcessor inputListener, SpriteBatch batch, OrthographicCamera gameCamera,
			OrthographicCamera debugCamera, EventBus bus) {
		WinSystem winS = new WinSystem(bus);

		// PHYSICS
		this.physicsWorld = new com.badlogic.gdx.physics.box2d.World(
				PositionConverter.toPhysicUnits(new Vector2(0, -2000F)), true);
		this.physicsWorld.setAutoClearForces(false);
		this.physicsWorld.setContactListener(new GameContactListener());

		// ECS
		spriteRenderSystem = new SpriteRenderSystem(gameCamera, batch);
		debugRenderSystem = new DebugPhysicsRenderSystem(gameCamera, debugCamera, physicsWorld);
		EnemyInitSystem initS = new EnemyInitSystem();
		physicsSystem = new PhysicsSystem(physicsWorld);
		WorldConfiguration config = new WorldConfigurationBuilder()
				/* Render */
				.withPassive(1, spriteRenderSystem).withPassive(1, debugRenderSystem)
				/* Physics */
				.with(new EntityPhysicsHandlerSystem(physicsWorld)).with(physicsSystem)
				.with(new RenderPositionUpdateSystem())
				/* Win */
				.with(winS)
				/* Misc */
				.with(new InputProcessingSystem(inputListener)).with(initS).build();
		this.entityWorld = new com.artemis.World(config);

		// Generate world
		WorldGenerator gen = new WorldGenerator();
		gen.generate(this, entityWorld, physicsWorld, bus);

		winS.setBounds(bounds);
		initS.bounds = bounds;
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

	@Subscribe
	public void onDeathEvent(PlayerDeathEvent ev) {
		physicsSystem.setEnabled(false);
	}

	@Subscribe
	public void onGrannyContactEvent(GrannyContatcEvent ev) {
		PhysicsComponent comp = ev.granny.getComponent(PhysicsComponent.class);
		PropsFactory.createDeadGranny(entityWorld, physicsWorld, comp.getPos(), comp.getVel());
		ev.granny.deleteFromWorld();
	}

}
