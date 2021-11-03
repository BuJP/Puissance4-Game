import javax.swing.JLabel;
import java.awt.Color;

public class Info extends JLabel  implements Vue{
    P4 modele;
    public Info(){
        setForeground(Color.WHITE);
    }

    @Override
    public void notifierChangement() {
        if(modele.getTour()%2==0){
            this.setText("Tour de l'equipe jaune");
        }
        else{
            this.setText("Tour de l'equipe rouge");
        }

        if(modele.getGagnant() == 1){
            this.setText("Victoire de l'equipe jaune");
        }
        else if(modele.getGagnant() == 2){
            this.setText("Victoire de l'equipe rouge");
        }
        
    }

    @Override
    public void setModele(Modele m) {
        // TODO Auto-generated method stub
        modele = (P4)m;
        m.enregistrer(this);
        notifierChangement();
        
    }
    


    
}
