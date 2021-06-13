package electricity_management;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class BillDetails extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable t1;
	String x[] = { "Meter Number", "Month", "Units", "Total Bill", "Status" };
	String y[][] = new String[40][8];
	int i = 0, j = 0;
	JLabel jl;

	BillDetails(String meter) {
		super("Bill Details");
		setSize(700, 650);
		setLocation(300, 100);
		setLayout(null);
		// getContentPane().setBackground(Color.WHITE);
		jl = new JLabel();
		jl.setBounds(250, 20, 200, 40);
		jl.setFont(new Font("Tahoma", Font.BOLD, 24));
		add(jl);
		try {
			conn c = new conn();
			String s2 = "select Name from customer where Meter_number = '" + meter + "'";
			ResultSet rs2 = c.s.executeQuery(s2);
			while (rs2.next()) {
				jl.setText(rs2.getString("Name"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		t1 = new JTable(y, x);

		try {
			conn c = new conn();
			String s1 = "select * from bill where meter = '" + meter + "'";

			ResultSet rs = c.s.executeQuery(s1);

			t1.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			e.printStackTrace();
		}

		JScrollPane sp = new JScrollPane(t1);
		sp.setBounds(0, 100, 700, 650);
		add(sp);

	}

	public static void main(String[] args) {
		new BillDetails("").setVisible(true);
	}

}
