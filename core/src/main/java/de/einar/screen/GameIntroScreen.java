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

import java.util.concurrent.TimeUnit;

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

	// TEST:
	@InjectAsset("ui/start_button.png")
	private Texture startButtonImage;
	@InjectAsset("ui/start_button_down.png")
	private Texture startButtonDownImage;

	@Override
	protected void initUI() {

		Image logoImage = new Image(startPhoneTexture);

		ImageButton sendFirstMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		ImageButton sendSecondMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		ImageButton sendThirdMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		ImageButton sendFourthMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));

		//Buttons after option 1
		ImageButton option1aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		ImageButton option1bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));

		//Buttons after option 2
		ImageButton option2aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		ImageButton option2bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));

		//Buttons after option 3
		ImageButton option3aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		ImageButton option3bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));

		//Buttons after option 4
		ImageButton option4aMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));
		ImageButton option4bMessageButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(startButtonImage)),
				new TextureRegionDrawable(new TextureRegion(startButtonDownImage)));



		// OPTION 1
		sendFirstMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option1_1PhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option1_2PhoneTexture)));
						mainTable.clear();
						mainTable.add(logoImage).padBottom(5);
						mainTable.add(option1aMessageButton).padLeft(50);
						mainTable.add(option1bMessageButton).padLeft(50);

						//game.pushScreen("game");
					}
				}, 2.5F);

				return true;
			}
		});

		// OPTION 1A
		option1aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option1_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option1_4aPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});

		// OPTION 1B
		option1bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option1_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option1_4bPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});


		// OPTION 2
		sendSecondMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option2_1PhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option2_2PhoneTexture)));
						mainTable.clear();
						mainTable.add(logoImage).padBottom(5);
						mainTable.add(option2aMessageButton).padLeft(50);
						mainTable.add(option2bMessageButton).padLeft(50);


						//game.pushScreen("game");
					}
				}, 2.5F);

				return true;
			}
		});

		// OPTION 2A
		option2aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option2_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option2_4aPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});

		// OPTION 2B
		option2bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option2_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option2_4bPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});

		// OPTION 3
		sendThirdMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option3_1PhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option3_2PhoneTexture)));
						mainTable.clear();
						mainTable.add(logoImage).padBottom(5);
						mainTable.add(option3aMessageButton).padLeft(50);
						mainTable.add(option3bMessageButton).padLeft(50);

						//game.pushScreen("game");
					}
				}, 2.5F);

				return true;
			}
		});

		//OPTION 3A
		option3aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option3_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option3_4aPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});

		//OPTION 3B
		option3bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option3_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option3_4bPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});


		// OPTION 4
		sendFourthMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren

				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option4_1PhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option4_2PhoneTexture)));
						mainTable.clear();
						mainTable.add(logoImage).padBottom(5);
						mainTable.add(option4aMessageButton).padLeft(50);
						mainTable.add(option4bMessageButton).padLeft(50);


						//game.pushScreen("game");
					}
				}, 2.5F);

				return true;
			}
		});

		// OPTION 4A
		option4aMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option4_3aPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option4_4aPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});

		// OPTION 4B
		option4bMessageButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				clickSound.play(1F);

				// TODO angezeigtes Bild entsprechend ändern & Buttons deaktivieren
				logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option4_3bPhoneTexture)));

				// Antwort kommt wenige Sekunden später
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						receivedSound.play(1F);

						// TODO angezeigtes Bild entsprechend ändern & Buttons anpassen
						logoImage.setDrawable(new TextureRegionDrawable(new TextureRegion(option4_4bPhoneTexture)));
						//game.pushScreen("game");
					}
				}, 1F);
				Timer.instance().scheduleTask(new Timer.Task() {
					@Override
					public void run() {
						// START GAME
						game.pushScreen("game");
					}
				}, 3.5F);

				return true;
			}
		});


		mainTable.add(logoImage).padBottom(5);
		mainTable.add(sendFirstMessageButton).padLeft(50).row();
		mainTable.add(sendSecondMessageButton).padLeft(50).row();
		mainTable.add(sendThirdMessageButton).padLeft(50).row();
		mainTable.add(sendFourthMessageButton).padLeft(50).row();
	}

}
