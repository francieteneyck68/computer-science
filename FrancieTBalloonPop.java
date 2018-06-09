package Classwork;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//Frances TenEyck
//Period H
//May 8, 2018
//Description: this is an applet that will create a game where you have to "pop" as 20 balloons as fast as you can
//

public class FrancieTBalloonPop extends JoeApplet implements KeyListener , MouseListener , MouseMotionListener
{
	Random gen = new Random();
	
	//ints for the random speeds
	int upper1 = 10;
	int lower1 = 5;
	int upper2 = 11;
	int lower2 = 7;
	int upper3 = 10;
	int lower3 = 5;
	int upper4 = 10;
	int lower4 = 5;
	int upper5 = 11;
	int lower5 = 7;
	int range;
	
	//ints for the score and timer
	int score = 0;
	int counter;
	int count;
	int timer;
	int seconds;
	
	//ints for placement of first balloons
	int balloonX = 100;
	int balloonY = 100;
	int balloon2X = 250;
	int balloon2Y = 100;
	int balloon3X = 400;
	int balloon3Y = 100;
	int balloon4X = 550;
	int balloon4Y = 100;
	int balloon5X = 700;
	int balloon5Y = 100;
	
	//ints for placement of second balloons
	int balloon6Y = -200;
	int balloon7Y = -200;
	int balloon8Y = -200;
	int balloon9Y = -200;
	int balloon10Y = -200;
	int balloon6X = 150;
	int balloon7X = 250;
	int balloon8X = 350;
	int balloon9X = 450;
	int balloon10X = 550;
	int speedBalloonY1, speedBalloonY2,speedBalloonY3, speedBalloonY4, speedBalloonY5;
	
	int mouseX, mouseY;
	int rectX = 100;
	int rectY = 100;
	
	
	//creates balloons and mouse as solid object
	SolidObject balloon1 = new SolidObject();
	SolidObject balloon2 = new SolidObject();
	SolidObject balloon3 = new SolidObject();
	SolidObject balloon4 = new SolidObject();
	SolidObject balloon5 = new SolidObject();
	SolidObject balloon6 = new SolidObject();
	SolidObject balloon7 = new SolidObject();
	SolidObject balloon8 = new SolidObject();
	SolidObject balloon9 = new SolidObject();
	SolidObject balloon10 = new SolidObject();
	SolidObject mouseSO = new SolidObject();
	SolidObject mouseRect = new SolidObject();
	
	Color skyBlue = new Color(135,206,235);
	Color randomColor;
	Color[] colors = {Color.red, Color.green, Color.blue, Color.yellow, Color.magenta};
	
	Font newFont = new Font("Comic Sans", Font.BOLD, 75);
	Font newFont2 = new Font("Verdana", Font.BOLD,50);
	Font newFont3 = new Font("Times New Roman",Font.BOLD,25);
	
	boolean home = true;
	boolean playGame = true;
	boolean instruction = true;
	
	//happens once at the beginning of the program
	public void init()
	{
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	
	}
	
	//this method creates the home screen
	public void homeScreen(Graphics art)
	{
		
		art.setColor(Color.pink);
		art.setFont(newFont);
		art.drawString("Balloon Pop", 200, 200);
		art.setFont(newFont2);
		art.drawString("Press Z for instructions", 100, 325);
		art.drawString("Hit the spacebar to start", 100, 450);
		
	}
	
	//this method creates the instruction screen with the instructions
	public void drawInstructions(Graphics art)
	{
		art.setColor(Color.green);
		art.setFont(newFont3);
		art.drawString("The goal of this game is to pop 20 balloons as fast as you can.", 100, 100);
		art.drawString("A timer will be counting to see how fast you are.", 150, 200);
		art.drawString("Click on the balloon that matches the color of the ballon in the upper right corner.",5,300);
		art.drawString("If you click on the right one, your score will go up.", 100, 400);
		art.drawString("If you click on the wrong one, your score will go down.", 100, 500);
		art.drawString("Press B to go back", 300, 600);
		
	}
	
	//this method draws the first set of balloons that are going to fall
	public void drawBalloon(Graphics art)
	{
		art.setColor(Color.green);
		art.fillOval(balloonX,balloonY,60,75);
		balloon1.makeSolidObject(balloonX,balloonY,60,75);
		art.setColor(Color.red);
		art.fillOval(balloon2X,balloon2Y,60,75);
        balloon2.makeSolidObject(balloon2X,balloon2Y,60,75);
		art.setColor(Color.blue);
		art.fillOval(balloon3X, balloon3Y,60,75);
		balloon3.makeSolidObject(balloon3X,balloon3Y,60,75);
		art.setColor(Color.yellow);
		art.fillOval(balloon4X, balloon4Y,60,75);
		balloon4.makeSolidObject(balloon4X,balloon4Y,60,75);
		art.setColor(Color.magenta);
		art.fillOval(balloon5X, balloon5Y,60,75);
		balloon5.makeSolidObject(balloon5X,balloon5Y,60,75); 
	}
	
