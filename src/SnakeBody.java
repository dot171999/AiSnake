import java.awt.*;
import java.util.ArrayList;

class SnakeBody extends Component {

    private Point head; // Point for snake's head.
    private ArrayList<Point> snakeParts = new ArrayList<>(); // List that stores body parts of the snake.
    private ArrayList<Point> past=new ArrayList<>(); // List that stores every move of the snake's head.
    private static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4; // Directions
    private static int direction = DOWN; // Initial direction set to down.
    private int move = DOWN; // temp variable to store direction.
    private int i;
    
    SnakeBody() { // Snake's head starts from (x,y) -> (1,1).
        this.head = new Point(Render.box(1),Render.box(1));
    }

    private boolean doOverlap(Point l1, Point l2){ // Function checks if snake's head overlaps with prey.
        if (l1.x > l2.x+18 || l2.x > l1.x+18 || l1.y > l2.y+18 || l2.y > l1.y+18) return false;
        return true;
    }

    private Point findMid(Point point){ // Find middle point of the given square.
        int x,y;
        x=(point.x + point.x + 19)/2;
        y=(point.y + point.y + 19)/2;
        return new Point(x,y);
    }
    
    private int checkLeastDis(ArrayList<Integer> displacement){
        int j;
        int x=displacement.get(0);
        for(j=1;j<Cherry.cherryCount;j++){
            if(displacement.get(j)<x){
                x=displacement.get(j);
            }
        }
        return x;
    }
    private void shiftDirection(){
        Point mid = new Point(); // Stores middle point of head's square.
        ArrayList<Point> midCherry = new ArrayList<>(); // Stores middle point of the prey's square.
        ArrayList<Integer> displacement = new ArrayList<>();
        int tempDisplacement =600; // Stores displacement b/w snake's head and prey.
        int displacemenT;
        
        if((head.y - 1) % 20 == 0 && (head.x - 1) % 20 == 0) { // Run this function if snake's head completely in box/grid.
               
            for(i=0;i<Cherry.cherryCount;i++){
                midCherry.add(findMid(Cherry.cherry.get(i)));
            }
            

            if(head.y - 20 < 1){  //UP
                // future update.
            }else{
                mid.setLocation(findMid(new Point(head.x,head.y - 20)));
                
                for(i=0;i<Cherry.cherryCount;i++){
                    displacement.add((int) Math.sqrt(Math.pow(midCherry.get(i).x - mid.x,2) + Math.pow(midCherry.get(i).y - mid.y,2))); // Formula to get displacement b/w two points.
                }
                       
                tempDisplacement = checkLeastDis(displacement);
                displacement.clear();
                move = UP;
            }

            if(head.y + 20 > 600){  //DOWN
            }else{
                mid.setLocation(findMid(new Point(head.x,head.y + 20)));
                
                for(i=0;i<Cherry.cherryCount;i++){
                    displacement.add((int) Math.sqrt(Math.pow(midCherry.get(i).x - mid.x,2) + Math.pow(midCherry.get(i).y - mid.y,2))); // Formula to get displacement b/w two points.
                }
                   
                displacemenT = checkLeastDis(displacement);
                
                if(displacemenT < tempDisplacement){
                    move = DOWN;
                    tempDisplacement = displacemenT;
                }
                displacement.clear();
            }

            if(head.x - 20 < 1){  //LEFT
            }else{
                mid.setLocation(findMid(new Point(head.x - 20,head.y)));
                
                for(i=0;i<Cherry.cherryCount;i++){
                    displacement.add((int) Math.sqrt(Math.pow(midCherry.get(i).x - mid.x,2) + Math.pow(midCherry.get(i).y - mid.y,2))); // Formula to get displacement b/w two points.
                }

                displacemenT = checkLeastDis(displacement);
                
                if(displacemenT < tempDisplacement){
                    move = LEFT;
                    tempDisplacement = displacemenT;
                }
                displacement.clear();
            }

            if(head.x + 20 > 600){  //RIGHT
            }else{
                mid.setLocation(findMid(new Point(head.x + 20,head.y)));
                
                for(i=0;i<Cherry.cherryCount;i++){
                    displacement.add((int) Math.sqrt(Math.pow(midCherry.get(i).x - mid.x,2) + Math.pow(midCherry.get(i).y - mid.y,2))); // Formula to get displacement b/w two points.
                }

                displacemenT = checkLeastDis(displacement);
                
                if(displacemenT < tempDisplacement){
                    move = RIGHT;
                    tempDisplacement = displacemenT;
                }
                displacement.clear();
            }

            direction = move; // Set direction to optimal direction.
        }

        if(direction == DOWN){
            head = new Point(head.x, head.y + 4); // Shift head towards direction by 4 points.
            for (int i=0;i<snakeParts.size();i++) // Retrieve past movements of the head and put into snake's body parts
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

    void paintBody(Graphics g){ // Function that paints the snake's body.

        past.add(new Point(head.x,head.y)); // Add every move of snake's head.

        shiftDirection(); // Calculate optimal direction.

        //boundaryConditions(); // Future update
        for(i=0;i<Cherry.cherryCount;i++){
            if(doOverlap(head,Cherry.cherry.get(i))){ // If prey eaten then add a body part to snake.
                Cherry.cherry.remove(i);
                Cherry.generateCherry();           
                snakeParts.add(new Point(past.get(past.size()-Cherry.score*5).x,past.get(past.size()-Cherry.score*5).y));
            }
        }
        
        g.setColor(Color.pink);
        for (Point point : snakeParts) g.fillRect(point.x,point.y,19,19); // Paint all body parts of the snake if any.

        g.setColor(Color.RED);
        g.fillRect(head.x,head.y,19,19); // Paint the snake's head.
    }

    /*private void boundaryConditions(){
        if(head.y>=600) head=new Point(head.x,Render.box(1));
        if(head.y<0) head=new Point(head.x,Render.box(30));
        if(head.x < 0) head=new Point(Render.box(30),head.y);
        if(head.x>=600) head=new Point(Render.box(1),head.y);
    }*/

}
