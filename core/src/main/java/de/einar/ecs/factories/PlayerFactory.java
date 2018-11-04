package de.einar.ecs.factories;

import com.artemis.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.einar.ecs.components.LerpComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.PhysicsComponent.Category;
import de.einar.ecs.components.PhysicsComponent.Mask;
import de.einar.ecs.components.PlayerComponent;
import de.einar.ecs.components.SpriteComponent;
import de.einar.util.PositionConverter;

public class PlayerFactory {

	@InjectAsset("textures/player_animation.png")
	private static Texture playerAnimationTexture;

	private PlayerFactory() {
		// not used
	}

	public static Entity createPlayer(com.artemis.World ecsWorld, com.badlogic.gdx.physics.box2d.World physicsWorld) {
		Entity e = ecsWorld.createEntity();

		// PHYSICS
		CircleShape shape = new CircleShape();
		shape.setRadius(PositionConverter.toPhysicUnits(playerAnimationTexture.getHeight() + 2) / 2);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1f;
		fixtureDef.friction = 0f;
		fixtureDef.filter.categoryBits = Category.PLAYER;
		fixtureDef.filter.maskBits = Mask.PLAYER;

		Body body = PhysicsComponent.createBody(physicsWorld, BodyType.DynamicBody, 150, 200, null, e, true,
				fixtureDef);

		PhysicsComponent phyComp = new PhysicsComponent(body);

		shape.dispose();
		// TEXTURE
		SpriteComponent spriteComp = new SpriteComponent(playerAnimationTexture, (1f / 9f), 1, 8, 0, 0,
				(-playerAnimationTexture.getHeight() / 2), (-playerAnimationTexture.getWidth() / 8 / 2 - 1));
		spriteComp.setLayer(2);

		// PLAYER
		PlayerComponent playerComp = new PlayerComponent();

		// Add components
		e.edit().add(phyComp).add(new LerpComponent()).add(playerComp).add(spriteComp);

		return e;
	}

}
