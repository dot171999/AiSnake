import javax.swing.*;
import java.awt.*;

class Render extends JPanel {

    private SnakeBody snakeBody = new SnakeBody(); // Class that creates snake's body.
    private  Cherry cherry = new Cherry(); // Class that randomly paints the prey.

    Render() {
        setPreferredSize(new Dimension(601,601)); //Grid = 601 * 601 px
        setBackground(Color.BLACK);
    }

    static int box(int x){ // Function that return grid number from points.
        return (((x-1) * 20) + 1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);

        int j,i,k=0;
        for(i=0;i<31;i++){ // Paints the 30 * 30 grid
            g.drawLine(k,0,k,600);
            g.drawLine(0,k,600,k);
            for(j=0;j<19;j++) {
                k++;
            }
            k++;

        }

        snakeBody.paintBody(g); // function in SnakeBody class that paints the snake's parts.
        cherry.paintCherry(g); // function in Cherry class that paints the prey.
    }
}
