package zombie;
import java.awt.Color;
import java.util.*; 

///**** Color guide****//// 
/*
	White = human 
	Magenta = Alpha Zombie
	Pink = Zombie
	Yellow = resting or running human 
	CYAN = HOTSPOT for human= where no zombie can go one but humans can
*/
////***//////


public class Human{
	
	private ZombieModel model; 
	private int x; //for human location
	private int y; //for location
	private Direction dir;
	private Random ram = new Random();    //Call ram.next(Int8) from 0-7
	private Color oldColor = Color.BLACK;
	boolean ran = false;
	int runCount = 0;
	int restCount = 0;
	
	public Human(ZombieModel modelAvg) {
		
		model = modelAvg; 
		
		//TODO: use a while loop to randomly generage a location ON THE CAMPUS 
		x = ram.nextInt(model.getWidth()); 							//Need it within bounds 
		y = ram.nextInt(model.getHeight());
	
			//until location is qualified (color is black) 
			while(model.getColor(x,y) != Color.BLACK){				//Can put white dot on there and jump out of loop
				x = ram.nextInt(model.getWidth()); 								
				y = ram.nextInt(model.getHeight());
			}
			
			if(model.getColor(x, y) == Color.BLACK) {
				model.setColor(x, y, Color.WHITE);  				//Putting the human there

			}
				
			this.dir = Direction.NORTH; 
		
	}
	
