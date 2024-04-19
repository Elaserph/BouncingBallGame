import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.Random;

public class startingpoint extends Applet implements Runnable, KeyListener,MouseMotionListener{
	

	private Image i;
	private Graphics doubleG;
	ball b=new ball(),b2,b3,b4;
	platform p[]= new platform[7];
	item it[]=new item[3];
	private int score;
	double cityX=0;
	double cityDx=.3;
	URL url;
	Image city;
	int levelcheck=0;
	boolean gameover=false;
	boolean mouseIn=false;
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public void init() {
		setSize(800,600);
		addKeyListener(this);
		addMouseMotionListener(this);
		try{
			url = getDocumentBase();
		}catch(Exception e){
			
		}
		city = getImage(url,"images/dd4.PNG");
		new Pictures(this);
		Pictures.music.loop();
		
		}
	
	@Override
	public void start() {
		b= new ball();
		score=0;
		//b2=new ball(250,250);
		//b3=new ball(100,100);
		//b4=new ball(500,500);
		for(int i=0;i<p.length;i++){
			Random r= new Random();
		p[i]= new platform(120*i , 300);
		}
		
		for(int i=0;i<it.length;i++){
			Random r= new Random();
			switch (r.nextInt(5)){
			case 0:
			it[i]=new GravUp(getWidth()+2000*i);
			    break;
			case 1:
			it[i]=new GravDown(getWidth()+2000*i);
				break;
			case 2:
			it[i]=new AgilUp(getWidth()+2000*i);
				break;
			case 3:
			it[i]=new AgilDown(getWidth()+2000*i);
				break;
			case 4:
				it[i]=new score(getWidth()+2000*i,this);
				break;
		}
		}
		//p2=new platform(100,425);
		Thread thread = new Thread(this);
		thread.start();
		
	}
	@Override
	public void run() {
		while(true){
			
			//if(x < 0-width)
			for(int i=0;i<p.length;i++){
				int testx=p[i].getX();
				if(testx < 0-p[i].getWidth()){
					Random r= new Random();
					int fakei=i;
					if(i==0){
						fakei =p.length;
					}//the toughest
					p[i].setX(p[fakei-1].getX()+p[i].getWidth()+Pictures.level*r.nextInt(25));
			}
		}
			
			gameover = b.getGameOver();
			
			if(levelcheck>1000){
				Pictures.level++;
				levelcheck=0;
			}
			levelcheck++;
			
			
			if(cityX>getWidth()*-1){
			cityX-=cityDx;
			}else
			{
				cityX=0;
			}
			if(!gameover){
			score++;
			}
			
		Random r=new Random();
			
		for(int i=0;i<it.length;i++){
			if(it[i].isCreateNew()){
				it[i]= null;
				switch (r.nextInt(5)){
				case 0:
				it[i]=new GravUp(getWidth()+10*r.nextInt(500));
				    break;
				case 1:
				it[i]=new GravDown(getWidth()+10*r.nextInt(500));
					break;
				case 2:
				it[i]=new AgilUp(getWidth()+r.nextInt(500));
					break;
				case 3:
				it[i]=new AgilDown(getWidth()+r.nextInt(500));
					break;
				case 4:
					it[i]=new score(getWidth()+r.nextInt(500),this);
					break;
					}
				it[i].setCreateNew(false);
				}
		}
			b.physics(this);
			//b2.physics(this);
			//b3.physics(this);
			//b4.physics(this);
			for(int i=0;i<p.length;i++){
				p[i].physics(this, b);
			}
			for(int i=0;i<it.length;i++){
				it[i].physics(this, b);
			}
			//p.physics(this, b);
			//p2.physics(this, b);
			//x+=dx;
			//y+=dy;
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	}
	@Override 
	public void update(Graphics g) {
		if(i==null){
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
	     doubleG.setColor(getBackground());
	     doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
	
	doubleG.setColor(getForeground());
	paint(doubleG);
	g.drawImage(i, 0, 0, this);
	}
	@Override
	public void paint(Graphics g) {
		
		g.setColor(new Color(15,77,147));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(city,(int)cityX, 0, this);
		g.drawImage(city,(int)cityX+getWidth(), 0, this);
		
		//b2.paint(g);
		//b3.paint(g);
		//b4.paint(g);
		for(int i=0;i<p.length;i++){
			p[i].paint(g);
		}
		for(int i=0;i<it.length;i++){
			it[i].paint(g);
		}
		b.paint(g);
		//p.paint(g);
		//p2.paint(g);
		String s = Integer.toString(score);
		Font font =new Font("Serif",Font.BOLD,32);
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString(s, getWidth()-150+2, 50+2);
		g.setColor(new Color(198,226,255));
		g.drawString(s, getWidth()-150, 50);
		
		if(gameover){
			g.setColor(Color.WHITE);
			g.drawString("GAME OVER!!!",300 ,300);
			g.drawString("          By",300 ,500);
			g.drawString("Shreepal Shekhawat",300 ,550);
			if(mouseIn){
				g.setColor(Color.red);
				g.drawString("Play Again?",280 ,340);
				//g.drawString("          By",300 ,350);
				//g.drawString("Shreepal Shekhawat",300 ,400);
				g.drawRect(280,320,180,40);
				
			}else{
				g.setColor(Color.white);
				g.drawString("Play Again?",280 ,340);
			
				}
			}
	}
	
	@Override
	public void stop() {
		}
	@Override
	public void destroy() {
		}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			b.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			b.moveRight();
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		}

	@Override
	public void keyTyped(KeyEvent e) {
		}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
