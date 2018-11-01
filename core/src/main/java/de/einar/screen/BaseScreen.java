package de.einar.screen;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import de.damios.gamedev.game.LoadableScreen;
import de.einar.core.EinarGame;

/**
 * A basic screen that takes care of registering input and event listeners when
 * used in conjunction with {@link EinarGame}.
 * 
 * @see #addInputProcessor(InputProcessor)
 * @see EinarGame#getEventBus()
 */
public abstract class BaseScreen extends LoadableScreen {

	protected EinarGame game;
	protected Color backgroundColor = Color.BLACK;
	/**
	 * Input processors added to this list get automatically registered when the
	 * screen is {@linkplain #show() shown} and unregistered when the screen is
	 * {@linkplain #hide() hidden}.
	 *
	 * @see #addInputProcessor(InputProcessor)
	 */
	private Array<InputProcessor> inputProcessors = new Array<>(4);

	/**
	 * Initializes the screen. Is automatically called by {@link EinarGame}.
	 *
	 * @param game
	 *            the game this screen is a part of.
	 */
	public final void init(EinarGame game) {
		this.game = game;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void show() {
		game.getEventBus().register(this);
		game.getInputMultiplexer().setProcessors(new Array<>(inputProcessors));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hide() {
		game.getEventBus().unregister(this);
		game.getInputMultiplexer().removeInputProcessors(inputProcessors);
	}

	@Override
	public void pause() {
		// unused
	}

	@Override
	public void resume() {
		// unused
	}

	@Override
	public void resize(int width, int height) {
		// isn't needed as the game can't be resized
	}

	/**
	 * Adds an input processor that is automatically registered and unregistered
	 * whenever the screen is {@linkplain #show() shown}/{@linkplain #hide()
	 * hidden}.
	 *
	 * @param processor
	 *            The processor to add.
	 */
	protected void addInputProcessor(InputProcessor processor) {
		inputProcessors.add(processor);
	}
}