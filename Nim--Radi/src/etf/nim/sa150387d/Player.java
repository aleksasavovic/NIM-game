package etf.nim.sa150387d;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author Aleksa
 *Klasa u kojoj se nalaze sve vrste poteza(Covek, Minimax,AlfaBeta i Takmicar)
 */
public class Player  {
	private String name;
	private char playerType;
	private Game game;
	private boolean mim;
	private int pcType;
	Scanner sc;
	public int counter;
	private HashMap<Node,Node> hm;

	public Player(String name, char playerType,Game game,int pctype,boolean mm) {
		this.name = name;
		this.playerType=playerType;
		this.game=game;
        pcType=pctype;	
        mim=mm;
	}
	public Player(String s,char pt,Game g){
		name=s;
		playerType=pt;
		game=g;
	}
	
	/**
	 * @param numOfDiscsToRemove
	 * @param pillarToRemoveFrom
	 * potez koji igra covek.
	 * @return
	 */
	public int makeAMove(int numOfDiscsToRemove,int pillarToRemoveFrom){
   	  
	  	  int[] discsOnPillar=game.getDiscsOnPillar();
	  	  int prevDiscsTaken=game.getPrevDiscsTaken();
		  int numOfPillars=game.getNumOfPillars();
	  	  
	  	  int canBeRemoved;
	      
	           if ((pillarToRemoveFrom>=game.getNumOfPillars())||(pillarToRemoveFrom<0)){
		      	 
		      	 JOptionPane.showMessageDialog(game,"Dati stub ne postoji", "GRESKA",JOptionPane.ERROR_MESSAGE);
		    	 return 0;
		    	 
		    }
		       if((discsOnPillar[pillarToRemoveFrom]==0)){
		   
		    	   JOptionPane.showMessageDialog(game, "Na zeljenom stubu nema diskova", "GRESKA",JOptionPane.ERROR_MESSAGE);
					 
		    	  return 0;
		       }
		       System.out.println("Izaberi broj diskova koje zelis da uzmes");
		        if (numOfDiscsToRemove> 2*prevDiscsTaken){
		    	   JOptionPane.showMessageDialog(game, "Ne mozete uzeti toliko diskova", "GRESKA",JOptionPane.ERROR_MESSAGE);
			    	  
		    	return 0;
		       }
		        if (numOfDiscsToRemove<=0){
		        	JOptionPane.showMessageDialog(game, "Morate uzeti bar jedan disk", "GRESKA",JOptionPane.ERROR_MESSAGE);
			    	  
			    	return 0;

		        	
		        }
		      
		       if(discsOnPillar[pillarToRemoveFrom]<numOfDiscsToRemove){
		    	   System.out.println("Na zeljenom stubu nema dovoljno diskova");
		    	   JOptionPane.showMessageDialog(game, "Na zeljenom stubu nema dovoljno diskova", "GRESKA",JOptionPane.ERROR_MESSAGE);
		    	  return 0;
		       }
		       int temp=discsOnPillar[pillarToRemoveFrom]-numOfDiscsToRemove;
		       
		       canBeRemoved=1;
		       if (temp>0){
		          for (int j=0;j<numOfPillars;j++){
		    	     if (j!=pillarToRemoveFrom){
		    		    if (discsOnPillar[j]==temp){
		    		 	   canBeRemoved=0;
		    			   System.out.println("Uzimanje tog broja diskova za zeljenog stuba nije moguce jer dolazi do pojave istog broja diskova na 2 razlicita stuba!!");
		    			   JOptionPane.showMessageDialog(game, "Uzimanje tog broja diskova za zeljenog stuba nije moguce jer dolazi do pojave istog broja diskova na 2 razlicita stuba!!", "GRESKA",JOptionPane.ERROR_MESSAGE);		
		    			  return 0;
		    		   }
		    	   }
		       }
          
		       if(canBeRemoved==0) return 0;
		  
	      }
		  game.setPrevDiscsTaken(numOfDiscsToRemove);
	      discsOnPillar[pillarToRemoveFrom]-=numOfDiscsToRemove;
	      return 1;
	      
		 
	}

	public String getName() {
		return name;
	}

	public char getPlayerType() {
		return playerType;
	}
    
	/**
	 * @param node
	 * @param depth
	 * @param maximizingPlayer
	 * @return
	 * Metoda u kojoj racunar potez bira Minimax algoritmom. Korisi se stablo. 
	 */
	public int minimax(Node node,int depth, boolean maximizingPlayer){
		if ((depth==0)||(node.getChildren()==null)) return  node.calculateHeuristicValue();
	    
		if (maximizingPlayer){
			int bestValue=Integer.MIN_VALUE;
			int pom=node.getChildren().length;
			for (int i=0;i<pom;i++){
				int temp=minimax(node.getAChild(i),depth-1,false);
				bestValue=chooseLarger(bestValue,temp);
			}
			node.setHeuristicValue(bestValue);
			return bestValue;
		}
		else{
			int bestValue=Integer.MAX_VALUE;
			int pom=node.getChildren().length;
			for (int i=0;i<pom;i++){
				int temp=minimax(node.getAChild(i),depth-1,true);
				bestValue=chooseSmaller(bestValue,temp);
			}
			node.setHeuristicValue(bestValue);
			return bestValue;
		}
	} 
	
