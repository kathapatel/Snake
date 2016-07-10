import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class mainscreen {
	public static int pos_x =100;
	public static int pos_y =100;
	public static Texture texture;
	public static TrueTypeFont font,font2, font3;
	public static Color color_selected=Color.white,
						color=Color.black;
    public static int count=0;
    public static boolean antiAlias = true;
    public static List<Cordinate> data = new ArrayList<Cordinate>();
	public static Cordinate head= new Cordinate();
    public static int length=1;
    public static Audio background_music;
    methods method = new methods();
    public static int speed=50;
	
    
    public void start() {
		try {
			background_music = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/di-evantile_doomed-to-immortality.wav"));
			background_music.playAsMusic(1.0f, 1.0f, true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			try{
				head.x=100;
				head.y=100;
				data.add(head);
			}catch(Exception e){
				System.out.println(e);
			}
			System.out.println("hello1");
			Display.setDisplayMode(new DisplayMode(500, 500));
            Display.setTitle("CGV");
            Display.create();
        } catch (LWJGLException e) {
            System.err.println("Display wasn't initialized correctly.");
            System.exit(1);
        }
		
		init();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_SMOOTH);        
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_LIGHTING);                    
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);                
        GL11.glClearDepth(1);                                       
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
         GL11.glLoadIdentity();
         GL11.glOrtho(0,500,500,0,1,-1);
         GL11.glMatrixMode(GL11.GL_MODELVIEW);
 
        while (!Display.isCloseRequested()) {   		
        	method.render();
    		method.take_input();
            Display.update();
           Display.sync(speed);
        }
 
        Display.destroy();
        System.exit(0);
	}
	
	
    
    
    
    public void init() {
        
        try {
            // load texture from PNG file
            texture = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream("res/life_is_game_wallpaper_by_nullf-d46jc3n.png"));
            Font awtFont = new Font("Arial", Font.BOLD, 30);
            font = new TrueTypeFont(awtFont, antiAlias);
            awtFont = new Font("Arial", Font.BOLD, 50);
            font2=new TrueTypeFont(awtFont, antiAlias);
            awtFont = new Font("Arial", Font.BOLD, 40);
            font3=new TrueTypeFont(awtFont, antiAlias);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
    
    
    public static void main(String[] args) {
		mainscreen ms = new mainscreen();
    	ms.start();	
    }
}
