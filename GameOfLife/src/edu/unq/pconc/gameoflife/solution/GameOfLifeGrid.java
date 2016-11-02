package edu.unq.pconc.gameoflife.solution;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import edu.unq.pconc.gameoflife.CellGrid;

public class GameOfLifeGrid implements CellGrid {
	private List<List<Boolean>> board = new ArrayList<List<Boolean>>();
	private List<List<Boolean>> boardNext = new ArrayList<List<Boolean>>();
	private int x = 0;
	private int y = 0;
	private int gen = 0;
	private int threads = 1;
	
	////////// setters //////////
	private void setWidth(int x){
		this.x = x;
	}
	private void setHigh(int y){
		this.y = y;
	}
	////////////////////////////
	///////// getters //////////
	public int getThreads(){
		return this.threads;
	}
	////////////////////////////
	

	@Override
	public boolean getCell(int col, int row) {
		return this.board.get(col).get(row) ;
	}

	@Override
	public synchronized void setCell(int col, int row, boolean cell) {
		this.board.get(col).set(row,cell);
		
	}

	@Override
	public Dimension getDimension() {
		return new Dimension(x,y);
	}

	@Override
	public void clear() {
		this.board = new ArrayList<List<Boolean>>();
		this.resize(this.x, this.y);
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
		this.board= this.boardNext;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int col, int row) {
		List<List<Boolean>> newBoard =new ArrayList<List<Boolean>>();
		int c= 0;
		int r= 0;
		if(this.board.isEmpty()){
			while(c<col){
				newBoard.add(c,new ArrayList<Boolean>());
				while(r<row){
					newBoard.get(c).add(r,false);
					r+=1;
				}
				r= 0;
				c+=1;
			}
		}
		else{
			while(c<col){
				newBoard.add(c, new ArrayList<Boolean>());
				while (r<row){
					if(newBoard.size() <= this.board.size()){
						if(newBoard.get(c).size() < this.board.get(c).size()){
							newBoard.get(c).add(r, this.board.get(c).get(r));
						}else{
							newBoard.get(c).add(r, false);
						}
					}else{
						newBoard.get(c).add(r, false);
					}
					r+=1;
				}
				r= 0;
				c+=1;
			}
		}
		this.board = newBoard;
		this.boardNext = new ArrayList<List<Boolean>>();
		this.boardNext.addAll(newBoard);
		this.setHigh(col);
		this.setWidth(row);
	}

	@Override
	public void setThreads(int threads) {
		if(threads < 1){
			this.setThreads(1);
			System.out.println("El numero de threads debe ser 1 o superior");
		}else{
			this.threads=threads;
		}
		
	}

}
