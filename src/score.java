import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class score extends item {
	
	private startingpoint appletinfo = new startingpoint();
	public score(int x, startingpoint appletInfo) {
		super(x);
		// TODO Auto-generated constructor stub
		this.appletinfo=appletinfo;
	}
	@Override
	public void performAction(ball b) {
		// TODO Auto-generated method stub
		Random r= new Random();
		appletinfo.setScore(appletinfo.getScore()+500+r.nextInt(2000));
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.pink);
		super.paint(g);
	}
}
