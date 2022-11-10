package etf.nim.sa150387d;

import java.util.Arrays;

/**
 * @author Aleksa
 *Klasa koja reprezentuje cvor od kojeg ce se sastojati stablo igre
 *Values[i] broj diskova na stubu i
 *hValue- vrednost heuristicke f-ije
 *
 */
public class Node {
	private int[] values;
	private int fvalue;
	Node children[];
	private int lvl;
	private int hValue;
	private int prevDiscsTaken;
	
	public Node(int numOfValues,int numOfChildren,int lvls,int... vallues){
		if (numOfChildren==0) children=null;
		else  children =new Node[numOfChildren];
		values=new int[numOfValues];
		lvl=lvls;
		int i=0;
	    for (Integer v:vallues){
	    	values[i]=v;
	    	i++;
	    }
	   
		
	}
	
	public int getValue(int i){
		return values[i];
	}
	
	public Node[] getChildren(){
		return children;
	}
	public Node getAChild(int index){
		return children[index]; 
	}
	public int[] getVaules(){
		return values;
	}
	public void setLvl(int level){
		lvl=level;
	}
	public int getLvl(){
		return lvl;
	}
	public int calculateHeuristicValue(boolean maximizingPlayer){
		int pom=values.length;
	    hValue=values[0];
		for (int i=1;i<pom;i++){
			hValue^=values[i];
			
		}
		if (hValue==0){
			int i=0;
			for (i=0;i<values.length;i++){
				if (values[i]!=0) break;
			}
			if(i==values.length){
				if (maximizingPlayer) return hValue=Integer.MIN_VALUE;
				else hValue= Integer.MAX_VALUE;
			}
		}
		return hValue;}
	public int calculateHeuristicValue(){
		int pom=values.length;
	    hValue=values[0];
		for (int i=1;i<pom;i++){
			hValue^=values[i];		
		} 
		return hValue;
	}
	
	public void setHeuristicValue(int i){
		hValue=i;
		
	}
	public int getHeuristicValue(){
		return hValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (!Arrays.equals(values, other.values))
			return false;
		return true;
	}

	
	public int getPrevDiscsTaken() {
		return prevDiscsTaken;
	}

	public void setPrevDiscsTaken(int prevDiscsTaken) {
		this.prevDiscsTaken = prevDiscsTaken;
	}

	
}
