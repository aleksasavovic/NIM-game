package etf.nim.sa150387d;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




/**
 * @author Aleksa
 *  Pravimo objekat igre i inicijalizujemo je.
 */
public class Go {
	  
  
	public static void main(String[] args) {
		Game game=new Game();
		try {
			game.initializeGame();
		} catch (IOException e) {
			
			e.printStackTrace();
		}	
			
		}
		
		 
		
	
	   
}	
