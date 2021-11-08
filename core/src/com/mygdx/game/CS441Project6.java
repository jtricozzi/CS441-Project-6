package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class CS441Project6 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, goalImg;
	Sprite player1, player2, goal;

	float x, y, dx, dy, x2, y2, dx2, dy2;
	float w, h;

	Rectangle rectP1, rectP2, p1Goal, p2Goal;

	int p1Flag, p2Flag;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		goalImg = new Texture("goalImg.png");
		player1 = new Sprite(img);
		player2 = new Sprite(img);
		goal = new Sprite(goalImg);

		p1Flag = p2Flag = 0;

		x = 1700;
		y = 450;
		x2 = 0;
		y2 = 0;
		dx = dx2 = 0;
		dy = dy2 = 0;

		rectP1 = player1.getBoundingRectangle();
		rectP2 = player1.getBoundingRectangle();
		p1Goal = goal.getBoundingRectangle();

		Gdx.input.setInputProcessor(new InputAdapter() {
			@Override public boolean keyDown (int keycode) {
				if(keycode == Input.Keys.RIGHT){
					p1Flag = 1;
				}else if(keycode == Input.Keys.LEFT){
					p1Flag = -1;
				}else if(keycode == Input.Keys.UP){
					p1Flag = 2;
				}else if(keycode == Input.Keys.DOWN){
					p1Flag = -2;
				}

				if(keycode == Input.Keys.D){
					p2Flag = 1;
				}else if(keycode == Input.Keys.A){
					p2Flag = -1;
				}else if(keycode == Input.Keys.W){
					p2Flag = 2;
				}else if(keycode == Input.Keys.S) {
					p2Flag = -2;
				}
				return true;
			}

			@Override public boolean keyUp (int keycode) {
				if(keycode == Input.Keys.RIGHT){
					p1Flag = 0;
				}else if(keycode == Input.Keys.LEFT){
					p1Flag = 0;
				}else if(keycode == Input.Keys.UP){
					p1Flag = 0;
				}else if(keycode == Input.Keys.DOWN){
					p1Flag = 0;
				}

				if(keycode == Input.Keys.D){
					p2Flag = 0;
				}else if(keycode == Input.Keys.A){
					p2Flag = 0;
				}else if(keycode == Input.Keys.W){
					p2Flag = 0;
				}else if(keycode == Input.Keys.S) {
					p2Flag = 0;
				}

				return false;
			}
		});
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 1);

		if(p2Flag == 1) {
			x2 += 20;
		}else if (p2Flag == 2) {
			y2 += 20;
		}else if (p2Flag == -1) {
			x2 += -20;
		}else if (p2Flag == -2) {
			y2 += -20;
		}

		if(p1Flag == 1) {
			y += 20;
		}else if (p1Flag == 2) {
			x += -20;
		}else if (p1Flag == -1) {
			y += -20;
		}else if (p1Flag == -2) {
			x += 20;
		}

		//x = x + dx;
		//y = y + dy;
		//x2 = x2 + dx2;
		//y2 = y2 + dy2;


		batch.begin();
		batch.draw(player2, x2, y2);
		batch.draw(player1, x, y);
		batch.draw(goal, 0, 0);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
