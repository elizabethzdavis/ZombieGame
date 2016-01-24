package zombie;

import java.awt.Color;
import java.util.Random;

public class Hotspot {
	private ZombieModel model; 
	private int x; //for human location
	private int y; //for location
	private Random ram = new Random(); 
	
	public Hotspot(ZombieModel modelAvg) {
		model = modelAvg; 
		//TODO: use a while loop to randomly generage a location ON THE CAMPUS 
		x = ram.nextInt(model.getWidth() -1); 		
		//Need it within bounds 
		int height = model.getHeight();
	
		if( x < 5) {
			for(int z = x; z < x+6; z++) {
				for(int h = 0; h < height / 4; h++) {
					if(model.getColor(z, h) == Color.BLACK) {
						model.setColor(z, h, Color.CYAN); 
					}
				}
			}
		}
		
		else {
			for(int z = x; z > x-6; z--) {
				for(int h = 0; h < height / 4; h++) {
					if(model.getColor(z, h) == Color.BLACK) {
						model.setColor(z, h, Color.CYAN); 
					}
				}
			}
		}
	}
}