	//this method draws the second set of balloons that fall shortly after the first set
	public void drawBalloon2(Graphics art)
	{
		art.setColor(Color.green);
		art.fillOval(balloon6X,balloon6Y,60,75);
		balloon6.makeSolidObject(balloon6X,balloon6Y,60,75);
		art.setColor(Color.red);
		art.fillOval(balloon7X,balloon7Y,60,75);
		balloon7.makeSolidObject(balloon7Y,balloon7Y,60,75);
		art.setColor(Color.blue);
		art.fillOval(balloon8X, balloon8Y,60,75);
		balloon8.makeSolidObject(balloon8X,balloon8Y,60,75);
		art.setColor(Color.yellow);
		art.fillOval(balloon9X, balloon9Y,60,75);
		balloon9.makeSolidObject(balloon9X,balloon9Y,60,75);
		art.setColor(Color.magenta);
		art.fillOval(balloon10X, balloon10Y,60,75);
		balloon10.makeSolidObject(balloon10X,balloon10Y,60,75);
	}
	
	//there are 5 different speed ranges to assign random speeds so each balloon isn't falling at the same time
	//this is one method to create a random speed for the balloons speed of falling down
	public void speedRange1()
	{
		range = (gen.nextInt(upper1 - lower1)+1) + lower1;
		speedBalloonY1 = range;
	}
	
	//this is a second method to create a random speed for the balloons speed of falling down
	public void speedRange2()
	{
		range = (gen.nextInt(upper2 - lower2)+1) + lower2;
		speedBalloonY2 = range;
	}
	
	//this is a third method to create a random speed for the balloons speed of falling down
	public void speedRange3()
	{
		range = (gen.nextInt(upper3 - lower3)+1) + lower3;
		speedBalloonY3 = range;
	}
	
	//this is a fourth method to create a random speed for the balloons speed of falling down
	public void speedRange4()
	{
		range = (gen.nextInt(upper4 - lower4)+1) + lower4;
		speedBalloonY4 = range;
	}
	
	//this is the last method to create a random speed for the balloons speed of falling down
	public void speedRange5()
	{
		range = (gen.nextInt(upper5 - lower5)+1) + lower5;
		speedBalloonY5 = range;
	}
	
	//this draws the score that will be on the upper left hand corner 
	public void drawScore(Graphics art)
	{
		art.setColor(Color.black);
		art.drawString("Score ="+score, 50, 50);
		if(score >= 20)
		{
			playGame = false;
		}
	}
	
	//this creates the timer that is able to count by seconds to time the user playing the game
	public void drawTimer(Graphics art)
	{
		counter++;
		if(counter % 60 == 0)
		{
			seconds++;
		}
		art.drawString("Time ="+seconds, 50, 120);
	}
	
	//this method allows for the first set of balloons to move down the screen
	public void movement()
	{
		balloonY = balloonY + speedBalloonY1;
		if(balloonY > 700)
		{
			balloonY = -50;
			balloonX = (gen.nextInt(900)-75);
		}
		balloon2Y = balloon2Y + speedBalloonY2;
		if(balloon2Y > 700)
		{
			balloon2Y = -50;
			balloon2X = (gen.nextInt(900)-75);
		}
		balloon3Y = balloon3Y + speedBalloonY3;
		if(balloon3Y > 700)
		{
			balloon3Y = -50;
			balloon3X = (gen.nextInt(900)-75);
		}
		balloon4Y = balloon4Y + speedBalloonY4;
		if(balloon4Y > 700)
		{
			balloon4Y = -50;
			balloon4X = (gen.nextInt(900)-75);
		}
		
		balloon5Y = balloon5Y + speedBalloonY5;
		if(balloon5Y > 700)
		{
			balloon5Y = -50;
			balloon5X = (gen.nextInt(900)-75);
		}
	}
	
	//this method allows for the second set of balloons to fall down the screen
	public void movement2()
	{
		balloon6Y = balloon6Y + speedBalloonY1;
		if(balloon6Y > 700)
		{
			balloon6Y = -100;
			balloon6X = (gen.nextInt(900)-75);
		}
		balloon7Y = balloon7Y + speedBalloonY2;
		if(balloon7Y > 700)
		{
			balloon7Y = -100;
			balloon7X = (gen.nextInt(900)-75);
		}
		balloon8Y = balloon8Y + speedBalloonY3;
		if(balloon8Y > 700)
		{
			balloon8Y = -100;
			balloon8X = (gen.nextInt(900)-75);
		}
		balloon9Y = balloon9Y + speedBalloonY4;
		if(balloon9Y > 700)
		{
			balloon9Y = -100;
			balloon9X = (gen.nextInt(900)-75);
		}
		balloon10Y = balloon10Y + speedBalloonY5;
		if(balloon10Y > 700)
		{
			balloon10Y = -100;
			balloon10X = (gen.nextInt(900)-75);
		}
	}
	
	//this method draws the game over screen at the end of the game
	public void gameOver(Graphics art)
	{
		art.setColor(Color.blue);
		art.drawString("Game Over",300,100);
		art.drawString("Your time is "+seconds+" seconds", 200, 300);
		art.drawString("Press the R to restart the game",10,500);
	}
	