	public void update() {
		//update newX and newY based on direction      
				//Need to make sure the new location of newX and newY is on campus qualified
				//need to set the old dot to black and new dot to white 
				//if both are true, move human and update 
				becomeZombie();
			
		
				int newX = x;
				int newY = y;
			
				
				//System.out.println("Newx = " + newX + " Newy = " + newY);		
				
				
				///DIRECTION////
				
				
				///** see if it needs to run***////
				//System.out.println("Run count = " + runCount);
				if(isHuman()) {
					//Resting
					if(runCount == 0 && restCount > 0) {
						//System.out.println("THIS FUCKING RUNS");
						newX = x;
						newY = y;
						restCount --;
						//System.out.println("REST: RunCount = " + runCount + " RestCount = " + restCount);
						if (restCount == 0) {
							ran = false;
						}

						
					} 
					else if(runCount > 0) {  //runs again 
						//System.out.println("It knows to run again$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
						if(dir == Direction.NORTH && y > 0) {
							newY--;
							//System.out.println("Newx = " + newX + " Newy = " + newY);
						}
						
						if(dir == Direction.SOUTH && y < model.getHeight() - 3) {
							//System.out.println("This is running");
							newY ++;
							//System.out.println("Newx = " + newX + " Newy = " + newY);
						}
						
						if(dir == Direction.EAST && x < model.getWidth() -1) {
							newX ++;
							//System.out.println("Newx = " + newX + " Newy = " + newY);
						}
						
						if(dir == Direction.WEST && x > 0) {
							newX --;
							//System.out.println("Newx = " + newX + " Newy = " + newY);
						}
						
						runCount--;
						//System.out.println("RUN II/III RunCount = " + runCount + " RestCount = " + restCount);

					}
					//Start Running
					else if(checkZombieNearby() && !ran) {
						
						//System.out.println(dir);
						dir = DirectionOfZombie(); 
						//System.out.println("new dir = " + dir);
						//First have it run//
							if(dir == Direction.NORTH && y > 0) {
								newY--;
								//System.out.println("Newx = " + newX + " Newy = " + newY);
							}
							
							if(dir == Direction.SOUTH && y < model.getHeight() - 3) {
								//System.out.println("This is running");
								newY ++;
								//System.out.println("Newx = " + newX + " Newy = " + newY);
							}
							
							if(dir == Direction.EAST && x < model.getWidth() -1) {
								newX ++;
								//System.out.println("Newx = " + newX + " Newy = " + newY);
							}
							
							if(dir == Direction.WEST && x > 0) {
								newX --;
								//System.out.println("Newx = " + newX + " Newy = " + newY);
							}
							 
						ran = true;
						runCount = 2;
						restCount = 2;
						//System.out.println("It knows there is a zombie##################################################################");
						//System.out.println("RUN I RunCount = " + runCount + " RestCount = " + restCount);

						
					}
					//Run second time
					
					else {
						//System.out.println("RunCount = " + runCount + " RestCount = " + restCount);
						ran = false;
						randomDirHuman();	

					}
				}

				/////** changing the directions ** /////
				
				if(dir == Direction.NORTH && y > 0) {
					newY--;
					//System.out.println("Newx = " + newX + " Newy = " + newY);
				}
				
				if(dir == Direction.SOUTH && y < model.getHeight() - 3) {
					//System.out.println("This is running");
					newY ++;
					//System.out.println("Newx = " + newX + " Newy = " + newY);
				}
				
				if(dir == Direction.EAST && x < model.getWidth() -1) {
					newX ++;
					//System.out.println("Newx = " + newX + " Newy = " + newY);
				}
				
				if(dir == Direction.WEST && x > 0) {
					newX --;
					//System.out.println("Newx = " + newX + " Newy = " + newY);
				}
				
				
				//// ***Change the clolors ***///
				
				
				//human 
				
				if(isHuman()) {
				if (newX < model.getWidth() && newY < model.getHeight()
						&& model.getColor(newX, newY) == Color.BLACK) {
					//System.out.println("Newx = " + newX + " Newy = " + newY);
					//System.out.println("movement");
					if(ran) {
						model.setColor(newX, newY, Color.YELLOW);
					}
					else { model.setColor(newX, newY, Color.WHITE); }
					model.setColor(x, y, oldColor);
					oldColor = Color.BLACK;
					x = newX;
					y = newY;
				}
				
				else if (newX < model.getWidth() && newY < model.getHeight()
						&& model.getColor(newX, newY) == Color.CYAN) {
					//System.out.println("Newx = " + newX + " Newy = " + newY);
					//System.out.println("movement");
					if(ran) {
						model.setColor(newX, newY, Color.YELLOW);
					}
					else { model.setColor(newX, newY, Color.WHITE); }
					model.setColor(x, y, oldColor);
					oldColor = Color.CYAN;
					x = newX;
					y = newY;
				}
				
				
				///** hotspot**//
				
					else if(newX < model.getWidth() && newY < model.getHeight()
							 && model.getColor(newX, newY) == Color.CYAN) { 
						if(ran) {
							model.setColor(newX, newY, Color.YELLOW);
						}
						else { model.setColor(newX, newY, Color.WHITE); }
						model.setColor(x, y, oldColor);
						oldColor = Color.CYAN;
						x = newX;
						y = newY;
					}
				
					//If it is leaving the hotspot
					else if(newX < model.getWidth() && newY < model.getHeight()
							&& 
							model.getColor(newX, newY) == Color.BLACK) {
						if(ran) {
							model.setColor(newX, newY, Color.YELLOW);
						}
						else { model.setColor(newX, newY, Color.WHITE); }
						model.setColor(x, y, oldColor);
						oldColor = Color.BLACK;
						x = newX;
						y = newY;
					}
				
					//If it is leaving hotspot
					else if(newX < model.getWidth() && newY < model.getHeight()
						 && model.getColor(newX, newY) == Color.CYAN) {
						model.setColor(newX, newY, Color.WHITE); 
						model.setColor(x, y, oldColor);
						oldColor = Color.CYAN;
						x = newX;
						y = newY;
					}
				/// ***if it's an obsticle in that direction ****////
					else if((model.getColor(newX, newY) != Color.BLACK && model.getColor(newX, newY) != Color.CYAN) ||
							newX == model.getWidth() -1 ||
							newY == model.getHeight() -1 ||
							newX == 1 ||
							newY == 1) { 
						//System.out.println("Obsticle!");
							dir = opposite(dir);
					}
				
				}
				
				//Alpha zombie 
				if(isAlpha()) {
					if (newX < model.getWidth() && newY < model.getHeight()
							&& model.getColor(newX, newY) == Color.BLACK) {
						//System.out.println("Newx = " + newX + " Newy = " + newY);
						//System.out.println("movement");
						model.setColor(newX, newY, Color.MAGENTA); 
						model.setColor(x, y, oldColor);
						oldColor = Color.BLACK;
						x = newX;
						y = newY;
					}
					
					/// ***if it's an obsticle in that direction ****////
					//check for river 
					
						//If it is in the river, set new one to magenta and old to blue
						else if(newX < model.getWidth() && newY < model.getHeight()
								 && model.getColor(newX, newY) == Color.BLUE) { 
							model.setColor(newX, newY, Color.MAGENTA); 
							model.setColor(x, y, oldColor);
							oldColor = Color.BLUE;
							x = newX;
							y = newY;
						}
					
						//If it is leaving the river 
						else if(newX < model.getWidth() && newY < model.getHeight()
								&& 
								model.getColor(newX, newY) == Color.BLACK) {
							model.setColor(newX, newY, Color.MAGENTA); 
							model.setColor(x, y, oldColor);
							oldColor = Color.BLACK;
							x = newX;
							y = newY;
						}
					
						//If it is entering a river
						else if(newX < model.getWidth() && newY < model.getHeight()
							 && model.getColor(newX, newY) == Color.BLUE) {
							model.setColor(newX, newY, Color.MAGENTA); 
							model.setColor(x, y, oldColor);
							oldColor = Color.BLUE;
							x = newX;
							y = newY;
						}
						
						else if(model.getColor(newX, newY) != Color.BLACK || 
								newX == model.getWidth() -1 ||
								newY == model.getHeight() -1 ||
								newX == 1 ||
								newY == 1) { 
							//System.out.println("Obsticle!");
								dir = opposite(dir);
						}
					}
				
				if(isZombie()) {
					if (newX < model.getWidth() && newY < model.getHeight()
							&& model.getColor(newX, newY) == Color.BLACK) {
						//System.out.println("Newx = " + newX + " Newy = " + newY);
						//System.out.println("movement");
						model.setColor(newX, newY, Color.PINK); 
						model.setColor(x, y, oldColor);
						oldColor = Color.BLACK;
						x = newX;
						y = newY;
					}
					
					/// ***if it's an obsticle in that direction ****////
					//check for river 
						else if(newX < model.getWidth() && newY < model.getHeight()
								 && model.getColor(newX, newY) == Color.BLUE) { 
							model.setColor(newX, newY, Color.PINK); 
							model.setColor(x, y, oldColor);
							oldColor = Color.BLUE;
							x = newX;
							y = newY;
						}
					
						//If it is leaving the river 
						else if(newX < model.getWidth() && newY < model.getHeight()
								&& 
								model.getColor(newX, newY) == Color.BLACK) {
							model.setColor(newX, newY, Color.PINK); 
							model.setColor(x, y, oldColor);
							oldColor = Color.BLACK;
							x = newX;
							y = newY;
						}
					
						//If it is entering a river
						else if(newX < model.getWidth() && newY < model.getHeight()
							 && model.getColor(newX, newY) == Color.BLUE) {
							model.setColor(newX, newY, Color.PINK); 
							model.setColor(x, y, oldColor);
							oldColor = Color.BLUE;
							x = newX;
							y = newY;
						}
						
						else if(model.getColor(newX, newY) != Color.BLACK || 
								newX == model.getWidth() -1 ||
								newY == model.getHeight() -1 ||
								newX == 1 ||
								newY == 1) { 
							//System.out.println("Obsticle!");
								dir = opposite(dir);
						}
					}
				
				
				///***choosing the new direction***////
				
				
				if(isZombie() || isAlpha()) {
					if(checkHumanNearby() ) {
						//System.out.println("It knows there is a human");
						//System.out.println(dir);
						dir = DirectionOfHuman(); 
						//System.out.println("new dir = " + dir);
					}
					else { randomDirZombie(); }
				}
				
				
	}
	

