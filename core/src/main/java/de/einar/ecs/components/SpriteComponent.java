package de.einar.ecs.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
	private int layer = 1;

	private float paddingLeft = 0;
	private float paddingBottom = 0;

	private boolean visible = true;
	
	public SpriteComponent() {
		// default public constructor
	}

	//TODO: Keyframes
	private float frameDuration = 0.6F;
	private Animation animation = new Animation(frameDuration);
	private boolean isPlaying;



	public boolean isPlaying() {
		return isPlaying;
	}

	public void setFrameDuration(float frameDuration) {
		this.frameDuration = frameDuration;
	}

	public void setPlaying(boolean playing) {
		isPlaying = playing;
	}


	public SpriteComponent(Texture texture, int posX, int posY) {
		this(texture, posX, posY,
				texture == null ? 0 : (-texture.getWidth() / 2),
				texture == null ? 0 : (-texture.getHeight() / 2));
	}

	public SpriteComponent(Texture texture, int posX, int posY,
			float paddingLeft, float paddingBottom) {
		Preconditions.checkNotNull(texture, "texture cannot be null.");
		Preconditions.checkArgument(texture.isManaged(),
				"texture isn't loaded.");

		this.texture = texture;
		this.paddingLeft = paddingLeft;
		this.paddingBottom = paddingBottom;
		this.posX = posX;
		this.posY = posY;
	}

	public Texture getTexture() {
		return this.texture;
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

}
