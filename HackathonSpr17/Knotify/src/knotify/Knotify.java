package knotify;

//also utilizes the custom Java ID3 Tag Library: http://javamusictag.sourceforge.net/index.html

import java.io.File;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.beaglebuddy.mp3.MP3;
import com.sun.jndi.toolkit.url.Uri;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author jfeld
 */
public class Knotify extends Application {
    
    public int trackNumber = 0;
    
    @Override
    public void start(Stage primaryStage) {
        
        //will instantiate current directory and mp3 file to read from
        File currentDirectory = new File("Load");
        File[] filesInDir = currentDirectory.listFiles();
        ArrayList<MP3> mp3FileObjectList = new ArrayList<>();
        
        //will contain names of all mp3 files in specified directory
        
        //instantiate Text nodes to contain metatag info for artist, song name and album title
        Label artistName = new Label();
        Label trackName = new Label();
        Label albumName = new Label();
        
        //populate the ArrayList with names of all files
        //while the specified file/stream/etc is not empty, do this:
        try{
            //will create individual MP3 object for each track in directory; serves to print out
            //artist name, track name and album name in the GUI
                for (int i = 0; i < filesInDir.length; i++){
                        if((filesInDir[i].isFile()) && (filesInDir[i].getName().endsWith(".mp3"))){
                            String mp3FileName = filesInDir[i].getName();
                            String truncatedMp3FileName = filesInDir[i].getName().substring(3, mp3FileName.length() - 4);
                            //System.out.println("File: " + truncatedMp3FileName);
                            MP3 mp3FileObject = new MP3(mp3FileName);
                            mp3FileObjectList.add(mp3FileObject);
                            }  
                        }
                    }
                    catch(IOException ex){
                        System.out.println("bleh");
                    }   
            ArrayList<MediaPlayer> trackList = new ArrayList<>();
        
        //traverse the MP3File ArrayList to make Media objects for each 
            for(int i = 0; i < mp3FileObjectList.size(); i++){
                trackList.add(new MediaPlayer(new Media(this.getClass().getResource(filesInDir[i].getName()).toExternalForm())));
            }   
        
        MediaView playlistNode = new MediaView();
        
        //traverse the MediaPlayer ArrayList to set the corresponding MediaPlayer to MediaView and play in order
        for (int i = 0; i < trackList.size(); i++) {
            MediaPlayer hi = trackList.get(i);
            trackNumber++;
            hi.play();
        }
        
        playlistNode.setFitHeight(250);
        playlistNode.setFitWidth(250);
        
        Button rewind = new Button( "<" );
        rewind.setOnAction( e-> {
	if ( rewind.getText().equals( ">" ) ) {
            trackList.get(trackNumber).play();	
            rewind.setText( "||" );
	}
	else { 
            trackList.get(trackNumber).pause();
               rewind.setText( ">" ); 
            }        
        }
        );
        
        Button playPause = new Button( ">" );
        playPause.setOnAction( e-> {
	if ( playPause.getText().equals( ">" ) ) {
            trackList.get(trackNumber).play();	
            playPause.setText( "||" );
	}
	else { 
            trackList.get(trackNumber).pause();
               playPause.setText( ">" ); 
            }        
        }
        );
        
        Button fastForward = new Button( ">>" );
        fastForward.setOnAction((ActionEvent e) -> {
            if ( fastForward.getText().equals( ">" ) ) {
                trackList.get(trackNumber).play();
                fastForward.setText( "||" );
            }
            else {
                trackList.get(trackNumber).pause();
                fastForward.setText( ">" );
            }
        });
        
        /**
        Slider volumeRocker = new Slider();
        volumeRocker.setOrientation(Orientation.HORIZONTAL);
        volumeRocker.setMin(0);
        volumeRocker.setMax(100);
        volumeRocker.setMinWidth(500)
        volumeRocker.setValue(30);
        volumeRocker.valueChangingProperty().addListener( 
                e-> {   
            @Override
            public void invalidated(Observable observable){
                    if (volumeRocker.isValueChanging()) {
                        mediaPlayer.setVolume(volumeRocker.getValue() / 100.0);
			}
                }      
        }
        );**/
        

        //creation of VBox to hold horizontal controls
        GridPane playerControls = new GridPane();
        playerControls.setAlignment(Pos.BOTTOM_CENTER);
        playerControls.setPadding(new Insets(10, 10, 10, 10));
        playerControls.setHgap(15);
        playerControls.setVgap(15);
        
        
        playerControls.add(rewind, 0, 1);
        playerControls.add(playPause, 1, 1);
        playerControls.add(fastForward, 2, 1);
        
        
        
        //creation of FlowPane to hold both HBox and VBox
        FlowPane generalPane = new FlowPane();
        generalPane.setStyle("-fx-background-color: GREY;"
                             + "-fx-background-color: linear-gradient(to right, darkslategray, lightslategray);");
        generalPane.getChildren().addAll(playlistNode, playerControls);
        
        Scene scene = new Scene(generalPane, 600, 600);
        
        primaryStage.setTitle("Knotify™ Web Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
      
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

    
            
        
        
        
        

        
        
            
            
        
    
