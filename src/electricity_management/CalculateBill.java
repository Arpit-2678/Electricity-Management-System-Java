package electricity_management;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculateBill extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1, l2, l3, l4, l5;
	JTextField t1;
	Choice c1, c2;
	JButton b1, b2;
	JPanel p;

	public CalculateBill() {
		// TODO Auto-generated constructor stub
		p = new JPanel();
		p.setLayout(null);
		p.setBackground(new Color(173, 216, 230));

		l1 = new JLabel("Calculate Electricity Bill");
		l1.setBounds(30, 10, 400, 30);

		l2 = new JLabel("Meter No");
		l2.setBounds(60, 70, 100, 30);

		JLabel l6 = new JLabel("Name");
		l6.setBounds(60, 120, 100, 30);

		JLabel l7 = new JLabel("Address");
		l7.setBounds(60, 170, 100, 30);

		l3 = new JLabel("Units Cosumed");
		l3.setBounds(60, 220, 100, 30);

		l5 = new JLabel("Month");
		l5.setBounds(60, 270, 100, 30);

		c1 = new Choice();
		c1.setBounds(200, 70, 180, 20);
		try {
			conn c = new conn();
			ResultSet rs = c.s.executeQuery("select * from customer");
			while (rs.next()) {
				c1.add(rs.getString("Meter_number"));
			}
		} catch (Exception e) {
		}
		JLabel l11 = new JLabel();
		l11.setBounds(200, 120, 180, 20);
		p.add(l11);

		JLabel l12 = new JLabel();
		l12.setBounds(200, 170, 180, 20);
		p.add(l12);

		try {
			conn c = new conn();
			ResultSet rs = c.s
					.executeQuery("select * from customer where Meter_number = '" + c1.getSelectedItem() + "'");
			while (rs.next()) {
				l11.setText(rs.getString("Name"));
				l12.setText(rs.getString("Address"));
			}
		} catch (Exception e) {
		}

		c1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent ae) {
				try {
					conn c = new conn();
					ResultSet rs = c.s
							.executeQuery("select * from customer where Meter_number = '" + c1.getSelectedItem() + "'");
					while (rs.next()) {
						l11.setText(rs.getString("Name"));
						l12.setText(rs.getString("Address"));
					}
				} catch (Exception e) {
				}
			}
		});
		t1 = new JTextField();
		t1.setBounds(200, 220, 180, 20);

		c2 = new Choice();
		c2.setBounds(200, 270, 180, 20);
		c2.add("January");
		c2.add("February");
		c2.add("March");
		c2.add("April");
		c2.add("May");
		c2.add("June");
		c2.add("July");
		c2.add("August");
		c2.add("September");
		c2.add("October");
		c2.add("November");
		c2.add("December");

		b1 = new JButton("Submit");
		b1.setBounds(100, 350, 100, 25);
		b2 = new JButton("Cancel");
		b2.setBounds(230, 350, 100, 25);

		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);

		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);

		ImageIcon i1 = new ImageIcon(
				"D:\\Core Java Programs\\Electricity_Mangement\\src\\electricity_management\\Icons\\hicon2.jpg");
		Image i2 = i1.getImage().getScaledInstance(180, 270, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		l4 = new JLabel(i3);

		l1.setFont(new Font("Senserif", Font.PLAIN, 26));
		// Move the label to center
		l1.setHorizontalAlignment(JLabel.CENTER);

		p.add(l1);
		p.add(l2);
		p.add(l6);
		p.add(l7);
		p.add(c1);
		p.add(l5);
		p.add(c2);
		p.add(l3);
		p.add(t1);
		p.add(b1);
		p.add(b2);

		setLayout(new BorderLayout(30, 30));

		add(p, "Center");
		add(l4, "West");

		b1.addActionListener(this);
		b2.addActionListener(this);

		getContentPane().setBackground(Color.WHITE);
		setSize(750, 500);
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b1) {
			String meter_no = c1.getSelectedItem();
			String units = t1.getText();
			String month = c2.getSelectedItem();

			int units_consumed = Integer.parseInt(units);

			int total_bill = 0;
			try {
				conn c = new conn();
				ResultSet rs = c.s.executeQuery("select * from tax");
				while (rs.next()) {
					total_bill = units_consumed * Integer.parseInt(rs.getString("cost_per_unit")); // 120 * 7
					total_bill += Integer.parseInt(rs.getString("meter_rent"));
					total_bill += Integer.parseInt(rs.getString("service_charge"));
					total_bill += Integer.parseInt(rs.getString("service_tax"));
					total_bill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
					total_bill += Integer.parseInt(rs.getString("fixed_tax"));
				}
			} catch (Exception e) {
			}

			String q = "insert into bill values('" + meter_no + "','" + month + "','" + units + "','" + total_bill
					+ "', 'Not Paid')";

			try {
				conn c1 = new conn();
				c1.s.executeUpdate(q);
				JOptionPane.showMessageDialog(null, "Customer Bill Updated Successfully");
				this.setVisible(false);
			} catch (Exception aee) {
				aee.printStackTrace();
			}

		} else if (ae.getSource() == b2) {
			this.setVisible(false);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CalculateBill().setVisible(true);

	}

}
