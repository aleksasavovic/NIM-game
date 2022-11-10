package etf.nim.sa150387d;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * @author Aleksa
 * Svrha klase je da locira prozor u centar ekrana.
 *
 */
public class Playmate extends JFrame{
	
	public Playmate(String s,int i, int j) {
		super(s);
		
		setResizable(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(dim.width/i,dim.height/j);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
	}

}
