package edu.unq.pconc.gameoflife.solution;

import java.awt.Dimension;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.unq.pconc.gameoflife.CellGrid;

public class GameOfLifeGrid implements CellGrid {
	private List<List<Boolean>> board = new ArrayList<List<Boolean>>();
	private List<List<Boolean>> boardNext = new ArrayList<List<Boolean>>();
	private int x = 0;
	private int y = 0;
	private int gen = 0;
	private int threads = 1;
	private ExecutorService pool;
	private List<List<Integer>> indicesCurrents;
	
	////////// setters //////////
	private void setWidth(int x){
		this.x = x;
	}
	private void setHeight(int y){
		this.y = y;
	}
	////////////////////////////
	///////// getters //////////
	public int getThreads(){
		return this.threads;
	}
	public List<List<Boolean>> getBoard(){
		return this.board;
	}
	public List<List<Boolean>> getBoardNext(){
		return this.boardNext;
	}
	public int getWidth() {
		return this.x;
	}
	public int getHeight() {
		return this.y;
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
		List<List<List<Boolean>>> tasksThreads = this.dividerTask();
		for(int i=0; i< this.threads; i++){
			Runnable runnable = new GameOfLifeGridEvaluator(this, tasksThreads.get(i), this.indicesCurrents.get(i));
			this.pool.execute(runnable);
		}
		//se terminan los threads y avisan
		this.board= this.boardNext;
	}

	private List<List<List<Boolean>>> dividerTask() {
		List<List<List<Boolean>>> tasks = new ArrayList<List<List<Boolean>>>();
		List<List<Integer>> indices = new ArrayList<List<Integer>>();
		int cantColumns = this.x / this.threads;//cantidad de columnas para cada thread
		int leftover = this.x % this.threads;//cantidad de columnas restantes
		int t= 0;
		int i= 0;
		int init= 0;
		while(t < this.threads){
			List<List<Boolean>> columns = new ArrayList<List<Boolean>>();
			ArrayList<Integer> numbers = new ArrayList<Integer>();
			while(i < cantColumns){
				columns.add(this.board.get(init));
				numbers.add(init);
				i+= 1;
				init+= 1;
			}
			tasks.add(columns);
			indices.add(numbers);
			i= 0;
			t+= 1;
		}
		t= 0;
		while(t < this.threads & init < this.x){
			while(i < leftover){
				tasks.get(i).add(this.board.get(init));
				indices.get(i).add(init);
				i+= 1;
				init+= 1;
			}
			i= 0;
			t+= 1;
		}
		this.indicesCurrents = indices;
		return tasks;
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
		this.setHeight(col);
		this.setWidth(row);
	}

	@Override
	public void setThreads(int threads) {
		this.pool = Executors.newFixedThreadPool(threads);
		this.threads = threads;
		//if(threads < 1){
		//	System.out.println("El numero de threads debe ser 1 o superior");
		//}	
	}
}
