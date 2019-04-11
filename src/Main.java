import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main implements ActionListener, KeyListener {

    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    private Render render;
    private int speed =0;

    private Main() {
        JFrame jFrame=new JFrame("Snake");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        render=new Render();
        jFrame.add(render,BorderLayout.CENTER);

        JPanel jPanel =new JPanel(new GridLayout(1,3));

        JButton plusSpeedBtn,minusSpeedBtn;
        JLabel scoreLabel;
        scoreLabel = new JLabel("SPEED");
        plusSpeedBtn = new JButton("+");
        minusSpeedBtn = new JButton("-");

        jPanel.add(minusSpeedBtn);
        jPanel.add(scoreLabel);
        jPanel.add(plusSpeedBtn);

        minusSpeedBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed = 10;
                System.out.println("SPEED "+ speed);
            }
        });

        jFrame.add(jPanel,BorderLayout.SOUTH);

        jFrame.setSize(607,700);
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
