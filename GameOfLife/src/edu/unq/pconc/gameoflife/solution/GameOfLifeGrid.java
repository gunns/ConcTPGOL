package edu.unq.pconc.gameoflife.solution;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import edu.unq.pconc.gameoflife.CellGrid;

public class GameOfLifeGrid implements CellGrid {
	private List<List<Boolean>> tabla = new ArrayList<List<Boolean>>();
	private List<List<Boolean>> tablaSiguiente = new ArrayList<List<Boolean>>();
	private int x = 0;
	private int y = 0;
	private int gen = 0;
	private int threads = 1;
	


	@Override
	public boolean getCell(int col, int row) {
		return this.tabla.get(col).get(row) ;
	}

	@Override
	public synchronized void setCell(int col, int row, boolean cell) {
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
	public synchronized void next() {
		this.gen = this.gen+1;
		//aca van los threads
		//se terminan los threads y avisan
		this.tabla=new ArrayList<List<Boolean>>();
		this.tabla.addAll(this.tablaSiguiente);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int col, int row) {
		List<List<Boolean>> nuevaTabla =new ArrayList<List<Boolean>>();
		int i = 0;
		if(this.tabla ==null){
			while (i<=col){
				int r=0;
				nuevaTabla.add(i,new ArrayList<Boolean>());
				while (r<row){
					nuevaTabla.get(i).add(r,false);
					r=r+1;
				}
			i=i+1;
			}
		}
		else{
			while (i<=col-1){
				int r = 0;
				nuevaTabla.add(i, new ArrayList<Boolean>());
				while (r<=row-1){
					if (this.tabla.get(i)!= null){
						if(this.tabla.get(i).get(r)!=null){
							nuevaTabla.get(i).add(r, this.tabla.get(i).get(r));
						}
					}else{
						nuevaTabla.get(i).add(r, false);
					}
				}
				r=r+1;
			}
			i=i+1;
		}	
		this.tabla = nuevaTabla;
		this.tablaSiguiente = new ArrayList<List<Boolean>>();
		this.tablaSiguiente.addAll(nuevaTabla);
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
