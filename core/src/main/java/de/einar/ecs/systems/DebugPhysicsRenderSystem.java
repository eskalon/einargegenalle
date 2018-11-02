package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import de.einar.util.PositionConverter;

/**
 * This system renders all entities at their position in the physics world.
 */
public class DebugPhysicsRenderSystem extends BaseEntitySystem {

	private World physicsWorld;
	private SpriteBatch batch;
	private Box2DDebugRenderer debugRenderer;
	private Matrix4 debugMatrix;

	public DebugPhysicsRenderSystem(OrthographicCamera camera, SpriteBatch batch, World physicsWorld) {
		super(Aspect.exclude());
		this.debugMatrix = new Matrix4(camera.combined.scl(PositionConverter.toPixels(1)));
		this.batch = batch;
		this.physicsWorld = physicsWorld;
		this.debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	@Override
	protected void processSystem() {
		batch.begin();
		debugRenderer.render(physicsWorld, debugMatrix);
		batch.end();
	}

}
