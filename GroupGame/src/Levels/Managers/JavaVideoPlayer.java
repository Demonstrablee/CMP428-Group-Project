// package Levels.Managers;


// // Java program to Build a Media 
// // Player in JavaFX 
// import java.io.File; 
// import java.net.MalformedURLException; 

// import javafx.application.Application; 
// import javafx.event.ActionEvent; 
// import javafx.event.EventHandler; 
// import javafx.stage.FileChooser; 
// import javafx.stage.Stage; 
// import javafx.scene.Scene; 
// import javafx.scene.control.Menu; 
// import javafx.scene.control.MenuBar; 
// import javafx.scene.control.MenuItem; 
// import javafx.scene.layout.BorderPane; 
// import javafx.scene.paint.Color; 

// // launches the application 
// public class Main extends Application { 
// 	Player player; 
// 	FileChooser fileChooser; 
// 	public void start(final Stage primaryStage) 
// 	{ 
// 		// setting up the stages 
// 		MenuItem open = new MenuItem("Open"); 
// 		Menu file = new Menu("File"); 
// 		MenuBar menu = new MenuBar(); 

// 		// Connecting the above three 
// 		file.getItems().add(open); // it would connect open with file 
// 		menu.getMenus().add(file); 

// 		// Adding functionality to switch to different videos 
// 		fileChooser = new FileChooser(); 
// 		open.setOnAction(new EventHandler<ActionEvent>(){ 
// 			public void handle(ActionEvent e) 
// 			{ 
// 				// Pausing the video while switching 
// 				player.player.pause(); 
// 				File file = fileChooser.showOpenDialog(primaryStage); 

// 				// Choosing the file to play 
// 				if (file != null) { 
// 					try { 
// 						player = new Player(file.toURI().toURL().toExternalForm()); 
// 						Scene scene = new Scene(player, 720, 535, Color.BLACK); 
// 						primaryStage.setScene(scene); 
// 					} 
// 					catch (MalformedURLException e1) { 
// 						e1.printStackTrace(); 
// 					} 
// 				} 
// 			} 

// 			// here you can choose any video 
// 			player = new Player("file:/// F:/songs/srk.mp4"); 

// 			// Setting the menu at the top 
// 			player.setTop(menu); 

// 			// Adding player to the Scene 
// 			Scene scene = new Scene(player, 720, 535, Color.BLACK); 
			
// 			// height and width of the video player 
// 			// background color set to Black 
// 			primaryStage.setScene(scene); // Setting the scene to stage 
// 			primaryStage.show(); // Showing the stage 
// 	} 

// 	// Main function to launch the application 
// 	public static void main(String[] args){ 
// 		launch(args); 
// 	} 
// } 

// 	import javafx.scene.layout.BorderPane; 
// 	import javafx.scene.layout.Pane; 
// 	import javafx.scene.media.Media; 
// 	import javafx.scene.media.MediaPlayer; 
// 	import javafx.scene.media.MediaView; 

// 	public class JavaVideoPlayer extends BorderPane // Player class extend BorderPane 
// 	// in order to divide the media 
// 	// player into regions 
// 	{ 
// 		Media media; 
// 		MediaPlayer player; 
// 		MediaView view; 
// 		Pane mpane; 
// 		MediaBar bar; 
// 		public Player(String file) 
// 		{ // Default constructor 
// 			media = new Media(file); 
// 			player = new MediaPlayer(media); 
// 			view = new MediaView(player); 
// 			mpane = new Pane(); 
// 			mpane.getChildren().add(view); // Calling the function getChildren 

// 			// inorder to add the view 
// 			setCenter(mpane); 
// 			bar = new MediaBar(player); // Passing the player to MediaBar 
// 			setBottom(bar); // Setting the MediaBar at bottom 
// 			setStyle("-fx-background-color:#bfc2c7"); // Adding color to the mediabar 
// 			player.play(); // Making the video play 
// 		} 
// 	} 

