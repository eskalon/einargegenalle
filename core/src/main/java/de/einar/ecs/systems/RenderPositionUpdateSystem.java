package de.einar.ecs.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;

import de.einar.ecs.components.LerpComponent;
import de.einar.ecs.components.PhysicsComponent;
import de.einar.ecs.components.SpriteComponent;
import de.einar.util.PositionConverter;

/**
 * Syncs the position in the {@linkplain PhysicsComponent physics engine} to the
 * {@linkplain SpriteComponent render} position by using the
 * {@link LerpComponent}.
 */
public class RenderPositionUpdateSystem extends IteratingSystem {

	public boolean lerpActivated = true;
	@Wire
	ComponentMapper<SpriteComponent> spriteMapper;
	@Wire
	ComponentMapper<LerpComponent> lerpMapper;

	public RenderPositionUpdateSystem() {
		super(Aspect.all(SpriteComponent.class, LerpComponent.class));
	}

	@Override
	protected void process(int id) {
		LerpComponent lerpComp = lerpMapper.get(id);

		if (lerpComp.intervall != -1) {
			SpriteComponent spriteComp = spriteMapper.get(id);
			lerpComp.intervall += world.delta;

			int x = PositionConverter.toPixels(lerpComp.targetPosX);
			int y = PositionConverter.toPixels(lerpComp.targetPosY);

			if (lerpActivated && lerpComp.intervall < PhysicsSystem.interval) {
				x = Math.round(lerp(spriteComp.getPosX(),

						PositionConverter.toPixels(lerpComp.targetPosX),
						(lerpComp.intervall / PhysicsSystem.interval)));

				y = Math.round((int) lerp(spriteComp.getPosY(),
						PositionConverter.toPixels(lerpComp.targetPosY),
						(lerpComp.intervall / PhysicsSystem.interval)));

			}

			spriteComp.setPosX(x);
			spriteComp.setPosY(y);
		}
	}

	/**
	 * Interpolates a <i>value</i> linearly.
	 *
	 * @param v0
	 * @param v1
	 * @param t
	 * @return
	 */
	public static float lerp(float v0, float v1, float t) {
		return v0 + t * (v1 - v0);
	}

}
