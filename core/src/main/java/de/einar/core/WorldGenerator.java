package de.einar.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.google.common.eventbus.EventBus;

import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PhysicsComponent.Category;
import de.einar.ecs.components.PhysicsComponent.Mask;
import de.einar.ecs.factory.EnemyFactory;
import de.einar.ecs.factory.PlayerFactory;
import de.einar.util.PositionConverter;

public class WorldGenerator {

	public WorldGenerator() {
		//
	}

	public void generate(GameSession session, com.artemis.World ecsWorld,
			com.badlogic.gdx.physics.box2d.World physicsWorld, EventBus bus) {
		// TODO das Spiel aufsetzen, d.h. alle Entities zum ECS hinzufügen

		// PLAYER
		PlayerFactory.createPlayer(ecsWorld, physicsWorld);

		// FLOOR
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(PositionConverter.toPhysicUnits(1280 * 6), PositionConverter.toPhysicUnits(50));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0;
		fixtureDef.filter.categoryBits = Category.BOUNDARY;
		fixtureDef.filter.maskBits = Mask.BOUNDARY;

		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.StaticBody, 1280 * 3, 100, null, null,
				fixtureDef);
		MassData floorMassData = new MassData();
		floorMassData.mass = 10000;
		body.setMassData(floorMassData);

		// ANCHOR
		PolygonShape shape2 = new PolygonShape();
		shape2.setAsBox(PositionConverter.toPhysicUnits(15), PositionConverter.toPhysicUnits(15));
		FixtureDef fixtureDef2 = new FixtureDef();
		fixtureDef2.shape = shape2;
		fixtureDef2.density = 1f;
		fixtureDef2.friction = 0;
		fixtureDef2.filter.categoryBits = Category.BOUNDARY;
		fixtureDef2.filter.maskBits = Mask.BOUNDARY;

		Body body2 = PhysicsComponent.createBody(physicsWorld, BodyType.DynamicBody, 20, 200,
				new Vector2(GameSession.worldSpeed, 0), null, fixtureDef2);

		// ENEMY
		EnemyFactory.createCar(ecsWorld, physicsWorld, 1600, 500, 50, bus);

		session.bounds = body2;
	}

}
