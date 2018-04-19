package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import java.awt.Font;

public class GuiHelp {

	private JFrame frmStat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiHelp window = new GuiHelp();
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
	public GuiHelp() {
		frmStat = new JFrame();
		frmStat.setTitle("Help");
		frmStat.setBounds(100, 100, 826, 550);
		frmStat.setVisible(true);
		frmStat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmStat.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		frmStat.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		JTextArea txtrSettingEsperimentoSingolo = new JTextArea();
		txtrSettingEsperimentoSingolo.setFont(new Font("Segoe UI", Font.BOLD, 14));	
		txtrSettingEsperimentoSingolo.setText("SETTING SINGLE TEST\r\nChannel Coding\r\nEnter channel coding by following:\r\n- rx where x is the length (ex. r3) for repetition code;\r\n- h(n,k) where k is the message length and the block length is n=k+redundancy bits (ex. h(7,4)) for Hamming codes;\r\n- more of the two separated by whitespace (ex.  r5 r3 r7 ) for concatenated codes.\r\n\r\nChannel Model\r\nEnter channel model by following:\r\n- alpha crossover probability for a binary symmetric channel;\r\n- Pgg Pbb Pg Pb in a Gilbert–Elliott channel model;\r\n\r\n\r\nSETTING MULTIPLE TESTS\r\nChannel Coding\r\nEnter channels coding to compare, as above, separated by \" ; \" (ex. r3; r3 r3; r3 r5).\r\n\r\nChannel Model\r\nEnter error probabilities, as above, separated by \" ; \" \r\n(ex. \r\n0.001; 0.01; 0.1  in case of BS\r\n0.99 0.80 0.0001 0.01; 0.99 0.80 0.001 0.1  in case of GE).");
		txtrSettingEsperimentoSingolo.setEditable(false);
		scrollPane.setViewportView(txtrSettingEsperimentoSingolo);
	}

}
