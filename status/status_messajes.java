package com.elizalde.status;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class status_messajes extends JPanel{
	
	public static String[] mensajes= {"Productor:","Consumidor:"};
	public JLabel labels[] = new JLabel[6];
	
	public status_messajes(int width,int height)
	{
		
		setLayout(new GridLayout(3,2,0,0));
		setBounds(0,0,width,height);
		setBorder(BorderFactory.createLineBorder(Color.RED,2));
		for(int i = 0; i < labels.length;i++)
		{
			labels[i] = new JLabel();
		}
		
		
		labels[0].setText(mensajes[0]);
		labels[2].setText(mensajes[1]);
		
		for(int i = 0; i < labels.length; i++)
		{
			add(labels[i]);
		}
		
		
		
		
	}
}
