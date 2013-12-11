package com.brakassey.sunproject;

import com.badlogic.gdx.Game;
import com.brakassey.sunproject.screens.MenuScreen;

public class SunGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

}
