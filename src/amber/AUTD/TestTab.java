package amber.AUTD;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class TestTab extends JFrame {

	private JPanel contentPane;
	private JTextField txtHello;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestTab frame = new TestTab();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TestTab() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);		
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		//Jpanel1;Will go in tab1
		JPanel panel = new JPanel();
		
		//Tab1
		JTabbedPane tabbedPane1 = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane1);
		tabbedPane1.addTab("Tab1",null,panel,"Will do something");
		//Text box in panel of tab1
		txtHello = new JTextField();
		txtHello.setText("Hello");
		panel.add(txtHello);
		txtHello.setColumns(10);
		
		//For Tab2
		JPanel panel2 = new JPanel();//JPanel2
		JTabbedPane tabbedPane2 = new JTabbedPane(JTabbedPane.TOP); //Tab2
		contentPane.add(tabbedPane2);
		tabbedPane2.addTab("Tab2",null,panel2,"Here too, will do something");
		
		
	}

}
