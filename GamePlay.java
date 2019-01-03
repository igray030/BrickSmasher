package brickBreaker;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer time;
    private int delay = 8;
    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballDirX = -1;
    private int ballDiry = -2;


    private MapGenerator map;



    public GamePlay(){
        map = new MapGenerator(3,7);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay,this);
        time.start();

    }
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);


        // drawing map
        map.draw((Graphics2D)g) ;



        g.setColor(Color.yellow);



        // Border hopefully
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);

        /// score board

        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString("" + score,590,30);

        // panel color..hopefully

        g.setColor(Color.green);
        g.fillRect(playerX,550,100,8);

        // ball
        g.setColor(Color.green);
        g.fillOval(ballPosX,ballPosY,20,20);

        if( ballPosY > 570 ){
            play = false;
            ballDirX=0;
            ballDiry=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game over, scores: " + score,190,300);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Press Enter to Restart",230,350);





        }
        g.dispose();










    }






    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();

        if ( play = true){
            if( new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8))){
                ballDiry = - ballDiry;
            }

          A:  for ( int i =0; i < map.map.length; i++){
                for ( int j =0; j < map.map[0].length; j++){
                    if (map.map[i][j] > 0){
                        int brickX  = j * map.brickWidth +80;
                        int brickY  = i * map.brickHeight +50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX,brickY,brickWidth,brickHeight);
                        Rectangle ballReact = new Rectangle(ballPosX,ballPosY,20,20);
                        Rectangle brickReact = rect;

                        if(ballReact.intersects(brickReact)){
                            map.setBrickValue(0,i,j);
                            totalBricks --;
                            score +=5;

                            if(ballPosX + 19 <= brickReact.x || ballPosX + 1 >= brickReact.x + brickReact.width){
                                ballDirX = - ballDirX;
                            }
                            else {
                                ballDiry = - ballDiry;
                            }
                            break A;


                        }

                    }
                }
            }
            ballPosX+=ballDirX;
            ballPosY+=ballDiry;
            if(ballPosX < 0){
                ballDirX = - ballDirX;
            }

            if(ballPosY < 0){
                ballDiry= - ballDiry;
            }

            if(ballPosX > 670){
                ballDirX = - ballDirX;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            if ( playerX >= 600 ){
                playerX = 600;
            }
            else {
                moveRight();
            }

        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            if ( playerX < 10 ){
                playerX = 10;
            }
            else {
                moveLeft();
            }

        }
        if ( e.getKeyCode() == KeyEvent.VK_ENTER){
            if( !play){
                play=true;
                ballPosX =120;
                ballPosY =350;
                ballDirX=-1;
                ballDiry=-2;
                playerX=310;
                score=0;
                totalBricks=21;
                map = new MapGenerator(3,7);
                repaint();
            }
        }

    }


    public void moveRight(){
        play = true;
        playerX += 20;

    }

    public void moveLeft(){
        play = true;
        playerX -= 20;

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
