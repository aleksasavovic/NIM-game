package etf.nim.sa150387d;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 * @author Aleksa
 *Klasa u kojoj se pravi korisnicki interfejs, unose podaci i zapocinje igra.
 */
public class Game extends JFrame implements ActionListener{
	private int prevDiscsTaken;
	private int discsOnPillar[];
	private static final int MAX=10;
	private Playmate pm,cpm,cpm1;
	private JButton confirm,confirm1;
	private int numOfPillars;
	private JTextField choices[],pillarToRemoveFrominp,numOfDiscsToRemoveinp ;
	private JLabel text[],pillarToRemoveFromlab,numOfDiscsToRemovelab ;
	private int whoPlays,lvlChoice,howDeep;
	private  volatile int success;
	private JComboBox listOfChoices;
	private char play1,play2;
	private JPanel p,p1,pWest,pSouth,pPom,pCenter;
	private Player player1,player2;
	private int numToRemove,removeFrom;
	private ImageIcon myPicture[];
	private JLabel pictureArray[],turn;
	GameTree mt;
		
	
	
	Scanner sc=new Scanner(System.in);	
	public Game() {	
	}


	public int getPrevDiscsTaken() {
		return prevDiscsTaken;
	}


	public void setPrevDiscsTaken(int prevPillarsTaken) {
		this.prevDiscsTaken = prevPillarsTaken;
	}


	public int getNumOfPillars() {
		return numOfPillars;
	}


	public void setNumOfPillars(int numOfPillars) {
		this.numOfPillars = numOfPillars;
	}


	public int[] getDiscsOnPillar() {
		return discsOnPillar;
	}


	public void setDiscsOnPillar(int[] discsOnPillar) {
		this.discsOnPillar = discsOnPillar;
	}
	
	/**
	 * @throws IOException
	 * kreiramo korisnicki interfejs i ucitavamo podatke.
	 */
	
