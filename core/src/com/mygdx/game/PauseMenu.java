package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class PauseMenu extends Stage {

    public PauseMenu(SpriteBatch batch, OrthographicCamera cam, int width, int height, StateManager manager) {
        super(new FitViewport(width, height,cam), batch);
        Animation<TextureRegion> pauseIconAnimation = AnimationFactory.createAnimation(new Texture(Gdx.files.internal("pauseIcon.png")),28, 29, 66, 0.07f);
        AnimationActor pauseIcon = new AnimationActor(pauseIconAnimation, width / 2 - pauseIconAnimation.getKeyFrame(0).getRegionWidth() / 2, height / 2 - pauseIconAnimation.getKeyFrame(0).getRegionHeight() / 2);

        ButtonActor exitButton = new ButtonActor("exitButton.png", "exitButtonPressed.png", 5, 147) {
            @Override
            public void onButtonClick() {
                manager.changeState(StateManager.State.MENU);
            }
        };
        this.addActor(pauseIcon);
        this.addActor(exitButton);
    }
}
