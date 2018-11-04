package de.einar.screen;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
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
	@InjectAsset("ui/phone/phone_dead.png")
	private Texture deadPhoneTexture;

	// OPTION 1:
	@InjectAsset("ui/phone/option1/option1_1.png")
	private Texture option1_1PhoneTexture;
	@InjectAsset("ui/phone/option1/option1_2.png")
	private Texture option1_2PhoneTexture;
	@InjectAsset("ui/phone/option1/option1_3a.png")
	private Texture option1_3aPhoneTexture;
	@InjectAsset("ui/phone/option1/option1_4a.png")
	private Texture option1_4aPhoneTexture;
	@InjectAsset("ui/phone/option1/option1_5a.png")
	private Texture option1_5aPhoneTexture;
	@InjectAsset("ui/phone/option1/option1_3b.png")
	private Texture option1_3bPhoneTexture;
	@InjectAsset("ui/phone/option1/option1_4b.png")
	private Texture option1_4bPhoneTexture;
	@InjectAsset("ui/phone/option1/option1_5b.png")
	private Texture option1_5bPhoneTexture;

	// OPTION 2:
	@InjectAsset("ui/phone/option2/option2_1.png")
	private Texture option2_1PhoneTexture;
	@InjectAsset("ui/phone/option2/option2_2.png")
	private Texture option2_2PhoneTexture;
	@InjectAsset("ui/phone/option2/option2_3a.png")
	private Texture option2_3aPhoneTexture;
	@InjectAsset("ui/phone/option2/option2_4a.png")
	private Texture option2_4aPhoneTexture;
	@InjectAsset("ui/phone/option2/option2_5a.png")
	private Texture option2_5aPhoneTexture;
	@InjectAsset("ui/phone/option2/option2_3b.png")
	private Texture option2_3bPhoneTexture;
	@InjectAsset("ui/phone/option2/option2_4b.png")
	private Texture option2_4bPhoneTexture;
	@InjectAsset("ui/phone/option2/option2_5b.png")
	private Texture option2_5bPhoneTexture;

	// OPTION 3:
	@InjectAsset("ui/phone/option3/option3_1.png")
	private Texture option3_1PhoneTexture;
	@InjectAsset("ui/phone/option3/option3_2.png")
	private Texture option3_2PhoneTexture;
	@InjectAsset("ui/phone/option3/option3_3a.png")
	private Texture option3_3aPhoneTexture;
	@InjectAsset("ui/phone/option3/option3_4a.png")
	private Texture option3_4aPhoneTexture;
	@InjectAsset("ui/phone/option3/option3_5a.png")
	private Texture option3_5aPhoneTexture;
	@InjectAsset("ui/phone/option3/option3_3b.png")
	private Texture option3_3bPhoneTexture;
	@InjectAsset("ui/phone/option3/option3_4b.png")
	private Texture option3_4bPhoneTexture;
	@InjectAsset("ui/phone/option3/option3_5b.png")
	private Texture option3_5bPhoneTexture;

	// OPTION 4:
	@InjectAsset("ui/phone/option4/option4_1.png")
	private Texture option4_1PhoneTexture;
	@InjectAsset("ui/phone/option4/option4_2.png")
	private Texture option4_2PhoneTexture;
	@InjectAsset("ui/phone/option4/option4_3a.png")
	private Texture option4_3aPhoneTexture;
	@InjectAsset("ui/phone/option4/option4_4a.png")
	private Texture option4_4aPhoneTexture;
	@InjectAsset("ui/phone/option4/option4_5a.png")
	private Texture option4_5aPhoneTexture;
	@InjectAsset("ui/phone/option4/option4_3b.png")
	private Texture option4_3bPhoneTexture;
	@InjectAsset("ui/phone/option4/option4_4b.png")
	private Texture option4_4bPhoneTexture;
	@InjectAsset("ui/phone/option4/option4_5b.png")
	private Texture option4_5bPhoneTexture;

	// BUTTONS:
	@InjectAsset("ui/phone/option1button.png")
	private Texture option1Image;
	@InjectAsset("ui/phone/option2button.png")
	private Texture option2Image;
	@InjectAsset("ui/phone/option3button.png")
	private Texture option3Image;
	@InjectAsset("ui/phone/option4button.png")
	private Texture option4Image;
	@InjectAsset("ui/phone/option1abutton.png")
	private Texture option1aImage;
	@InjectAsset("ui/phone/option1bbutton.png")
	private Texture option1bImage;
	@InjectAsset("ui/phone/option2abutton.png")
	private Texture option2aImage;
	@InjectAsset("ui/phone/option2bbutton.png")
	private Texture option2bImage;
	@InjectAsset("ui/phone/option3abutton.png")
	private Texture option3aImage;
	@InjectAsset("ui/phone/option3bbutton.png")
	private Texture option3bImage;
	@InjectAsset("ui/phone/option4abutton.png")
	private Texture option4aImage;
	@InjectAsset("ui/phone/option4bbutton.png")
	private Texture option4bImage;

	@Override
	protected void initUI() {
		Table sideTable = new Table();
		backgroundColor = new Color(0.141f, 0.141f, 0.141f, 1f);
		Image phoneImage = new Image(startPhoneTexture);

		ImageButton sendFirstMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option1Image)),
				new TextureRegionDrawable(new TextureRegion(option1Image)));
		ImageButton sendSecondMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option2Image)),
				new TextureRegionDrawable(new TextureRegion(option2Image)));
		ImageButton sendThirdMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option3Image)),
				new TextureRegionDrawable(new TextureRegion(option3Image)));
		ImageButton sendFourthMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option4Image)),
				new TextureRegionDrawable(new TextureRegion(option4Image)));

		//Buttons after option 1
		ImageButton option1aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option1aImage)),
				new TextureRegionDrawable(new TextureRegion(option1aImage)));
		ImageButton option1bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option1bImage)),
				new TextureRegionDrawable(new TextureRegion(option1bImage)));

		//Buttons after option 2
		ImageButton option2aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option2aImage)),
				new TextureRegionDrawable(new TextureRegion(option2aImage)));
		ImageButton option2bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option2bImage)),
				new TextureRegionDrawable(new TextureRegion(option2bImage)));

		//Buttons after option 3
		ImageButton option3aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option3aImage)),
				new TextureRegionDrawable(new TextureRegion(option3aImage)));
		ImageButton option3bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option3bImage)),
				new TextureRegionDrawable(new TextureRegion(option3bImage)));

		//Buttons after option 4
		ImageButton option4aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option4aImage)),
				new TextureRegionDrawable(new TextureRegion(option4aImage)));
		ImageButton option4bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(option4bImage)),
				new TextureRegionDrawable(new TextureRegion(option4bImage)));

		// OPTION 1
		sendFirstMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option1_1PhoneTexture)));
				sendFirstMessageButton.setTouchable(Touchable.disabled);
				sendSecondMessageButton.setTouchable(Touchable.disabled);
				sendThirdMessageButton.setTouchable(Touchable.disabled);
				sendFourthMessageButton.setTouchable(Touchable.disabled);

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option1_2PhoneTexture)));
						sideTable.clear();
						sideTable.add(option1aMessageButton).padLeft(50).row();
						sideTable.add(option1bMessageButton).padLeft(50).padTop(25);
					}
				}, 2.5F);

				return true;
			}
		});

		// OPTION 1A
		option1aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option1aMessageButton.setTouchable(Touchable.disabled);
				option1bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option1_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 1;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option1_4aPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				},3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		// OPTION 1B
		option1bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option1aMessageButton.setTouchable(Touchable.disabled);
				option1bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option1_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 2;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option1_4bPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				},3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		// OPTION 2
		sendSecondMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option2_1PhoneTexture)));
				sendFirstMessageButton.setTouchable(Touchable.disabled);
				sendSecondMessageButton.setTouchable(Touchable.disabled);
				sendThirdMessageButton.setTouchable(Touchable.disabled);
				sendFourthMessageButton.setTouchable(Touchable.disabled);

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option2_2PhoneTexture)));
						sideTable.clear();
						sideTable.add(option2aMessageButton).padLeft(50).row();
						sideTable.add(option2bMessageButton).padLeft(50).padTop(25);
					}
				}, 2.5F);

				return true;
			}
		});

		// OPTION 2A
		option2aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option2aMessageButton.setTouchable(Touchable.disabled);
				option2bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option2_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 3;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option2_4aPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				},3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		// OPTION 2B
		option2bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option2aMessageButton.setTouchable(Touchable.disabled);
				option2bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option2_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 4;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option2_4bPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				},3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		// OPTION 3
		sendThirdMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option3_1PhoneTexture)));
				sendFirstMessageButton.setTouchable(Touchable.disabled);
				sendSecondMessageButton.setTouchable(Touchable.disabled);
				sendThirdMessageButton.setTouchable(Touchable.disabled);
				sendFourthMessageButton.setTouchable(Touchable.disabled);

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option3_2PhoneTexture)));
						sideTable.clear();
						sideTable.add(option3aMessageButton).padLeft(50).row();
						sideTable.add(option3bMessageButton).padLeft(50).padTop(25);
					}
				}, 2.5F);

				return true;
			}
		});

		// OPTION 3A
		option3aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option3aMessageButton.setTouchable(Touchable.disabled);
				option3bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option3_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 5;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option3_4aPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				},3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		// OPTION 3B
		option3bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option3aMessageButton.setTouchable(Touchable.disabled);
				option3bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option3_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 6;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option3_4bPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				},3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		// OPTION 4
		sendFourthMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option4_1PhoneTexture)));
				sendFirstMessageButton.setTouchable(Touchable.disabled);
				sendSecondMessageButton.setTouchable(Touchable.disabled);
				sendThirdMessageButton.setTouchable(Touchable.disabled);
				sendFourthMessageButton.setTouchable(Touchable.disabled);

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option4_2PhoneTexture)));
						sideTable.clear();
						sideTable.add(option4aMessageButton).padLeft(50).row();
						sideTable.add(option4bMessageButton).padLeft(50).padTop(25);
					}
				}, 2.5F);

				return true;
			}
		});

		// OPTION 4A
		option4aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option4aMessageButton.setTouchable(Touchable.disabled);
				option4bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option4_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 7;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option4_4aPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				},3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		// OPTION 4B
		option4bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				clickSound.play(1F);

				option4aMessageButton.setTouchable(Touchable.disabled);
				option4bMessageButton.setTouchable(Touchable.disabled);
				phoneImage.setDrawable(new TextureRegionDrawable(
						new TextureRegion(option4_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						game.chosenChatOption = 8;
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(option4_4bPhoneTexture)));
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						phoneImage.setDrawable(new TextureRegionDrawable(
								new TextureRegion(deadPhoneTexture)));
					}
				}, 3.25F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 5.75F);

				return true;
			}
		});

		mainTable.add(phoneImage).padBottom(5);
		sideTable.add(sendFirstMessageButton).padLeft(50).row();
		sideTable.add(sendSecondMessageButton).padLeft(50).padTop(25).row();
		sideTable.add(sendThirdMessageButton).padLeft(50).padTop(25).row();
		sideTable.add(sendFourthMessageButton).padLeft(50).padTop(25);
		mainTable.add(sideTable);
	}

}
