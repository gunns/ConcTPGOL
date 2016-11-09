package edu.unq.pconc.gameoflife.solution;

public class Barrera {
	
	private int canThreads;
	private int b =0;
	
	public Barrera(int threads) {
		this.canThreads=threads;
	}
	
	
	public synchronized void esperar(){
		b+=1;
		while(this.b!=this.canThreads){
			try{
				wait();
			}catch (InterruptedException e){
				
			}
		}
		notifyAll();
		this.b=0;
	}
}

