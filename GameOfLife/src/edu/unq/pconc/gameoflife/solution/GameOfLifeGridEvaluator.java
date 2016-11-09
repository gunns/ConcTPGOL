package edu.unq.pconc.gameoflife.solution;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeGridEvaluator implements Runnable {
	GameOfLifeGrid golG;
	private List<List<Boolean>> columns;
	private List<Integer> indices;
	private List<List<Boolean>> nuevaLista = new ArrayList<List<Boolean>>();
	
	public GameOfLifeGridEvaluator(GameOfLifeGrid golg, List<List<Boolean>> columns, List<Integer> indices) {
		this.golG = golg;
		this.columns = columns;
		this.indices = indices;		
	}
	@Override
	public void run() {
		/*for(int i=0; i < this.indices.size(); i++){
			for(int r=0; r < this.columns.get(0).size(); r++){
				this.evaluateCell(indices.get(i), r);
			}
		}*/
		this.nuevaLista= new ArrayList<List<Boolean>>();
		for (int i=0; i<this.indices.size();i+=1){
			int indicesI= this.indices.get(i);
			this.nuevaLista.add(indicesI,new ArrayList<Boolean>());
			this.nuevaLista.get(indicesI).addAll(this.golG.getBoard().get(indicesI));
		}
		int indice=0;
		while (indice <this.indices.size()){
			int row = 0;
			while (row < this.columns.get(0).size()){
				//System.out.println("(" + col + "," + row + ") : " + this.quantityOfNeighboring(col, row));
				this.evaluateCell(indices.get(indice), row);
				row +=1;
			}
			indice +=1;
		}
		for (int i=0; i<this.indices.size();i+=1){
			int indicesI=this.indices.get(i);
			for (int r=0; r<this.golG.getWidth();r++){
			this.golG.getBoardNext().get(indicesI).set( r,this.nuevaLista.get(indicesI).get(r));
			}
		}	
		this.golG.barrera.esperar();
		
	}
	
	public void evaluateCell(int col, int row){
		Boolean valueCell= this.golG.getCell(col, row);
		if(valueCell){
			if(this.quantityOfNeighboring(col,row) < 2 | this.quantityOfNeighboring(col,row) > 3){
				this.nuevaLista.get(col).set(row, false);
			}
		}else{
			nuevaLista.get(col).set(row, this.quantityOfNeighboring(col, row)==3);
		}
	}

	private int quantityOfNeighboring(int col, int row) {
		int cant = 0;
		if(row>0){
			cant+= this.evaluateRow(col, row-1);
		}
		cant+= this.neighboringInRow(col, row);
		if(row+1 <  this.golG.getHeight()){
			cant+= this.evaluateRow(col, row+1);
		}
		return cant;
	}


	public int neighboringInRow(int col, int row){
		int cant = 0;
		if(col != 0){
			if(this.golG.getCell(col-1, row)){
				cant+= 1;
			}
		}
		if(col+1 < (this.golG.getWidth())){
			if(this.golG.getCell(col+1, row)){
				cant+= 1;
			}
		}
		return cant;
	}
	
	public int evaluateRow(int col, int row){
		int cant= 0;
		if (col !=0){
			if (this.golG.getCell(col-1, row)){
				cant+= 1;
			}
		}
		if (this.golG.getCell(col, row)){
			cant+= 1;
		}
		if(col+1 < (this.golG.getWidth())){
			if(this.golG.getCell(col+1, row)){
				cant+= 1;
			}
		}
		return cant;
	}
}