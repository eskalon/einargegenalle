package de.einar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;

/**
 * This screen represents the main menu.
 */
public class MainMenuScreen extends BaseUIScreen {

	@InjectAsset("ui/main-menu-background.png")
	private Texture backgroundImage;
	@InjectAsset("ui/logo.png")
	private Texture logoTexture;
	@InjectAsset("audio/button-tick.mp3")
	private Sound clickSound;
	@InjectAsset("ui/github.png")
	private Texture githubLogoTexture;

	@Override
	protected void initUI() {
		super.backgroundTexture = backgroundImage;

		ImageTextButton startGameButton = new ImageTextButton("Spiel starten",
				skin);
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);
				game.pushScreen("game-intro");
				return true;
			}
		});

		ImageTextButton exitButton = new ImageTextButton("Beenden", skin);
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				Gdx.app.exit();
				return true;
			}
		});

		ImageButton githubRepoButton = new ImageButton(
				new TextureRegionDrawable(
						new TextureRegion(githubLogoTexture)));
		githubRepoButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.net.openURI("https://github.com/eskalon/einargegenalle");
				return true;
			}
		});

		Image logoImage = new Image(logoTexture);

		mainTable.add(logoImage).padBottom(25f).padTop(-285f).row();
		mainTable.add(startGameButton).padBottom(11f).row();
		// mainTable.add(creditsButton).padBottom(11f).row();
		mainTable.add(exitButton);

		githubRepoButton.padLeft(3).padBottom(3).bottom().left();

		stage.addActor(githubRepoButton);
	}

}
