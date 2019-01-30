package commandline;

public class Game {
	
	public static void main(String[] args) {	//will merge into template later...
//		int i = 0;
//		i = (int) (Math.ceil(Math.random()*5));
//		System.out.println(i);
		TTModel model = new TTModel();
		TTView view = new TTView(model);
		TTController controller = new TTController(model,view);
		controller.startGame();
		
	}
	
	
	
	
}
