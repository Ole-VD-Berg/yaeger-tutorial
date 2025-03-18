package com.github.hanyaeger.tutorial.scenes;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.hanyaeger.tutorial.Waterworld;
import com.github.hanyaeger.tutorial.buttons.RetryButton;
import com.github.hanyaeger.tutorial.buttons.StartButton;

public class RetryScene extends StaticScene {
    private Waterworld waterworld;

    public RetryScene(Waterworld waterworld) {
        this.waterworld = waterworld;
    }

    @Override
    public void setupScene() {
        setBackgroundAudio("audio/ocean.mp3");
        setBackgroundImage("backgrounds/background1.jpg");
    }

    @Override
    public void setupEntities(){
        RetryButton retryButton = new RetryButton(new Coordinate2D(getWidth() / 2, getHeight() / 2 + 100));
        addEntity(retryButton);
    }
}
