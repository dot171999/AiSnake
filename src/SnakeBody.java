import java.awt.*;
import java.util.ArrayList;

class SnakeBody extends Component {

    private Point head;
    private ArrayList<Point> snakeParts=new ArrayList<>();
    private ArrayList<Point> past=new ArrayList<>();
    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    private static int direction=DOWN;
    static int move=DOWN;

    SnakeBody() {
        this.head=new Point(Render.box(1),Render.box(1));
    }

    private boolean doOverlap(Point l1, Point l2){
        if (l1.x > l2.x+18 || l2.x > l1.x+18 || l1.y > l2.y+18 || l2.y > l1.y+18) return false;
        return true;
    }

    Point findMid(Point point){
        int x,y;
        x=(point.x + point.x + 19)/2;
        y=(point.y + point.y + 19)/2;
        return new Point(x,y);
    }

    private void shiftDirection(){
        Point mid = new Point();
        Point midCherry = new Point();
        int displacement,tempDisplacement =600;

        if((head.y - 1) % 20 == 0 && (head.x - 1) % 20 == 0) {

            if(head.y - 20 < 1){  //UP

            }else{

                mid.setLocation(findMid(new Point(head.x,head.y - 20)));
                midCherry.setLocation(findMid(Cheery.cheery));
                displacement = (int) Math.sqrt(Math.pow(midCherry.x - mid.x,2) + Math.pow(midCherry.y - mid.y,2));
                tempDisplacement = displacement;
                System.out.println("UP "+displacement);
                move = UP;
            }

            if(head.y + 20 > 600){  //DOWN

            }else{
                mid.setLocation(findMid(new Point(head.x,head.y + 20)));
                midCherry.setLocation(findMid(Cheery.cheery));
                displacement = (int) Math.sqrt(Math.pow(midCherry.x - mid.x,2) + Math.pow(midCherry.y - mid.y,2));

                if(displacement < tempDisplacement){
                    move = DOWN;
                    tempDisplacement = displacement;
                }
                System.out.println("Down "+displacement);
            }

            if(head.x - 20 < 1){  //LEFT

            }else{
                mid.setLocation(findMid(new Point(head.x - 20,head.y)));
                midCherry.setLocation(findMid(Cheery.cheery));
                displacement = (int) Math.sqrt(Math.pow(midCherry.x - mid.x,2) + Math.pow(midCherry.y - mid.y,2));

                if(displacement < tempDisplacement){
                    move = LEFT;
                    tempDisplacement = displacement;
                }
                System.out.println("left "+displacement);
            }

            if(head.x + 20 > 600){  //RIGHT

            }else{
                mid.setLocation(findMid(new Point(head.x + 20,head.y)));
                midCherry.setLocation(findMid(Cheery.cheery));
                displacement = (int) Math.sqrt(Math.pow(midCherry.x - mid.x,2) + Math.pow(midCherry.y - mid.y,2));

                if(displacement < tempDisplacement){
                    move = RIGHT;
                }
                System.out.println("right "+displacement);
            }

            System.out.println("direction"+ move);

            direction = move;
        }

        if(direction == DOWN){
            head = new Point(head.x, head.y + 4);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*5).x,past.get((past.size())-(i+1)*5).y));
        }

        if (direction == UP) {
            head = new Point(head.x, head.y - 4);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*5).x,past.get((past.size())-(i+1)*5).y));
        }

        if (direction == RIGHT) {
            head = new Point(head.x+4, head.y);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*5).x,past.get((past.size())-(i+1)*5).y));
        }

        if (direction == LEFT) {
            head = new Point(head.x-4, head.y);
            for (int i=0;i<snakeParts.size();i++)
                snakeParts.set(i,new Point(past.get((past.size())-(i+1)*5).x,past.get((past.size())-(i+1)*5).y));
        }
    }

    private void boundaryConditions(){
        if(head.y>=600) head=new Point(head.x,Render.box(1));
        if(head.y<0) head=new Point(head.x,Render.box(30));
        if(head.x < 0) head=new Point(Render.box(30),head.y);
        if(head.x>=600) head=new Point(Render.box(1),head.y);
    }

    void paintBody(Graphics g){

        g.setColor(Color.RED);

        past.add(new Point(head.x,head.y));

        shiftDirection();

        boundaryConditions();

        if(doOverlap(head,Cheery.cheery)){
            Cheery.generateCherry();
            snakeParts.add(new Point(past.get(past.size()-Cheery.score*5).x,past.get(past.size()-Cheery.score*5).y));
        }

        for (Point point : snakeParts) g.fillRect(point.x,point.y,19,19);

        g.fillRect(head.x,head.y,19,19);
    }

}
