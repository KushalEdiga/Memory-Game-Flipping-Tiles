import javax.swing.JButton;
import java.awt.Font;

public class Tile {
	
 private int id;
 private int value;
 private boolean flipped;
 private JButton button ;
 
 public Tile(int id, int value) {
	 this.id = id;
	 this.value = value;
	 this.flipped = false;
	 this.button = new JButton();
 }
 public int getId() {
	 return this.id;	 
}
 public int getValue() {
	 return this.value;
 }
 public boolean isFlipped() {
	 return flipped;
 }
 public void flip() {
	 flipped = !flipped;
	 if(flipped) {
		 button.setText(Integer.toString(value));
	 }
	 else {
		 button.setText("");
	 }
	 
 }
public JButton getButton() {
	button.setFont(new Font("Arial", Font.PLAIN, 40));
	return button;
}
}
