
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
public class MovingBallAWT {
    public static void main(String[] args) {
		FFrame f = new FFrame();
		f.setSize(800, 800);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.show();
	}


	static class FFrame extends Frame implements Runnable {

		Thread th;
		Ball myBall1;
        Ball[] myBall;
        int n = 5;
		Random s,m = new Random();
		private boolean enable = true;
		private int counter = 0;

		FFrame() {
			th = new Thread(this);
			th.start();
		}

		public void run() {

            myBall = new Ball[n];
            for(int i = 0;i<n;i++){
                int r = i*5+10;
                myBall[i] = new Ball();
                myBall[i].setPosition(200+4*r,200+4*r);
                myBall[i].setR(r);
                myBall[i].setColor(new Color(30*i+60,40*i+20,35*i+10));
            }
			myBall1 = new Ball();
			myBall1.setPosition(200, 150);
			myBall1.setR(10);
			myBall1.setColor(Color.RED);

		

			while (enable) {

				try {
					th.sleep(100);
					counter++;
					if (counter >= 200) enable = false;
				} catch (InterruptedException e) {
				}


				myBall1.move();
				for(int i = 0;i<n;i++){
                    myBall[i].move();
                }

				repaint();  // paint()メソッドが呼び出される
			}
		}


		public void paint(Graphics g) {
			for(int i = 0;i<n;i++){
                myBall[i].draw(g);
            }
		}

		// Ball というインナークラスを作る
		class Ball {
			double x;
			double y;
			int r; // 半径
			Color c = Color.RED;

			double xDir = 1;  // 1:+方向  -1: -方向
			double yDir = 1;

			void setColor(Color c) {
				this.c = c;
			}

            void setVelocity(double a , double b){
                xDir = a;
                yDir = b;
            }


			void move() {

				if ((xDir == 1) && (x >= 700)) {
					xDir *= -1;
				}
				if ((xDir == -1) && (x <= 100)) {
					xDir *= -1;
				}

				
					x = x + 10*xDir;
				


				if ((yDir == 1) && (y >= 700)) {
					yDir *= -1;
				}
				if ((yDir == -1) && (y <= 100)) {
					yDir *= -1;
				}

				
					y = y + 10*yDir;
				


			}


			void setPosition(int x, int y) {
				this.x = x;
				this.y = y;
			}

			void setR(int r) {
				this.r = r;
			}

			void draw(Graphics g) {
				g.setColor(c);
				g.fillOval((int)x, (int)y, 2 * r, 2 * r);  // rは半径なので2倍にする
			}

		}//innner class Ball end

	}

}
