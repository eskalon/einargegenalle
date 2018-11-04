package de.einar.screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;

/**
 * This screen represents the death screen.
 */
public class GameDeathScreen extends BaseUIScreen {

	@InjectAsset("ui/death-background.png")
	private Texture backgroundImage;
	@InjectAsset("audio/button-tick.mp3")
	private Sound clickSound;
	@InjectAsset("ui/done2_button.png")
	private Texture doneButtonImage;
	@InjectAsset("ui/done2_button_down.png")
	private Texture doneButtonDownImage;

	@Override
	protected void initUI() {
		super.backgroundTexture = backgroundImage;

		ImageButton testButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(doneButtonImage)),
				new TextureRegionDrawable(new TextureRegion(doneButtonDownImage)));
		testButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);
				game.pushScreen("mainMenu");
				return true;
			}
		});

		mainTable.add(testButton).padTop(395);
	}

}