	//this method draws the balloon at the upper right hand corner that randomly changes color to tell the user
	//which balloon to click
	public void randBalloonColor(Graphics art)
	{
		art.setColor(Color.white);
		art.fillRect(0, 0, 900, 150);
		count++;
		if(count % 100 == 0)
		{
			randomColor = colors[gen.nextInt(colors.length)];
		}
		art.setColor(Color.red);
		art.setColor(randomColor);
		art.fillOval(750, 47, 55, 70);
		
	}
	
	public void rectMouse(Graphics art)
	{
		art.setColor(skyBlue);
		art.fillRect(mouseX,mouseY, 30,30);
		mouseRect.makeSolidObject(mouseX, mouseY, 30, 30);
		
	}
	
	//this contains the methods and the different statements that allow for the screens to change
	public void paint(Graphics art)
	{ 
		setSize(900,700);
		
		if(home)
		{
			setBackground(Color.cyan);
			homeScreen(art);
		}
		else if(instruction)
		{
			setBackground(Color.gray);
			drawInstructions(art);
		}
		else if(playGame)
		{ 
			setBackground(skyBlue);
			drawBalloon(art);
			drawBalloon2(art);
			movement();
			movement2();
			randBalloonColor(art);
			drawScore(art);
			drawTimer(art);
			speedRange1();
			speedRange2();
			speedRange3();
			speedRange4();
			speedRange5();
			rectMouse(art);
		}
		else
		{
			setBackground(Color.yellow);
			gameOver(art);
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		
		mouseX = e.getX();
		mouseY = e.getY();

	}
	

	//this says that when the mouse is clicking on the certain balloon, the balloon will go back to the top of the screen and 
	//the score will either go up or down depending on if the person hit the right balloon
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		mouseSO.makeSolidObject(e.getX(), e.getY(), 5, 5);
		
			if(mouseRect.isCollidingWith(balloon1))
			{
				balloonY = 100;
				balloonX = (gen.nextInt(650)-75);
				if(randomColor.equals(Color.green))
				{
					score++;
				}
				else
				{
					score--;
				}
			}
			else
			{
				if(mouseRect.isCollidingWith(balloon2))
				{
				balloon2Y = 100;
				balloon2X = (gen.nextInt(650)-75);
				if(randomColor.equals(Color.red))
				{
					score++;
				}
				else
				{
					score--;
				}
				}
				else
				{
					if(mouseRect.isCollidingWith(balloon3))
					{
						balloon3Y = 100;
						balloon3X = (gen.nextInt(650)-75);
						if(randomColor.equals(Color.blue))
						{
							score++;
						}
						else
						{
							score--;
						}
					}
					else
					{
						if(mouseRect.isCollidingWith(balloon4))
						{
							balloon4Y = 100;
							balloon4X = (gen.nextInt(650)-75);
							if(randomColor.equals(Color.yellow))
							{
								score++;
							}
							else
							{
								score--;
							}
						}
						else
						{
							if(mouseRect.isCollidingWith(balloon5))
							{
								balloon5Y = 100;
								balloon5X = (gen.nextInt(650)-75);
								if(randomColor.equals(Color.magenta))
								{
									score++;
								}
								else
								{
									score--;
								}
							}
							else
							{
								if(mouseRect.isCollidingWith(balloon6))
								{
									balloon6Y = 50;
									balloon6X = (gen.nextInt(650)-75);
									if(randomColor.equals(Color.green))
									{
										score++;
									}
									else
									{
										score--;
									}
								}
								else
								{
									if(mouseRect.isCollidingWith(balloon7))
									{
										balloon7Y = 50;
										balloon7X = (gen.nextInt(650)-75);
										if(randomColor.equals(Color.red))
										{
											score++;
										}
										else
										{
											score--;
										}
									}
									else
									{
										if(mouseRect.isCollidingWith(balloon8))
										{
											balloon8Y = 50;
											balloon8X = (gen.nextInt(650)-75);
											if(randomColor.equals(Color.blue))
											{
												score++;
											}
											else
											{
												score--;
											}
										}
										else
										{
											if(mouseRect.isCollidingWith(balloon9))
											{
												balloon9Y = 50;
												balloon9X = (gen.nextInt(650)-75);
												if(randomColor.equals(Color.yellow))
												{
													score++;
												}
												else
												{
													score--;
												}
											}
											else
											{
												if(mouseRect.isCollidingWith(balloon10))
												{
													balloon10Y = 50;
													balloon10X = (gen.nextInt(650)-75);
													if(randomColor.equals(Color.magenta))
													{
														score++;
													}
													else
													{
														score--;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
	}

	
		
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	//if the space bar is pressed, it will play the game. if the r is pressed, it will go back to the homescreen, if the z is pressed, it 
	// will go to the instruction page, and if you hit b, it will go back to the home page
	//this allows for the computer to read that if these buttons are pressed, a specific screen will either appear or go away
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int key = e.getKeyCode();
		if(key == e.VK_SPACE)
		{
			home = false;
			instruction = false;
		}
		if(key == e.VK_R)
		{
			home = true;
		}
		if(key == e.VK_Z)
		{
			home = false;
		}
		if(key == e.VK_B)
		{
			home = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
