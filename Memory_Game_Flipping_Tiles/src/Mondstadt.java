
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.util.Collections;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mondstadt {
	private ArrayList<Tile> tiles;
	private Tile FirstTile;
	private Tile SecondTile;
	private Timer timer;
	private JLabel timeLabel;
	ImageIcon imageIcon = new ImageIcon("D://java project//Memory_Game_Flipping_Tiles/Emblem_Mondstadt.png");
	String imageIconString = imageIcon.toString();
	Image Jimage = null;
	{
	
	try 
	{
	Jimage = ImageIO.read(new File("D://java project//Memory_Game_Flipping_Tiles/mondstadt.jpg"));
	} catch (IOException e){
		e.printStackTrace();
	}}
	
	public Mondstadt() {
		
		initializeTiles();
		
		

		
		
		JFrame frame = new JFrame("Flipping tiles Game");
		
		JPanel panel = new JPanel(new GridLayout(6,6)){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Jimage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        for(Tile tile : tiles) {
			panel.add(tile.getButton());
			tile.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flipTile(tile);
			}
			});
		}
        int size = Math.min(frame.getWidth(), frame.getHeight());
        panel.setPreferredSize(new Dimension(size, size));
        
		frame.add(panel);
		timeLabel = new JLabel();
		timeLabel.setText("Time: 60");
		frame.add(timeLabel, "East");
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		startTimer();
		
	}
	public void initializeTiles() {
		tiles = new ArrayList<Tile>();
		for(int i = 0; i < 18; i++) {
			tiles.add(new Tile(i,i%9,imageIconString));
			tiles.add(new Tile(i+18,i%9,imageIconString));
		}
		Collections.shuffle(tiles);
	}
	private void flipTile(Tile tile) {
		
		if(FirstTile == null) {
			FirstTile = tile;
			FirstTile.flip();
		}
		else if(SecondTile == null && tile!=FirstTile) {
			SecondTile = tile;
			SecondTile.flip();
			if(FirstTile.getValue() == SecondTile.getValue()) {
				tiles.remove(FirstTile);
				tiles.remove(SecondTile);
				FirstTile = null;
				SecondTile = null;
				
				
				
			}
			else if(FirstTile.getValue() != SecondTile.getValue()) {
				FirstTile.flip();
				SecondTile.flip();
				FirstTile = null;
				SecondTile = null;
			}
			else {
				timer.restart();
			}
		}
		
	}
	private void startTimer() {
		timer = new Timer(1000,new ActionListener() {
			private int count = 600;
			public void actionPerformed(ActionEvent e) {
				count--;
				if(tiles.size() == 0) {
					timer.stop();
					timeLabel.setText("You Win!");
				}
				else if(count >= 0) {
					timeLabel.setText("Time:" + count);
				}
				
				else {
					timer.stop();
					timeLabel.setText("Game Over");
				}
			}
		});
		timer.start();
	}
	public static void main(String[] args) {
		new Mondstadt();

	}

}
