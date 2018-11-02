package de.einar.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.factory.PlayerFactory;
import de.einar.util.PositionConverter;

public class WorldGenerator {

	public WorldGenerator() {
		//
	}

	public void generate(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld) {
		// TODO das Spiel aufsetzen, d.h. alle Entities zum ECS hinzufügen
		PlayerFactory.createPlayer(ecsWorld, physicsWorld);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(PositionConverter.toPhysicUnits(1280 * 6), PositionConverter.toPhysicUnits(50));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0;

		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.StaticBody, 1280 * 3, 100, null, null,
				fixtureDef);
		MassData floorMassData = new MassData();
		floorMassData.mass = 10000;
		body.setMassData(floorMassData);
	}

}
