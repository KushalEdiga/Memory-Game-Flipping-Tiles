import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class GlassButton extends JButton {
    private final Color bgColor = new Color(0, 0, 0, 100);
    private final Color borderColor = new Color(255, 255, 255, 50);
    private final Color hoverColor = new Color(255, 255, 255, 20);
    private final Color clickColor = new Color(255, 255, 255, 40);
    private final Color textColor = Color.white;
    
    public GlassButton() {
        super();
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        
        
        
        g2d.setColor(bgColor);
        g2d.fillRoundRect(0, 0, width, height, 10, 10);

        if (getModel().isRollover()) {
            g2d.setColor(hoverColor);
            g2d.fillRoundRect(0, 0, width, height, 10, 10);
        }
        if (getModel().isPressed()) {
            g2d.setColor(clickColor);
            g2d.fillRoundRect(0, 0, width, height, 10, 10);
        }
        g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
        
        g2d.fillRect(0,0,width, height);
        
        g2d.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(g);
        g2d.dispose();
    }
}

