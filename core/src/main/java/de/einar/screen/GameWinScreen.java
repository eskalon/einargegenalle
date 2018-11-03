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
 * This screen represents the main menu.
 */
public class GameWinScreen extends BaseUIScreen {
	@InjectAsset("audio/button-tick.mp3")
	private Sound clickSound;
	@InjectAsset("ui/done_button.png")
	private Texture doneButtonImage;
	@InjectAsset("ui/done_button_down.png")
	private Texture doneButtonDownImage;

	@Override
	protected void initUI() {
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

		mainTable.add(testButton).padBottom(11f);
	}

}
