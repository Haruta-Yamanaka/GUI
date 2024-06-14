import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class test {
    public static void main(String[] args){
		new test();
	}

    test(){
		FaceFrame f = new FaceFrame();
		f.setSize(800,800);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
			System.exit(0);}});
		f.setVisible(true);
	}

	// インナークラスを定義
	class FaceFrame extends Frame{

	
    private FaceObj fobj;

	
	FaceFrame(){
		//fobj  = new FaceObj(30,30,40,40,20,20);
		/*fobj= new FaceObj[3][3];
		for(int i = 0; i<3;i++){
			for(int j = 0; j<3;j++){
				fobj[i][j] = new FaceObj();
			}
		}*/
    }

	public void paint(Graphics g) {
	// この中には、g.drawLine というのは入ってこない
	// Graphicsクラス(型のようなもの---今は--)のgという変数はメソッドに渡す
	

		FaceObj[][] fobj= new FaceObj[3][3];
		for(int i = 0; i<3;i++){
			for(int m = 0;m<3;m++){
				fobj[i][m]  = new FaceObj(30+m*200, 30+i*200, 40, (i-1)*20, 20, 20+(i-1)*10, m*5, m*3+10); 
				fobj[i][m].paintFace(g);
			}
		}
		//fobj.paintFace(g);
		//(30,30,40,40,10,20,10,10);
	
	}
	
	
}//FaceFrame end

	//Faceクラスを作ってみよう。
	private class FaceObj{
		private int w;
	private int h;
	private int xStart;
	private int yStart;
	private int mx;
	private int my;
	private int slope1;
	private int slope2;
	private int dy;
	private int r;

		FaceObj(int xStart,int yStart,int mx,int my,int slope1,int slope2 ,int dy,int r){
			w = 200;
			h = 200;
			this.xStart=xStart;
			this.yStart=yStart;
			this.slope1 = slope1;
			this.slope2 = slope2;
			this.mx = mx;
			this.my = my;
			this.dy = dy;
			this.r = r;
		}


		public void paintFace(Graphics g){
			drawRim(g,xStart,yStart);
			drawBrow(g,slope1,slope2);
			drawEye(g, r);
			drawNose(g, 40);
			drawMouth(g, mx,my,dy);
		}
		public void drawRim(Graphics g,int x, int y) {  // wが横幅、hが縦幅
			g.drawLine(x, y, x+w, y);
			g.drawLine(x, y, x, y+h);
			g.drawLine(x, y+h, x+w, y+h);
			g.drawLine(x+w, y, x+w, y+h);
		}
	
		public void drawBrow(Graphics g, int slope,int slope2) { // xは目の幅 呼ばれる方(=定義する方)
			int xStart1 = xStart+20;
			int xStart2 = xStart+20+w/2;
			int yStart1 = yStart+60;
			g.drawLine(xStart1,yStart1+slope2,xStart1+50,yStart1+slope);
			g.drawLine(xStart2,yStart1+slope, xStart2+50, yStart1+slope2);
		}
	
		public void drawNose(Graphics g, int nx) { // xは鼻の長さ
			g.drawRect(xStart+w/2-nx/2, yStart+h/2, nx, nx);
		}
	
		public void drawEye(Graphics g, int r) { // rは目の半径
			g.drawOval(xStart+55,yStart+85,r,r);
			g.drawOval(xStart+55+70,yStart+85,r,r);
			
		}
	
		public void drawMouth(Graphics g, int mx ,int my,int dy) { // xは口の幅
			int xMiddle = xStart + w/2;
			int yMiddle = yStart + h - 60;
			g.drawLine(xMiddle - mx/2, yMiddle+20, xMiddle , yMiddle+my+20);
			g.drawLine(xMiddle - mx/2, yMiddle+20,xMiddle - mx/2,yMiddle+dy+20);
			g.drawLine(xMiddle , yMiddle+my+20,xMiddle,yMiddle+my+dy+20);
			g.drawLine(xMiddle - mx/2,yMiddle+dy+20,xMiddle,yMiddle+my+dy+20);//左側
			g.drawLine(xMiddle , yMiddle+my+20, xMiddle + mx/2,yMiddle+20 );
			g.drawLine(xMiddle + mx/2,yMiddle+20,xMiddle+mx/2,yMiddle+dy +20);
			g.drawLine( xMiddle ,yMiddle+my+dy+20,xMiddle+mx/2,yMiddle+dy+20  );
		}

	}

}
