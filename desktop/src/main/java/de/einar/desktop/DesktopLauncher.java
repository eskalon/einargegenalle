package de.einar.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.einar.core.EinarGame;

/**
 * Starts the application for the desktop-based builds.
 */
public class DesktopLauncher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = EinarGame.NAME;
		config.height = 720;
		config.width = 1280;
		config.resizable = false;
		config.foregroundFPS = 120;
		config.addIcon("icons/icon16.png", Files.FileType.Absolute);
		config.addIcon("icons/icon32.png", Files.FileType.Absolute);
		config.addIcon("icons/icon48.png", Files.FileType.Absolute);

		try {
			// Start the game
			new LwjglApplication(new EinarGame(args != null && args.length > 0 && "--debug".equalsIgnoreCase(args[0])),
					config);
		} catch (Exception e) {
			exitWithError(String.format("Beim Starten des Spiels ist ein unerwarteter Fehler aufgetreten: %s", e));
		}
	}

	private static void exitWithError(String errorMsg) {
		System.err.println(errorMsg);
		System.exit(-1);
	}

}
