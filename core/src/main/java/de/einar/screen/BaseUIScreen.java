package de.einar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * The base class of all UI screens. It automatically loads the
 * {@linkplain #skin skin} and sets the {@link #stage} as
 * {@linkplain InputProcessor input processor}. All actors have to be added to
 * the {@link #mainTable}.
 */
public abstract class BaseUIScreen extends BaseScreen {
	/**
	 * Contains a {@linkplain #mainTable table} by default. The stage is cleared
	 * whenever the screen is {@linkplain #show() shown}.
	 */
	protected Stage stage;
	/**
	 * The main table, to which all {@link Actor}s will be added. The mainTable
	 * is recreated whenever the screen is {@linkplain #show() shown}.
	 */
	protected Table mainTable;
	/**
	 * The default UI skin. Is automatically set in the
	 * {@link #onInit()}-method.
	 */
	protected Skin skin;
	/**
	 * An easy way to add a backgroundImage is to set this variable. The screen
	 * automatically takes care of rendering it.
	 */
	protected Texture backgroundTexture;

	@Override
	protected void onInit() {
		skin = game.getUISkin();
		stage = new Stage(new ScreenViewport(), game.getSpriteBatch());
		addInputProcessor(stage);
	}

	/**
	 * This method is the called whenever this screen is {@linkplain #show
	 * shown}. The stage's actors will be constructed in this method.
	 */
	protected abstract void initUI();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g,
				backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (backgroundTexture != null) {
			game.getSpriteBatch().begin();
			game.getSpriteBatch()
					.setProjectionMatrix(game.getUICamera().combined);
			game.getSpriteBatch().draw(this.backgroundTexture, 0, 0,
					game.getViewportWidth(), game.getViewportHeight());
			game.getSpriteBatch().end();
		}

		stage.getBatch().setProjectionMatrix(game.getUICamera().combined);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
		super.show();
		stage.clear();
		mainTable = new Table();
		stage.addActor(mainTable);
		mainTable.setFillParent(true);

		// mainTable.setDebug(game.showDebugStuff());

		initUI();

		stage.mouseMoved(1, 1); // verhindert einen kleinen Anzeige-Bug bei
								// erneutem Anzeigen eines Screens
	}

	@Override
	public void dispose() {
		if (stage != null)
			stage.dispose();
	}
}
