import javax.swing.*;

import java.awt.event.MouseEvent;

import java.awt.event.MouseAdapter;

import java.awt.Graphics;

import java.awt.Color;



import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

public class GraphiqueP4 extends JPanel implements Vue {
    P4 p4;
    final int tailleCase = 64;
    final int space = 5;
    final int yellowPlayerValue = 1;
    final int redPlayerValue = 2;
    private int currentPlayer;


    public GraphiqueP4(){
        
        currentPlayer = yellowPlayerValue;
        this.addMouseListener(mouseAdapter);
        
    }
    public void init(){
        currentPlayer = yellowPlayerValue;
        repaint();
    }


    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());

        drawGrille(g);
       drawLineVictory(g);
    }

    private void drawGrille(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        int[][] grille = p4.getGrille();
        int y=-1;
        for(int[] ligne : grille){
            y++;
            int x = -1;
            for(int piece : ligne){
                
                x++;
                if(piece == 0){
                    g2.setColor(Color.GRAY);
                }
                else if(piece == 1){
                    g2.setColor(Color.YELLOW);
                }
                else if(piece == 2){
                    g2.setColor(Color.RED);
                }
                
                g2.fillOval(x*(space+tailleCase), y*(space+ tailleCase), tailleCase, tailleCase);
                
            }
        }


    }
    private void drawLineVictory(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2.setStroke(new BasicStroke(2));
        //System.out.println("oooo "+ p4.getXG() + (p4.getXG() != -1) );
        if(p4.getXG() != -1){
            //System.out.println("enntre !!! " + p4.getCurrX() +" "+ p4.getCurrY() +" "+ p4.getXG() +" "+ p4.getYG() +" ");
            g2.setColor(Color.BLACK);
            //g.drawLine(p4.getCurrX()*(space + tailleCase), p4.getCurrY()*(space + tailleCase), p4.getXG()*(space + tailleCase), p4.getYG()*(space + tailleCase));
            int xdep = p4.getCurrX();
            int ydep = p4.getCurrY();

            int xArr = p4.getXG();
            int yArr = p4.getYG();
            if(xdep == xArr){
                g2.drawLine(xdep*(space + tailleCase )+32, ydep*(space + tailleCase ), xArr*(space + tailleCase )+32, yArr*(space + tailleCase)+70);
            }
            else if(ydep == yArr){
                if(xdep < xArr){
                    g2.drawLine(xdep*(space + tailleCase ), ydep*(space + tailleCase )+32, xArr*(space + tailleCase )+64, yArr*(space + tailleCase)+32);
                }
                else{
                    g2.drawLine(xdep*(space + tailleCase )+64, ydep*(space + tailleCase )+32, xArr*(space + tailleCase ), yArr*(space + tailleCase)+32);
                }
                
            }
            else if(xdep < xArr){
                g2.drawLine(xdep*(space + tailleCase )+10, ydep*(space + tailleCase )+10, xArr*(space + tailleCase )+54, yArr*(space + tailleCase)+54);
            }

            else{
                g2.drawLine(xdep*(space + tailleCase )+64-10, ydep*(space + tailleCase )+6, xArr*(space + tailleCase )+5, yArr*(space + tailleCase)+64-10);
            }

            
            
            //drawGrille(g);
        }
    }



    private MouseAdapter mouseAdapter = new MouseAdapter(){

        public void mousePressed(MouseEvent e){
            
            int clickX = (e.getX()/(64+space));
            System.out.println("Colonne : "+clickX);
            if(p4.peutJouer() &&p4.getGagnant() == -1){
                if(p4.ajoutValide(clickX)){
                    p4.addPiece(clickX, currentPlayer);
                    if(currentPlayer == (yellowPlayerValue)){
                      //  repaint();
                        currentPlayer = redPlayerValue;
                    }
                    else{
                        currentPlayer = yellowPlayerValue;
                    }
            }
            
                    
                
            }
            

            
        }
    };

    @Override
    public void notifierChangement() {
        // TODO Auto-generated method stub
        repaint();
        
    }
    @Override
    public void setModele(Modele m) {
        p4 = (P4)m;
        m.enregistrer(this);
        // TODO Auto-generated method stub
        
    }
}
