package com.elizalde.productor_consumidor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.elizalde.input.Keyboard;
import com.elizalde.status.status_messajes;



public class Productor_Consumidor extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String title = "Productor-Consumidor";
	public static String[] columns = {"","","","",""};
	public static int width = 1200;
	public static int height = 600;
	
	private Thread productor;
	
	boolean running = false;
	private boolean choice = false;
	public JLabel[] labels;
	private JPanel panel;
	private JPanel gridPanel;
	
	private status_messajes status;
	private static hilo_Consumidor hiloC;
	private static hilo_Productor  hiloP;
	private static Keyboard key;
	
	private static boolean executionC = false;
	private static boolean executionP = false;
	
	private static boolean cSleeping = false;
	private static boolean pSleeping = false;
	
	private static boolean cIntento = false;
	private static boolean pIntento = false;
	boolean casillas[];

	
	public static void main(String[] args)
	{
		Productor_Consumidor ps = new Productor_Consumidor();
		ps.setResizable(true);
		ps.setTitle(Productor_Consumidor.title);
		ps.pack();
		ps.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ps.setVisible(true);
		hiloC = new hilo_Consumidor(ps);
		hiloP = new hilo_Productor(ps);
		hiloC.startThread();
		hiloP.startThread();
		//ps.startTimer();
	}
	public Productor_Consumidor()
	{
		key = new Keyboard();
		
		this.setPreferredSize(new Dimension(width,height));
		panel = new JPanel(null);
		//panel.setPreferredSize(new Dimension(width,height));
		
		status = new status_messajes((width/8)*2,height-30);
		
		
		gridPanel = new JPanel();
		gridPanel.setBounds((width/8)*2,0,(width/8)*6, height-30);
		gridPanel.setLayout(new GridLayout(5,8,0,0));
		gridPanel.setBackground(Color.white);
		
		
		labels = new JLabel[40];
		casillas = new boolean[40];
		for(int i = 0;i < casillas.length;i++)
		{
			casillas[i] = false;
		}
		
		
		
		for(int i = 0; i < 40; i++)
		{
			labels[i] = new JLabel();
			labels[i].setText(String.valueOf(i));
			labels[i].setOpaque(true);
			labels[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
			gridPanel.add(labels[i]);
		}	
		panel.add(status);
		panel.add(gridPanel);
		this.addKeyListener(key);
		this.add(panel);
		startTimer();
	}
	
	public synchronized void startTimer()
	{
		running = true;
		productor = new Thread(this,"Productor");
		productor.start();
	}
	
	public boolean getChoice()
	{
		return choice;
	}
	public void setExecutionC()
	{
		executionC = !executionC;
	}
	public void setExecutionP()
	{
		executionP = !executionP;
	}
	public void setCSleeping()
	{
		cSleeping = !cSleeping;
	}
	public void setPSleeping()
	{
		pSleeping = !pSleeping;
	}
	public void setCIntento()
	{
		cIntento = !cIntento;
	}
	public void setPIntento()
	{
		pIntento = !pIntento;
	}
	public boolean getCSleeping()
	{
		return cSleeping;
	}
	public boolean getPSleeping()
	{
		return pSleeping;
	}
	public boolean getExecutionC()
	{
		return executionC;
	}
	public boolean getExecutionP()
	{
		return executionP;
	}
	
	public void update()
	{
		key.update();
		if(key.esc == true)
		{
			setVisible(false);
			dispose();
			running = false;
			hiloC.running = false;
			hiloP.running = false;
			System.out.println("Closing");
		}
	}
	
	@Override
	public void run() {
		
		Random randomSleep = new SecureRandom();
		int sleepTime = randomSleep.nextInt(500-01)+500;
		Random random = new SecureRandom();
		int selection;
		
		while(running)
		{
			update();
				try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				selection = random.nextInt(10-1)+1;
				
				if(selection < 5)
				{
					choice = true;
				}
				else
				{
					choice = false;
				}
				
				if(executionP == true)
				{
					status.labels[1].setText("Trabajando");
				}
				else if(executionC == true)
				{
					status.labels[3].setText("Trabajando");
				}
				if(pSleeping == true)
				{
					status.labels[1].setText("Dormido");
				}
				else if(cSleeping == true)
				{
					status.labels[3].setText("Dormido");
				}
				
				if(pIntento == true)
				{
					status.labels[1].setText("Intento Trabajar");
					setPIntento();
				}
				if(cIntento == true)
				{
					status.labels[3].setText("Intento Trabajar");
					setCIntento();
				}
				
				
			
		}
		
		
	}
	
}
