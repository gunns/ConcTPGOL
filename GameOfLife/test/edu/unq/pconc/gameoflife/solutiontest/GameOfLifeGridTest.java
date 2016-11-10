package edu.unq.pconc.gameoflife.solutiontest;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

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
		
		assertTrue(this.golg.getCell(2,2));
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
	
	@Test
	public void testDividerTask() {
		this.golg.resize(4, 5);
		this.golg.setThreads(2);
		
		List<Boolean> columna = new ArrayList<Boolean>();
		columna.add(false);
		columna.add(false);
		columna.add(false);
		columna.add(false);
		List<List<Boolean>> t1 = new ArrayList<List<Boolean>>();
		t1.add(0, columna);
		t1.add(1, columna);
		t1.add(2, columna);
		List<List<Boolean>> t2 = new ArrayList<List<Boolean>>();
		t2.add(0, columna);
		t2.add(1, columna);
		List<List<List<Boolean>>> tasks = new ArrayList<List<List<Boolean>>>();
		tasks.add(t1);
		tasks.add(t2);
		
		System.out.println(tasks);
		assertEquals(tasks, this.golg.dividerTask());
	}
	
	@Test
	public void testIndicesCurrents() {
		this.golg.resize(4, 5);
		this.golg.setThreads(2);
		List<List<List<Boolean>>> tasks = this.golg.dividerTask();
		
		List<Integer> i1 = new ArrayList<Integer>();
		i1.add(0, 0);
		i1.add(1, 1);
		i1.add(2, 4);
		List<Integer> i2 = new ArrayList<Integer>();
		i2.add(0, 2);
		i2.add(1, 3);
		List<List<Integer>> indices = new ArrayList<List<Integer>>();
		indices.add(i1);
		indices.add(i2);
		
		assertEquals(indices, this.golg.getIndicesCurrents());
	}	

}
