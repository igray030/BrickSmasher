package brickBreaker;
import javax.swing.JFrame;
public class Main {
    public static void main(String [] args){
        JFrame object = new JFrame();
        GamePlay game = new GamePlay();

        object.setBounds(10,10,700,600);
        object.setTitle("Smash Ball");
        object.setResizable(false);
        object.setVisible(true);
        object.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        object.add(game);
        object.setLocationRelativeTo(null);

    }
}
