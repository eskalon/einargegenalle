package de.einar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;

/**
 * This screen represents the main menu.
 */
public class MainMenuScreen extends BaseUIScreen {

	@InjectAsset("ui/main-menu-background.png")
	private Texture backgroundImage;
	@InjectAsset("ui/start_button.png")
	private Texture startButtonImage;
	@InjectAsset("ui/start_button_down.png")
	private Texture startButtonDownImage;
	@InjectAsset("ui/quit_button.png")
	private Texture quitButtonImage;
	@InjectAsset("ui/quit_button_down.png")
	private Texture quitButtonDownImage;

	@InjectAsset("ui/logo.png")
	private Texture logoTexture;
	@InjectAsset("audio/button-tick.mp3")
	private Sound clickSound;
	@InjectAsset("ui/github.png")
	private Texture githubLogoTexture;

	@Override
	protected void initUI() {
		super.backgroundTexture = backgroundImage;

		ImageButton startGameButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);
				game.pushScreen("game-intro");
				return true;
			}
		});

		ImageButton exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(quitButtonImage)),
				new TextureRegionDrawable(new TextureRegion(quitButtonDownImage)));
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				Gdx.app.exit();
				return true;
			}
		});

		ImageButton githubRepoButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(githubLogoTexture)));
		githubRepoButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.net.openURI("https://github.com/eskalon/einargegenalle");
				return true;
			}
		});

		Image logoImage = new Image(logoTexture);

		mainTable.add(logoImage).padBottom(25f).padTop(-285f).row();
		mainTable.add(startGameButton).padBottom(15f).row();
		// mainTable.add(creditsButton).padBottom(11f).row();
		mainTable.add(exitButton);

		githubRepoButton.padLeft(3).padBottom(3).bottom().left();

		stage.addActor(githubRepoButton);
	}

}
