package de.einar.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import de.damios.gamedev.asset.JSON;
import de.damios.gamedev.asset.JSONLoader;
import de.damios.gamedev.game.LoadableScreen;
import de.damios.gamedev.game.ScreenGame;
import de.damios.gamedev.input.BasicInputMultiplexer;
import de.damios.gamedev.log.Log;
import de.damios.gamedev.misc.EventQueueBus;
import de.einar.screen.BaseScreen;
import de.einar.screen.BaseUIScreen;
import de.einar.screen.GameEndScreen;
import de.einar.screen.GameIntroScreen;
import de.einar.screen.GameScreen;
import de.einar.screen.MainMenuScreen;
import de.einar.screen.SplashScreen;

/**
 * This class starts the game by creating all the necessary screens and then
 * displaying the {@link SplashScreen}.
 * <p>
 * Only {@link BaseScreen}s are supported.
 */
public class EinarGame extends ScreenGame {

	public static final String NAME = "Einar gegen alle";

	private SpriteBatch batch;

	private OrthographicCamera uiCamera;
	private OrthographicCamera gameCamera;
	private OrthographicCamera debugCamera;

	private GameSettings settings;

	private boolean debug;

	private BasicInputMultiplexer inputProcessor;

	private Skin uiSkin;

	/**
	 * Event bus. All events are queued first and then taken care of in the
	 * rendering thread.
	 */
	private EventQueueBus eventBus;

	public EinarGame(boolean debug) {
		this.debug = debug;
	}

	@Override
	public final void create() {
		super.create();

		if (debug)
			Log.enableDebugLogging();
		else
			Log.disableDebugLogging();

		Log.info("Start ", "Spiel gestartet");

		// Initialize sprite batch
		this.batch = new SpriteBatch();

		// Initialize asset manager
		FileHandleResolver resolver = new InternalFileHandleResolver();
		this.assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		this.assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		this.assetManager.setLoader(JSON.class, new JSONLoader(new InternalFileHandleResolver()));

		this.viewportWidth = Gdx.graphics.getWidth();
		this.viewportHeight = Gdx.graphics.getHeight();

		// Initialize cameras
		this.uiCamera = new OrthographicCamera(viewportWidth, viewportHeight);
		this.uiCamera.translate(viewportWidth / 2, viewportHeight / 2, 0);
		this.uiCamera.update();

		this.gameCamera = new OrthographicCamera(viewportWidth, viewportHeight);
		this.gameCamera.translate(viewportWidth / 2, viewportHeight / 2, 0);
		// this.camera.update();
		this.batch.setProjectionMatrix(this.gameCamera.combined);

		this.debugCamera = new OrthographicCamera(viewportWidth, viewportHeight);
		this.debugCamera.translate(viewportWidth / 2, viewportHeight / 2, 0);

		// Load game settings
		this.settings = new GameSettings(NAME.trim().replace(" ", "-").toLowerCase());

		// Create the input multiplexer
		this.inputProcessor = new BasicInputMultiplexer();

		// Create the event bus
		this.eventBus = new EventQueueBus();

		// Set input processor
		Gdx.input.setInputProcessor(inputProcessor);

		// Add screens
		addScreen("splash", new SplashScreen());
		addScreen("mainMenu", new MainMenuScreen());
		addScreen("game", new GameScreen());
		addScreen("game-intro", new GameIntroScreen());
		addScreen("game-end", new GameEndScreen());

		pushScreen("splash");
	}

	@Override
	public void render() {
		// Takes care of posting the events in the rendering thread
		eventBus.distributeEvents();

		super.render();
	}

	@Override
	protected void onScreenInitialization(LoadableScreen screen) {
		((BaseScreen) screen).init(this);
	}

	@Override
	public final void dispose() {
		super.dispose();

		this.batch.dispose();
		if (uiSkin != null)
			this.uiSkin.dispose();
	}

	/**
	 * @return the camera used in the actual game.
	 */
	public OrthographicCamera getGameCamera() {
		return this.gameCamera;
	}

	/**
	 * @return the camera used by the UI screens.
	 * @see BaseUIScreen#render(float)
	 */
	public Camera getUICamera() {
		return this.uiCamera;
	}

	public OrthographicCamera getDebugCamera() {
		return debugCamera;
	}

	/**
	 * @return an instance of the game settings handler.
	 */
	public GameSettings getSettings() {
		return settings;
	}

	/**
	 * @return the events bus. See {@link EventQueueBus}. Events are processed in
	 *         the rendering thread.
	 */
	public EventQueueBus getEventBus() {
		return eventBus;
	}

	/**
	 * Sets the UI skin.
	 *
	 * @param skin
	 *            the UI skin.
	 */
	public void setUISkin(Skin skin) {
		this.uiSkin = skin;
	}

	/**
	 * @return the skin for the game's UI elements.
	 */
	public Skin getUISkin() {
		return uiSkin;
	}

	/**
	 * @return the sprite batch to render 2D stuff with.
	 */
	public SpriteBatch getSpriteBatch() {
		return batch;
	}

	/**
	 * Returns the input multiplexer of the game. Should be used to add input
	 * listeners instead of {@link Input#setInputProcessor(InputProcessor)}.
	 *
	 * @return the game's input multiplexer.
	 */
	public BasicInputMultiplexer getInputMultiplexer() {
		return inputProcessor;
	}

	/**
	 * @return whether the debug flag is set and thus debug stuff should get
	 *         rendered.
	 */
	public boolean showDebugStuff() {
		return debug;
	}

}
