package electricity_management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class customer_detail extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable t1;
	JButton b1;
	String x[] = { "Customer Name", "Meter Number", "Address", "City", "State", "Email", "Phone" };
	String y[][] = new String[40][8];
	int i = 0, j = 0;

	public customer_detail() {
		// TODO Auto-generated constructor stub
		super("Customer Details");
		setSize(1200, 650);
		setLocationRelativeTo(null);

		try {
			conn c1 = new conn();
			String s1 = "select * from customer";
			ResultSet rs = c1.s.executeQuery(s1);
			while (rs.next()) {
				y[i][j++] = rs.getString("Name");
				y[i][j++] = rs.getString("Meter_number");
				y[i][j++] = rs.getString("Address");
				y[i][j++] = rs.getString("State");
				y[i][j++] = rs.getString("City");
				y[i][j++] = rs.getString("Email");
				y[i][j++] = rs.getString("Phone");
				i++;
				j = 0;
			}
			t1 = new JTable(y, x);

		} catch (Exception e) {
			e.printStackTrace();
		}

		b1 = new JButton("Print");
		add(b1, "South");
		JScrollPane sp = new JScrollPane(t1);
		add(sp);
		b1.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			t1.print();
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new customer_detail().setVisible(true);

	}

}
