import java.awt.Color;
import java.awt.Graphics;

public class AgilUp extends item{

	public AgilUp(int x) {
		super(x);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void performAction(ball b) {
		// TODO Auto-generated method stub
		if(b.getAgility()<8)
		b.setAgility(b.getAgility()+1);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		super.paint(g);
	}
}
