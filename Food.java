public class Food {

	private Snake s = new Snake();
	private int foodX;
	private int foodY; 
	
	//getter methods
	public int getFoodX() {
	    return foodX;
	}
	
	public int getFoodY() {
	    return foodY;
	}
	
	//makes snake
	public void generate() {
		
	    int location = (int) (Math.random() * 30);
	    foodX = ((location * Board.getDotSize()));
	
	    location = (int) (Math.random() * 30);
	    foodY = ((location * Board.getDotSize()));
	
	    if ((foodX == s.getSnakeX(0)) && (foodY == s.getSnakeY(0))) {
	        generate();
	    }
	}
}
