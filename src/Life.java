import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

class Life extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	BufferedImage I = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
	Graphics g = this.getGraphics();
	Graphics offgc;
	Image offscreen = null;
	
	int boardSize = 90;
	
	boolean grid[][] = new boolean[boardSize][boardSize];
	boolean tempgrid[][] = new boolean[boardSize][boardSize];
	
	//top left point of grid
	int origin_x = 50;
	int origin_y = 50;
	int size = 10;
	int length = boardSize;

	public Life(){
		setTitle("Life - Shazam");
		setSize(1000,1000); // default size is 0,0
		setLocation(600,0); // default is 0,0 (top left corner)
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		grid[10][10] = true;
	}

	public void clearBoard(){
		for (int i=0;i<boardSize;i++){
			for (int j=0;j<boardSize;j++){
				grid[i][j] = false;
			}
		}
	}
	
	public void iterate(){
		for (int i=0;i<boardSize;i++){
			for (int j=0;j<boardSize;j++){
				int count = getNeighbours(i,j);
				if (grid[i][j] == true) {
					if (count < 2) {
						tempgrid[i][j] = false;
					} else if (count > 3) {
						tempgrid[i][j] = false;
					} else {
						tempgrid[i][j] = true;
					}
				} else {
					if (count == 3) {
						tempgrid[i][j] = true;
					}
				}
			}
		}
		for (int i=0;i<boardSize;i++){
			for (int j=0;j<boardSize;j++){
				grid[i][j] = tempgrid[i][j];
			}
		}
		System.out.println("iterate!");
	}
	
	public void fillGrid(int x, int y){
		System.out.println ((x-origin_x)/size + "  "+ (y-origin_y)/size);
		int xtemp = (x-origin_x)/size;
		int ytemp = (y-origin_y)/size;
		if (xtemp >= boardSize || xtemp < 0) {
			return;
		}
		if (ytemp >= boardSize || ytemp < 0) {
			return;
		}
		grid[xtemp][ytemp] = true;
	}
	
	public int getNeighbours(int x, int y){
		int result = 0;
		if (x > 0 && y > 0) {
			if (grid[x-1][y-1] == true) {
				result +=1;
			}
		}
		if (y > 0 ){
			if (grid[x][y-1] == true) {
				result +=1;
			}
		}
		if (x < boardSize -1 && y > 0) {
			if (grid[x+1][y-1] == true) {
				result +=1;
			}
		}
		if (x > 0) {
			if (grid[x-1][y] == true) {
				result +=1;
			}
		}
		if (x < boardSize - 1){
			if (grid[x+1][y] == true) {
				result +=1;
			}
		}
		
		if (x > 0 && y < boardSize -1) {
			if (grid[x-1][y+1] == true) {
				result +=1;
			}
		}
		if (y < boardSize - 1) {
			if (grid[x][y+1] == true) {
				result +=1;
			}
		}
		if (x < boardSize -1 && y < boardSize -1){
			if (grid[x+1][y+1] == true) {
				result +=1;
			}
		}
		return result;
	}
	
	public void drawScreen(){
		offscreen = createImage(1000,1000);
		offgc = offscreen.getGraphics();
		offgc.setColor(Color.GRAY);
		offgc.fillRect(0,0,1000,1000);
		offgc.setColor(Color.BLACK);
		
		//draw grid
		for (int i=0;i<=length; i++){
			offgc.drawLine(origin_x+i*size, origin_y, origin_x+i*size, origin_y+length*size);	// vertical lines
			offgc.drawLine(origin_x, origin_y+i*size, origin_x+length*size, origin_y+i*size); // horizontal lines
		}
		
		//fill grid with current
		for (int i=0;i<boardSize;i++){
			for (int j=0;j<boardSize;j++){
				if (grid[i][j] == true) {
					offgc.setColor(Color.BLUE);
					offgc.fillRect(origin_x+size*i,origin_y+ size*j, 10, 10);
				}
			}
		}
		I = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		g = this.getGraphics();
		g.drawImage(offscreen, 0, 0, this);
		g.dispose();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if (arg0.getKeyCode() == 32) {
			iterate();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		fillGrid(arg0.getX() ,arg0.getY());
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getButton());
		//System.out.println(arg0.getX() + "  " + arg0.getY());
		fillGrid(arg0.getX() ,arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