	/**
	 * @param node
	 * @param depth
	 * @param alfa
	 * @param beta
	 * @param maximizingPlayer
	 * 
	 * @return
	 *  Metod u kojem racunar potez bira AlfaBeta odsecanjem. 
	 */
	public int alfaBeta(Node node,int depth,int alfa, int beta,boolean maximizingPlayer){
		if ((depth==0)||(node.getChildren()==null)) {


			return  node.calculateHeuristicValue();
		}
		
		if (maximizingPlayer){
			int pomValue=Integer.MIN_VALUE;
			int pom=node.getChildren().length;
			for(int i=0;i<pom;i++){
				int temp=alfaBeta(node.getAChild(i),depth-1,alfa,beta,false);
				pomValue=chooseLarger(pomValue,temp);
				alfa=chooseLarger(alfa,pomValue);
				if (beta<=alfa) break;
			
			}

			node.setHeuristicValue(pomValue);
			return pomValue;}
		else{
			int pomValue=Integer.MAX_VALUE;
			int pom=node.getChildren().length;
			for(int i=0;i<pom;i++){
				int temp=alfaBeta(node.getAChild(i),depth-1,alfa,beta,true);
				pomValue=chooseSmaller(pomValue,temp);
				beta=chooseSmaller(beta,pomValue);
				if (beta<=alfa) break;
			} 
			node.setHeuristicValue(pomValue);
			return pomValue;}

		
	}
	/**
	 * @return
	 * vraca tezinu racunara, odnosno da li je Minimax,AlfaBeta ili Takmicar
	 */
	public int getPcType(){  
		return pcType;
	}
	public int competitor(Node node,int depth,int alfa, int beta,boolean maximizingPlayer){
		if ((depth==0)||(node.getChildren()==null)) {


			return  node.calculateHeuristicValue(maximizingPlayer);
		}
		
		if (maximizingPlayer){
			int pomValue=Integer.MIN_VALUE;
			int pom=node.getChildren().length;
			for(int i=0;i<pom;i++){
				int temp=competitor(node.getAChild(i),depth-1,alfa,beta,false);
				pomValue=chooseLarger(pomValue,temp);
				alfa=chooseLarger(alfa,pomValue);
				if (beta<=alfa) break;
			
			}

			node.setHeuristicValue(pomValue);
			return pomValue;}
		else{
			int pomValue=Integer.MAX_VALUE;
			int pom=node.getChildren().length;
			for(int i=0;i<pom;i++){
				int temp=competitor(node.getAChild(i),depth-1,alfa,beta,true);
				pomValue=chooseSmaller(pomValue,temp);
				beta=chooseSmaller(beta,pomValue);
				if (beta<=alfa) break;
			} 
			node.setHeuristicValue(pomValue);
			return pomValue;}

		
	}
	public int hashInitiation(Node node,int depth,int alfa, int beta,boolean maximizingPlayer){
		hm = new HashMap<Node,Node>();
		return hashingCompetitor( node, depth,alfa, beta, maximizingPlayer);
	}
	public int hashingCompetitor(Node node,int depth,int alfa, int beta,boolean maximizingPlayer){
		if ((depth==0)||(node.getChildren()==null)) {


			return  node.calculateHeuristicValue(maximizingPlayer);
		}
		
		if (maximizingPlayer){
			Node n=hm.get(node.getVaules());
			if(n!=null){
				if((n.getLvl()>=depth)&&(node.getPrevDiscsTaken()<=n.getPrevDiscsTaken())){
					return n.getHeuristicValue();
				}
			}
			int pomValue=Integer.MIN_VALUE;
			int pom=node.getChildren().length;
			for(int i=0;i<pom;i++){
				int temp=competitor(node.getAChild(i),depth-1,alfa,beta,false);
				pomValue=chooseLarger(pomValue,temp);
				alfa=chooseLarger(alfa,pomValue);
				if (beta<=alfa) break;
			
			}

			node.setHeuristicValue(pomValue);
			return pomValue;}
		else{
			int pomValue=Integer.MAX_VALUE;
			int pom=node.getChildren().length;
			for(int i=0;i<pom;i++){
				int temp=competitor(node.getAChild(i),depth-1,alfa,beta,true);
				pomValue=chooseSmaller(pomValue,temp);
				beta=chooseSmaller(beta,pomValue);
				if (beta<=alfa) break;
			} 
			node.setHeuristicValue(pomValue);
			return pomValue;}

		
	}
	public int chooseLarger(int a,int b){
		if (a>=b) return a;
		else return b;
	}
	public int chooseSmaller(int a,int b){
		if (a<=b) return a;
		else return b;
	}

	
  /**
 * @return
 * Da li je max ili min igrac
 */
public boolean getMim() {
	return mim;
}
}
