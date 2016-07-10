import java.io.IOException;
import java.util.Random;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;


public class food {
	public static int food_x=200;
	public static int food_y=200;
	
	
	
	public static void food_generator(){
		Random randomGenerator = new Random();
		food_x=(int) randomGenerator.nextInt(500);
		food_x-=food_x%10;
		food_y=(int) randomGenerator.nextInt(500);
		food_y-=food_y%10;
		
	}
	
	
	
	public static void eat(){
		gamescreen.score+=10;
		Cordinate new_part= new Cordinate();
		if (methods.state==0){
	    	new_part.x=mainscreen.data.get(mainscreen.length-1).x+10;
	    	new_part.y=mainscreen.data.get(mainscreen.length-1).y;
	    }else if (methods.state==1){
	    	new_part.x=mainscreen.data.get(mainscreen.length-1).x;
	    	new_part.y=mainscreen.data.get(mainscreen.length-1).y+10;
	    }else if (methods.state==2){
	    	new_part.x=mainscreen.data.get(mainscreen.length-1).x-10;
	    	new_part.y=mainscreen.data.get(mainscreen.length-1).y;
	    }else if (methods.state==3){
	    	new_part.x=mainscreen.data.get(mainscreen.length-1).x;
	    	new_part.y=mainscreen.data.get(mainscreen.length-1).y-10;
	    }
		mainscreen.data.add(new_part);
		mainscreen.length++;
		food_generator();
	}
}

