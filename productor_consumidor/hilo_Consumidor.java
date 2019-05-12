package com.elizalde.productor_consumidor;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class hilo_Consumidor implements Runnable{

	Thread thread;
	private Productor_Consumidor ps;
	public boolean casillas[];
	boolean running;
	
	int lastPosition;
	int startingWaitTime = 700;
	public hilo_Consumidor(Productor_Consumidor ps)
	{
		this.ps = ps;
	}
	
	public void startThread()
	{
		running = true;
		thread = new Thread(this,"Consumidor");
		thread.start();
	}
	
	@Override
	public void run() {
		
		Random random = new Random();
		int consumer;
		while(running)
		{
			ps.setCSleeping();	
			try {
				TimeUnit.MILLISECONDS.sleep(startingWaitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps.setCSleeping();
			if(ps.getChoice() && ps.getExecutionP() == false)
			{
				ps.setExecutionC();
				consumer = random.nextInt(8-2+1)+2;
				System.out.print(consumer);
				for(int i = 0; i < consumer;i++)
				{
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(lastPosition == 40)
					{lastPosition = 0;}
				
					if(ps.casillas[lastPosition] == true)
					{
						ps.casillas[lastPosition] = false;
						ps.labels[lastPosition].setBackground(Color.WHITE);
						lastPosition++;
					}
					else break;
				}
				ps.setExecutionC();
			}
			else
			{
				ps.setCIntento();
			}
			startingWaitTime = random.nextInt(500-1+1)+500;
			
		}
		
	}

}
