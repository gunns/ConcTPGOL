package edu.unq.pconc.gameoflife.solutiontest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import edu.unq.pconc.gameoflife.solution.GameOfLifeGrid;
import edu.unq.pconc.gameoflife.solution.GameOfLifeGridEvaluator;

public class GameOfLifeGridEvaluatorTest {
	
	GameOfLifeGrid golg = new GameOfLifeGrid();
	
	@Test
	public void testEvaluateUpperRowOfCoordinate2And1() {
		this.golg.resize(4, 4);
		this.golg.setCell(2,1, true);
		this.golg.setCell(2,2, true);
		this.golg.setCell(2,3, true);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(0);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		
		GameOfLifeGridEvaluator runnable = new GameOfLifeGridEvaluator(this.golg, this.golg.getBoard(), numbers);
		
		int liveAmount = runnable.evaluateRow(2,0); //fila de arriba=> (2,1)-(0,1) = (2,0)  
		
		assertEquals(0, liveAmount);
	}
	
	@Test
	public void testEvaluateNetherRowOfCoordinate2And1() {
		this.golg.resize(4, 4);
		this.golg.setCell(2,1, true);
		this.golg.setCell(2,2, true);
		this.golg.setCell(2,3, true);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(0);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		
		GameOfLifeGridEvaluator runnable = new GameOfLifeGridEvaluator(this.golg, this.golg.getBoard(), numbers);
		
		int liveAmount = runnable.evaluateRow(2,2); //fila de abajo=> (2,1)+(0,1) = (2,2)  
		
		assertEquals(1, liveAmount);
	}
	
	@Test
	public void testEvaluatePreviousAndSubsequentCellOfCoordinate2And1() {
		this.golg.resize(4, 4);
		this.golg.setCell(2,1, true);
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(0);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		
		GameOfLifeGridEvaluator runnable = new GameOfLifeGridEvaluator(this.golg, this.golg.getBoard(), numbers);
		
		int liveAmount1 = runnable.neighboringInRow(2,1);
		
		assertEquals(0, liveAmount1);
	}

	@Test
	public void test() {
		this.golg.resize(4, 4);
		this.golg.setCell(2,1, true);
		this.golg.setCell(2,2, true);
		this.golg.setCell(2,3, true);
		System.out.println(golg.getBoard());
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		numbers.add(0);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		
		Runnable runnable = new GameOfLifeGridEvaluator(this.golg, this.golg.getBoard(), numbers);
		
		runnable.run();
		System.out.println(this.golg.getBoardNext());
	}
}
