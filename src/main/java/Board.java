import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class Board extends Pane {
    private int height;
    private int width;
    private List<Body> body = new ArrayList<>();
    private int score = 0;
    private int speed =2;
    Food food;
    Snake snake;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        setMinSize(width*Main.getBlockSize()+6,height*Main.getBlockSize());
        setBackground(new Background(new BackgroundFill(Color.color(0.6,0.6,0.6),null,null)));
        setBorder(new Border(new BorderStroke(Color.CORAL,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,new BorderWidths(1))));
        addFood();
    }
    public void addSnake(Snake s){
        snake = s;
        for(Body b:s.getBody()){
            addBody(b);
        }
    }
    public void update(){
        for(Body b:body){
            b.update();
        }
        if (isItEaten(food)){
            score += 10;
            speed ++;
            addFood();
            addNewBody();
        }
    }
    public void addNewBody (){
        Body newBody = new Body(snake.tail.getPositionX(),snake.tail.getPositionY(), snake.tail,this);
        snake.tail = newBody;
        newBody.setFill(Color.BLANCHEDALMOND);
        newBody.setStroke(Color.BROWN);
        addBody(newBody);
    }
    private void addBody (Body b){
        getChildren().add(b);
        body.add(b);
    }
    public void addFood (){
        int randomX=(int) (Math.random()*width);
        int randomY=(int) (Math.random()*height);
        Food newFood = new Food (randomX, randomY);
        getChildren().add(newFood);
        getChildren().remove(food);
        food =newFood;
    }
    public boolean isItEaten (Food food){
        if (food == null){
            return false;
        }
        return food.getPositionX() == snake.head.getPositionX() && food.getPositionY() == snake.head.getPositionY();
    }
    public boolean isDead (){
        for(Body b:body){
            if (b != snake.head){
                if (b.getPositionX()==snake.head.getPositionX() && b.getPositionY()==snake.head.getPositionY()) {
                    return true;
                }
            }
        }
        return false;
    }
    public int getH() {
        return height;
    }
    public int getW() {
        return width;
    }
    public int getScore() {
        return score;
    }
    public int getSpeed() {
        return speed;
    }
}
