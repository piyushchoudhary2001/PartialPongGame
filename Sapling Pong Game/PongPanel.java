import javax.swing.JPanel;
import java.awt.*;

public class PongPanel extends JPanel implements Runnable{

    int GAME_WIDTH=1000;
    int GAME_HEIGHT=(int)(GAME_WIDTH*(0.555));

    int PADDLE_WIDTGH=25;
    int PADDLE_HEIGHT=100;

    int BALL_WIDTH=20;

    Paddle Paddle1;
    Paddle Paddle2;

    Ball ball;


    Image image;
    Graphics graphics;

    Dimension screen_size=new Dimension(GAME_WIDTH,GAME_HEIGHT);

    Thread gameThread;



    PongPanel()
    {
        newPaddle();
        newBall();
        gameThread=new Thread(this);
//        run();
        gameThread.start();
        this.setPreferredSize(screen_size);
    }

    private void newBall() {

        ball=new Ball(0,0,BALL_WIDTH,BALL_WIDTH);
    }


    public void newPaddle()
    {
        Paddle1=new Paddle(0,GAME_HEIGHT/2-PADDLE_HEIGHT/2,PADDLE_WIDTGH,PADDLE_HEIGHT,1);
        Paddle2=new Paddle(GAME_WIDTH-PADDLE_WIDTGH,GAME_HEIGHT/2-PADDLE_HEIGHT/2,PADDLE_WIDTGH,PADDLE_HEIGHT,2);
    }

// we will use this to create graphisc over the screen
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }
//    int run()
//    {
//        while(true)
//        {
//            move();
//            repaint();
//        }
//
//    }

    public void move()
    {
        ball.move();
    }

    private void draw(Graphics g) {
       Paddle1.draw(g);
       Paddle2.draw(g);
       ball.draw(g);
    }

    @Override
    public void run() {
        long lastTime=System.nanoTime();
        double amountOfTicks=60.0;
        double ns=1000000000/amountOfTicks;
        double delta=0;
        while(true)
        {
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            if(delta>=1)
            {
                move();
                repaint();
                delta--;
            }
        }
    }
}
