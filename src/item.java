import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class item {
	private int x,y,dx,radius;
	private startingpoint sp;
	private boolean createNew = false;
	
	public boolean isCreateNew() {
	
		return createNew;
	}
	public void setCreateNew(boolean createNew) {
		this.createNew = createNew;
	}
	
	public item(int x) {
		// TODO Auto-generated constructor stub
		this.x=x;
		Random r= new Random();
		y= r.nextInt(400)+radius;
		dx=-2;
		radius = 10;
	}
	public void physics (startingpoint sp, ball b){
		x+=dx;
		this.sp =sp;
		checkForCollosion(b);
		if(x <0-radius){
			createNew = true;
			//Random r = new Random(); 
			//x =sp.getWidth()+200+ r.nextInt(300);
			
		}
		
	}
    private void checkForCollosion(ball b) {
    	int ballx = b.getX();
    	int bally = b.getY();
    	int ballR = b.getRadius();
    	
    	int a=x-ballx;
    	int b1= y-bally;
    	int collide = radius+ballR;
    	double c=Math.sqrt((double)(a*a)+(double)(b1*b1));

    	if (c<collide){
    		performAction(b);
    		//x=0;
    		//y=sp.getHeight()+100;
    		createNew=true;
    	}
	}
    public void performAction(ball b) {
		// TODO Auto-generated method stub
		
	}
	public void paint(Graphics g){
		
		//g.setColor(Color.black);
		//g.drawRect(x,y,width,height);
		//g.fillRect(x, y, width, height);
		g.fillOval(x-radius, y-radius, radius*2, radius*2);
		
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	

}
