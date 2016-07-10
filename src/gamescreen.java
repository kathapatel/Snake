import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class gamescreen {
	
	public static List<Cordinate> block = new ArrayList<Cordinate>();	
	public static int score=0;
	
	
	public void start(){	
			mainscreen.count=-1;
            System.out.println("katha");
	}
	
	
//generate obstacles and add to list	
	public void generate_block(){
		Random randomGenerator = new Random();
		Cordinate cord=new Cordinate();
		cord.x=(int) randomGenerator.nextInt(500);
		cord.x-=cord.x%10;
		cord.y=(int) randomGenerator.nextInt(500);
		cord.y-=cord.y%10;
		block.add(cord);
	}
	
	
	
//draw obstacles
	public void draw_block(){
		for (int i = 0; i < block.size(); i++) {
			System.out.println("block num: " + block.size());
			GL11.glBegin(GL11.GL_QUADS);
			GL11.glColor4d(1, 0, 1, 1);
			GL11.glVertex2f(block.get(i).x,
					block.get(i).y);
			GL11.glColor4d(0, 0, 0, 1);
			GL11.glVertex2f(block.get(i).x + 10,
					block.get(i).y);
			GL11.glColor4d(1, 0, 0, 1);
			GL11.glVertex2f(block.get(i).x + 10,
					block.get(i).y + 10);
			GL11.glColor4d(1, 0, 0, 1);
			GL11.glVertex2f(block.get(i).x,
					block.get(i).y + 10);
			GL11.glEnd();

		}
	}
}


class Cordinate{
	int x;
	int y;
}

