package de.einar.ecs.systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.einar.ecs.components.SpriteComponent;

/**
 * This system renders all sprites while taking their
 * {@linkplain SpriteComponent#getLayer() layer} into account.
 */
public class SpriteRenderSystem extends EntitySystem {

	private List<SpriteComponent> sortedSprites;
	private SpriteBatch batch;

	public SpriteRenderSystem(SpriteBatch batch) {
		super(Aspect.all(SpriteComponent.class));

		this.batch = batch;
	}

	@Override
	protected void initialize() {
		sortedSprites = new ArrayList<SpriteComponent>();
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	protected void renderSpriteComponent(SpriteComponent sprite) {
		if (sprite.isVisible())
			batch.draw(sprite.getTexture(),
					sprite.getPosX() + sprite.getPaddingLeft(),
					sprite.getPosY() + sprite.getPaddingBottom());
	}

	private void sortSprites() {
		Collections.sort(sortedSprites, new Comparator<SpriteComponent>() {
			@Override
			public int compare(SpriteComponent s1, SpriteComponent s2) {
				return s1.getLayer().compareTo(s2.getLayer());
			}
		});
	}

	@Override
	public void inserted(Entity e) {
		sortedSprites.add(e.getComponent(SpriteComponent.class));
		sortSprites();
	}

	@Override
	public void removed(Entity e) {
		sortedSprites.remove(e.getComponent(SpriteComponent.class));
		sortSprites();
	}

	@Override
	protected void processSystem() {
		batch.begin();
		for (int i = 0; sortedSprites.size() > i; i++) {
			renderSpriteComponent(sortedSprites.get(i));
		}
		batch.end();
	}

}
