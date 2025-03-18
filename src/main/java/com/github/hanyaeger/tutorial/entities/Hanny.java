package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import com.github.hanyaeger.tutorial.Waterworld;
import com.github.hanyaeger.tutorial.entities.text.HealthText;
import javafx.geometry.BoundingBox;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class Hanny extends DynamicSpriteEntity implements SceneBorderCrossingWatcher,  KeyListener, SceneBorderTouchingWatcher, Newtonian, Collided {

    private HealthText healthText;
    private int health = 10;
    private Waterworld waterworld;



    public Hanny(Coordinate2D location, HealthText healthText, Waterworld waterworld) {
        super("sprites/hanny.png", location, new Size(20,40), 1, 2);

        this.healthText = healthText;
        this.waterworld = waterworld;
        healthText.setHealthText(health);

        setGravityConstant(0.005);
        setFrictionConstant(0.04);
    }


    @Override
    public void notifyBoundaryCrossing(SceneBorder border){
        setAnchorLocationX(getSceneWidth());
        setAnchorLocationY(new Random().nextInt((int) getSceneHeight()- 81));
    }



    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if(pressedKeys.contains(KeyCode.LEFT)){
            setMotion(3,270d);
            setCurrentFrameIndex(0);
        } else if(pressedKeys.contains(KeyCode.RIGHT)){
            setMotion(3,90d);
            setCurrentFrameIndex(1);
        } else if(pressedKeys.contains(KeyCode.UP)){
            setMotion(3,180d);
        } else if(pressedKeys.contains(KeyCode.DOWN)){
            setMotion(3,0d);
        }

    }

    @Override
    public void notifyBoundaryTouching(SceneBorder border){
        setSpeed(0);

        switch(border){
            case TOP:
                setAnchorLocationY(1);
                break;
            case BOTTOM:
                setAnchorLocationY(getSceneHeight() - getHeight() - 1);
                break;
            case LEFT:
                setAnchorLocationX(1);
                break;
            case RIGHT:
                setAnchorLocationX(getSceneWidth() - getWidth() - 1);
            default:
                break;
        }
    }

    @Override
    public void onCollision(List<Collider> collidingObject){
        setAnchorLocation(new Coordinate2D(
                new Random().nextInt((int)(getSceneWidth()-getWidth())),
                new Random().nextInt((int)(getSceneHeight()-getHeight())))
        );

        health--;
        if (health <= 8){
            health = 10;
            waterworld.setActiveScene(2);
        }
        healthText.setHealthText(health);
    }


}
