import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {
	private final static int BWIDTH = 800;
	private final static int BHEIGHT = 800;
	private final static int PSIZE = 25;
	private final static int TOTALPIXELS = (BWIDTH * BHEIGHT)/(PSIZE * PSIZE);
	private int highScore=0;
	//private DrawingPanel panel=new DrawingPanel(300,300);
	private static int speed = 75;
	private boolean isAlive = true;
	
	private Timer timer;
	private Snake s = new Snake();
	private Food apple = new Food();
	
	//constructs board
	public Board() {
	    //setBackground(Color.WHITE);
	    setFocusable(true);
	    addKeyListener(new Keys());
	    setPreferredSize(new Dimension(BWIDTH, BHEIGHT));
	    playGame();
	}
	
	//getter methods
	public static int getAllDots() {
	    return TOTALPIXELS;
	}
	
	public static int getDotSize() {
	    return PSIZE;
	}

	//paints graphics
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    draw(g);
	}
	//draws snake and the food
	public void draw(Graphics g) {
		
	    // Only draw if the snake is alive
	    if (isAlive == true) {
	        Color gr=new Color(0,153,0);
        	Color tongue=new Color(231,158,169);
        	
        	// apple
	        g.setColor(Color.RED);
	        g.fillOval(apple.getFoodX(), apple.getFoodY(), 20, 20);
	        
	        //stem
	        Color brown =new Color(139,69,19);
	        g.setColor(brown);
	        g.fillRect(apple.getFoodX()+9, apple.getFoodY()-2,3,5);
	        
	    	//leaf
        	g.setColor(gr);
        	g.fillRoundRect(apple.getFoodX()+11, apple.getFoodY()-1,4,4,4,4);
        	
	        // Draw snake
	        for (int i = s.getSnakeLen()-1; i >= 0; i--) {
	        	
	            // Snake's head
	            if (i == 0) {
	                g.setColor(gr);
 	                g.fillRoundRect(s.getSnakeX(i), s.getSnakeY(i),
 	                		25, 25, PSIZE, PSIZE);
	                if(s.isRightDirection()) {
	                	//creates tongue
		                g.setColor(tongue);
		                g.fillOval(s.getSnakeX(i)+PSIZE*3/4,s.getSnakeY(i)+PSIZE*5/16
	                			, PSIZE*7/16, PSIZE/2);
		                g.setColor(gr);
		                g.fillRoundRect(s.getSnakeX(i), s.getSnakeY(i),
	 	                		PSIZE, PSIZE, PSIZE, PSIZE);
		                //creates eyes
	                	g.setColor(Color.BLACK);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE*5/8,s.getSnakeY(i)+PSIZE*5/16
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE*5/8,s.getSnakeY(i)+PSIZE*5/8
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
		                g.setColor(gr);
	                }else if(s.isDownDirection()) {
	                	//creates tongue
		                g.setColor(tongue);
		                g.fillOval(s.getSnakeX(i)+PSIZE*5/16,s.getSnakeY(i)+PSIZE*3/4
	                			, PSIZE*7/16, PSIZE/2);
		                g.setColor(gr);
		                g.fillRoundRect(s.getSnakeX(i), s.getSnakeY(i),
	 	                		PSIZE, PSIZE, PSIZE, PSIZE);
		                //creates eyes
	                	g.setColor(Color.BLACK);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE/4,s.getSnakeY(i)+PSIZE*5/8
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE*5/8,s.getSnakeY(i)+PSIZE*5/8
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
		                g.setColor(gr);

	                }else if(s.isLeftDirection()) {
	                	//creates tongue
		                g.setColor(tongue);
		                g.fillOval(s.getSnakeX(i)-PSIZE/4,s.getSnakeY(i)+PSIZE*5/16
		                		, PSIZE/2, PSIZE*7/16);
		                g.setColor(gr);
		                g.fillRoundRect(s.getSnakeX(i), s.getSnakeY(i),
	 	                		PSIZE, PSIZE, PSIZE, PSIZE);
		                //creates eyes
	                	g.setColor(Color.BLACK);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE*3/8,s.getSnakeY(i)+PSIZE*5/16
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE*3/8,s.getSnakeY(i)+PSIZE*5/8
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
		                g.setColor(gr);
	                }else {
	                	//creates tongue
		                g.setColor(tongue);
		                g.fillOval(s.getSnakeX(i)+PSIZE*5/16,s.getSnakeY(i)-PSIZE/4
	                			, PSIZE*7/16, PSIZE/2);
		                g.setColor(gr);
		                g.fillRoundRect(s.getSnakeX(i), s.getSnakeY(i),
	 	                		PSIZE, PSIZE, PSIZE, PSIZE);
		                //creates eyes
	                	g.setColor(Color.BLACK);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE/4,s.getSnakeY(i)+PSIZE*5/16
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
	                	g.fillRoundRect(s.getSnakeX(i)+PSIZE*5/8,s.getSnakeY(i)+PSIZE*5/16
	                			, PSIZE/6, PSIZE/6, PSIZE/6, PSIZE/6);
		                g.setColor(gr);
	                }
	               
	                // Body of snake
	            } else { 
		            	g.fillRoundRect(s.getSnakeX(i), s.getSnakeY(i),
 	                		25, 25, PSIZE, PSIZE);
	            }
	        }
	    } else {
	    	//updates high score
	    	if((s.getSnakeLen()-3)>highScore) {
	    		highScore=(s.getSnakeLen()-3);
	    	}
	    	
	    	//ends game
	        endGame(g);
	    }
	}
	
	//plays through game
	public void playGame() {
		//sets initial snake length and direction
	    s.setSnakeLen(3); 
	    s.setRightDirection(true);
		
	    //sets snake's starting location
	    for (int i = 0; i < s.getSnakeLen(); i++) {
	        s.setSnakeX(BWIDTH / 2);
	        s.setSnakeY(BHEIGHT / 2);
	    }	
	    apple.generate();
	    timer = new Timer(speed, this);
	    timer.start();
	}
	//checks when the snake eats apples
	//prints statements
	public void checkFoodCollisions() {
	
	    if ((proximity(s.getSnakeX(0), apple.getFoodX(), 20))&& (proximity(s.getSnakeY(0), apple.getFoodY(), 20))) {
	    	
	    	//prints random messages when apples are eaten
	    	int rand=(int)(Math.random()*5);
	    	if(rand==0) {
		        System.out.print("Yum!          ");
	    	}else if(rand==1) {
	    		System.out.print("Keep eating!  ");
	    	}else if(rand==2) {
	    		System.out.print("I love apples!");
	    	}else if(rand==3) {
	    		System.out.print("Nom Nom Nom...");
	    	}else if(rand==4) {
	    		System.out.print("Delicious!    ");
	    	}
	    	
	    	//prints the number of apples eaten
	    	if(s.getSnakeLen()-2==1) {
		    	System.out.println("\t You ate "+(s.getSnakeLen()-2)+" apple.");
	    	}else {
		    	System.out.println("\t You ate "+(s.getSnakeLen()-2)+" apples.");
	    	}
	    	
	    	//updates snake length
	        s.setSnakeLen(s.getSnakeLen() + 1);
	        apple.generate();
	    }
	}
	
	// Used to check collisions with snake's self and board edges
	public void checkCollisions() {
		
		//checks if snake hits itself
	    for (int i = s.getSnakeLen(); i > 0; i--) {
	        if (s.getSnakeLen()>3&&(s.getSnakeX(0) == s.getSnakeX(i) 
	        		&& (s.getSnakeY(0) == s.getSnakeY(i)))) {
	            isAlive = false; 
	        }
	    }
	    
	    //checks if snake transgresses boundaries of the board
	    if (s.getSnakeY(0) >= BHEIGHT) {
	        isAlive = false;
	    }
	    if (s.getSnakeX(0) >= BWIDTH) {
	        isAlive = false;
	    }	
	    if (s.getSnakeY(0) < 0) {
	        isAlive = false;
	    }
	    if (s.getSnakeX(0) < 0) {
	        isAlive = false;
	    }
	    
	    //stops timer when snake is dead
	    if (!isAlive) {
	        timer.stop();
	    }
	}
	//generates end screen, message, and scoreboard
	public void endGame(Graphics g) {
		Color gold=new Color(218,165,32);
		Color lavender=new Color(230,230,250);
	    Font font = new Font("Helvetica", Font.BOLD, 30);
	    FontMetrics metrics = getFontMetrics(font);
	    g.setColor(Color.red);
	    g.setFont(font);
	
	    // End Screen
	    String message = "Game Over";
	    g.drawString(message, (BWIDTH - metrics.stringWidth(message)) / 2,
	            BHEIGHT / 2);
	
	    Font font1 = new Font("Helvetica", Font.PLAIN, 10);
	    g.setColor(Color.BLACK);
	    g.setFont(font1);
	    String message1 = "        (\"Enter\" to play again!)";
	    g.drawString(message1, (BWIDTH - metrics.stringWidth(message)) / 2,
	            BHEIGHT / 2+20);
	    String message5 = "(Or, press 1,2,3,4 to change speed)";
	    g.drawString(message5, (BWIDTH - metrics.stringWidth(message)) / 2,
	            BHEIGHT / 2+35);
	    
	    //scoreboard
	    g.setColor(lavender);
		g.fillRoundRect(320, 625, 160, 80, 20, 20);
		g.setColor(gold);
		g.drawRoundRect(320, 625, 160, 80, 20, 20);
		
	    //Scores
	    Font font2 = new Font("Helvetica", Font.BOLD, 15);
	    g.setColor(Color.BLACK);
	    g.setFont(font2);
	    String message2 = ("     Apples Eaten:   "+(s.getSnakeLen()-3)+"");
	    g.drawString(message2, (BWIDTH - metrics.stringWidth(message)) / 2,
	            BHEIGHT / 2+260);
	    
	    //high score
	    Font font3 = new Font("Helvetica", Font.BOLD, 15);
	    g.setColor(gold);
	    g.setFont(font3);
	    String message3 = ("       High Score:    "+highScore);
	    g.drawString(message3, (BWIDTH - metrics.stringWidth(message)) / 2,
	            BHEIGHT / 2+280);
	    
	    //Endgame println statements
	    System.out.println("Game Over :(");
	    System.out.println("Please press \"Enter\" to continue!");
	    System.out.println();
	}
	
	//checks the poximity of the snake with the apple
	private boolean proximity(int a, int b, int closeness) {
	    return Math.abs((long) a - b) <= closeness;
	}
	
	//continually checks food eaten and other collisions while snake's alive
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (isAlive == true) {
	
	        checkFoodCollisions();
	        checkCollisions();
	        s.moveSnake();
	    }
	    repaint();
	}
	
	//checks the keys hit that allow the snake to move
	//also allows player to change speed
	private class Keys extends KeyAdapter {
	
	    @Override
	    public void keyPressed(KeyEvent e) {
	
	        int key = e.getKeyCode();
	        //changes speed of snake when 1,2,3,4 is pressed
	        if ((key == KeyEvent.VK_1) && (isAlive == false)) {
	        	
	            isAlive = true;
	            speed=150;
	            s.setDownDirection(false);
	            s.setRightDirection(false);
	            s.setLeftDirection(false);
	            s.setUpDirection(false);
	            playGame();
	        }
	        if ((key == KeyEvent.VK_2) && (isAlive == false)) {
	        	
	            isAlive = true;
	            speed=75;
	            s.setDownDirection(false);
	            s.setRightDirection(false);
	            s.setLeftDirection(false);
	            s.setUpDirection(false);
	            playGame();
	        }
	        if ((key == KeyEvent.VK_3) && (isAlive == false)) {
	        	
	            isAlive = true;
	            speed=50;
	            s.setDownDirection(false);
	            s.setRightDirection(false);
	            s.setLeftDirection(false);
	            s.setUpDirection(false);
	            playGame();
	        }
	        if ((key == KeyEvent.VK_4) && (isAlive == false)) {
	        	
	            isAlive = true;
	            speed=25;
	            s.setDownDirection(false);
	            s.setRightDirection(false);
	            s.setLeftDirection(false);
	            s.setUpDirection(false);
	            playGame();
	        }	
	        
	        //moves snake up, down, left, right
	        if ((key == KeyEvent.VK_UP) && (!s.isDownDirection())) {
	            s.setUpDirection(true);
	            s.setRightDirection(false);
	            s.setLeftDirection(false);
	        }
	        if ((key == KeyEvent.VK_DOWN) && (!s.isUpDirection())) {
	            s.setDownDirection(true);
	            s.setRightDirection(false);
	            s.setLeftDirection(false);
	        }
	        if ((key == KeyEvent.VK_LEFT) && (!s.isRightDirection())) {
	            s.setLeftDirection(true);
	            s.setUpDirection(false);
	            s.setDownDirection(false);
	        }
	        if ((key == KeyEvent.VK_RIGHT) && (!s.isLeftDirection())) {
	            s.setRightDirection(true);
	            s.setUpDirection(false);
	            s.setDownDirection(false);
	        }
	        
	        //continues game without changing speed
	        if ((key == KeyEvent.VK_ENTER) && (isAlive == false)) {
	
	            isAlive = true;
	            s.setDownDirection(false);
	            s.setRightDirection(false);
	            s.setLeftDirection(false);
	            s.setUpDirection(false);
	            playGame();
	        }
	    }
	}
}
