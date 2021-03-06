package net.roarsoftware.tracker.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import net.roarsoftware.tracker.core.filters.FilterFactory;
import net.roarsoftware.tracker.core.filters.TaskFilter;
import net.roarsoftware.tracker.core.filters.TaskFilterInfo;

public class CreateFilterDialog extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JComboBox filterComboBox;
	private JComboBox ruleComboBox;
	private JTextField valueTextField;
	private JComboBox valueComboBox;

	private TaskFilterInfo filterInfo;

	public CreateFilterDialog(Frame f) {
		super(f, "Create Filter", true);
		setContentPane(contentPane);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});

// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		FilterFactory[] factories = FilterFactory.getFactories();
		filterComboBox.setModel(new DefaultComboBoxModel(factories));
		filterComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FilterFactory factory = (FilterFactory) filterComboBox.getSelectedItem();
				ruleComboBox.setModel(new DefaultComboBoxModel(factory.getRules()));
				Object[] objects = factory.getValues();
				if (objects == null || objects.length == 1) {
					valueComboBox.setEnabled(false);
					valueTextField.setEnabled(true);
					if (objects != null && objects.length == 1)
						valueTextField.setText(objects[0].toString());
					valueComboBox.setModel(new DefaultComboBoxModel());
				} else {
					valueComboBox.setEnabled(true);
					valueTextField.setEnabled(false);
					valueTextField.setText("");
					valueComboBox.setModel(new DefaultComboBoxModel(objects));
				}
			}
		});
		filterComboBox.setSelectedIndex(0);
		pack();
		setLocationRelativeTo(null);
	}

	private void onOK() {
// add your code here
		FilterFactory factory = (FilterFactory) filterComboBox.getSelectedItem();
		Object value = valueTextField.getText();
		if (valueComboBox.isEnabled())
			value = valueComboBox.getSelectedItem();
		FilterFactory.Rule rule = (FilterFactory.Rule) ruleComboBox.getSelectedItem();
		TaskFilter filter = factory.createFilter(rule, value);
		filterInfo = new TaskFilterInfo(filter, factory, rule, value);
		dispose();
	}

	public TaskFilterInfo getFilterInfo() {
		return filterInfo;
	}

	public void setFilterInfo(TaskFilterInfo info) {
		filterComboBox.setSelectedItem(info.getFactory());
		ruleComboBox.setSelectedItem(info.getRule());
		Object[] objects = info.getFactory().getValues();
		if (objects == null || objects.length == 1) {
			valueComboBox.setEnabled(false);
			valueTextField.setEnabled(true);
			valueTextField.setText(info.getValue().toString());
			valueComboBox.setModel(new DefaultComboBoxModel());
		} else {
			valueComboBox.setEnabled(true);
			valueTextField.setEnabled(false);
			valueTextField.setText("");
			valueComboBox.setModel(new DefaultComboBoxModel(objects));
			valueComboBox.setSelectedItem(info.getValue());
		}
	}

	private void onCancel() {
// add your code here if necessary
		dispose();
	}

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
		final JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
				GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				1, null, null, null, 0, false));
		final Spacer spacer1 = new Spacer();
		panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
				GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
		panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0,
				false));
		buttonOK = new JButton();
		buttonOK.setText("OK");
		panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
				GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		buttonCancel = new JButton();
		buttonCancel.setText("Cancel");
		panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER,
				GridConstraints.FILL_HORIZONTAL,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
		contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
				GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
				GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0,
				false));
		final JLabel label1 = new JLabel();
		label1.setText("Filter:");
		panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		final Spacer spacer2 = new Spacer();
		panel3.add(spacer2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
				GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
		final Spacer spacer3 = new Spacer();
		panel3.add(spacer3, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER,
				GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
		filterComboBox = new JComboBox();
		panel3.add(filterComboBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST,
				GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label2 = new JLabel();
		label2.setText("Rule:");
		panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		ruleComboBox = new JComboBox();
		panel3.add(ruleComboBox, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST,
				GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		final JLabel label3 = new JLabel();
		label3.setText("Value:");
		panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
				GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		valueTextField = new JTextField();
		valueTextField.setText("");
		panel3.add(valueTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST,
				GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, new Dimension(150, -1), null, 0, false));
		valueComboBox = new JComboBox();
		panel3.add(valueComboBox, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST,
				GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED,
				null, null, null, 0, false));
		label1.setLabelFor(filterComboBox);
		label2.setLabelFor(ruleComboBox);
		label3.setLabelFor(valueTextField);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return contentPane;
	}
}
