package de.einar.ecs.factory;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PlayerComponent;
import de.einar.ecs.components.SpriteComponent;
import de.einar.util.PositionConverter;

public class PlayerFactory {

	@InjectAsset("textures/player.png")
	private static Texture playerTexture;

	private PlayerFactory() {
		// not used
	}

	public static void createPlayer(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld) {
		Entity e = ecsWorld.createEntity();

		// PHYSICS
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(PositionConverter.toPhysicUnits(playerTexture.getWidth() / 2),
				PositionConverter.toPhysicUnits(playerTexture.getHeight() / 2));
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0f;
		//fixtureDef.restitution = 1.1F;

		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.DynamicBody, 50, 200, new Vector2(145, 0), e,
				fixtureDef);

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
