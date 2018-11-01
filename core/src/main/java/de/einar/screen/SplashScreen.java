package de.einar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.kotcrab.vis.ui.VisUI;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;

/**
 * This screen is the first screen shown to the user when he starts the game.
 */
public class SplashScreen extends BaseScreen {

	protected long startTime = -1;
	protected long duration = 1275;
	@InjectAsset("ui/eskalon.png")
	private Texture titleImage;
	private int xPos;
	private int yPos;

	@Override
	protected void onInit() {
		xPos = (game.getViewportWidth() - titleImage.getWidth()) / 2;
		yPos = (game.getViewportHeight() - titleImage.getHeight()) / 2 + 20;

		game.getAssetManager().load(MainMenuScreen.class);
		game.getAssetManager().load(GameIntroScreen.class);
		game.getAssetManager().load(GameScreen.class);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g,
				backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getSpriteBatch().begin();
		game.getSpriteBatch().setProjectionMatrix(game.getUICamera().combined);

		game.getSpriteBatch().draw(this.titleImage, xPos, yPos);

		if (startTime == -1) {
			this.startTime = System.currentTimeMillis();
		}

		if (game.getAssetManager().update()
				&& (startTime + duration) < System.currentTimeMillis()) {
			onFinishedLoading();
		}

		game.getSpriteBatch().end();
	}

	private void onFinishedLoading() {
		// Load skin
		VisUI.load();
		game.setUISkin(VisUI.getSkin());

		// Inject loaded assets
		game.getAssetManager().injectAssets(game.getScreen("mainMenu"));
		game.getAssetManager().injectAssets(game.getScreen("game-intro"));
		game.getAssetManager().injectAssets(game.getScreen("game"));

		// Notify loaded screens
		game.getScreen("mainMenu").finishLoading();
		game.getScreen("game-intro").finishLoading();
		game.getScreen("game").finishLoading();

		game.pushScreen("mainMenu");
	}

	@Override
	public void resize(int width, int height) {
		xPos = (width - titleImage.getWidth()) / 2;
		yPos = (height - titleImage.getHeight()) / 2 + 40;
	}

	@Override
	public void show() {
		// unused
	}

	@Override
	public void hide() {
		// unused
	}

	@Override
	public void dispose() {
		// unused
	}

}
