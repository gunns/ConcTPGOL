package edu.unq.pconc.gameoflife.solution;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import edu.unq.pconc.gameoflife.CellGrid;

public class GameOfLifeGrid implements CellGrid {
	private List<List<Boolean>> tabla = new ArrayList<List<Boolean>>();
	private int x = 0;
	private int y = 0;
	private int gen = 0;
	private int threads = 1;
	


	@Override
	public boolean getCell(int col, int row) {
		return this.tabla.get(col).get(row) ;
	}

	@Override
	public void setCell(int col, int row, boolean cell) {
		this.tabla.get(col).set(row,cell);
		
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(x,y);
	}

	@Override
	public void clear() {
		int c = 0;
		while (c<=this.x){
			int f = 0;
			while (f<=this.y){
				this.tabla.get(c).set(f, false);
			}
		}
	}

	@Override
	public int getGenerations() {
		return this.gen;
	}

	@Override
	public void next() {
		this.gen = this.gen+1;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int col, int row) {
		List<List<Boolean>> nuevaTabla =new ArrayList<List<Boolean>>();
		int i = 0;
		while (i<=col){
			int r = 0;
			nuevaTabla.add(new ArrayList<Boolean>());
			while (r<=row){
				if (this.tabla.get(i).get(r)!= null){
					nuevaTabla.get(i).add(r, this.tabla.get(i).get(r));
				}else{
					nuevaTabla.get(i).add(r, false);
				}
			}
			
		}
		this.tabla = nuevaTabla;
		this.x = col;
		this.y = row;
	}

	@Override
	public void setThreads(int threads) {
		if(threads<1){
			this.threads=1;
			System.out.println("El numero de threads debe ser 1 o superior");
		}else{
			this.threads=threads;
		}
		
	}

}
