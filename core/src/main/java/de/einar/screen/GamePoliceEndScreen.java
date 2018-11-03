package de.einar.screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;

public class GamePoliceEndScreen extends BaseUIScreen {
	@InjectAsset("audio/button-tick.mp3")
	private Sound clickSound;
	@InjectAsset("audio/sirens.wav")
	private Sound policeSound;

	// TODO Hintergrundbild

	@Override
	protected void initUI() {
		ImageTextButton testButton = new ImageTextButton("Beenden", skin);
		testButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);
				game.pushScreen("mainMenu");
				return true;
			}
		});

		mainTable.add(testButton).padBottom(11f);

		policeSound.play(1.1F);
	}

}
