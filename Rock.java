package zombie;

import java.awt.Color;
import java.util.Random;
import java.util.*;
import java.lang.Math;

public class Rock {
	private ZombieModel model; 
	private int x; //for human location
	private int y; //for location
	private Random ram = new Random();
	
	//Rocks- make a square, then 
	
	
	public Rock(ZombieModel modelAvg) {
		model = modelAvg;
		//int count = 0;
		int startx, starty, radius;
		double centerx, centery;
		
		//if radius is > 8, DONT DRAW a a dot 
		
		for(int count = 0; count < 6; ) {
			radius = ram.nextInt(8);											//radius between 2-4 
				//make sure its greater than two:
			//System.out.println("radius = " + radius);
			while(radius < 4) {
				radius = ram.nextInt(8);
				//System.out.println("radius = " + radius);
				if(radius >= 4) {break;}
			}
			
			//Get the random locations
			startx = ram.nextInt(model.getWidth()- radius); 							//Need it within bounds 
			starty = ram.nextInt(model.getHeight() - radius);	
			//System.out.println("Height = " + model.getHeight());//Include the fact edge is center + radius
			//System.out.println("Startx = " + startx + " Start y = " + starty);
			
			while(startx < radius) {
				startx = ram.nextInt(model.getWidth()- radius); 
				//System.out.println("Startx = " + startx);
			}
																						//Checks lower bounds 
			while(starty < radius) {
				starty = ram.nextInt(model.getHeight() - radius);
				//System.out.println("Starty = " + starty);
			}
			
			centerx = startx;															//set to double 
			centery = starty;
				//System.out.println("Centerx = " + centerx + " Centery = " + centery);
			
			if(model.getColor(startx, starty) == Color.BLACK) {										//check to see if center is ok
				model.setColor(startx, starty, Color.GRAY);											//color center
				
				//Color out in one direction
				
				double edgexLeft = centerx + radius;
					//System.out.println("Left bound on x = " + edgexLeft);
				double edgexRight = centerx - radius;
					//System.out.println("Right bound on x = " + edgexRight);
				double edgeyUp = centery + radius;
					//System.out.println("Upper bound on y = " + edgeyUp);
				double edgeyDown = centery - radius;
					//System.out.println("Lower bound on x = " + edgeyDown);
				
				//Need to check the upper bounds and make sure they're within this 	
					
				for(double i = edgexRight; i <= edgexLeft; i++) {			//From x - y start to finish
					for(double j = edgeyDown; j <= edgeyUp; j++) {
							//System.out.println("It goes through this loop");
						double distance = distance(i, j, centerx, centery); //Check the distance from center to edges
							//System.out.println("distance = " + distance );
						if(distance < radius ) {
								//System.out.println("This comparison is true" );
							int newi = (int) i;
								//System.out.println("newi = " + newi);
							int newj = (int) j;
								//System.out.println("newj = " + newj);
							if(newi <= model.getWidth() && newj <= model.getHeight()) {	
								if(model.getColor(newi, newj) == Color.BLACK) {
									model.setColor(newi, newj, Color.GRAY);
								}
							}
						}
					}
				}
				count++;
			}
		}
	}

	private double distance(double a, double b, double c, double d) {
		double x = a; 
		double y = b;
		double centerx = c;
		double centery = d;
		double dist = Math.sqrt(((centerx - x) * (centerx - x)) + ((centery - y) * (centery - y)));
		return dist;
	
	}
}
