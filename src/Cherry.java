import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

class Cherry extends Component {
    static  ArrayList<Point> cherry = new ArrayList<>();
    static int score = 0;
    static int cherryCount = 5;
    private int i;
    Cherry() {
        
        Random ran = new Random();
        for(i=0;i<cherryCount;i++){
            cherry.add(new Point(Render.box(ran.nextInt(29) + 1),Render.box(ran.nextInt(29) + 1)));
        }
    }

    static void generateCherry(){ // Generate the prey randomly.
        score++;
        Random ran = new Random();
        cherry.add(new Point(Render.box(ran.nextInt(29) + 1),Render.box(ran.nextInt(29) + 1)));
    }

    void paintCherry(Graphics g) { // Paint the cherry,
        g.setColor(Color.GREEN);
        for(i=0;i<cherryCount;i++){
            g.fillRect(cherry.get(i).x,cherry.get(i).y,19,19);
        }
        
    }
}
