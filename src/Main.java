import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements ActionListener, KeyListener {

    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    private Render render;

    private Main() {
        JFrame jFrame=new JFrame("Snake");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        jFrame.setLayout(new GridLayout(1,1));

        render=new Render();
        jFrame.add(render);
        jFrame.pack();

        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        jFrame.addKeyListener(this);
        Timer timer=new Timer(30,this);

        timer.start();
    }


    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        render.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i=e.getKeyCode();

        if(i==KeyEvent.VK_A || i==KeyEvent.VK_LEFT) {
            SnakeBody.move=LEFT;
        }

        if(i==KeyEvent.VK_D || i==KeyEvent.VK_RIGHT) {
            SnakeBody.move=RIGHT;
        }

        if(i==KeyEvent.VK_W || i==KeyEvent.VK_UP) {
            SnakeBody.move=UP;
        }

        if(i==KeyEvent.VK_S || i==KeyEvent.VK_DOWN) {
            SnakeBody.move=DOWN;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
