package de.einar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;
import com.google.common.eventbus.Subscribe;

import de.damios.gamedev.asset.AnnotationAssetManager.InjectAsset;
import de.damios.gamedev.misc.RandomUtils;
import de.einar.core.GameSession;
import de.einar.events.GrannyContatcEvent;
import de.einar.events.JamSoundEvent;
import de.einar.events.PlayerDeathEvent;
import de.einar.events.PlayerWinEvent;
import de.einar.input.GameInputProcessor;

/**
 * This screen is the main game screen and displays the battlefield.
 */
public class GameScreen extends BaseScreen {

	private int stars;
	private GameSession session;
	private GameInputProcessor gameInputProcessor;
	@InjectAsset("audio/crash.wav")
	private Sound crashSound;
	@InjectAsset("audio/jam.wav")
	private Sound jamSound;
	@InjectAsset("audio/honk1.wav")
	private Sound honk1Sound;
	@InjectAsset("audio/honk2.wav")
	private Sound honk2Sound;
	@InjectAsset("audio/dying.wav")
	private Sound dyingSound;
	@InjectAsset("audio/street.wav")
	private Sound streetSound;

	private long backgroundSoundId;

	@Override
	protected void onInit() {
		gameInputProcessor = new GameInputProcessor();
		addInputProcessor(gameInputProcessor);
	}

	@Override
	public void show() {
		super.show();

		this.session = new GameSession(gameInputProcessor, game.getSpriteBatch(), game.getGameCamera(),
				game.getDebugCamera(), game.getEventBus());
		game.getEventBus().register(session);

		backgroundSoundId = streetSound.loop(3F);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getSpriteBatch().setProjectionMatrix(game.getGameCamera().combined);

		session.render(delta);

		if (game.showDebugStuff())
			session.renderDebug();

		session.update(delta);
	}

	@Subscribe
	public void onDeathEvent(JamSoundEvent ev) {
		switch (RandomUtils.getRandomNumber(1, 10)) {
		case 1: {
			jamSound.play(0.5F);
			break;
		}
		case 2:
		case 3:
		case 4:
		case 5:
		case 6: {
			honk1Sound.play(0.75F);
			break;
		}
		default:
		case 7:
		case 8:
		case 9:
		case 10: {
			honk2Sound.play(0.9F);
			break;
		}
		}
	}

	@Subscribe
	public void onDeathEvent(PlayerDeathEvent ev) {
		crashSound.play(1F);

		Timer.instance().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				game.pushScreen("game-death");
			}
		}, 1.5F);
	}

	@Subscribe
	public void onWinEvent(PlayerWinEvent ev) {
		game.pushScreen("game-win");
	}

	@Subscribe
	public void onGrannyContactEvent(GrannyContatcEvent ev) {
		dyingSound.play(1.1F);

		// TODO Sterne anzeigen
		if (ev.byPlayer)
			stars++;

		if (stars > 3)
			game.pushScreen("game-police-end");
	}

	@Override
	public void hide() {
		super.hide();

		streetSound.stop(backgroundSoundId);

		game.getEventBus().unregister(session);
		session.dispose();
		session = null;
	}

	@Override
	public void dispose() {
		// unused
	}

}
