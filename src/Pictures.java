import java.applet.AudioClip;
import java.awt.Image;
import java.net.URL;

public class Pictures {
	
	static Image platform, ball;
	URL url;
	static startingpoint sp;
	static AudioClip music,bounce;
	static int level=1;
	
	public Pictures(startingpoint sp) {
		
		try{
			url = sp.getDocumentBase();
		}catch(Exception e){
		
		}
		music = sp.getAudioClip(url, "music/Oops.au");
		bounce = sp.getAudioClip(url, "music/bounce.au");
		platform =sp.getImage(url,"images/boxes.PNG");
		this.sp=sp;
	}
	
}
