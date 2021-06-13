package electricity_management;

import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Splash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		fframe f1 = new fframe();
//		f1.setSize(500, 600);
//		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
		int i;
		int x = 1;
		for (i = 2; i <= 600; i += 4, x += 1) {
			f1.setLocation(600 - ((i + x) / 2), 370 - (i / 2));
			f1.setSize(i + x, i);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

}

class fframe extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Thread t1;

	public fframe() {
		// TODO Auto-generated constructor stub
		super("Electricity Billing System");
		setLayout(new FlowLayout());
		ImageIcon c1 = new ImageIcon(
				"D:\\Core Java Programs\\Electricity_Mangement\\src\\electricity_management\\Icons\\elect.jpg");
		Image i1 = c1.getImage().getScaledInstance(750, 550, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(i1);
		JLabel l1 = new JLabel(i2);
		add(l1);
		t1 = new Thread(this);
		t1.start();

	}

	@Override
	public void run() {
		try {
			Thread.sleep(7000);
			this.setVisible(false);
			Login l = new Login();
			l.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
