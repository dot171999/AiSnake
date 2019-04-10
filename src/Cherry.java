import java.awt.*;
import java.util.Random;

class Cheery extends Component {
    static  Point cheery=new Point();
    static int score=0;

    Cheery() {
        generateCherry();
    }

    static void generateCherry(){
        score++;
        Random ran=new Random();
        cheery.setLocation(Render.box(ran.nextInt(29) + 1),Render.box(ran.nextInt(29) + 1));
    }

    void paintCherry(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(cheery.x,cheery.y,19,19);
    }
}
