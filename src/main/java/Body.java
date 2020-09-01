import javafx.scene.shape.Rectangle;

public class Body extends Rectangle {
    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private int positionX;
    private int positionY;
    private int previousPositionX;
    private int previousPositionY;
    private Body previousBody;
    private int direction = LEFT;
    private int maxX;
    private int maxY;

    public Body (int x, int y, Body pBody, Board board){
        super(Main.getBlockSize(),Main.getBlockSize());
        this.positionX = x;
        this.positionY = y;
        setTranslateX(positionX*Main.getBlockSize());
        setTranslateY(positionY*Main.getBlockSize());
        previousBody=pBody;
        maxX=board.getW();
        maxY=board.getH();
    }
    public void update(){
        previousPositionX = positionX;
        previousPositionY = positionY;

        if (previousBody == null){
            switch (direction){
                case UP: moveUp();
                break;
                case RIGHT: moveRight();
                break;
                case DOWN: moveDown();
                break;
                case LEFT: moveLeft();
                break;
            }
        } else {
            positionX = previousBody.previousPositionX;
            positionY = previousBody.previousPositionY;
        }
        updatePosition();
    }
    public void moveUp(){
        positionY--;
        if (positionY<0){
            positionY=maxY-1;
        }
    }
    public void moveDown(){
        positionY++;
        if (positionY>=maxY){
            positionY=0;
        }
    }
    public void moveRight(){
        positionX++;
        if (positionX>maxX){
            positionX=0;
        }
    }
    public void moveLeft(){
        positionX--;
        if (positionX<0){
            positionX=maxX;
        }
    }
    public void updatePosition(){
        setTranslateX(positionX*Main.getBlockSize());
        setTranslateY(positionY*Main.getBlockSize());
    }
    public static int getUP() {
        return UP;
    }
    public static int getRIGHT() {
        return RIGHT;
    }
    public static int getDOWN() {
        return DOWN;
    }
    public static int getLEFT() {
        return LEFT;
    }
    public void setDirection(int direction) {
        this.direction = direction;
    }
    public int getDirection() {
        return direction;
    }
    public int getUp(){
        return UP;
    }
    public int getDown(){
        return DOWN;
    }
    public int getRight(){
        return RIGHT;
    }
    public int getLeft(){
        return LEFT;
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public int getPreviousPositionX() {
        return previousPositionX;
    }
    public int getPreviousPositionY() {
        return previousPositionY;
    }
}
