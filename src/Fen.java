import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fen extends JFrame implements Controleur {
    P4 p4;
    JPanel pan;
  

    private JPanel jeu;

    public Fen(P4 p4){
        super("PUISSANCE 4 ");
        this.p4 = p4;
        
		
		this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
		this.setVisible(true);
    }
 

    public void initComposant(GraphiqueP4 gp4, Info info){
        JPanel panPrincipal = new JPanel();
        panPrincipal.setLayout(new BorderLayout());
		this.add(panPrincipal);

      //  gP4.setPreferredSize(new Dimension(getWidth(),getHeight()));
        jeu = new JPanel();
        jeu.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
        jeu.add(buildGrille(gp4));
        jeu.setBackground(Color.BLACK);
        panPrincipal.add(jeu);
        JPanel bas = new JPanel();
        bas.setLayout(new BorderLayout());
        bas.setBackground(Color.RED);
        bas.add(buildBas(info));
        panPrincipal.add(bas,BorderLayout.SOUTH);
        


    }

    public JPanel buildGrille(GraphiqueP4 gp4){
        pan = new JPanel();
       // pan.setLayout(new BorderLayout());

        JPanel container = new JPanel();
        
        container.setBackground(Color.BLUE);
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        gp4.setPreferredSize(new Dimension(480,410));
        container.add(gp4);
        container.setBorder(BorderFactory.createEtchedBorder());
        
        pan.add(container);
        
        return pan;
    }

    public JPanel buildBas(Info info){
        JPanel pan = new JPanel();
        
        pan.setLayout(new GridLayout(1,3));
        pan.setPreferredSize(new Dimension(getWidth(), 80));
        JButton play =new JButton("JOUER");
        play.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("lololo");
                p4.rennitialiser();
            }
        });
        pan.add(play);
        JPanel player = new JPanel();
        player.setLayout(new FlowLayout(FlowLayout.CENTER, 0,30));

    
        
        player.add(info);
        player.setBackground(Color.BLUE);

        notif();
        pan.add(player);
        

        //pan.add(new JPanel());

        pan.setBackground(Color.BLUE);

        return pan;
    }

    public void notif(){

        

        if(p4.getGagnant() == 1){
            jeu.setBackground(Color.YELLOW);
            pan.setBackground(Color.YELLOW);
        }
        else if(p4.getGagnant() == 2){
            jeu.setBackground(Color.RED);
            pan.setBackground(Color.RED);
        }
        else{
            jeu.setBackground(null);
            pan.setBackground(null);
        } 
    }
    public static void main(String[] args){
        P4 p4 = new P4();

        GraphiqueP4 gp4 = new GraphiqueP4();
        gp4.setModele(p4);

        Info info = new Info();
        info.setModele(p4);

        Fen fen = new Fen(p4);
        fen.initComposant(gp4, info);

        p4.enregistrer(fen);
    }
}
