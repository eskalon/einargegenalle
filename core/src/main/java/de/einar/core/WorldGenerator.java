package de.einar.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.google.common.eventbus.EventBus;

import de.damios.gamedev.misc.RandomUtils;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PhysicsComponent.Category;
import de.einar.ecs.components.PhysicsComponent.Mask;
import de.einar.ecs.factories.EnemyFactory;
import de.einar.ecs.factories.PlayerFactory;
import de.einar.ecs.factories.PropsFactory;
import de.einar.util.PositionConverter;
import de.einar.worldgeneration.ObstacleBlock;
import de.einar.worldgeneration.ObstacleList;

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
		shape.setAsBox(
				PositionConverter
						.toPhysicUnits(1280 * GameSession.worldLenght * 2),
				PositionConverter.toPhysicUnits(50));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0;
		fixtureDef.filter.categoryBits = Category.BOUNDARY;
		fixtureDef.filter.maskBits = Mask.BOUNDARY;

		Body body = PhysicsComponent.createBody(physicsWorld,
				BodyType.StaticBody, 1280 * GameSession.worldLenght, 100, null,
				null, true, fixtureDef);
		MassData floorMassData = new MassData();
		floorMassData.mass = 10000;
		body.setMassData(floorMassData);

		// ANCHOR
		PolygonShape shape2 = new PolygonShape();
		shape2.setAsBox(PositionConverter.toPhysicUnits(15),
				PositionConverter.toPhysicUnits(15));
		FixtureDef fixtureDef2 = new FixtureDef();
		fixtureDef2.shape = shape2;
		fixtureDef2.density = 1f;
		fixtureDef2.friction = 0;
		fixtureDef2.filter.categoryBits = Category.BOUNDARY;
		fixtureDef2.filter.maskBits = Mask.BOUNDARY;

		Body body2 = PhysicsComponent.createBody(physicsWorld,
				BodyType.DynamicBody, 20, 200,
				new Vector2(-GameSession.worldSpeed, 0), null, true,
				fixtureDef2);

		// STUFF
		ObstacleList list = new ObstacleList(35);
		for (ObstacleBlock o : list.getObstacleList()) {
			int posX = (int) o.getObstacle().getLeftBottom().x;

			if (posX < (1280 * (GameSession.worldLenght - 0.1)) && posX > 1300)
				if (RandomUtils.getRandomNumber(1, 3) <= 2) {
					EnemyFactory.createCar(ecsWorld, physicsWorld, posX,
							RandomUtils.getRandomNumber(
									GameSession.carSpeed - 30,
									GameSession.carSpeed),
							bus);
				} else
					EnemyFactory.createGrandma(ecsWorld, physicsWorld, posX,
							GameSession.worldSpeed, bus);
		}

		PropsFactory.createBackground(ecsWorld, physicsWorld);

		session.bounds = body2;
	}

}
