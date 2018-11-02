package de.einar.ecs.factory;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.google.common.eventbus.EventBus;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.einar.collisions.CarPhysicsListener;
import de.einar.collisions.CollisionListener;
import de.einar.ecs.components.EnemyComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PhysicsComponent.Category;
import de.einar.ecs.components.PhysicsComponent.Mask;
import de.einar.ecs.components.SpriteComponent;
import de.einar.util.PositionConverter;

public class EnemyFactory {

	@InjectAsset("textures/player.png")
	private static Texture carTexture;
	@InjectAsset("textures/player.png")
	private static Texture grannyTexture;

	private EnemyFactory() {
		// not used
	}

	private static void createEnemy(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld,
			Texture text, short cat, short mask, int speed, int posX, int posY, CollisionListener collListener) {
		Entity e = ecsWorld.createEntity();

		// PHYSICS
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(PositionConverter.toPhysicUnits(text.getWidth() / 2),
				PositionConverter.toPhysicUnits(text.getHeight() / 2));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0f;
		fixtureDef.filter.categoryBits = cat;
		fixtureDef.filter.maskBits = mask;

		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.DynamicBody, 50, 200, null, e, fixtureDef);

		PhysicsComponent phyComp = new PhysicsComponent(body, collListener);

		shape.dispose();

		// TEXTURE
		SpriteComponent spriteComp = new SpriteComponent(text, 0, 0);

		// ENEMY
		EnemyComponent enemyComp = new EnemyComponent();
		enemyComp.setSpeed(speed);

		// Add components
		e.edit().add(phyComp).add(enemyComp).add(spriteComp);
	}

	public static void createCar(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld,
			int posX, int posY, int speed, EventBus bus) {
		createEnemy(ecsWorld, physicsWorld, carTexture, Category.CAR, Mask.CAR, speed, posX, posY,
				new CarPhysicsListener(bus));
	}

}