	public void initializeGame() throws IOException{

		p=new JPanel();
	    p1=new JPanel();
	    pWest=new JPanel();
		pm = new Playmate("Nim",2,2);
		pillarToRemoveFrominp=new JTextField();
		pillarToRemoveFromlab=new JLabel("Broj stuba : ");
		numOfDiscsToRemoveinp=new JTextField();
		numOfDiscsToRemovelab=new JLabel("Broj diskova sa stuba :");
	    turn=new JLabel("Na potezu je:");
		p1.setLayout(new BorderLayout());
		pWest.setLayout(new GridLayout(9,2));
		pWest.add(pillarToRemoveFromlab);
		pWest.add(pillarToRemoveFrominp);
		pWest.add(numOfDiscsToRemovelab);
		pWest.add(numOfDiscsToRemoveinp);
		pWest.add(turn);
		p1.add(pWest, BorderLayout.WEST);
		
		confirm1=new JButton("Potvrdi");
		confirm1.addActionListener(this);
		pSouth=new JPanel();
	    pSouth.add(confirm1);
	    p1.add(pSouth,BorderLayout.SOUTH); 

        myPicture = new ImageIcon[MAX+1];
        for(int i=0;i<MAX+1;i++){
        	myPicture[i]=new ImageIcon(ImageIO.read(new File("img\\"+i+".png")));}
        
      
	    
		
		
		
		
		
		
		
	   // cekamo da korisnik unese podatke.
		while(1==1) {
		    numOfPillars=Integer.parseInt(JOptionPane.showInputDialog(pm,"Unesite broj stubova,","Broj",JOptionPane.QUESTION_MESSAGE));
		    if ((numOfPillars>MAX)||(numOfPillars<1)){
		    	 JOptionPane.showMessageDialog(pm,"Broj stubova nije u opsegu","Greska",JOptionPane.ERROR_MESSAGE);
		    	 continue;
		    }
		    break;}
		// Unos broja diskova po stubu
		pictureArray= new JLabel[numOfPillars];
		p.setLayout(new GridLayout(0,2));
	    discsOnPillar=new int[numOfPillars];	
	   	text=new JLabel[numOfPillars];		
		choices= new JTextField[numOfPillars];
		text=new JLabel[numOfPillars];
		for (int i=0;i<numOfPillars;i++) {
			int temp=i+1;
			text[i]=new JLabel("Stub "+temp);
			choices[i]=new JTextField();
			p.add(text[i]);
			p.add(choices[i]);
		}
		pPom=new JPanel();
		pPom.setLayout(new BorderLayout());
		cpm=new Playmate("Choice",8,4);
	    confirm=new JButton("Confirm");
	    pPom.add(p,BorderLayout.CENTER);
	    pPom.add(confirm, BorderLayout.SOUTH);
	    success=0;
		confirm.addActionListener(this);
		//p.add(confirm);
		cpm.add(pPom);
		cpm.setVisible(true);
		while (success==0);
		
		
		cpm.setVisible(false);
		pCenter=new JPanel();
		for(int i=0;i<numOfPillars;i++){
			pictureArray[i]=new JLabel(myPicture[discsOnPillar[i]]);
			pCenter.add(pictureArray[i]);
			
		}
		pCenter.setBackground(Color.white);
		p1.add(pCenter,BorderLayout.CENTER);
		pm.add(p1);
		
		pm.setVisible(true);
		this.setAlwaysOnTop(true);
		String []choices1= {"Covek protiv Coveka","Covek protiv Racunara","Racunar protiv Coveka","Racunar protiv Racunara"};	
	  
	    whoPlays= JOptionPane.showOptionDialog(cpm,"Izaberi tip igre ", "Tip",0, JOptionPane.QUESTION_MESSAGE,null, choices1,0);
	    String []lvlChoices= {"MiniMax","AlfaBeta","Takmicar"};
	      
	    switch (whoPlays){
		
		   case 0: {play1='h';play2='h';String name1=JOptionPane.showInputDialog(pm,"Unesite ime 1. igraca,","Igrac",JOptionPane.QUESTION_MESSAGE);
		           String name2=JOptionPane.showInputDialog(pm,"Unesite ime 2. igraca,","Igrac",JOptionPane.QUESTION_MESSAGE);
		           player1=new Player(name1,'h',this);
	           	   player2=new Player(name2,'h',this);
	           	   mt=new GameTree(numOfPillars,discsOnPillar,howDeep);
	      		
	           	   break;}
		 case   1: {  play1='h'; play2='c';
		           
		           String name1=JOptionPane.showInputDialog(pm,"Unesite ime igraca,","Igrac",JOptionPane.QUESTION_MESSAGE);
		           howDeep=Integer.parseInt(JOptionPane.showInputDialog(pm,"Unesite dubinu stabla,","Tezina",JOptionPane.QUESTION_MESSAGE));
		           lvlChoice= JOptionPane.showOptionDialog(cpm,"Izaberi tip igre za PC1 ", "Tip",0, JOptionPane.QUESTION_MESSAGE,null, lvlChoices,0);
			       
		           player1=new Player(name1,'h',this);
		           player2=new Player("PC1",'c',this,lvlChoice,true);
		           mt=new GameTree(numOfPillars,discsOnPillar,howDeep);
		      		
		           break;}
		 
		 case 2: {play1='c'; play2='h';
		          String name2=JOptionPane.showInputDialog(pm,"Unesite ime  igraca,","Igrac",JOptionPane.QUESTION_MESSAGE);
		          howDeep=Integer.parseInt(JOptionPane.showInputDialog(pm,"Unesite dubinu stabla,","Tezina",JOptionPane.QUESTION_MESSAGE));
		          lvlChoice= JOptionPane.showOptionDialog(cpm,"Izaberi tip igre za PC1", "Tip",0, JOptionPane.QUESTION_MESSAGE,null, lvlChoices,0);
			
		          player2=new Player(name2,'h',this);
		          player1=new Player("PC1",'c',this,lvlChoice,false);
		          mt=new GameTree(numOfPillars,discsOnPillar,howDeep);		
		          break;}
		 
		 case 3: {play1='c';play2='c';
		          howDeep=Integer.parseInt(JOptionPane.showInputDialog(pm,"Unesite dubinu stabla,","Tezina",JOptionPane.QUESTION_MESSAGE));		 
	          	  lvlChoice= JOptionPane.showOptionDialog(cpm,"Izaberi tip igre za PC1", "Tip",0, JOptionPane.QUESTION_MESSAGE,null, lvlChoices,0);
		    	  
		          player1=new Player("PC1",'c',this,lvlChoice,false);
		          lvlChoice= JOptionPane.showOptionDialog(cpm,"Izaberi tip igre za PC2", "Tip",0, JOptionPane.QUESTION_MESSAGE,null, lvlChoices,0);			
		          player2= new Player("PC2",'c',this,lvlChoice,true);
		          mt=new GameTree(numOfPillars,discsOnPillar,howDeep);
		          break;}
		 
		 }
	     turn.setText("Na potezu je "+player1.getName());
	     p1.revalidate();
		 prevDiscsTaken=1;
		 play(player1,player2);
		 
			/*listOfChoices =new JComboBox(choices1);
			listOfChoices.setSelectedIndex(0);
			listOfChoices.setSize(20,40);
			p1.add(listOfChoices);
			=new JButton("Confirm");
			confirm1.addActionListener(this);
			p1.add(confirm1);
			cpm.add(p1);*/

		
	}
	
