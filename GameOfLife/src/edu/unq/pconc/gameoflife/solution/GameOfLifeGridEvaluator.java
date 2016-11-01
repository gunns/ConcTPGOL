package edu.unq.pconc.gameoflife.solution;

public class GameOfLifeGridEvaluator {
	GameOfLifeGrid golG;
	
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