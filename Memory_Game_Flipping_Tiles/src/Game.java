
import java.util.ArrayList;
import java.util.Collections;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {
	private ArrayList<Tile> tiles;
	private Tile FirstTile;
	private Tile SecondTile;
	private Timer timer;
	private JLabel timeLabel;
	public Game() {
		
		initializeTiles();
		
		JPanel panel = new JPanel(new GridLayout(8,8));
		for(Tile tile : tiles) {
			panel.add(tile.getButton());
			tile.getButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flipTile(tile);
			}
			});
		}
		
		JFrame frame = new JFrame("Flipping tiles Game");
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
		for(int i = 0; i < 32; i++) {
			tiles.add(new Tile(i,i%16));
			tiles.add(new Tile(i+15,i%16));
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
		new Game();

	}

}
