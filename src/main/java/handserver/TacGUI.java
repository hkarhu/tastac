package handserver;

import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import gnu.io.SerialPort;

public class TacGUI extends JFrame {

	JComboBox<SerialPort> comboBoxSerial;
	JToggleButton buttonConnectSerial;
	JLabel labelSerial;
	JPanel tabletPanel;
	JToggleButton buttonGrabMouse;
	
	public TacGUI() {

	        comboBoxSerial = new JComboBox<>();
	        buttonConnectSerial = new JToggleButton();
	        labelSerial = new JLabel();
	        tabletPanel = new JPanel();
	        buttonGrabMouse = new JToggleButton();

	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	        //comboBoxSerial.setModel(new DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

	        buttonConnectSerial.setText("Connect");
	        buttonConnectSerial.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	            	
	            }
	        });

	        labelSerial.setHorizontalAlignment(SwingConstants.TRAILING);
	        labelSerial.setText("Serial connection:");
	        labelSerial.setToolTipText("");

	        tabletPanel.setBackground(new java.awt.Color(51, 51, 51));
	        tabletPanel.setPreferredSize(new java.awt.Dimension(320, 240));

	        GroupLayout jPanel1Layout = new GroupLayout(tabletPanel);
	        tabletPanel.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGap(0, 0, Short.MAX_VALUE)
	        );

	        buttonGrabMouse.setText("Grab Mouse");

	        GroupLayout layout = new GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	                    .addComponent(tabletPanel, GroupLayout.DEFAULT_SIZE, 435, Short.MAX_VALUE)
	                    .addGroup(layout.createSequentialGroup()
	                        .addComponent(labelSerial, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(comboBoxSerial, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
	                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                        .addComponent(buttonConnectSerial))
	                    .addComponent(buttonGrabMouse, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                .addContainerGap())
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
	                    .addComponent(comboBoxSerial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	                    .addComponent(buttonConnectSerial)
	                    .addComponent(labelSerial))
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(tabletPanel, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
	                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(buttonGrabMouse)
	                .addContainerGap())
	        );

	        pack();
	}
	

}
