import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class UneFenetre extends JFrame implements ActionListener 
{
    UnMobile sonMobile;
    private final int LARG=800, HAUT=250;
    JButton sonBouton ;
    boolean marche = true ;
    Thread laThread ;
    
    public UneFenetre()
    {
    	super("le Mobile") ;
    	
    	this.setLayout(new GridLayout(1,2));
    	
    	//Instanciation et ajout du mobile
    	sonMobile = new UnMobile(LARG/2, HAUT) ;
    	this.add(sonMobile);
    	
    	//Création de la Thread
    	laThread = new Thread(sonMobile) ;
    	laThread.start();
    	
    	//Création et ajout du bouton
    	sonBouton = new JButton("Marche/Arrêt");
    	sonBouton.addActionListener(this);
    	sonBouton.setMaximumSize(new Dimension(50,50));;
    	this.add(sonBouton);
    	
    	
    	//Ajustement de la fenêtre
    	this.setSize(LARG,HAUT);
    	this.setVisible(true);
    }

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == sonBouton) {
			if (marche) {
				laThread.suspend();
				marche = false;
			}
			
			else {
				laThread.resume();
				marche = true ;
			}
		}
	}
}
