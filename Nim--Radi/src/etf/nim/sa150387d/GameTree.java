package etf.nim.sa150387d;

import java.util.HashMap;

/**
 * @author Aleksa
 *Klasa koja pretstavlja nase stablo igre.
 */
public class GameTree {
	private int numOfPillars;
	private int discsOnPillar[];
	private int howDeep;
	private int lvl;
	private Node root;
	
	
	public GameTree(int numofp,int[] discsOnP,int hd){
		numOfPillars=numofp;
		discsOnPillar=discsOnP;
		howDeep=hd;
		root=null;}
	
	
	/**
	 * @param prevPillarsTaken
	 * @param discs
	 * @param father
	 * @param index
	 * @param lvl
	 * Rekurzivno generisemo stablo. U prvom delu algoritma racunamo koliko dece ima neki cvor(u koliko stanja se moze preci iz trenutnog)
	 */
	public void makeATree(int prevDiscsTaken,int discs[],Node father,int index,int lvl){
		
		int cout=0;
		if (howDeep>lvl){
		for (int i=0;i<numOfPillars;i++)
			for(int j=1;j<=prevDiscsTaken*2;j++)
				if ((discs[i]-j)>=0) {
					boolean check=true;
					if ((discs[i]-j)!=0)
					for(int z=0;z<numOfPillars;z++){
						if ((i!=z)&&((discs[i]-j)==discs[z])){
							check=false;
							break;
						}
					}
						if(check) cout++;
					    
					
				}
				else break;
		}
		Node tempNode;
		if (father==null){
			root=new Node(numOfPillars,cout,0,discs);
			tempNode=root;
		}
		else {father.children[index]= new Node(numOfPillars,cout,0,discs);
		tempNode=father.children[index];
		}
		tempNode.setLvl(lvl);
		tempNode.setPrevDiscsTaken(prevDiscsTaken);
		int currChild=0;
		if (howDeep<=lvl) return;
		for (int i=0;i<numOfPillars;i++){
			for(int j=1;j<=prevDiscsTaken*2;j++){
				
				if(discs[i]-j>=0){
					boolean check=true;
					if((discs[i]-j)!=0)
					for(int z=0;z<numOfPillars;z++){
						if ((i!=z)&&((discs[i]-j)==discs[z])){
							check=false;
							break;
						}}
						if(check){
			  		        discs[i]-=j;
					        makeATree(j,discs,tempNode,currChild,lvl+1);
				         	currChild++;
			       	    	discs[i]+=j;
				
			}	
		}	else break;	
	}}
		return;}
	
	
  public Node getRoot(){return root;}

  public void setRoot(Node n){ root=n;}
}
 