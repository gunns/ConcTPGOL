package edu.unq.pconc.gameoflife.solution;

import java.util.List;

public class GameOfLifeGridEvaluator implements Runnable {
	GameOfLifeGrid golG;
	private List<List<Boolean>> columns;
	private List<Integer> indices;
	
	public GameOfLifeGridEvaluator(List<List<Boolean>> columns, List<Integer> indices) {
		this.columns = columns;
		this.indices = indices;		
	}

	@Override
	public void run() {
		for(int i=0; i < this.columns.size(); i++){
			for(int r=0; r < this.indices.size(); r++){
				this.evaluarCelda(indices.get(i), r);
			}
		}
	}
	
	public void evaluarCelda(int col, int row){
		Boolean cell= this.golG.getCell(col, row);
		if (!cell){
			this.golG.setCell(col, row, (this.cantVecinas(col, row)==3));
		}
	}

	private int cantVecinas(int col, int row) {
		int cant = 0;
		if(row!=0){
			cant=cant + this.filaEvaluada(col, row-1);
		}
		cant = cant + this.vecinasDeFilas(col, row);
		if(row!=this.golG.getDimension().height){
			cant=cant+ this.filaEvaluada(col, row+1);
		}
		return cant;
	}


	private int vecinasDeFilas(int col, int row){
		int cant = 0;
		if(col != 0){
			if(this.golG.getCell(col-1, row)){
				cant=cant+1;
			}
		}
		if(col != this.golG.getDimension().width){
			if(this.golG.getCell(col+1, row)){
				cant=cant+1;
			}
		}
		return cant;
	}
	
	private int filaEvaluada(int col, int row){
		int cant =0;
		if (col !=0){
			if (this.golG.getCell(col-1, row)){
				cant = cant+1;
			}
		}
		if (this.golG.getCell(col, row)){
			cant=cant+1;
		}
		if(col != this.golG.getDimension().width){
			if(this.golG.getCell(col+1, row)){
				cant=cant+1;
			}
		}
		return cant;
	}
}