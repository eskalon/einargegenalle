package de.einar.screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;

/**
 * This screen represents the story intro of the game.
 */
public class GameIntroScreen extends BaseUIScreen {
	@InjectAsset("audio/button-tick.mp3")
	private Sound clickSound;
	@InjectAsset("audio/received.wav")
	private Sound receivedSound;

	@InjectAsset("ui/phone/phone_neutral.png")
	private Texture startPhoneTexture;

	// TEST:
	@InjectAsset("ui/start_button.png")
	private Texture startButtonImage;
	@InjectAsset("ui/start_button_down.png")
	private Texture startButtonDownImage;

	@Override
	protected void initUI() {
		ImageButton sendTestMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		sendTestMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen

						game.pushScreen("game");
					}
				}, 2.5F);

				return true;
			}
		});

		Image logoImage = new Image(startPhoneTexture);
		mainTable.add(logoImage).padBottom(5);
		mainTable.add(sendTestMessageButton).padLeft(50);
	}

}