	public void randomDirHuman() {
		int direction = ram.nextInt(99); //choose between 0-99
		//System.out.println(direction);
		//turn around//
				if(direction <= 4 ) {
					//System.out.println("It should be turning around");
					dir = opposite(dir);
				}
		//turn left
				else if(direction > 4 && direction <= 14) {
					//System.out.println("it should be turning left");
					dir = left(dir);
				}
	
		//turn Right
				else if(direction > 14 && direction <= 24) {
					//System.out.println("it should be turning right");
					dir = right(dir);
				}
		//Stay the same 
				else if (direction > 24) {
					//System.out.println("it should be continuing on");
					dir = this.dir;
				}				
	}
	
	public void randomDirZombie() {
		int direction = ram.nextInt(99); //choose between 0-99
		//System.out.println(direction);
		//turn around//
				if(direction <= 9 ) {
					//System.out.println("It should be turning around");
					dir = opposite(dir);
				}
		//turn left
				else if(direction > 9 && direction <= 29) {
					//System.out.println("it should be turning left");
					dir = left(dir);
				}
	
		//turn Right
				else if(direction > 29 && direction <= 49) {
					//System.out.println("it should be turning right");
					dir = right(dir);
				}
		//Stay the same 
				else if (direction > 49) {
					//System.out.println("it should be continuing on");
					dir = this.dir;
				}				
	}
	
