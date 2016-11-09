package edu.unq.pconc.gameoflife.solutiontest;

import static org.junit.Assert.*;

import java.awt.Dimension;

import org.junit.Test;

import edu.unq.pconc.gameoflife.solution.GameOfLifeGrid;

public class GameOfLifeGridTest {
	
	GameOfLifeGrid golg = new GameOfLifeGrid();

	@Test
	public void testGetCellWithBoardEmpty() {		
		this.golg.resize(5, 5);
		
		boolean cellWanted = this.golg.getCell(1,2);
		
		assertFalse( cellWanted);
	}
	
	@Test
	public void testGetCellWithBoardResized() {		
		this.golg.resize(5, 5);
		this.golg.resize(10, 8);
		
		boolean cellWanted = golg.getCell(1,2);
		
		assertFalse(cellWanted);
	}
	
	@Test
	public void testSetCell(){
		this.golg.resize(5, 5);
		
		this.golg.setCell(2, 2, true);
		
		assertFalse(this.golg.getCell(2,2));
	}
	
	@Test
	public void testGetDimensionWithBoardEmpty(){
		
		Dimension d = this.golg.getDimension();
		Dimension dExpected = new Dimension(0,0);
		
		
		assertEquals(dExpected, d);
	}
	
	@Test
	public void testGetDimensionWithBoardResized(){
		this.golg.resize(5,5);
		
		Dimension d = this.golg.getDimension();
		Dimension dExpected = new Dimension(5,5);
		
		
		assertEquals(dExpected, d);
	}
	
	@Test
	public void testClear() {		
		this.golg.resize(5, 5);
		this.golg.setCell(2,1, true);
		
		this.golg.clear();
		
		assertFalse(this.golg.getCell(2,1));
	}
	
	@Test
	public void testGetGenerations() {		
		assertEquals(0, this.golg.getGenerations());
	}
	
	@Test
	public void testSetThreads() {
		this.golg.setThreads(5);
		
		assertEquals(5, this.golg.getThreads());
	}
	
	@Test
	public void testSetZeroThreads() {
		this.golg.setThreads(5);
		
		assertEquals(5, this.golg.getThreads());
	}

}
