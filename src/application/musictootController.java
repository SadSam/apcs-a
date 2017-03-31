package application;

import java.io.File;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


/**I'm not really sure how to write javadocs comments, so please forgive me
 * 
 * This class is the controller class where I create the methods I need and connect it to scenebuilder.
 * I import my fields with FXML because i'm using it in a gui.
 * The mediaplayer and media is for me to be able to play sounds through my gui and the slider is used for volume.
 * The imageview is something I created that I never got to work, but I left it in by mistake.
 * @author Sam Chung
 *
 */
public class musictootController implements Initializable{
	@FXML
	private MediaPlayer mp;
	private Media me;
	@FXML
	private Slider volume;
	@FXML
	private ImageView image;
	
	
	public void initialize(URL location, ResourceBundle resources){
		/**
		 * Here, i set the absolute path of the file I need to make sure it returns the absolute
		 * pathname I need from the directory. Then I create  a new file to string and set it to me.
		 * I set it so that the music is set to play right when the program is launched.
		 * 
		 * For the volume, I receive the setting(volume) of the current music playing and set the value of it
		 * to the volume in slider. Then I create a listener for the volume and made it so it listens any time
		 * it is considered invalid which is anywhere in between min and max volume and it has to be an observable.
		 * 
		 * I put override because the eclipse told me to.
		 */
		String path = new File("resources/Étude in A minor Op.25 No.11 Winter Wind – Chopin.mp3").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		mp.setAutoPlay(true);
		
		volume.setValue(mp.getVolume() * 100);
		volume.valueProperty().addListener(new InvalidationListener(){

			@Override
			public void invalidated(javafx.beans.Observable observable) {
				mp.setVolume(volume.getValue() / 100);
				
			}
			
		});
		
	}
	/**
	 * These are methods to connect to my gui.
	 * These are the buttons to play, pause , and restart the song.
	 * I had to put the play inside restart because setting the song back to start would just pause it.
	 * 
	 */
	
	public void play(ActionEvent event){
		mp.play();
	}

	public void pause(ActionEvent event){
		mp.pause();
	}
	
	public void restart(ActionEvent event){
		mp.seek(mp.getStartTime());
		mp.play();
	}

}