	public boolean isZombie() {
		if(model.getColor(x,y) == Color.PINK) {
			return true;
		}
		
		return false;
	}
	
	public boolean isAlpha() {
		if(model.getColor(x,y) == Color.MAGENTA) {
			return true;
		}
		
		return false;
	}
	
	public boolean isHuman() {
		if(model.getColor(x,y) == Color.WHITE ||model.getColor(x,y) == Color.YELLOW ) {
			return true;
		}
		
		return false;
	}
	
	public void setToZombie(int a, int b) {
		//Human newZombie = new Human(model);
		model.setColor(a,b, Color.PINK);
	}
	
	public boolean checkZombieAdjacent() {
		if(x+1 < model.getWidth() && 
				(model.getColor(x +1 ,y) == Color.PINK || model.getColor(x +1 ,y) == Color.MAGENTA)) {
			return true;
		}
		
		if(x -1 >= 0 && 
				(model.getColor(x -1 ,y) == Color.PINK || model.getColor(x -1 ,y) == Color.MAGENTA)) {
			return true;
		}
		
		if(y -1 >= 0 && 
				(model.getColor(x,y -1 ) == Color.PINK || model.getColor(x, y -1) == Color.MAGENTA)) {
			return true;
		}
		
		if(y +1 < model.getHeight() && 
				(model.getColor(x,y +1 ) == Color.PINK || model.getColor(x, y +1) == Color.MAGENTA)) {
			return true;
		}
		
		return false;
	}

