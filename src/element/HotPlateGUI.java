package element;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class HotPlateGUI extends JFrame implements ChangeListener {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					HotPlateGUI frame = new HotPlateGUI();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// GUI Components
	private JPanel contentPane;
	private ElementPanel elementPanel; // Holds all the elements
	private JPanel southPanel;
	private JSlider heatConstSlider; // heat constant slider
	private JSlider tempSlider; // temperature slider
	private JLabel lblTemperature; // temperature label
	private JLabel lblHeatConstant; // heat constant label

	private JPanel tempSliderPanel;
	private JPanel heatConstSliderPanel;

	public HotPlateGUI() {
		
		createFrame();
	}

	/**
	 * Add neighbouring elements to the list, left and right, top and bottom
	 * @param i
	 * @param j
	 */
	
	public JSlider getSlider()
	{
		return tempSlider;
	}
	
	/**
	 * Create the frame.
	 */
	public void createFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		elementPanel = new ElementPanel(this);
		contentPane.add(elementPanel, BorderLayout.CENTER);
		//elementPanel.setLayout(new GridLayout(0, SIZE, 0, 0));

		southPanel = new JPanel();
		contentPane.add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new GridLayout(0, 1, 0, 0));

		createSouthPanel();

	}

	private void createSouthPanel() {
		tempSliderPanel = new JPanel();
		southPanel.add(tempSliderPanel);
		tempSliderPanel.setLayout(new GridLayout(0, 1, 0, 0));
		tempSliderPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));


		lblTemperature = new JLabel("Temperature: 500");
		tempSliderPanel.add(lblTemperature);
		lblTemperature.setHorizontalAlignment(SwingConstants.CENTER);

		tempSlider = new JSlider(0, 1000, 500);
		tempSliderPanel.add(tempSlider);
		tempSlider.setPaintTicks(true);
		tempSlider.setPaintTrack(true);
		tempSlider.setPaintLabels(true);

		tempSlider.setMinorTickSpacing(10);
		tempSlider.setMajorTickSpacing(250);

		tempSlider.setToolTipText("Sets the temperature applied");
		tempSlider.addChangeListener(this);

		heatConstSliderPanel = new JPanel();
		heatConstSliderPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		southPanel.add(heatConstSliderPanel);
		heatConstSliderPanel.setLayout(new GridLayout(0, 1, 0, 0));
		heatConstSliderPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY, Color.DARK_GRAY));

		lblHeatConstant = new JLabel("Heat constant: 0.1");
		heatConstSliderPanel.add(lblHeatConstant);
		lblHeatConstant.setHorizontalAlignment(SwingConstants.CENTER);

		heatConstSlider = new JSlider(0, 100, 10);
		heatConstSliderPanel.add(heatConstSlider);
		heatConstSlider.setPaintTicks(true);
		heatConstSlider.setPaintTrack(true);
		heatConstSlider.setPaintLabels(true);

		heatConstSlider.setMinorTickSpacing(1);
		heatConstSlider.setMajorTickSpacing(25);

		heatConstSlider.addChangeListener(this);
	}

	/** Start all element threads
	 * @param i
	 * @param j
	 */
	

	/**
	 * Listen for changes on the slider
	 */
	@Override
	public void stateChanged(ChangeEvent e)
	{
		Object source = e.getSource();

		if (source == tempSlider)
		{
			lblTemperature.setText("Temperature: " + tempSlider.getValue());
		}
		else if (source == heatConstSlider)
		{
			Double heatConst = new Double(heatConstSlider.getValue());
			heatConst /= 100.0; // To get the decimal values instead
			Element.heatConstant = heatConst;

			lblHeatConstant.setText("Heat constant: " + heatConst);
		}
	}
}