// 	import javafx.application.Platform; 
// 	import javafx.beans.InvalidationListener; 
// 	import javafx.beans.Observable; 
// 	import javafx.event.ActionEvent; 
// 	import javafx.event.EventHandler; 
// 	import javafx.geometry.Insets; 
// 	import javafx.geometry.Pos; 
// 	import javafx.scene.control.Button; 
// 	import javafx.scene.control.Label; 
// 	import javafx.scene.control.Slider; 
// 	import javafx.scene.layout.HBox; 
// 	import javafx.scene.layout.Priority; 
// 	import javafx.scene.media.MediaPlayer; 
// 	import javafx.scene.media.MediaPlayer.Status; 

// 	public class MediaBar extends HBox { // MediaBar extends Horizontal Box 

// 		// introducing Sliders 
// 		Slider time = new Slider(); // Slider for time 
// 		Slider vol = new Slider(); // Slider for volume 
// 		Button PlayButton = new Button("||"); // For pausing the player 
// 		Label volume = new Label("Volume: "); 
// 		MediaPlayer player; 

// 		public MediaBar(MediaPlayer play) 
// 		{ // Default constructor taking 
// 			// the MediaPlayer object 
// 			player = play; 

// 			setAlignment(Pos.CENTER); // setting the HBox to center 
// 			setPadding(new Insets(5, 10, 5, 10)); 
// 			// Settih the preference for volume bar 
// 			vol.setPrefWidth(70); 
// 			vol.setMinWidth(30); 
// 			vol.setValue(100); 
// 			HBox.setHgrow(time, Priority.ALWAYS); 
// 			PlayButton.setPrefWidth(30); 

// 			// Adding the components to the bottom 

// 			getChildren().add(PlayButton); // Playbutton 
// 			getChildren().add(time); // time slider 
// 			getChildren().add(volume); // volume slider 
// 			getChildren().add(vol); 

// 			// Adding Functionality 
// 			// to play the media player 
// 			PlayButton.setOnAction(new EventHandler<ActionEvent>() { 
// 				public void handle(ActionEvent e) 
// 				{ 
// 					Status status = player.getStatus(); // To get the status of Player 
// 					if (status == status.PLAYING) { 

// 						// If the status is Video playing 
// 						if (player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())) { 

// 							// If the player is at the end of video 
// 							player.seek(player.getStartTime()); // Restart the video 
// 							player.play(); 
// 						} 
// 						else { 
// 							// Pausing the player 
// 							player.pause(); 

// 							PlayButton.setText(">"); 
// 						} 
// 					} // If the video is stopped, halted or paused 
// 					if (status == Status.HALTED || status == Status.STOPPED || status == Status.PAUSED) { 
// 						player.play(); // Start the video 
// 						PlayButton.setText("||"); 
// 					} 
// 				} 
// 			}); 

// 			// Providing functionality to time slider 
// 			player.currentTimeProperty().addListener(new InvalidationListener() { 
// 				public void invalidated(Observable ov) 
// 				{ 
// 					updatesValues(); 
// 				} 
// 			}); 

// 			// Inorder to jump to the certain part of video 
// 			time.valueProperty().addListener(new InvalidationListener() { 
// 				public void invalidated(Observable ov) 
// 				{ 
// 					if (time.isPressed()) { // It would set the time 
// 						// as specified by user by pressing 
// 						player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100)); 
// 					} 
// 				} 
// 			}); 

// 			// providing functionality to volume slider 
// 			vol.valueProperty().addListener(new InvalidationListener() { 
// 				public void invalidated(Observable ov) 
// 				{ 
// 					if (vol.isPressed()) { 
// 						player.setVolume(vol.getValue() / 100); // It would set the volume 
// 						// as specified by user by pressing 
// 					} 
// 				} 
// 			}); 
// 		} 

// 		// Outside the constructor 
// 		protected void updatesValues() 
// 		{ 
// 			Platform.runLater(new Runnable() { 
// 				public void run() 
// 				{ 
// 					// Updating to the new time value 
// 					// This will move the slider while running your video 
// 					time.setValue(player.getCurrentTime().toMillis() 
// 									player.getTotalDuration() 
// 										.toMillis() 
// 								* 100); 
// 				} 
// 			}); 
// 		} 
// 	} 

