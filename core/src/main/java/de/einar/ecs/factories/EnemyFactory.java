package de.einar.ecs.factories;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.google.common.eventbus.EventBus;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.damios.gamedev.misc.RandomUtils;
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

	@InjectAsset("textures/car_animation.png")
	private static Texture carAnimationTexture;
	@InjectAsset("textures/granny.png")
	private static Texture grannyTexture;
	@InjectAsset("textures/granny2.png")
	private static Texture granny2Texture;

	private EnemyFactory() {
		// not used
	}

	private static void createEnemy(com.artemis.World ecsWorld,
			com.badlogic.gdx.physics.box2d.World physicsWorld,
			SpriteComponent spriteComp, Shape shape, short cat, short mask,
			int speed, int posX, int posY, CollisionListener collListener, int type) {
		Entity e = ecsWorld.createEntity();

		// PHYSICS
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0f;
		fixtureDef.filter.categoryBits = cat;
		fixtureDef.filter.maskBits = mask;
		fixtureDef.isSensor = true;

		Body body = PhysicsComponent.createBody(physicsWorld,
				BodyType.DynamicBody, posX, posY, null, e, false, fixtureDef);
		body.setLinearVelocity(PositionConverter
				.toPhysicUnits(new Vector2(-GameSession.worldSpeed, 0)));

		PhysicsComponent phyComp = new PhysicsComponent(body, collListener);

		shape.dispose();

		// ENEMY
		EnemyComponent enemyComp = new EnemyComponent();
		if (speed == GameSession.worldSpeed)
			enemyComp.setStarted(true);
		else
			enemyComp.setSpeed(-speed);
		enemyComp.type = type;

		// Add components
		e.edit().add(phyComp).add(enemyComp).add(new LerpComponent())
				.add(spriteComp);
	}

	public static void createCar(com.artemis.World ecsWorld,
			com.badlogic.gdx.physics.box2d.World physicsWorld, int posX,
			int speed, EventBus bus) {
		SpriteComponent comp = new SpriteComponent(carAnimationTexture, 0.025f,
				1, 6, 0, 0, (-carAnimationTexture.getWidth() / 6 / 2),
				(-carAnimationTexture.getHeight() / 2 + 1));

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(
				PositionConverter.toPhysicUnits(
						carAnimationTexture.getWidth() / 6 / 2 - 26),
				PositionConverter.toPhysicUnits(
						carAnimationTexture.getHeight() / 2 - 20));

		createEnemy(ecsWorld, physicsWorld, comp, shape, Category.CAR, Mask.CAR,
				speed, posX, 204, new CarPhysicsListener(bus), 1);
	}

	public static void createGrandma(com.artemis.World ecsWorld,
			com.badlogic.gdx.physics.box2d.World physicsWorld, int posX,
			int speed, EventBus bus) {
		
		int type = RandomUtils.getRandomNumber(1, 2);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(PositionConverter.toPhysicUnits(120) / 2);
		createEnemy(ecsWorld, physicsWorld,
				new SpriteComponent(type == 1 ? grannyTexture : granny2Texture, 0, 0), shape,
				Category.GRANNY, Mask.GRANNY, speed, posX, 216,
				new GrannyPhysicsListener(bus), type);
	}

}
