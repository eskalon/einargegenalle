package de.einar.ecs.factories;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.einar.ecs.components.BackgroundComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PhysicsComponent.Category;
import de.einar.ecs.components.PhysicsComponent.Mask;
import de.einar.ecs.components.SpriteComponent;
import de.einar.util.PositionConverter;

public class PropsFactory {

	@InjectAsset("textures/player.png")
	private static Texture deadGrannyTexture;
	@InjectAsset("textures/background.png")
	private static Texture background1Texture;
	@InjectAsset("textures/background.png")
	private static Texture background2Texture;

	private PropsFactory() {
		// not used
	}

	public static void createBackground(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld) {
		Entity e = ecsWorld.createEntity();

		// TEXTURE
		SpriteComponent spriteComp = new SpriteComponent(background1Texture, 0, 0, 0, 0);
		spriteComp.setLayer(-1);

		// Add components
		e.edit().add(new BackgroundComponent()).add(spriteComp);

		Entity e2 = ecsWorld.createEntity();

		// TEXTURE
		SpriteComponent spriteComp2 = new SpriteComponent(background2Texture, background1Texture.getWidth(), 0, 0, 0);
		spriteComp2.setLayer(-1);

		// Add components
		e2.edit().add(new BackgroundComponent()).add(spriteComp2);
	}

	public static void createDeadGranny(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld,
			Vector2 pos, Vector2 vel) {
		createDeadGranny(ecsWorld, physicsWorld, pos, vel, deadGrannyTexture);
	}

	public static Entity createDeadGranny(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld,
			Vector2 pos, Vector2 vel, Texture texture) {
		Entity e = ecsWorld.createEntity();

		// PHYSICS
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(PositionConverter.toPhysicUnits(texture.getWidth() / 2),
				PositionConverter.toPhysicUnits(texture.getHeight() / 2));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0f;
		fixtureDef.filter.categoryBits = Category.PROPS;
		fixtureDef.filter.maskBits = Mask.PROPS;

		pos = PositionConverter.toPixels(pos);
		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.DynamicBody, (int) pos.x, (int) pos.y,
				PositionConverter.toPixels(vel), e, true, fixtureDef);

		PhysicsComponent phyComp = new PhysicsComponent(body);
		shape.dispose();

		// TEXTURE
		SpriteComponent spriteComp = new SpriteComponent(texture, 0, 0);

		// Add components
		e.edit().add(phyComp).add(spriteComp);

		return e;

	}

}
