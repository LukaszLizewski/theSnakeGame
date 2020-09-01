import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class Snake {
    private List<Body> body = new ArrayList<>();
    Body head;
    Body tail;
    public Snake (int initialLength, Board start){
        int initialPositionX = start.getW()/2;
        int initialPositionY = start.getH()/2;
        head = new Body(initialPositionX, initialPositionY,null, start);
        body.add(head);
        head.setFill(Color.LIME);
        head.setStroke(Color.BROWN);
        tail = head;
        for (int i = 0; i< initialLength; i++){
           Body nextSegments = new Body (initialPositionX+i+1,initialPositionY, tail, start);
           body.add(nextSegments);
           nextSegments.setFill(Color.BLANCHEDALMOND);
           nextSegments.setStroke(Color.BROWN);
           tail = nextSegments;
        }
    }
    public List<Body> getBody() {
        return body;
    }
    public void setDirection (int d){
        head.setDirection(d);
    }
    public int getDirection(){
        return head.getDirection();
    }
}
