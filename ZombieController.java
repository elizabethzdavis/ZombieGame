//test comment
package zombie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

public class ZombieController implements MouseListener {

	private final ZombieModel model;
	private final ZombieView view;
	private final int delay;
	
	public ZombieController(ZombieModel modelArg, ZombieView viewArg, int sleepTimeArg) {
		model = modelArg;
		view = viewArg;
		delay = sleepTimeArg;
	}

	public void beginSimulation() {
		model.initialize();
		view.draw();
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.update();
				view.draw();
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		//System.out.println("Mouse was clicked, direction was chosen to be: ");
		int x = e.getX() /10;		
		int y = e.getY() / 10;

		//TODO: Make sure the mouse-click location is within the ranges 
		//If yes, Change the human's direction
		//Towards where the mouse is ex: dir = NORTH

		//System.out.println("int x = " + x + " int y = " + y);
		
		if(x > 0 && x < model.getWidth()) {
			if(y > 0 && y < model.getHeight()) {
				if(x < 20) {	//Left Side 
					//System.out.println("West");
					model.getAlpha().setDirection(Direction.WEST);
				}

				if(x >= 20 && x <= 60 && y <= 30) {							//Top part
					//System.out.println("North");
					model.getAlpha().setDirection(Direction.NORTH);
				}
				
				if(x >= 20 && x <= 60 && y > 30) {							//Bottom Portion 
					//System.out.println("South");
					model.getAlpha().setDirection(Direction.SOUTH);
				}
				
				if(x > 60) {												//Right Side 
					//System.out.println("East");
					model.getAlpha().setDirection(Direction.EAST);
				}				
			}
		}
		//if mouse clicked location is within certain ranges, change the hum'as direction
	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
