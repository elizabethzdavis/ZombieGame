package zombie;

import java.awt.Color;
import java.util.ArrayList;

public class ZombieModel {
	
	private int tempx;
	private int tempy;
	
	private final Color[][] matrix;
	private final int width;
	private final int height;
	private final int dotSize;
	//private Human human; 
	private ArrayList<Human> humans = new ArrayList<Human>();	
	private River river;
	private Tree tree;
	private Rock rock;
	private Hotspot hotspot;
	
	public ZombieModel(int widthArg, int heightArg, int dotSizeArg) {
		width = widthArg;
		height = heightArg;
		dotSize = dotSizeArg;
		matrix = new Color[width][height];
		for (int i = 0; i < width; i++) { 
			for (int j = 0; j < height; j++) {
				matrix[i][j] = Color.BLACK;
			}
		}
	}
	
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDotSize() { return dotSize; }
	public Color getColor(int x, int y) { return matrix[x][y]; }
	public void setColor(int x, int y, Color color) { matrix[x][y] = color; }
	//public Human getHuman() { return human; }
	public Rock getRock() { return rock; }
	public River getRiver() { return river; }
	public Tree getTree() {return tree; }
	public Hotspot getHotspot() { return hotspot; }
	
	public void initialize() {
		//Create a backdrop a backdrop which contains a river, 6 rocks and 40 trees
		//Create a human and have it move 
		//initializeRocks();
		
		river = new River(this);
		hotspot = new Hotspot(this);
		rock = new Rock(this);
		tree  = new Tree(this);
		
		for(int i = 0; i < 31; i ++) {
			humans.add(new Human(this));
		}	
		
		humans.get(1).setAlphaZombie();
		
	}
	
	
	public void update() {
		for(int i= 0 ; i < humans.size() ; i++ ) {
			humans.get(i).update();
		}
	}
	
	public Human getAlpha() {
		return humans.get(1);
	}
}
