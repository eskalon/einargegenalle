package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.google.common.eventbus.EventBus;

import de.einar.ecs.components.PlayerComponent;
import de.einar.ecs.components.SpriteComponent;
import de.einar.events.PlayerWinEvent;

public class CameraMovementSystem extends IteratingSystem {

	private EventBus bus;
	private OrthographicCamera camera;

	public CameraMovementSystem(EventBus bus, OrthographicCamera camera) {
		super(Aspect.all(PlayerComponent.class));
		this.camera = camera;
		this.bus = bus;
	}

	@Wire
	ComponentMapper<PlayerComponent> playerMapper;
	@Wire
	ComponentMapper<SpriteComponent> spriteMapper;

	@Override
	protected void process(int id) {
		SpriteComponent spriteComp = spriteMapper.get(id);

		System.out.println(spriteComp.getPosX());

		if (spriteComp.getPosX() > 1280 / 2 && spriteComp.getPosX() < (1280 * 5.5F))
			camera.position.set(spriteComp.getPosX(), 720/2, 0);
		
		if(spriteComp.getPosX() > (1280 * 5.9F))
			bus.post(new PlayerWinEvent());
	}

}
