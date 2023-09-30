package com.livelyspark.ludumdare54;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.livelyspark.ludumdare54.enums.Screens;
import com.livelyspark.ludumdare54.managers.IScreenManager;
import com.livelyspark.ludumdare54.screens.GameScreen;
import com.livelyspark.ludumdare54.screens.LoadingScreen;
import com.livelyspark.ludumdare54.screens.MainMenuScreen;
import com.livelyspark.ludumdare54.screens.WorkshopScreen;

import java.util.HashMap;

public class LudumDare54Game extends ApplicationAdapter implements IScreenManager {

	private Screen screen;
	private HashMap<Screens, Screen> screenStore = new HashMap<Screens, Screen>();
	private final AssetManager assetManager;

	private LoadingScreen loadingScreen;
	private MainMenuScreen mainMenuScreen;
	private GameScreen gameScreen;
	private WorkshopScreen workshopScreen;
	SpriteBatch batch;
	Texture img;

	public LudumDare54Game(){
		this.assetManager = new AssetManager();
	}

	public void switchScreen(Screens screen)  {
		switch (screen) {
			case Loading:
				if(loadingScreen == null){loadingScreen = new LoadingScreen(this, assetManager);}
				setScreen(loadingScreen);
				break;
			case MainMenu:
				if(mainMenuScreen == null){mainMenuScreen = new MainMenuScreen(this, assetManager);}
				gameScreen = null;
				setScreen(mainMenuScreen);
				break;
			case Game:
				gameScreen = new GameScreen(this, assetManager);
				setScreen(gameScreen);
				break;
			case Workshop:
				workshopScreen = new WorkshopScreen(this, assetManager);
				setScreen(workshopScreen);
				break;
		}
	}

	/** Sets the current screen. {@link Screen#hide()} is called on any old screen, and {@link Screen#show()} is called on the new
	 * screen, if any.
	 * @param screen may be {@code null} */
	private void setScreen (Screen screen) {
		if (this.screen != null)
		{
			this.screen.hide();
		}
		this.screen = screen;
		if (this.screen != null) {
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	/** @return the currently active {@link Screen}. */
	public Screen getScreen () {
		return screen;
	}

	@Override
	public void create() {
		switchScreen(Screens.Loading);
	}

	@Override
	public void resize(int width, int height) {
		if (screen != null) screen.resize(width, height);
	}

	@Override
	public void render() {
		if (screen != null) screen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void pause() {
		if (screen != null) screen.pause();
	}

	@Override
	public void resume() {
		if (screen != null) screen.resume();
	}

	@Override
	public void dispose() {
		if (screen != null) screen.hide();
	}
}
