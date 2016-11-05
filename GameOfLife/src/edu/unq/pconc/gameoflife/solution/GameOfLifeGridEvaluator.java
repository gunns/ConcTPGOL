package edu.unq.pconc.gameoflife.solution;

import java.util.List;

public class GameOfLifeGridEvaluator implements Runnable {
	GameOfLifeGrid golG;
	private List<List<Boolean>> columns;
	private List<Integer> indices;
	
	public GameOfLifeGridEvaluator(GameOfLifeGrid golg, List<List<Boolean>> columns, List<Integer> indices) {
		this.golG = golg;
		this.columns = columns;
		this.indices = indices;		
	}

	@Override
	public void run() {
		for(int i=0; i < this.columns.size(); i++){
			for(int r=0; r < this.indices.size(); r++){
				this.evaluateCell(indices.get(i), r);
			}
		}
	}
	
	public void evaluateCell(int col, int row){
		Boolean valueCell= this.golG.getCell(col, row);
		if(valueCell){
			if(this.quantityOfNeighboring(col,row) < 2 | this.quantityOfNeighboring(col,row) > 3){
				this.golG.setCell(col, row, false);
			}
		}else{
			this.golG.setCell(col, row, (this.quantityOfNeighboring(col, row) == 3));
		}
	}

	private int quantityOfNeighboring(int col, int row) {
		int cant = 0;
		if(row!=0){
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