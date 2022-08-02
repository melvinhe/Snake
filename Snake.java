public class Snake {
	//array of x and y coordinates of snake
	private int[] x = new int[Board.getAllDots()];
	private int[] y = new int[Board.getAllDots()];
	
	//snake length
	private int snakeLen = 3; 
	
	//direction the snake is moving
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	
	//getter and setter methods
	public int getSnakeX(int index) {
	    return x[index];
	}
	
	public int getSnakeY(int index) {
	    return y[index];
	}
	
	public void setSnakeX(int i) {
	    x[0] = i;
	}
	
	public void setSnakeY(int i) {
	    y[0] = i;
	}
	public int getSnakeLen() {
	    return snakeLen;
	}
	
	public void setSnakeLen(int j) {
	    snakeLen = j;
	}
	public boolean isLeftDirection() {
	    return leftDirection;
	}
	
	public void setLeftDirection(boolean left) {
	    this.leftDirection = left;
	}
	
	public boolean isRightDirection() {
	    return rightDirection;
	}
	
	public void setRightDirection(boolean right) {
	    this.rightDirection = right;
	}
	
	public boolean isUpDirection() {
	    return upDirection;
	}
	
	public void setUpDirection(boolean up) {
	    this.upDirection = up;
	}
	
	public boolean isDownDirection() {
	    return downDirection;
	}
	
	public void setDownDirection(boolean down) {
	    this.downDirection = down;
	}

	//moves snake
	public void moveSnake() {
	    for (int i = snakeLen; i > 0; i--) {
	        x[i] = x[(i - 1)];
	        y[i] = y[(i - 1)];
	    }
	    if (upDirection) {
	        y[0] -= Board.getDotSize();
	    }
	    if (downDirection) {
	        y[0] += Board.getDotSize();
	    }
	    if (leftDirection) {
	        x[0] -= Board.getDotSize();
	    }
	    if (rightDirection) {
	        x[0] += Board.getDotSize();
	    }
	}
}
