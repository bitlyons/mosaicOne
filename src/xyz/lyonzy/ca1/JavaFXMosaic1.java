package xyz.lyonzy.ca1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFXMosaic1 extends Application {

    /*  This program will create a 300x300 window composed of several layouts.
        The numbers used for the preference size were chosen to allow the program
        to be resized beyond the initial 300x300, but keep the same aspect ratio.
        Created By : Brendan Lyons
        Tested on : Oracle JDK 1.8.0_72 (64 Bit)
      */

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane container = new GridPane();
        container.setPrefSize(30000, 30000);


        //create the 6x6 grid
        GridPane sixGrid = new GridPane();
        sixGrid.setPrefSize(10000, 10000);
        int count = 0; //used to alternate color each row
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Pane pane = new Pane();
                pane.setPrefSize(6000, 6000);
                Color colour = count % 2 == 0 ? Color.BLUE : Color.YELLOW;
                pane.setBackground(new Background(new BackgroundFill(colour, null,null)));

                sixGrid.add(pane, i, j);
                count++;
            }
            count = i % 2 == 0 ? 0 : 1; //if the remainder of i%2 is 0, count sets to 0 otherwise its 1, this is
            // to ensures that each row will not start with the same color, aka offset by 1.
        }

        //create a purple flow pane with a square
        FlowPane purpleBox = new FlowPane();

        purpleBox.setAlignment(Pos.CENTER);
        purpleBox.setPrefSize(10000, 10000);
        purpleBox.setBackground(new Background(new BackgroundFill(Color.PURPLE, null,null)));

        Pane purplePane = new Pane();
        purpleBox.getChildren().add(purplePane);

        Rectangle square = new Rectangle(40,40);
        square.setFill(Color.BLACK);

        Rectangle square2 = new Rectangle(40,40);
        square2.setFill(Color.WHITE);


        Rectangle square3= new Rectangle(25,25);
        square3.setFill(Color.GRAY);

        square.relocate(10, 15);
        square2.relocate(-5, 0);
        square3.relocate(10,15);

        //Todo get three squares to move when resized

        purplePane.getChildren().addAll(square2 ,square, square3);

        GridPane row1 = new GridPane();
        row1.add(sixGrid, 1, 1);
        row1.add(purpleBox, 1, 2);

        //midBox used to hold the three mid boxes
        HBox midBox = new HBox();
        midBox.setPrefSize(10000, 10000);

        //pink box
        HBox pinkBox = new HBox();

        pinkBox.setBackground(new Background(new BackgroundFill(Color.PINK, null,null)));
        pinkBox.setPrefSize(3333.33, 3000);


        //grey flag
        HBox greyFlag = new HBox(4);
        greyFlag.setAlignment(Pos.TOP_CENTER);
        greyFlag.setPrefSize(3333.33, 3000);
        greyFlag.setPadding(new Insets(3, 3, 3, 3));
        greyFlag.setBackground(new Background(new BackgroundFill(Color.GRAY, null,null)));

        Rectangle greenFlag = new Rectangle();
        greenFlag.setFill(Color.LIMEGREEN);
        greenFlag.widthProperty().bind(primaryStage.widthProperty().divide(30));
        greenFlag.heightProperty().bind(greenFlag.widthProperty());

        Rectangle whiteFlag = new Rectangle();
        whiteFlag.setFill(Color.WHITE);
        whiteFlag.widthProperty().bind(greenFlag.widthProperty());
        whiteFlag.heightProperty().bind(greenFlag.heightProperty());

        Rectangle orangeFlag = new Rectangle();
        orangeFlag.setFill(Color.ORANGE);
        orangeFlag.widthProperty().bind(greenFlag.widthProperty());
        orangeFlag.heightProperty().bind(greenFlag.heightProperty());

        greyFlag.getChildren().addAll(greenFlag, whiteFlag, orangeFlag);

        //blue box
        VBox blueBox = new VBox();
        blueBox.setBackground(new Background(new BackgroundFill(Color.SKYBLUE,null,null)));
        blueBox.setPrefSize(3333.33, 3000);
        blueBox.getChildren().addAll(new Text(" "));

        midBox.getChildren().addAll(pinkBox, greyFlag, blueBox);
        //brown bp
        BorderPane brownBP = new BorderPane();
        brownBP.setPrefSize(10000, 10000);

        //bpTOP
        HBox bptop = new HBox();
        bptop.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
        bptop.setPrefSize(10, 10);

        brownBP.setTop(bptop);

        //bpLeft
        VBox bpLeft = new VBox();
        bpLeft.setBackground(new Background(new BackgroundFill(Color.DARKSLATEGREY,null,null)));
        bpLeft.setPrefSize(10, 10);

        brownBP.setLeft(bpLeft);

        //bpRight
        VBox bpRight = new VBox();
        bpRight.setBackground(new Background(new BackgroundFill(Color.ORANGE, null,null)));
        bpRight.setPrefSize(10, 10);

        brownBP.setRight(bpRight);

        //bpBottom
        VBox bpBottom = new VBox();
        bpBottom.setBackground(new Background(new BackgroundFill(Color.LIMEGREEN, null,null)));
        bpBottom.setPrefSize(10, 10);

        brownBP.setBottom(bpBottom);

        //bpCentre
        FlowPane bpCentre = new FlowPane();
        bpCentre.setPrefWidth(10000);
        bpCentre.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD, null,null)));
        bpCentre.setAlignment(Pos.CENTER);

        Rectangle greenSquare = new Rectangle();
        greenSquare.setFill(Color.GREENYELLOW);
        greenSquare.widthProperty().bind(primaryStage.widthProperty().divide(5));
        greenSquare.heightProperty().bind(greenSquare.widthProperty());

        bpCentre.getChildren().add(greenSquare);
        brownBP.setCenter(bpCentre);

        //add all the layouts to the base container
        container.add(row1, 1, 1);
        container.add(midBox, 2, 1);
        container.add(brownBP, 3, 1);

        Scene mainScene = new Scene(container, 300, 300);


        primaryStage.setTitle("Mosaic");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }
}


/** alternative grid method using a single while loop
        int x = 1;
        int y = 1;
        int count = 0;
        GridPane grid = new GridPane();

        while(y!=7){
            Pane pane = new Pane();
            pane.setPrefSize(6000, 6000);
            Color colour = count % 2 == 0  ? Color.BLUE : Color.YELLOW;
            pane.setBackground(new Background(new BackgroundFill(colour, null,null)));
            grid.add(pane,x,y);

            if(x>=6){
                y++;
                x=1;
                count = y % 2 == 0 ? 1 : 0;
            }
            else {
                x++;
                count ++;
            }
        }
 */