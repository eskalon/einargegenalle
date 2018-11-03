package de.einar.ecs.factories;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.google.common.eventbus.EventBus;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.einar.collisions.CarPhysicsListener;
import de.einar.collisions.CollisionListener;
import de.einar.collisions.GrannyPhysicsListener;
import de.einar.core.GameSession;
import de.einar.ecs.components.EnemyComponent;
import de.einar.ecs.components.LerpComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PhysicsComponent.Category;
import de.einar.ecs.components.PhysicsComponent.Mask;
import de.einar.ecs.components.SpriteComponent;
import de.einar.util.PositionConverter;

public class EnemyFactory {

	@InjectAsset("textures/car.png")
	private static Texture carTexture;
	@InjectAsset("textures/granny.png")
	private static Texture grannyTexture;

	private EnemyFactory() {
		// not used
	}

	private static void createEnemy(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld,
			Texture text, short cat, short mask, int speed, int posX, CollisionListener collListener) {
		Entity e = ecsWorld.createEntity();

		// PHYSICS
		CircleShape shape = new CircleShape();
		shape.setRadius(PositionConverter.toPhysicUnits(Math.max(text.getWidth(), text.getHeight()) / 2 -5));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0f;
		fixtureDef.filter.categoryBits = cat;
		fixtureDef.filter.maskBits = mask;
		fixtureDef.isSensor = true;

		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.DynamicBody, posX, 216, null, e, false,
				fixtureDef);
		body.setLinearVelocity(PositionConverter.toPhysicUnits(new Vector2(-GameSession.worldSpeed, 0)));

		PhysicsComponent phyComp = new PhysicsComponent(body, collListener);

		shape.dispose();

		// TEXTURE
		SpriteComponent spriteComp = new SpriteComponent(text, 0, 0);

		// ENEMY
		EnemyComponent enemyComp = new EnemyComponent();
		if (speed == GameSession.worldSpeed)
			enemyComp.setStarted(true);
		else
			enemyComp.setSpeed(-speed);

		// Add components
		e.edit().add(phyComp).add(enemyComp).add(new LerpComponent()).add(spriteComp);
	}

	public static void createCar(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld,
			int posX, int speed, EventBus bus) {
		createEnemy(ecsWorld, physicsWorld, carTexture, Category.CAR, Mask.CAR, speed, posX,
				new CarPhysicsListener(bus));
	}

	public static void createGrandma(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld,
			int posX, int speed, EventBus bus) {
		createEnemy(ecsWorld, physicsWorld, grannyTexture, Category.GRANNY, Mask.GRANNY, speed, posX,
				new GrannyPhysicsListener(bus));
	}

}
