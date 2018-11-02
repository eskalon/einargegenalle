package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;

import de.einar.ecs.components.PlayerComponent;
import de.einar.ecs.components.SpriteComponent;

public class CameraMovementSystem extends IteratingSystem {

	private OrthographicCamera camera;

	public CameraMovementSystem(OrthographicCamera camera) {
		super(Aspect.all(PlayerComponent.class));
		this.camera = camera;
	}

	@Wire
	ComponentMapper<PlayerComponent> playerMapper;
	@Wire
	ComponentMapper<SpriteComponent> spriteMapper;

	@Override
	protected void process(int id) {
		SpriteComponent spriteComp = spriteMapper.get(id);

		System.out.println(spriteComp.getPosX());

		if (spriteComp.getPosX() > 1280 / 2)
			camera.position.set(spriteComp.getPosX(), 720/2, 0);
	}

}
