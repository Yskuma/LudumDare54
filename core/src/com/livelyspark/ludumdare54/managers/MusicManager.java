package com.livelyspark.ludumdare54.managers;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.livelyspark.ludumdare54.StaticConstants;
import com.livelyspark.ludumdare54.enums.Screens;

public class MusicManager {
    private final AssetManager assetManager;
    public Music TheMusic = null;

    Music musicWorkshop;
    Music musicBattle;


    public MusicManager(AssetManager assetManager)
    {
        this.assetManager = assetManager;
    }

    public void LoadMusic()
    {
        musicWorkshop = assetManager.get("music/WorkshopSong.ogg", Music.class);
        musicBattle = assetManager.get("music/BattleMusic.ogg", Music.class);
    }

    public void PickMusic(Screens screen){
        StopMusic();

        switch (screen) {
            case Game:
                TheMusic = musicBattle;
                break;
            case Shipyard:
                TheMusic = musicWorkshop;
                break;
        }

        if(TheMusic != null){
            TheMusic.setVolume(StaticConstants.musicVolume);
            TheMusic.setLooping(true);
            TheMusic.play();
        }
    }

    public void StopMusic(){
        if(TheMusic != null){
            TheMusic.stop();
        }
    }
}
