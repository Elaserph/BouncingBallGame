import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Random;

public class platform {

	int dx;
	int x,y,width,height;
	Image plat;
	URL url;
	float frame=0;
	
	
	
	//public platform() {
		//dx=-1;
		//x=450;
		//y=300;
		//width=120;
		//height=40;
		
	//}
	
	public platform(int x, int y){
		this.x=x;
		this.y=y;
		width=120;
		height=40;
		dx=-1;
		 plat = Pictures.platform;
	}
	
	public void physics (startingpoint sp, ball b){
		
		int tester =(int) (frame+.1);
		if(tester<3)
			frame+=.1;
		else
			frame=0;
		
		x+=-(Pictures.level);
		checkForCollosion(b);
		if(x < 0-width){
			Random r=new Random();
			y=sp.getHeight() - 40 - r.nextInt(400); 
			//x =sp.getWidth()+ r.nextInt(300);
			
		}
		
	}
    public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	private void checkForCollosion(ball b) {
    	int ballx = b.getX();
    	int bally = b.getY();
    	int radius = b.getRadius();
    	
    	if (bally+ radius > y && bally + radius < y+height){
    		if(ballx> x && ballx < x + width){
    		double newDy = b.getGameDy();
    		b.setY(y-radius);
    		b.setDy(newDy);
    		Pictures.bounce.play();
    		
    		}
    	}
		
	}
    public void paint(Graphics g){
		
		//g.setColor(Color.blue);
		//g.drawRect(x,y,width,height);
		//g.fillRect(x, y, width, height);
		
		//g.drawImage(plat, x, y, Pictures.sp);
		g.drawImage(plat, x, y, x+width, y+height, 0,47*(int)frame, 146, 47*(int)frame+47, Pictures.sp);
	
	}
	
}
