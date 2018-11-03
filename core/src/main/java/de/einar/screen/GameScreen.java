package de.einar.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Timer;
import com.google.common.eventbus.Subscribe;

import de.einar.core.GameSession;
import de.einar.events.GrannyContatcEvent;
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
	public void onDeathEvent(PlayerDeathEvent ev) {
		System.out.println("Tod");

		Timer.instance().scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				game.pushScreen("game-death");
			}
		}, 1.5F);
	}

	@Subscribe
	public void onWinEvent(PlayerWinEvent ev) {
		game.pushScreen("game-end");
	}

	@Subscribe
	public void onDeathEvent(GrannyContatcEvent ev) {
		// TODO Sterne anzeigen
		if (ev.byPlayer)
			stars++;

		if (stars > 3)
			game.pushScreen("game-police-end");
	}

	@Override
	public void hide() {
		super.hide();

		game.getEventBus().unregister(session);
		session.dispose();
		session = null;
	}

	@Override
	public void dispose() {
		// unused
	}

}