	/**
	 * @param player1
	 * @param player2
	 * Zapocinje igra.
	 * 
	 */
	public void play(Player player1, Player player2){
		  int i;
		  /*for ( i=0;i<numOfPillars;i++){
			   for (int j=0;j<discsOnPillar[i];j++)
				   System.out.print("-");
			   System.out.println(discsOnPillar[i]);
		  }*/
		  if (player1.getPlayerType()=='h'){
			  
			   numToRemove=0;removeFrom=0;
	    	  
	    	  while(1==1){
	    		 success=0; 
			     while(success==0);
			     int check=player1.makeAMove(numToRemove,removeFrom);
			     if (check==1){
			    	 pictureArray[removeFrom].setIcon(myPicture[discsOnPillar[removeFrom]]);
			    	 turn.setText("Na potezu je: "+player2.getName());
                    p1.revalidate();
			    	
			    	 break;
			     }
			  
			  
		  }
		  }
		  else{
				  try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  mt.makeATree(prevDiscsTaken, discsOnPillar, null, 0, 0);
			 
			  switch(player1.getPcType()){
			  
			  case 0: { 
				 
				  int temp=player1.minimax(mt.getRoot(), howDeep, player1.getMim());
				  int index;
				  for(index=0;index<mt.getRoot().getChildren().length;index++)
					  if (mt.getRoot().getAChild(index).getHeuristicValue()==temp) break;
				  Node n=mt.getRoot().getAChild(index);
				  for(int x=0;x<numOfPillars;x++)
					  if (discsOnPillar[x]!=n.getValue(x)){
					  index=x;
					  prevDiscsTaken=discsOnPillar[x]-n.getValue(x);
					  discsOnPillar[x]=n.getValue(x);
				  }
	
				  pictureArray[index].setIcon(myPicture[discsOnPillar[index]]);
				  turn.setText("Na potezu je: "+player2.getName());
				  p1.revalidate();
				  mt.setRoot(null);
				  break;
				  
				  
				  
			  }
			  case 1:{ 
			  
			  int temp=player1.alfaBeta(mt.getRoot(),howDeep,Integer.MIN_VALUE, Integer.MAX_VALUE,player1.getMim());
			  int index;
			  for(index=0;index<mt.getRoot().getChildren().length;index++)
				  if (mt.getRoot().getAChild(index).getHeuristicValue()==temp) break;
			  Node n=mt.getRoot().getAChild(index);
			  for(int x=0;x<numOfPillars;x++)
				  if (discsOnPillar[x]!=n.getValue(x)){
				  index=x;
				  prevDiscsTaken=discsOnPillar[x]-n.getValue(x);
				  discsOnPillar[x]=n.getValue(x);
			  }

			  pictureArray[index].setIcon(myPicture[discsOnPillar[index]]);
			  turn.setText("Na potezu je: "+player2.getName());
			  p1.revalidate();
			  mt.setRoot(null);
			  break;
			  
				  
			  }
			  case 2:{
				  int temp=player1.competitor(mt.getRoot(),howDeep,Integer.MIN_VALUE, Integer.MAX_VALUE,player1.getMim());
				  int index;
				  for(index=0;index<mt.getRoot().getChildren().length;index++)
					  if (mt.getRoot().getAChild(index).getHeuristicValue()==temp) break;
				  Node n=mt.getRoot().getAChild(index);
				  for(int x=0;x<numOfPillars;x++)
					  if (discsOnPillar[x]!=n.getValue(x)){
					  index=x;
					  prevDiscsTaken=discsOnPillar[x]-n.getValue(x);
					  discsOnPillar[x]=n.getValue(x);
				  }

				  pictureArray[index].setIcon(myPicture[discsOnPillar[index]]);
				  turn.setText("Na potezu je: "+player2.getName());
				  p1.revalidate();
				  mt.setRoot(null);
				  break;

				  
			  }
			  }
			  
		  }
			  
		    for (i=0;i<numOfPillars;i++){
				    if(discsOnPillar[i]!=0) break;
			    }
	
	         
		if (i==numOfPillars){
			System.out.println("POBEDAAA");
			JOptionPane.showMessageDialog(this, "Bravo "+player1.getName()+" pobedili ste!","Dobijen je pobednik",JOptionPane.INFORMATION_MESSAGE);
	    	  
		}
		else play(player2,player1);
			
}
		
	public void actionPerformed(ActionEvent e) {
		Object src=e.getSource();
		if( src.equals(confirm)) {
		    int check=0;
			for (int i=0;i<numOfPillars;i++) {
				if ((Integer.parseInt(choices[i].getText())<0)||(Integer.parseInt(choices[i].getText())>10)) {
					int temp=i+1;
					JOptionPane.showMessageDialog(pm,"Broj diskova na  "+temp+". stubu nije u opsegu","Greska",JOptionPane.ERROR_MESSAGE);
					 return;}
				else 
				for (int j=1+i;j<numOfPillars;j++) {
					if(Integer.parseInt(choices[i].getText())==Integer.parseInt(choices[j].getText())) {
						int temp=i+1;
						int temp2=j+1;
						JOptionPane.showMessageDialog(pm,"Na stubovima "+temp+" i "+temp2+" se nalazi jednak broj diskova ","Greska",JOptionPane.ERROR_MESSAGE);
						return;}
	
					}
				discsOnPillar[i]=Integer.parseInt(choices[i].getText());
				if(discsOnPillar[i]>0) check=1;
				
				}
			if (check==0){
				JOptionPane.showMessageDialog(pm,"Svi diskkovi su prazni! ","Greska",JOptionPane.ERROR_MESSAGE);
				return;
			}
			success=1;
			return;
			
			}
		if (src.equals(confirm1)){
			removeFrom=Integer.parseInt(pillarToRemoveFrominp.getText() );
			numToRemove=Integer.parseInt(numOfDiscsToRemoveinp.getText() );
			removeFrom--;
			success=1;
		}
	
			
		}
		
}