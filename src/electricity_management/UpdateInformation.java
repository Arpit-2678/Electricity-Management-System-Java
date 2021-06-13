package electricity_management;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateInformation extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField t1, t2, t3, t4, t5, t6, t7;
	JLabel l11, l12;
	JButton b1, b2;
	String meter;

	UpdateInformation(String meter) {
		this.meter = meter;

		setBounds(200, 220, 1050, 450);
		setLayout(null);
		getContentPane().setBackground(Color.WHITE);

		JLabel title = new JLabel("UPDATE CUSTOMER INFORMATION");
		title.setBounds(110, 0, 400, 30);
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(title);

		JLabel l1 = new JLabel("Name");
		l1.setBounds(30, 70, 100, 20);
		add(l1);

		l11 = new JLabel();
		l11.setBounds(230, 70, 200, 20);
		add(l11);

		JLabel l2 = new JLabel("Meter Number");
		l2.setBounds(30, 110, 100, 20);
		add(l2);

		l12 = new JLabel();
		l12.setBounds(230, 110, 200, 20);
		add(l12);

		JLabel l3 = new JLabel("Address");
		l3.setBounds(30, 150, 100, 20);
		add(l3);

		t1 = new JTextField();
		t1.setBounds(230, 150, 200, 20);
		add(t1);

		JLabel l4 = new JLabel("City");
		l4.setBounds(30, 190, 100, 20);
		add(l4);

		t2 = new JTextField();
		t2.setBounds(230, 190, 200, 20);
		add(t2);

		JLabel l5 = new JLabel("State");
		l5.setBounds(30, 230, 100, 20);
		add(l5);

		t3 = new JTextField();
		t3.setBounds(230, 230, 200, 20);
		add(t3);

		JLabel l6 = new JLabel("Email");
		l6.setBounds(30, 270, 100, 20);
		add(l6);

		t4 = new JTextField();
		t4.setBounds(230, 270, 200, 20);
		add(t4);

		JLabel l7 = new JLabel("Phone");
		l7.setBounds(30, 310, 100, 20);
		add(l7);

		t5 = new JTextField();
		t5.setBounds(230, 310, 200, 20);
		add(t5);

		b1 = new JButton("Update");
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		b1.setBounds(70, 360, 100, 25);
		b1.addActionListener(this);
		add(b1);

		b2 = new JButton("Back");
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		b2.setBounds(230, 360, 100, 25);
		b2.addActionListener(this);
		add(b2);

		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer where Meter_number = '" + meter + "'");
			while (rs.next()) {
				l11.setText(rs.getString(1));
				l12.setText(rs.getString(2));
				t1.setText(rs.getString(3));
				t2.setText(rs.getString(4));
				t3.setText(rs.getString(5));
				t4.setText(rs.getString(6));
				t5.setText(rs.getString(7));

			}
		} catch (Exception e) {
		}

		ImageIcon i1 = new ImageIcon(
				"D:\\Core Java Programs\\Electricity_Mangement\\src\\electricity_management\\Icons\\update.jpg");
		Image i2 = i1.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l8 = new JLabel(i3);
		l8.setBounds(550, 50, 400, 300);
		add(l8);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b1) {
			@SuppressWarnings("unused")
			String s1 = l11.getText();
			@SuppressWarnings("unused")
			String s2 = l12.getText();
			String s3 = t1.getText();
			String s4 = t2.getText();
			String s5 = t3.getText();
			String s6 = t4.getText();
			String s7 = t5.getText();

			try {
				conn c = new conn();
				c.s.executeUpdate("update customer set Address = '" + s3 + "', City = '" + s4 + "', State = '" + s5
						+ "', Email = '" + s6 + "', Phone = '" + s7 + "' where Meter_number = '" + meter + "'");
				JOptionPane.showMessageDialog(null, "Details Updated Successfully");
				this.setVisible(false);

			} catch (Exception e) {
			}

		} else if (ae.getSource() == b2) {
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		new UpdateInformation("").setVisible(true);

	}
}
