package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Score {
    private int currentScore;
    private float elapsedTime;

    public Score() {
        currentScore = 0;
    }

    public int getScore() {
        return currentScore;
    }

    public void addAirScore(int level2Count, int level3Count, int level4Count) {
        if (oneSecondElapsed()) {
            if (level2Count > 1) {
                this.currentScore += level2Count * 7;
            }
            if (level3Count > 1) {
                this.currentScore += level3Count * 10;
            }
            if (level4Count > 1) {
                this.currentScore += level4Count * 13;
            }
        }
    }

    private boolean oneSecondElapsed() {
        updateElapsedTime();
        if (this.elapsedTime > 1) {
            this.elapsedTime = 0;
            return true;
        } else {
            return false;
        }
    }

    private void updateElapsedTime() {
        this.elapsedTime += Gdx.graphics.getDeltaTime();
    }


    public void addHitScoreLevel1(int level1Count) {
        this.currentScore += level1Count * 1;
    }

    public void addHitScoreLevel2(int level2Count) {
        this.currentScore += level2Count * 5;
    }

    public void addHitScoreLevel3(int level3Count) {
        this.currentScore += level3Count * 8;
    }

    public void addHitScoreLevel4(int level4Count) {
        this.currentScore += level4Count * 12;
    }

    public void resetScore() {
        this.currentScore = 0;
    }

}
