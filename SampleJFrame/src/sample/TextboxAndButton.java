package sample;

import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TextboxAndButton extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static JTextField textHr;
	private static JTextField textMin;
	private static JButton btn;
	private static JButton btnStop;
	private static JLabel lbl;
	private static JLabel lblHr;
	private static JLabel lblMin;
	int count = 0, time = 0;
	javax.swing.Timer timer;
	Robot robot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		frame = new JFrame("textHr");
		lbl = new JLabel("0");
		lblHr = new JLabel("Hour");
		lblMin = new JLabel("Min");
		btn = new JButton("Start");
		btnStop = new JButton("Stop");
		TextboxAndButton te = new TextboxAndButton();
		btn.addActionListener(te);
		btnStop.addActionListener(te);
		textHr = new JTextField(10);
		textMin = new JTextField(10);
		textHr.setText("0");
		textMin.setText("0");
		JPanel p = new JPanel();

		p.add(lblHr);
		p.add(textHr);

		p.add(lblMin);
		p.add(textMin);

		p.add(btn);
		p.add(btnStop);
		p.add(lbl);

		frame.add(p);
		frame.setSize(800, 800);
		frame.setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public TextboxAndButton() {
		initialize();
	}

	public void changeCursorLocation() {
		try {
			robot = new Robot();
			final int W = getWidth();
			final int H = getHeight();
			ActionListener al = new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					count++;
					// count in seconds
					// 1 minute = 60 seconds
					// half an hr = 1800
					// 1 hr = 3600
					lbl.setText("Time in seconds " + String.valueOf(time) + " of " + String.valueOf(count));
					if (count >= time)
						timer.stop();
					robot.mouseMove((int) (Math.random() * W), (int) (Math.random() * H));

				}
			};
			timer = new javax.swing.Timer(1000, al);
			timer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}

	void calculate() {
		int hr = 0, hrOutput = 0;
		int min = 0, minOutput = 0;
		hr = Integer.parseInt(textHr.getText());
		min = Integer.parseInt(textMin.getText());
		hrOutput = hr * 3600;
		minOutput = min * 60;
		time = minOutput + hrOutput;
		lbl.setText(String.valueOf(time));
		changeCursorLocation();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equalsIgnoreCase("Start")) {
			calculate();
		}
		if (s.equalsIgnoreCase("Stop")) {
			timer.stop();
		}

	}

}
