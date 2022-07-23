/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

/**
 *
 * @author peterream
 */
public class PrefsDialog extends JDialog {
    
    private final JButton okButton;
    private final JButton cancelButton;
    private JSpinner portSpinner;
    private final SpinnerNumberModel spinnerModel;
    private JTextField userField;
    private JPasswordField passwordField;
    private PrefsListener prefsListener;
    
    public PrefsDialog(JFrame parent) {
        
        super(parent, "Preferences", false);
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerModel);
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');
        
        layoutControls();                                                           // Layout panel
        
        okButton.addActionListener(new ActionListener() {                           // (OK) clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int port = (int)portSpinner.getValue();
                String user = userField.getText();
                char[] password = passwordField.getPassword();
                
                if (prefsListener != null) {
                    prefsListener.prefsSet(user, new String(password), port);       // Pass prefs to MainFrame
                }

                setVisible(false);                                                  // Close PrefsDialog
            }
            
        });
        
        cancelButton.addActionListener(new ActionListener() {                       // (Cancel) clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                
                setVisible(false);                                                  // Close PrefsDialog
            }
            
        });
        setSize(340, 230);                                                          // Juggle for best look
        setLocationRelativeTo(parent);
    }

    /**
     * Set passed information in the fields of the display. 
     * @param user
     * @param password
     * @param port 
     */
    void setDefaults(String user, String password, int port) {
        
        userField.setText(user);
        passwordField.setText(password);
        portSpinner.setValue(port);
    }
    
    /**
     * 
     * @param prefsListener 
     */
    void setPrefsListener(PrefsListener prefsListener) {

        this.prefsListener = prefsListener;

    }

    private void layoutControls() {

        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();
        
        Border titleBorder = BorderFactory.
                createTitledBorder("Database Preferences");
        
        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        
        controlsPanel.setBorder(BorderFactory.
                createCompoundBorder(spaceBorder, titleBorder));
        
//        buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        controlsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        /////// controlsPanel
        
        Insets rightPadding = new Insets(0, 0, 0, 15);                              // Between label & field
        Insets noPadding = new Insets(0, 0, 0, 0);                                  // Reset padding
        Insets topRightPadding = new Insets(10, 0, 0, 15);                          // Padding on top for label
        Insets topNoPadding = new Insets(10, 0, 0, 0);                              // Padding on top for field
        
        gc.gridy = 0;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.5;                                                           // Less space between controls (vertically)
        gc.fill = GridBagConstraints.NONE;                                          // Controls preferred size, 
        // rather than fill whole cell
        gc.anchor = GridBagConstraints.EAST;                                        // Or LINE_END
        gc.insets = topRightPadding;
        // Horizontal space between controls
        controlsPanel.add(new JLabel("User: "), gc);

        gc.gridx++;                                                                 // Col 1 to right
        gc.anchor = GridBagConstraints.WEST;                                        // Or LINE_START
        gc.insets = topNoPadding;
        controlsPanel.add(userField, gc);

        ///////Next row - Password
        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.fill = GridBagConstraints.NONE;                                          // Controls preferred size, 
        // rather than fill whole cell
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Password: "), gc);

        gc.gridx++;                                                                 // Col 1 to right
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        controlsPanel.add(passwordField, gc);

        ///////Next row - Port spinner
        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 2.0;                                                           // Create space between u/p and port
        gc.fill = GridBagConstraints.NONE;                                          // Controls preferred size, 
        // rather than fill whole cell
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Port: "), gc);

        gc.gridx++;                                                                 // Col 1 to right
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = noPadding;
        controlsPanel.add(portSpinner, gc);
        
        /////// buttonsPanel
        
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        
        // Add sub panels to dialog
        setLayout(new BorderLayout());
        
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
        
        // Make buttons same size
        Dimension btnSize = cancelButton.getPreferredSize();
        
        okButton.setPreferredSize(btnSize);
        
/*
        ///////// Original layout /////////
        
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        ///////First row - User
        gc.gridy = 0;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.5;                                                           // Less space between controls (vertically)
        gc.fill = GridBagConstraints.NONE;                                          // Controls preferred size, 
        // rather than fill whole cell
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(10, 0, 0, 5);                                        // Top spacing
        // Horizontal space between controls
        add(new JLabel("User: "), gc);

        gc.gridx++;                                                                 // Col 1 to right
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);                                        // Top spacing
        add(userField, gc);

        ///////Next row - Password
        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.fill = GridBagConstraints.NONE;                                          // Controls preferred size, 
        // rather than fill whole cell
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);                                         // Horizontal space between controls
        add(new JLabel("Password: "), gc);

        gc.gridx++;                                                                 // Col 1 to right
        gc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gc);

        ///////Next row - Port spinner
        gc.gridy++;
        gc.gridx = 0;

        gc.weightx = 1;
        gc.weighty = 0.5;
        gc.fill = GridBagConstraints.NONE;                                          // Controls preferred size, 
        // rather than fill whole cell
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(new JLabel("Port: "), gc);

        gc.gridx++;                                                                 // Col 1 to right
        gc.anchor = GridBagConstraints.LINE_START;
        add(portSpinner, gc);

        ///////Next row - (OK) (Cancel)
        gc.gridy++;                                                                 // (OK)

        gc.gridx = 1;
        gc.weighty = 3.0;                                                           // More space for buttons
        gc.weightx = 0.3;                                                           // Buttons closer together
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.insets = new Insets(0, 0, 10, 0);                                        // Bottom
        add(okButton, gc);

        gc.gridx++;                                                                 // (Cancel)
        gc.weighty = 3.0;
        gc.weightx = 0.3;                                                           // Buttons closer together
        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.insets = new Insets(0, 0, 10, 10);                                       // Bottom and right
        add(cancelButton, gc);
*/
    }
}
