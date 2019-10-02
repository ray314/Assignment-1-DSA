package lab9;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Component;
import javax.swing.Box;

public class CreateStudent extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtIdinput;
	private JTextField txtNameinput;
	private JTextField txtAgeinput;
	private JLabel lblStudentId;
	private JLabel lblName;
	private JLabel lblAge;
	private JButton okButton;
	private JButton cancelButton;
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		
		if (source == cancelButton)
		{
			dispose();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateStudent(JFrame parent) {
		super(parent);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("86px"),
				ColumnSpec.decode("99px:grow"),},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		{
			lblStudentId = new JLabel("Student ID:");
			contentPanel.add(lblStudentId, "2, 2, left, fill");
		}
		{
			txtIdinput = new JTextField();
			contentPanel.add(txtIdinput, "3, 2, fill, fill");
			txtIdinput.setColumns(10);
		}
		{
			lblName = new JLabel("Name:");
			contentPanel.add(lblName, "2, 4");
		}
		{
			txtNameinput = new JTextField();
			contentPanel.add(txtNameinput, "3, 4, fill, default");
			txtNameinput.setColumns(10);
		}
		{
			lblAge = new JLabel("Age:");
			contentPanel.add(lblAge, "2, 6, left, default");
		}
		{
			txtAgeinput = new JTextField();
			contentPanel.add(txtAgeinput, "3, 6, fill, default");
			txtAgeinput.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(100);
			getContentPane().add(horizontalStrut, BorderLayout.WEST);
		}
	}

}
