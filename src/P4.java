

import java.util.ArrayList;


public class P4 implements Modele {
   int[][] grille;
   int xG;
   int yG;
   int currX;
   int currY;
   int tour;
   int gagnant;

   boolean peutJouer;

   ArrayList<Vue> vues;

private Fen fen;

    public P4(){
        
        grille=new int[6][7];

        for(int[] ligne : grille){
            for(int val : ligne){
                val = 0;
            }
        }
        xG = -1;
        yG = -1;
        tour = 0;
        gagnant =-1;
        //System.out.println();
        
        vues = new ArrayList<>();
        peutJouer = true;
    }
  
   
    public void addPiece(int colone, int couleur){
        
      
        Thread th = new Thread() {
            public void run() {
           
                
               
                peutJouer = false;
                int y = 0;
                while(y< grille.length && grille[y][colone]== 0 ){
                    if(y>0){
                        grille[y-1][colone]= 0;

                    }
                    grille[y][colone] = couleur;
                    
                    notifVues();
                    try {
                        sleep(20);
                    } catch (InterruptedException e) {
                        
                        e.printStackTrace();
                    }
                    
                    
                    y++;
                    
                                  }
                
               
                
                check(colone,y-1,couleur);
                tour++;
                peutJouer = true;
                notifVues();
                fen.notif();
                
               // stop();
            }
            
        
              
        };
        th.start();
        
        
    
        
    }
    public int getTour(){
        return this.tour;
    }
    public boolean peutJouer(){
        return peutJouer;
    }
    public boolean check( int colone,int ligne, int player){
        

        if(checkH(colone,ligne, player) || checkV(colone,ligne,player) || checkD(colone,ligne, player)){
            System.out.println(currX +" - " + currY + " - " + xG + " - "+ yG);
            System.out.println("----------------- GAGNANT : " + player);
        }
        return false;
    }
    private boolean checkD(int colone,int ligne, int player) {
        
        int compteur = 1;
        int x =  colone-1;
        int y = ligne-1;

        currX = colone;
        currY = ligne;
        
        //DIAGONALE HAUT GAUCHE
        while(x>=0 && y>= 0){
            if(grille[y][x] == player){
                compteur++;
                if(compteur == 4){
                    xG = x;
                    yG = y;
                    gagnant = player;
                    return true;
                }
                y--;
                x--;
            }
            else{
                break;
            }
            
        }
        
        //DIAGONALE BAS DROITE
        currX = x+1;
        currY = y+1;

        x = colone+1;
        y =  ligne+1;
        //compteur = 1;

        

        while(x<=6 && y<=5){
            if(grille[y][x] == player){
                compteur++;

                if(compteur == 4){
                    xG = x;
                    yG = y;

                    gagnant = player;
                    return true;
                }
                y++;
                x++;
            }
            else{
                break;
            }
        }

        //DIAGONALE HAUT DROITE
        x = colone+1;
        y =  ligne-1;
        compteur = 1;
        currX = colone;
        currY = ligne;

        while(x<=6 && y>= 0){
            if(grille[y][x] == player){
                compteur++;
                if(compteur == 4){
                    xG = x;
                    yG = y;
                    gagnant = player;
                    return true;
                }
                y--;
                x++;
            }
            else{
                break;
            }
        }

        //DIAGONALE BAS GAUCHE
        currX = x-1;
        currY = y+1;

        x =  colone-1;
        y = ligne+1;
        //compteur = 1;

        while(x>=0 && y<=5){
            if(grille[y][x] == player){
                compteur++;
                if(compteur == 4){
                    xG = x;
                    yG = y;
                    
                    gagnant = player;
                    return true;
                }
                y++;
                x--;
            }
            else{
                break;
            }
        }

        

        return false;
    }

    private boolean checkV(int colone, int ligne, int player) {
        
        int compteur = 1;
        int x = colone-1;
        int y = ligne;
        //GAUCHE
        currX = colone;
        currY = y;
        while(x>=0){
            
            if(grille[y][x]== player){
                
                compteur++;
                if(compteur == 4){
                    xG = x;
                    yG = y;
                    gagnant = player;
                    return true;
                }
                x--;
            }
            
            else{
                
                break;
            }
        }

        //DROITE
       // compteur = 1;
       currX = x+1;
        x = colone+1;
        y =  ligne;
        
        

        
        while(x<= 6){
            
            if(grille[y][x]== player){
                
                compteur++;
                
                if(compteur == 4){
                    xG = x;
                    yG = y;
                    System.out.println(currX +" - " + currY + " - " + xG + " - "+ yG);
                    gagnant = player;
                    return true;
                }
                x++;
            }
            
            else{
                break;
            }
        }

        return false;

    }

    private boolean checkH(int colone, int ligne, int player) {
        
        int compteur = 1;
        int x = colone;
        int y =  ligne-1;

        currX = colone;
        currY = ligne;

        //BAS
        compteur = 1;
        x = colone ;
        y = ligne+1;
        while(y<=5){
            if(grille[y][x]== player){
                compteur++;
                if(compteur == 4){
                    xG = x;
                    yG = y;
                    gagnant = player;
                    System.out.println(xG +" " + yG);
                    return true;
                }
                y++;
            }
            
            else{
                break;
            }
        }

        return false;
    }

    public boolean ajoutValide(int colone){
        int y = 0;
        if(grille[y][colone] != 0){
            return false;
        }
        return true;
    }


    public int[][] getGrille(){
        return grille;
    }
    public int getXG(){
        return xG;
    }
    public int getYG(){
        return yG;
    }
    public int getCurrX(){
        return currX;
    }
    public int getCurrY(){
        return currY;
    }
    public int getGagnant(){
        return gagnant;
    }
    
    public void rennitialiser(){
        grille =new int[6][7];
        for(int[] ligne : grille){
            for(int val : ligne){
                val = 0;
            }
        }
        tour = 0;
        gagnant = -1;
        xG = -1;
        yG = -1;

        for(Vue v : vues){
            if(v instanceof GraphiqueP4){
                ((GraphiqueP4)v).init();
                break;
            }
        }
        notifVues();
        
        fen.notif();
        
    }

    public void enregistrer(Controleur ctrl){
        this.fen = (Fen)ctrl;
    }

    public void notifVues(){
        for(Vue v : vues){
            v.notifierChangement();
        }
    }
    @Override
    public void enregistrer(Vue v) {
        vues.add(v);
        
    }
}
