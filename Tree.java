package zombie;


import java.awt.Color;
import java.util.Random;

public class Tree {

	private ZombieModel model; 
	private int x; //for human location
	private int y; //for location
	private Random ram = new Random(); 
	
	public Tree(ZombieModel modelAvg) {
		model = modelAvg;

		
		//Set color of center and then x+1, x-1, y+1, y-1 making sure in bounds
				for(int treeNum = 0; treeNum < 40;) {
					x = ram.nextInt(model.getWidth());
					y = ram.nextInt(model.getHeight()); 
					
					
					/*
					if(model.getColor(x, y) != Color.BLACK ||
							model.getColor(x+1, y) != Color.BLACK ||
							model.getColor(x-1, y) != Color.BLACK ||
							model.getColor(x, y-1) != Color.BLACK ||
							model.getColor(x, y +1) != Color.BLACK ) {
						x = ram.nextInt(model.getWidth());
						y = ram.nextInt(model.getHeight()); 
					}
					*/
					
					if(model.getColor(x,  y) == Color.BLACK) {
						model.setColor(x, y, Color.GREEN);
						
						if(x+1 < model.getWidth() && model.getColor(x+1, y) == Color.BLACK) {
							model.setColor(x+1, y, Color.GREEN);
						}
						
						if((x-1 > 0 || x-1 == 0) && model.getColor(x-1, y) == Color.BLACK) {
							model.setColor(x -1, y, Color.GREEN);
						}
						
						if(y+1 < model.getHeight() && model.getColor(x, y+1) == Color.BLACK) {
							model.setColor(x, y +1, Color.GREEN);
						}
						
						if((y -1 > 0 || y-1 == 0) && model.getColor( x, y -1) == Color.BLACK) {
							model.setColor(x, y -1, Color.GREEN);
						}
						
						treeNum ++;
					}	
				}
	}
}

