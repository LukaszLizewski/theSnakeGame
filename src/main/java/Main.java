import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private static int blockSize = 15;
    private static int width= 45;
    private static int height = 30;
    private int initialLength = 2;
    private long then = System.nanoTime();
    private boolean changed = false;
    private int nextUpdate;
    private boolean hasNextUpdate = false;

    private Board board;

    @Override
    public void start (Stage primaryStage) throws Exception{
        VBox road = new VBox(10);
        road.setPadding(new Insets(10));

        board = new Board(height,width);
        road.getChildren().add(board);
        board.addSnake(new Snake(initialLength, board));

        Label score = new Label("Score: 0");
        score.setFont(Font.font(20));
        road.getChildren().add(score);
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now-then > 1000000000/board.getSpeed()){
                    board.update();
                    then=now;
                    score.setText("Score: "+ board.getScore());
                    changed = false;
                    if (hasNextUpdate){
                        setBetterDirection(board.snake, nextUpdate);
                        hasNextUpdate = false;
                    }
                    if (board.isDead()){
                        stop();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText("You have lost!");
                        alert.setContentText("Your score: "+ board.getScore());
                        Platform.runLater(alert::showAndWait);
                        alert.setOnHidden(e->{
                            road.getChildren().clear();
                            board = new Board(height, width);
                            board.addSnake(new Snake(initialLength, board));
                            score.setText("Score: 0");
                            road.getChildren().addAll(board, score);
                            start();
                        });
                    }
                }
            }
        };
        timer.start();
        Scene scene = new Scene(road);
        scene.setOnKeyPressed(e->{
            if (e.getCode().equals(KeyCode.UP) && board.snake.getDirection() != Body.getDOWN()) {
                setBetterDirection(board.snake, Body.getUP());
            }
            if (e.getCode().equals(KeyCode.DOWN) && board.snake.getDirection() != Body.getUP()) {
                setBetterDirection(board.snake, Body.getDOWN());
            }
            if (e.getCode().equals(KeyCode.RIGHT) && board.snake.getDirection() != Body.getLEFT()) {
                setBetterDirection(board.snake, Body.getRIGHT());
            }
            if (e.getCode().equals(KeyCode.LEFT) && board.snake.getDirection() != Body.getRIGHT()) {
                setBetterDirection(board.snake, Body.getLEFT());
            }
        });
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Kodilla Snake");
        primaryStage.show();
    }
    public void setBetterDirection(Snake s, int d){
        if (!changed){
            s.setDirection(d);
            changed = true;
        } else {
            hasNextUpdate = true;
            nextUpdate = d;
        }
    }
    public static int getBlockSize() {
        return blockSize;
    }
    public static void main (String [] args){launch(args);}
}
