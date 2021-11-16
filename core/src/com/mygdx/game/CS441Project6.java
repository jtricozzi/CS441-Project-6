package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class CS441Project6 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, goalImg, goalImg2, ballImg;
	Sprite player1, player2, goal, goal2, ball;


	float x, y, dx, dy, x2, y2, dx2, dy2, ballDy, ballDx;
	float w, h;

	Rectangle rectP1, rectP2, p1Goal, p2Goal;
	Circle ballCircle;

	int p1Flag, p2Flag, p1Score, p2Score;

	BitmapFont fontP1, fontP2;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		goalImg = new Texture("goalImg.png");
		goalImg2 = new Texture("goalImg2.png");
		ballImg = new Texture("ballImg.png");
		player1 = new Sprite(img);
		player2 = new Sprite(img);
		goal = new Sprite(goalImg);
		goal2 = new Sprite(goalImg2);
		ball = new Sprite(ballImg);

		fontP1 = new BitmapFont();
		fontP1.setColor(Color.BLACK);
		fontP1.getData().setScale(5);

		fontP2 = new BitmapFont();
		fontP2.setColor(Color.BLACK);
		fontP2.getData().setScale(5);

		p1Flag = p2Flag = 0;

		x = 1700;
		y = 0;
		x2 = 250;
		y2 = 0;
		dx = dx2 = ballDy = ballDx = p1Score = p2Score = 0;
		dy = dy2 = 0;

		rectP1 = player1.getBoundingRectangle();
		rectP2 = player2.getBoundingRectangle();
		p1Goal = goal.getBoundingRectangle();
		p2Goal = goal2.getBoundingRectangle();

		ballCircle = new Circle(800, 800, ballImg.getWidth() / 2);

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
					//p2Flag = 0;
				}else if(keycode == Input.Keys.A){
					//p2Flag = 0;
				}else if(keycode == Input.Keys.W){
					//p2Flag = 0;
				}else if(keycode == Input.Keys.S) {
					//p2Flag = 0;
				}

				return false;
			}
		});
	}

	@Override
	public void render () {
		ScreenUtils.clear(255, 255, 255, 1);

		if(p2Flag == 1) {
			//x2 < 0 ||
			if(x2 > h){

			}else {
				x2 += 20;
				dx2 = 20;
			}
		}else if (p2Flag == 2) {
			if(y2 > 200){

			}else {
				y2 += 20;
				dy2 = 20;
			}
		}else if (p2Flag == -1) {
			//x2 < h
			//|| x2 > h
			if(x2 < 0){

			}else {
				x2 += -20;
				dx2 = -20;
			}
		}else if (p2Flag == -2) {
			if(y2 < 0){

			}else {
				y2 -= 20;
				dy2 = -20;
			}
		}

		if(p1Flag == 1) {
			if(y > h){

			}else{
				y += 20;
				dx = 20;
			}

		}else if (p1Flag == 2) {
			if(x > w){

			}else {
				x += -20;
				dy = -20;
			}

		}else if (p1Flag == -1) {
			if(y < 0) {

			}else {
				y += -20;
				dx = -20;
			}

		}else if (p1Flag == -2) {

			if (x < 0){

			}else {
				x += 20;
				dy = 20;
			}

		}

		if(ballCircle.y > 0){
			ballCircle.y -= 10;
		}
		if(ballDx > 50){
			ballDx = 50;
		}else if (ballDx < -50) {
			ballDx = 50;
		}

		if(ballDy > 0){
			ballDy -= 1;
		}else if (ballDy < 0) {
			ballDy += 1;
		}


		if(ballCircle.x > h + 650 || ballCircle.x < 0) {
			ballDx = -ballDx;
		}
		if((ballCircle.y > w)){
			ballDy = -ballDy;
		}
		if (ballCircle.y < 1) {
			ballCircle.y += 10;
		}

		rectP1.x = x;
		rectP1.y = y;
		rectP2.x = x2;
		rectP2.y = y2;
		ballCircle.x += ballDx;
		ballCircle.y += ballDy;

		if(Intersector.overlaps(ballCircle, rectP1)){
			ballDx = -50;
			ballDy = dx + 25;

		}

		if(Intersector.overlaps(ballCircle, rectP2)){
			ballDx = 50;
			ballDy = dy2 + 25;

		}

		if(Intersector.overlaps(ballCircle, p1Goal)){
			p2Score++;
			ballCircle.y = 800;
			ballCircle.x = 800;
			ballDx = 0;
			ballDy = -10;

		}

		if(Intersector.overlaps(ballCircle, p2Goal)){
			p1Score++;
			ballCircle.y = 800;
			ballCircle.x = 800;
			ballDx = 0;
			ballDy = -10;
		}


		batch.begin();
		batch.draw(player2, x2, y2);
		batch.draw(player1, x, y);
		batch.draw(goal, -300, -100);
		batch.draw(goal2, 1750, -100);
		batch.draw(ball, ballCircle.x, ballCircle.y);
		fontP1.draw(batch, String.valueOf(p1Score), 1600, 850);
		fontP2.draw(batch, String.valueOf(p2Score), 200, 850);

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		goalImg.dispose();
		goalImg2.dispose();
		ballImg.dispose();
	}

	@Override
	public void resize (int width, int height) {
		w = width;
		h = height;
	}
}
