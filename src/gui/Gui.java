package gui;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jfree.ui.RefineryUtilities;

import test.Execution;
import util.Util;

import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Gui {

	private JFrame frmProgettoTeoriaInformazione;
	private JTextField fileTextField;
	private JTextField codificaTextField;
	private JTextField binaryParam;
	private JTextField gilbertParam;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmProgettoTeoriaInformazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProgettoTeoriaInformazione = new JFrame();
		frmProgettoTeoriaInformazione.setTitle("Source-Channel Coding System");
		frmProgettoTeoriaInformazione.setBounds(250, 350, 944, 383);
		frmProgettoTeoriaInformazione.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JToolBar toolBar = new JToolBar();
		frmProgettoTeoriaInformazione.getContentPane().add(toolBar, BorderLayout.NORTH);

		JButton helpButton = new JButton("Help");
		helpButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		toolBar.add(helpButton);

		JPanel panel = new JPanel();
		frmProgettoTeoriaInformazione.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(5, 2, 0, 0));

		JLabel label = new JLabel("Input File:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(SystemColor.desktop);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		label.setBackground(SystemColor.activeCaption);
		panel.add(label);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		fileTextField = new JTextField();
		fileTextField.setText("C:\\");
		fileTextField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fileTextField.setColumns(10);
		panel_1.add(fileTextField);

		JButton browseButton = new JButton("Browse...");
		browseButton.setFont(new Font("Yu Gothic", Font.BOLD, 11));
		panel_1.add(browseButton);

		JLabel label_1 = new JLabel("Source Coding:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(SystemColor.desktop);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		label_1.setBackground(SystemColor.activeCaption);
		panel.add(label_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(2, 1, 0, 0));

		JRadioButton lzwButton = new JRadioButton("LZW");
		buttonGroup.add(lzwButton);
		lzwButton.setSelected(true);
		lzwButton.setHorizontalAlignment(SwingConstants.CENTER);
		lzwButton.setFont(new Font("Yu Gothic", Font.BOLD, 11));
		panel_2.add(lzwButton);

		JRadioButton lzmwButton = new JRadioButton("LZMW");
		buttonGroup.add(lzmwButton);
		lzmwButton.setSelected(false);
		lzmwButton.setHorizontalAlignment(SwingConstants.CENTER);
		lzmwButton.setFont(new Font("Yu Gothic", Font.BOLD, 11));
		panel_2.add(lzmwButton);

		JLabel label_2 = new JLabel("Channel Coding:");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(SystemColor.desktop);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		label_2.setBackground(SystemColor.activeCaption);
		panel.add(label_2);

		codificaTextField = new JTextField();
		codificaTextField.setText("r3 r5 h(7,4) ");
		codificaTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		codificaTextField.setColumns(10);
		codificaTextField.setBackground(Color.WHITE);
		panel.add(codificaTextField);

		JLabel label_3 = new JLabel("Channel Model:");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(SystemColor.desktop);
		label_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_3.setBorder(new LineBorder(new Color(128, 128, 128), 1, true));
		label_3.setBackground(SystemColor.activeCaption);
		panel.add(label_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(2, 2, 0, 0));

		JRadioButton binaryButton = new JRadioButton("Binary Symmetric (\u03B1)");
		buttonGroup_1.add(binaryButton);
		binaryButton.setSelected(true);
		binaryButton.setHorizontalAlignment(SwingConstants.LEFT);
		binaryButton.setFont(new Font("Yu Gothic", Font.BOLD, 11));
		panel_3.add(binaryButton);

		binaryParam = new JTextField();
		binaryParam.setText("0.01");
		binaryParam.setColumns(10);
		panel_3.add(binaryParam);

		JRadioButton gilbertButton = new JRadioButton("Gilbert-Elliott (Pgg Pbb Pg Pb)");
		buttonGroup_1.add(gilbertButton);
		gilbertButton.setHorizontalAlignment(SwingConstants.LEFT);
		gilbertButton.setFont(new Font("Yu Gothic", Font.BOLD, 11));
		panel_3.add(gilbertButton);

		gilbertParam = new JTextField();
		gilbertParam.setEnabled(false);
		gilbertParam.setText("0.99 0.80 0.0001 0.01");
		gilbertParam.setColumns(10);
		panel_3.add(gilbertParam);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel.add(panel_4);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY, 1, true));
		panel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JButton startButton = new JButton("START");
		startButton.setForeground(new Color(135, 206, 250));
		startButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_5.add(startButton);

		// Actions
		binaryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				binaryParam.setEditable(true);
				gilbertParam.setEnabled(false);
			}
		});
		gilbertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				binaryParam.setEditable(false);
				gilbertParam.setEnabled(true);
			}
		});

		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				// For Directory
				// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				// For File
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				fileChooser.setFileFilter(filter);

				int rVal = fileChooser.showOpenDialog(null);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					if (!fileChooser.getSelectedFile().exists()) {
						JOptionPane.showMessageDialog(null, "Select a valid file!");
					} else {
						fileTextField.setText(fileChooser.getSelectedFile().toString());
					}
				}
			}
		});

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = fileTextField.getText();

				int sourceCoding = lzwButton.isSelected() ? 1 : 2;

				double[][] parErr;
				try {
					if (binaryButton.isSelected()) {
						parErr = Util.parseError(binaryParam.getText(), binaryButton.isSelected());
					} else {
						parErr = Util.parseError(gilbertParam.getText(), binaryButton.isSelected());
					}
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, "Syntax error: " + e1.getMessage());
					return;
				}

				LinkedList<Integer>[] channels = null;
				LinkedList<String> ss = new LinkedList<>();
				try {
					channels = Util.parseChannel(codificaTextField.getText(), ss);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() + "\nCorrect syntax example: r3 r5 h(7,4)");
					return;
				}
				int chan = channels.length;
				int err = parErr.length;

				Execution ee = null;
				String[] series = new String[ss.size()];
				for (int i = 0; i < series.length; i++) {
					series[i] = ss.get(i);
				}
				double[][] x = new double[chan][err];
				double[][] y = new double[chan][err];
				try {
					for (int serie = 0; serie < chan; serie++) {
						for (int e_i = 0; e_i < err; e_i++) {
							ee = Execution.build(fileName, sourceCoding, channels[serie], parErr[e_i]);
							ee.execute();
							x[serie][e_i] = binaryButton.isSelected() ? parErr[e_i][0] : parErr[e_i][1];
							y[serie][e_i] = ee.stats.ber;
						}
					}
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
					return;
				}
				if (chan == 1 && err == 1) {
					GuiStat s = new GuiStat(ee.getStat());
				} else {
					String ax = binaryButton.isSelected() ? "\u03B1" : "Pbb";
					XYSplineRendererDemo appFrame = new XYSplineRendererDemo("Compare", series, x, y, ax);
					appFrame.pack();
					RefineryUtilities.centerFrameOnScreen(appFrame);
					appFrame.setVisible(true);
				}
			}
		});

		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuiHelp h = new GuiHelp();
			}
		});
	}

}
