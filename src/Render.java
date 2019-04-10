import javax.swing.*;
import java.awt.*;

class Render extends JPanel {

    private SnakeBody snakeBody = new SnakeBody();
    private  Cheery cheery = new Cheery();

    Render() {
        setPreferredSize(new Dimension(601,681));
    }

    static int box(int x){
        return (((x-1) * 20) + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        int j,i,k=0;
        for(i=0;i<31;i++){
            g.drawLine(k,0,k,600);
            g.drawLine(0,k,600,k);
            for(j=0;j<19;j++) {
                k++;
            }
            k++;

        }

        snakeBody.paintBody(g);
        cheery.paintCherry(g);

        g.setColor(Color.BLACK);
        g.fillRect(3,605,594,74);
    }
}
