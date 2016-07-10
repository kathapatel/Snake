import org.lwjgl.opengl.Display;


public class collision {
	private static int x;
	private static int y;
	
	
	
	public static void check_colloision(){
		x=methods.head.x;
		y=methods.head.y;
		System.out.println(x +  " " + y);
		//snake
		for(int i=1;i<mainscreen.length;i++){
			if(x==mainscreen.data.get(i).x && y==mainscreen.data.get(i).y){
				System.out.println(x + " " + mainscreen.data.get(i).x + " " + y + " " + mainscreen.data.get(i).y);
				mainscreen.count=-2;
			}
		}
		//withblock
		for(int i=1;i<gamescreen.block.size();i++){
			System.out.println("cccccccccccc" + x + " " + gamescreen.block.get(i).x + " " + y + " " + gamescreen.block.get(i).y);
			if(x==gamescreen.block.get(i).x && y==gamescreen.block.get(i).y){
				mainscreen.count=-2;
			}
		}
		//outofbound!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if(methods.head.x>500 || methods.head.x<0 || methods.head.y>500 || methods.head.y<0){
			mainscreen.count=-2;
			Display.setTitle("Game Over!!! :( your score"+ gamescreen.score);
			
		}
		
	}
}