	public boolean checkZombieNearby() {
		for(int i = -10; i <= 10; i++ ) {
			if(x+ i > 0 && x + i < model.getWidth()) {
				if(model.getColor(x + i ,y) == Color.PINK || model.getColor(x + i ,y) == Color.MAGENTA) {
					return true;
				}
			}
		}
		
		for(int j = -10; j <= 10; j++ ) {
			if(y+ j > 0 && y + j < model.getHeight()) {
				if(model.getColor(x ,y + j) == Color.PINK || model.getColor(x ,y + j) == Color.MAGENTA) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean checkHumanNearby() {
		for(int i = -10; i <= 10; i++ ) {
			if(x+ i > 0 && x + i < model.getWidth()) {
				if(model.getColor(x + i ,y) == Color.WHITE) {
					return true;
				}
			}
		}
		
		for(int j = -10; j <= 10; j++ ) {
			if(y+ j > 0 && y + j < model.getHeight()) {
				if(model.getColor(x ,y + j) == Color.WHITE) {
					return true;
				}
			}
		}
		
		return false;
	} 
	
	public Direction DirectionOfZombie() {
		Direction newD = Direction.NORTH;
		
		if (checkZombieNearby()) {
			//It is to the EAST
			for(int i = -10; i < 0; i++ ) {
				if(x+ i > 0 && x + i < model.getWidth()) {
				if(model.getColor(x + i ,y) == Color.PINK || model.getColor(x + i ,y) == Color.MAGENTA) {
					return Direction.EAST;
				}
				}
			}
			
			//To the WEST
			for(int i = 0; i < 10; i++ ) {
				if(x+ i > 0 && x + i < model.getWidth()) { 
				if(model.getColor(x + i ,y) == Color.PINK || model.getColor(x + i ,y) == Color.MAGENTA) {
					return Direction.WEST;
				}
				}
			}
			
			//SOUTH
			for(int j = -10; j < 0; j++ ) {
				if(y+ j > 0 && y + j < model.getHeight()) {
				if(model.getColor(x ,y + j) == Color.PINK || model.getColor(x ,y + j) == Color.MAGENTA) {
					return Direction.SOUTH;
				}
				}
			}
			
			//NORTH
			for(int j = 0; j < 10; j++ ) {
				if(y+ j > 0 && y + j < model.getHeight()) {
				if(model.getColor(x,y + j) == Color.PINK || model.getColor(x ,y + j) == Color.MAGENTA) {
					return Direction.NORTH;
				}
				}
			}
		}
		
		return newD;
	}
	
	public Direction DirectionOfHuman() {
		Direction newD = Direction.NORTH;
		
		if (checkHumanNearby()) {
			//It is to the EAST
			for(int i = -10; i < 0; i++ ) {
				if(x+ i > 0 && x + i < model.getWidth()) {
				if(model.getColor(x + i ,y) == Color.WHITE) {
					return Direction.WEST;
				}
				}
			}
			
			//To the WEST
			for(int i = 0; i < 10; i++ ) {
				if(x+ i > 0 && x + i < model.getWidth()) { 
				if(model.getColor(x + i ,y) == Color.WHITE) {
					return Direction.EAST;
				}
				}
			}
			
			//SOUTH
			for(int j = -10; j < 0; j++ ) {
				if(y+ j > 0 && y + j < model.getHeight()) {
				if(model.getColor(x ,y + j) == Color.WHITE) {
					return Direction.NORTH;
				}
				}
			}
			
			//NORTH
			for(int j = 0; j < 10; j++ ) {
				if(y+ j > 0 && y + j < model.getHeight()) {
				if(model.getColor(x ,y + j) == Color.WHITE) {
					return Direction.SOUTH;
				}
				}
			}
		}
		
		return newD;
	} 
	
	public void becomeZombie() {
		//Check all four sides 
		if(checkZombieAdjacent() && !isAlpha()) {
			setToZombie(x, y);
		}
	}
	
	public void setAlphaZombie() {
		//Human AlphaZombie = new Human(model);
		model.setColor(x, y, Color.MAGENTA);
		//return AlphaZombie;
	}

	public Direction opposite(Direction D) {
		Direction newD = null;
		
		if(D == Direction.NORTH) {
			newD = Direction.SOUTH;
		}
		if(D == Direction.SOUTH) {
			newD = Direction.NORTH;
		}
		if(D == Direction.EAST) {
			newD = Direction.WEST;
		}
		if(D == Direction.WEST) {
			newD = Direction.EAST;
		}
		
		return newD;
	}
	
	public Direction left(Direction D) {
		Direction newD = null;
		if(D == Direction.NORTH) {
			newD = Direction.WEST;
		}
		if(D == Direction.SOUTH) {
			newD = Direction.EAST;
		}
		if(D == Direction.EAST) {
			newD = Direction.NORTH;
		}
		if(D == Direction.WEST) {
			newD = Direction.SOUTH;
		}
		
		return newD;
	}
	
	public Direction right(Direction D) {
		Direction newD = null;
		if(D == Direction.NORTH) {
			newD = Direction.EAST;
		}
		if(D == Direction.SOUTH) {
			newD = Direction.WEST;
		}
		if(D == Direction.EAST) {
			newD = Direction.SOUTH;
		}
		if(D == Direction.WEST) {
			newD = Direction.NORTH;
		}
		return newD;
	}
	
	public void setDirection(Direction dirAvg){

		dir = dirAvg;
	}
 

 }
