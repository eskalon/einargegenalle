package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import de.einar.util.PositionConverter;

/**
 * This system renders all entities at their position in the physics world.
 */
public class DebugPhysicsRenderSystem extends BaseEntitySystem {

	private World physicsWorld;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera gameCamera;
	private OrthographicCamera debugCamera;

	public DebugPhysicsRenderSystem(OrthographicCamera gameCamera,
			OrthographicCamera debugCamera, World physicsWorld) {
		super(Aspect.exclude());
		this.gameCamera = gameCamera;
		this.debugCamera = debugCamera;
		this.physicsWorld = physicsWorld;
		this.debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processSystem() {
		debugCamera.position.set(gameCamera.position);
		debugCamera.update();

		debugRenderer.render(physicsWorld,
				debugCamera.combined.scl(PositionConverter.PIX_TO_PHY_FACTOR));
	}

}
