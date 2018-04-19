package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

public class GuiStat {

	private JFrame frmStat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiStat window = new GuiStat(null);
					window.frmStat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiStat(String s) {
		frmStat = new JFrame();
		frmStat.setTitle("Statistics");
		frmStat.setBounds(100, 100, 450, 429);
		frmStat.setVisible(true);
		frmStat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
		frmStat.getContentPane().add(textArea, BorderLayout.CENTER);;
		textArea.setEditable(false);
		textArea.setText(s);
	}

}
