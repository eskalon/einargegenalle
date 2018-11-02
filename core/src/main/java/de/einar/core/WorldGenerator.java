package de.einar.core;

import de.einar.ecs.factory.PlayerFactory;

public class WorldGenerator {

	public WorldGenerator() {
		//
	}

	public void generate(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld) {
		// TODO das Spiel aufsetzen, d.h. alle Entities zum ECS hinzufügen
		PlayerFactory.createPlayer(ecsWorld, physicsWorld);
	}

}
