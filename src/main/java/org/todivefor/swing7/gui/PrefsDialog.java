/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

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
        setSize(400, 300);
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
}