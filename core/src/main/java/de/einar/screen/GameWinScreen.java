package de.einar.screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;

/**
 * This screen represents screen shown when the game is "won".
 */
public class GameWinScreen extends BaseUIScreen {
	@InjectAsset("audio/button-tick.mp3")
	private Sound clickSound;

	@InjectAsset("ui/phone/option1/option1_5a.png")
	private Texture endOption1;
	@InjectAsset("ui/phone/option1/option1_5b.png")
	private Texture endOption2;
	@InjectAsset("ui/phone/option2/option2_5a.png")
	private Texture endOption3;
	@InjectAsset("ui/phone/option2/option2_5b.png")
	private Texture endOption4;
	@InjectAsset("ui/phone/option3/option3_5a.png")
	private Texture endOption5;
	@InjectAsset("ui/phone/option3/option3_5b.png")
	private Texture endOption6;
	@InjectAsset("ui/phone/option4/option4_5a.png")
	private Texture endOption7;
	@InjectAsset("ui/phone/option4/option4_5b.png")
	private Texture endOption8;

	@Override
	protected void initUI() {
		backgroundColor = new Color(0.141f, 0.141f, 0.141f, 1f);
		Texture image = null;

		switch (game.chosenChatOption) {
		case 1: {
			image = (endOption1);
			break;
		}
		case 2: {
			image = (endOption2);
			break;
		}
		case 3: {
			image = (endOption3);
			break;
		}
		case 4: {
			image = (endOption4);
			break;
		}
		case 5: {
			image = (endOption5);
			break;
		}
		case 6: {
			image = (endOption6);
			break;
		}
		case 7: {
			image = (endOption7);
			break;
		}
		case 8: {
			image = (endOption8);
			break;
		}
		}

		ImageButton endButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(image)),
				new TextureRegionDrawable(new TextureRegion(image)));
		endButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);
				game.pushScreen("mainMenu");
				return true;
			}
		});

		mainTable.add(endButton).padBottom(5);

	}

}
