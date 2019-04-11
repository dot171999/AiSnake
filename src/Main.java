import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener{

    private Render render;

    private Main() {
        JFrame jFrame = new JFrame("Snake");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);

        render=new Render();

        jFrame.setLayout(new GridLayout(1,1));

        jFrame.add(render); // Added Class that paints snake to the frame.
        jFrame.pack();

        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        Timer timer = new Timer(30,this); // Added timer to call actionPerformed function every 30ms to paint the snake.
        timer.start(); // started the timer
    }

    public static void main(String[] args) {
        new Main(); // Call Main class's constructor.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        render.repaint(); // Function in class Render that paints the snake.
    }

}
