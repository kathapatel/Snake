import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class methods {
	int num = 0;
	public static int state = 0;
	public static int new_state = 0;
	public static Cordinate head = mainscreen.head;
	gamescreen gs = new gamescreen();
	
	
	
	
	
	public void take_input() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (mainscreen.count == -2) {
				} else if (mainscreen.count != -1) {
					if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
						mainscreen.count = 1;
					} else if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
						mainscreen.count = 0;
					} else if (Keyboard.getEventKey() == Keyboard.KEY_SPACE) {
						if (mainscreen.count == 1) {
							System.out.println("here");
							Display.destroy();
							System.exit(0);
						} else {
							gamescreen gs = new gamescreen();
							gs.start();
						}
					}

				} else {
					if (Keyboard.getEventKey() == Keyboard.KEY_DOWN) {
						new_state = 3;

						if (new_state != state) {
							System.out.println("again");
							head.y += 10;
						}

					} else if (Keyboard.getEventKey() == Keyboard.KEY_UP) {
						new_state = 1;
						if (new_state != state)
							head.y -= 10;
					} else if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
						new_state = 0;
						if (new_state != state)
							head.x -= 10;
					} else if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
						new_state = 2;
						if (new_state != state)
							head.x += 10;
					}
				}
			}
		}
	}

	
	
	
	
	
	public void render() {
		int add = 0;
		Color.white.bind();
		mainscreen.texture.bind(); // or GL11.glBind(texture.getTextureID());
		if (mainscreen.count != -1) // offset-jugaad
			add = 10;
		else
			add = 0;
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0 + add, 0 + add);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(500, 0 + add);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(500, 500);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0 + add, 500);
		GL11.glEnd();
		if (mainscreen.count == 0) {
			mainscreen.font2.drawString(70, 350, "Play Game",
					mainscreen.color_selected);
			mainscreen.font.drawString(70, 400, "Exit", mainscreen.color);
		} else if (mainscreen.count == 1) {
			mainscreen.font.drawString(70, 350, "Play Game", mainscreen.color);
			mainscreen.font2.drawString(70, 400, "Exit",
					mainscreen.color_selected);
		} else if (mainscreen.count == -2) {
			try {
				mainscreen.texture = TextureLoader
						.getTexture(
								"png",
								ResourceLoader
										.getResourceAsStream("res/life_is_game_wallpaper_by_nullf-d46jc3n.png"));
				mainscreen.texture.bind();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mainscreen.font2.drawString(70, 350, "Game Over....!!",
					mainscreen.color_selected);
		} else {
			
			mainscreen.texture.release();
			draw(); //next position of snake
			for (int i = 0; i < mainscreen.length; i++) {

				GL11.glBegin(GL11.GL_QUADS);
				GL11.glColor4d(1, 0, 0, 1);
				GL11.glVertex2f(mainscreen.data.get(i).x,
						mainscreen.data.get(i).y);
				GL11.glColor4d(1, 0, 0, 1);
				GL11.glVertex2f(mainscreen.data.get(i).x + 10,
						mainscreen.data.get(i).y);
				GL11.glColor4d(1, 0, 0, 1);
				GL11.glVertex2f(mainscreen.data.get(i).x + 10,
						mainscreen.data.get(i).y + 10);
				GL11.glColor4d(1, 0, 0, 1);
				GL11.glVertex2f(mainscreen.data.get(i).x,
						mainscreen.data.get(i).y + 10);
				GL11.glEnd();

			}
			state = new_state;
		}
	}

	
	
	
	
	
	
	
	public void draw() {
		
		//every time check colloision
		collision.check_colloision();

		//Lunch-time!! ;)
		if (head.x == food.food_x && head.y == food.food_y) {
			food.eat();
		}
		//everytime display food
		num++;
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4d(1, 0, 1, 1);
		GL11.glVertex2f(food.food_x, food.food_y);
		GL11.glVertex2f(food.food_x + 10, food.food_y);
		GL11.glVertex2f(food.food_x + 10, food.food_y + 10);
		GL11.glVertex2f(food.food_x, food.food_y + 10);
		GL11.glEnd();
		gs.draw_block();
		if (num % 10 == 0) {
			gamescreen.score += 5;
			Display.setTitle("Score     " + gamescreen.score);
			if(num%100==0)
			if(gamescreen.score>200) gs.generate_block();
	if(gamescreen.score%200==0) mainscreen.speed+=20;
			
			for (int i = mainscreen.length - 1; i > 0; i--) {
				mainscreen.data.get(i).x = mainscreen.data.get(i - 1).x;
				mainscreen.data.get(i).y = mainscreen.data.get(i - 1).y;

			}
			if (new_state == 3) {
				head.y += 10;
				mainscreen.data.get(0).y = head.y;
			} else if (new_state == 1) {
				head.y -= 10;
				mainscreen.data.get(0).y = head.y;
			} else if (new_state == 0) {
				head.x -= 10;
				mainscreen.data.get(0).x = head.x;
			} else if (new_state == 2) {
				head.x += 10;
				mainscreen.data.get(0).x = head.x;
			}

		}
	}
}
