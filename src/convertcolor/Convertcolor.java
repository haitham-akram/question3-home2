/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package convertcolor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Haitham
 */
public class Convertcolor extends Application {
    
        TextArea text ;

    @Override
    public void start(Stage primaryStage) {
       MenuBar menubar = new MenuBar();
       Menu menufile = new Menu("_file");
       MenuItem menuitemop= new MenuItem("_Open");
       MenuItem menuitemcl= new MenuItem("_Close");
       MenuItem menuitemex= new MenuItem("E_xit");
       menufile.getItems().addAll(menuitemop,menuitemcl,menuitemex);
       Menu menuedit = new Menu("_Edit");
       MenuItem menuitemfont= new MenuItem("_Font");
       MenuItem menuitemcolor= new MenuItem("_Color");
       
        menuedit.getItems().addAll(menuitemfont,menuitemcolor);
        menubar.getMenus().addAll(menufile,menuedit);
       
       text = new TextArea("inital text");
       VBox vbox= new VBox(10,menubar,text);
       Eventhandler handler = new Eventhandler();
        menuitemop.setOnAction(handler);
        menuitemcl.setOnAction(handler);
        menuitemex.setOnAction(handler);
        menuitemfont.setOnAction(handler);
        menuitemcolor.setOnAction(handler);
        
        Scene scene = new Scene(vbox, 300, 250);
        
        primaryStage.setTitle("menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    class Eventhandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent event) {
             Alert alt=new Alert(Alert.AlertType.ERROR, "There an Error in File");
          String menu = ((MenuItem)event.getSource()).getText();
          switch (menu){
              case "_Open":{ 
                  FileChooser fc = new FileChooser();
                  fc.setTitle("Opening");
                  fc.setInitialDirectory(new File("."));
                  File file =fc.showOpenDialog(null);
                  Scanner scanner;
                  text.setText("");
                  text.setWrapText(true);
                  try {
                      scanner = new Scanner(file);
                      while(scanner.hasNext())
                          text.appendText(scanner.nextLine());
                  } catch (FileNotFoundException e) {
                     alt.show();
                  }
               break;
              }
             
              case "_Close":{ 
               text.clear();
               text.setEditable(false);
              break;
              }
              
              case "E_xit":{
              Platform.exit();
              System.exit(0);
              break;
              }
              
              case "_Font":{ 
              Dialog <String> dialog= new TextInputDialog("");
              dialog.setTitle("Color Selection");
              dialog.setHeaderText("Enter the size of the font");
              String siz = dialog.showAndWait().get();
              int size = Integer.parseInt(siz);
              text.setStyle("-fx-font-size:"+size+"px"+";");  
              break;
              }
              
              case "_Color":{ 
                  
              Dialog <String> dialog= new ChoiceDialog<>("blue","red","green","gold","gray");
              dialog.setTitle("Color Selection");
              dialog.setHeaderText("Select the color");
              String color = dialog.showAndWait().get();
              text.setStyle("-fx-text-fill:"+color+";");
              break;
              }
              
          
          
          } 
            
            
            
            
        }

       
    }
    
}
