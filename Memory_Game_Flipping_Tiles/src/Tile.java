import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;

public class Tile {
	
 private int id;
 private int value;
 private String ImageIconimageIconString;
 private boolean flipped;
 private GlassButton button ;
 
 public Tile(int id, int value, String ImageIconimageIconString) {
	 this.id = id;
	 this.value = value;
	 this.flipped = false;
	 this.ImageIconimageIconString = ImageIconimageIconString;
	 this.button = new GlassButton();
	 
 }
 public int getId() {
	 return this.id;	 
}
 public int getValue() {
	 return this.value;
 }
public String getImage() {
	return this.ImageIconimageIconString;
}
 public boolean isFlipped() {
	 return flipped;
 }
 public void flip() {
	 flipped = !flipped;
	 if(flipped) {
		 button.setText(Integer.toString(value));
		 button.setForeground(Color.WHITE);
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
