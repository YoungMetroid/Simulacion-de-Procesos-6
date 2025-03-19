package com.elizalde.productor_consumidor;

import java.awt.Color;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class hilo_Productor implements Runnable{
	
	Thread productor;
	private Productor_Consumidor ps;
	public boolean casillas[];
	boolean running = true;	
	
	int lastPosition;
	int startingWaitTime = 700;
	
	public hilo_Productor(Productor_Consumidor ps)
	{
		this.ps = ps;
	}
	
	public void startThread()
	{
		running = true;
		productor = new Thread(this,"Productor");
		productor.start();
	}

	@Override
	public void run() {
		Random random = new SecureRandom();
		int productor;
		while(running)
		{
			ps.setPSleeping();
			try {
				
				TimeUnit.MILLISECONDS.sleep(startingWaitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ps.setPSleeping();
			if(!ps.getChoice() && ps.getExecutionC() == false)
			{
				ps.setExecutionP();
				productor = random.nextInt(8-2+1)+2;
				
				for(int i = 0; i < productor;i++)
				{	
					try {
						TimeUnit.MILLISECONDS.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(lastPosition == 40)
					{lastPosition = 0;}
					
					if(ps.casillas[lastPosition] == false)
					{
						ps.casillas[lastPosition] = true;
						ps.labels[lastPosition].setBackground(Color.BLUE);
						lastPosition++;
					}
				}
				ps.setExecutionP();
			}
			else
			{
				ps.setPIntento();
			}
			startingWaitTime = random.nextInt(500-1+1)+500;
		}
		
	}

}
