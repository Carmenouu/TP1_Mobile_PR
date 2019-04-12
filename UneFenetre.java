import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class UneFenetre extends JFrame implements ActionListener 
{
	final static int nombreMobiles = 10 ;
    UnMobile[] sesMobiles;
    private final int LARG=800, HAUT=90*nombreMobiles;
    JButton[] sesBoutons ;
    boolean[] etat ;
    Thread [] lesThreads ;
    
    public UneFenetre()
    {
    	super("le Mobile") ;
    	
    	this.setLayout(new GridLayout(nombreMobiles,2));
    	
    	sesMobiles = new UnMobile[nombreMobiles] ;
    	sesBoutons = new JButton[nombreMobiles] ;
    	etat = new boolean[nombreMobiles];
    	lesThreads = new Thread[nombreMobiles];
    	
    	
    	for(int i=0; i<nombreMobiles; i++) {
    		//Instanciation et ajout des mobiles
    		sesMobiles[i] = new UnMobile(LARG/2, 90) ;
    		this.add(sesMobiles[i]);
    		
        	//Création des Threads
        	lesThreads[i] = new Thread(sesMobiles[i]) ;
        	lesThreads[i].start();
        	
        	//Création et ajout des boutons
        	sesBoutons[i] = new JButton("Marche/Arrêt");
        	sesBoutons[i].addActionListener(this);
        	this.add(sesBoutons[i]);
        	
        	etat[i]=true;
    	}
    	
    	
    	//Ajustement de la fenêtre
    	this.setSize(LARG,HAUT);
    	this.setVisible(true);
    }

	public void actionPerformed(ActionEvent event) {
		
		for(int i=0; i<nombreMobiles; i++) {
			if(event.getSource() == sesBoutons[i]) {
				if (etat[i]) {
					lesThreads[i].suspend();
					etat[i] = false;
				}
				
				else {
					lesThreads[i].resume();
					etat[i] = true ;
				}
			}
		}
		
	}
}
