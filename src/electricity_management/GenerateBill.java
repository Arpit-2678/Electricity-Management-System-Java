package electricity_management;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GenerateBill extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel l1, l2;
	JTextArea t1;
	JButton b1;
	Choice c2;
	JPanel p1;
	String meter;

	GenerateBill(String meter) {
		this.meter = meter;
		setSize(550, 600);
		setLayout(new BorderLayout());

		p1 = new JPanel();

		l1 = new JLabel("Generate Bill");

		l2 = new JLabel(meter);
		c2 = new Choice();

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

		t1 = new JTextArea(50, 15);
		t1.setText(
				"\n\n\n\n\n\n\n\n\t------- Click on the -------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month\n\n");
		JScrollPane jsp = new JScrollPane(t1);
		t1.setFont(new Font("Senserif", Font.ITALIC, 18));

		b1 = new JButton("Generate Bill");

		p1.add(l1);
		p1.add(l2);
		p1.add(c2);
		add(p1, "North");

		add(jsp, "Center");
		add(b1, "South");

		b1.addActionListener(this);

		setLocationRelativeTo(null);
//		p1.setBackground(Color.pink);
//		t1.setBackground(Color.pink);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			conn c = new conn();

			String month = c2.getSelectedItem();
			t1.setText("\tReliance Power Limited\nELECTRICITY BILL FOR THE MONTH OF " + month + " ,2021\n\n\n");

			ResultSet rs = c.s.executeQuery("select * from customer where Meter_number= '" + meter + "'");

			if (rs.next()) {
				t1.append("\n    Customer Name: " + rs.getString("Name"));
				t1.append("\n    Meter Number:   " + rs.getString("Meter_number"));
				t1.append("\n    Address:            " + rs.getString("Address"));
				t1.append("\n    State:                 " + rs.getString("City"));
				t1.append("\n    City:                   " + rs.getString("State"));
				t1.append("\n    Email:                " + rs.getString("Email"));
				t1.append("\n    Phone Number:  " + rs.getString("Phone"));
				t1.append("\n-------------------------------------------------------------");
				t1.append("\n");
			}

			rs = c.s.executeQuery("select * from meter_info where meter_number = " + meter);

			if (rs.next()) {
				t1.append("\n    Meter Location:  " + rs.getString("Meter_location"));
				t1.append("\n    Meter Type:      " + rs.getString("Meter_type"));
				t1.append("\n    Phase Code:    " + rs.getString("Phase_code"));
				t1.append("\n    Bill Type:         " + rs.getString("Bill_type"));
				t1.append("\n    Days:              " + rs.getString("Days"));
				t1.append("\n");
			}
			rs = c.s.executeQuery("select * from tax");
			if (rs.next()) {
				t1.append("---------------------------------------------------------------");
				t1.append("\n\n");
				t1.append("\n Cost per Unit:              Rs " + rs.getString("cost_per_unit"));
				t1.append("\n Meter Rent:                 Rs " + rs.getString("Meter_rent"));
				t1.append("\n Service Charge:          Rs " + rs.getString("service_charge"));
				t1.append("\n Service Tax:               Rs " + rs.getString("service_tax"));
				t1.append("\n Swacch Bharat Cess: Rs " + rs.getString("swacch_bharat_cess"));
				t1.append("\n Fixed Tax:                  Rs " + rs.getString("fixed_tax"));
				t1.append("\n");

			}

			rs = c.s.executeQuery(
					"select * from bill where meter=" + meter + " AND month = '" + c2.getSelectedItem() + "'");

			if (rs.next()) {
				t1.append("---------------------------------------------------------------");
				t1.append("\n    Current Month :\t" + rs.getString("month"));
				t1.append("\n    Units Consumed:\t" + rs.getString("units"));
				t1.append("\n    Total Charges :\t" + rs.getString("total_bill"));
				t1.append("\n---------------------------------------------------------------");
				t1.append("\n    TOTAL PAYABLE :\t" + rs.getString("total_bill"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new GenerateBill("").setVisible(true);
	}

}
