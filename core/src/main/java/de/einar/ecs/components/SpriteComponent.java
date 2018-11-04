package de.einar.ecs.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.google.common.base.Preconditions;

import de.einar.ecs.systems.RenderPositionUpdateSystem;

/**
 * This component is needed for rendering a sprite. If the entity also possesses
 * a {@link PhysicsComponent} its position is
 * {@linkplain RenderPositionUpdateSystem automatically synced} to the sprite
 * component.
 */
public class SpriteComponent extends Component {

	/**
	 * The x position in pixels.
	 */
	private int posX;
	/**
	 * The y position in pixels.
	 */
	private int posY;

	private Texture texture;

	private boolean isAnimation = false;
	private Animation<TextureRegion> animation;
	private float stateTime = 0f;

	private int layer = 1;

	private float paddingLeft = 0;
	private float paddingBottom = 0;

	private boolean visible = true;

	public SpriteComponent() {
		// default public constructor
	}

	public SpriteComponent(Texture texture, int posX, int posY) {
		this(texture, posX, posY,
				texture == null ? 0 : (-texture.getWidth() / 2),
				texture == null ? 0 : (-texture.getHeight() / 2));
	}

	public SpriteComponent(Texture textureSheet, float frameDuration, int rows,
			int cols, int posX, int posY, float paddingLeft,
			float paddingBottom) {
		Preconditions.checkNotNull(textureSheet, "texture cannot be null.");
		Preconditions.checkArgument(textureSheet.isManaged(),
				"texture isn't loaded.");

		// Create frames out of texture sheet
		TextureRegion[][] tmp = TextureRegion.split(textureSheet,
				textureSheet.getWidth() / cols,
				textureSheet.getHeight() / rows);
		TextureRegion[] frames = new TextureRegion[cols * rows];
		int index = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				frames[index++] = tmp[i][j];
			}
		}

		this.animation = new Animation<TextureRegion>(frameDuration, frames);
		this.isAnimation = true;
		this.paddingLeft = paddingLeft;
		this.paddingBottom = paddingBottom;
		this.posX = posX;
		this.posY = posY;
	}

	public SpriteComponent(Texture texture, int posX, int posY,
			float paddingLeft, float paddingBottom) {
		Preconditions.checkNotNull(texture, "texture cannot be null.");
		Preconditions.checkArgument(texture.isManaged(),
				"texture isn't loaded.");

		this.texture = texture;
		this.paddingLeft = paddingBottom;
		this.paddingBottom = paddingLeft;
		this.posX = posX;
		this.posY = posY;
	}

	public Texture getTexture() {
		Preconditions.checkState(!isAnimation);
		return this.texture;
	}

	public TextureRegion getTexture(float delta) {
		Preconditions.checkState(isAnimation);

		stateTime += delta;
		return animation.getKeyFrame(stateTime, true);
	}

	public boolean isAnimation() {
		return isAnimation;
	}

	/**
	 * @return The x position in pixels.
	 */
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return The y position in pixels.
	 */
	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Integer getLayer() {
		return layer;
	}

	public float getPaddingLeft() {
		return paddingLeft;
	}

	public float getPaddingBottom() {
		return paddingBottom;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

}
