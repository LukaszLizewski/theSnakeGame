import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;


public class Food extends Rectangle {

    private int positionX;
    private int positionY;

    public Food(int positionX, int positionY) {
        super(Main.getBlockSize(), Main.getBlockSize());
        this.positionX = positionX;
        this.positionY = positionY;
        setTranslateX(positionX*Main.getBlockSize());
        setTranslateY(positionY*Main.getBlockSize());
        setFill(Color.LIGHTGREEN);
        setStroke(Color.GREEN);
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
}

