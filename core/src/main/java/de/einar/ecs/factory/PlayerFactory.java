package de.einar.ecs.factory;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PlayerComponent;
import de.einar.ecs.components.SpriteComponent;

public class PlayerFactory {

	@InjectAsset("textures/player.png")
	private static Texture playerTexture;

	private PlayerFactory() {
		// not used
	}

	public static void createPlayer(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld) {
		Entity e = ecsWorld.createEntity();

		// PHYSICS
		// FixtureDef fixtureDef = new FixtureDef();
		// fixtureDef.shape = shape;
		// fixtureDef.density = 1f;

		// Fixture fixture = body.createFixture(fixtureDef);

		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.DynamicBody, 100, 100, null, e);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(playerTexture.getWidth() / 2, playerTexture.getHeight() / 2);

		PhysicsComponent phyComp = new PhysicsComponent(body);

		shape.dispose();

		// TEXTURE
		SpriteComponent spriteComp = new SpriteComponent(playerTexture, 0, 0);

		// PLAYER
		PlayerComponent playerComp = new PlayerComponent();

		// Add components
		e.edit().add(phyComp).add(playerComp).add(spriteComp);
	}

}
