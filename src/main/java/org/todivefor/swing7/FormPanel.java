/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.todivefor.swing7;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author peterream
 */
public class FormPanel extends JPanel {
    
    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private JList ageList;
    private JComboBox empCombo;
    private JCheckBox citizenCheck;
    private JLabel taxLabel;
    private JTextField taxField;
    
    private FormListener formListener;
    
    public FormPanel() {
        
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);
        
        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        nameField = new JTextField(10);                                             // 10 characters
        occupationField = new JTextField(10);
        ageList = new JList();
        empCombo = new JComboBox();
        citizenCheck = new JCheckBox();
        taxLabel = new JLabel("Tax ID: ");
        taxField = new JTextField(10);
        
        // Setup list box
        DefaultListModel ageModel = new DefaultListModel();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(1);                                                // Set default
        ageList.setPreferredSize(new Dimension(125,55));
        ageList.setBorder(BorderFactory.createEtchedBorder());
        
        // Setup combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Employed");
        empModel.addElement("Self-Employed");
        empModel.addElement("Unmployed");
        empCombo.setModel(empModel);
        empCombo.setPreferredSize(new Dimension(130, 35));
        
        // Setup tax id
        taxLabel.setEnabled(false);
        taxField.setEnabled(false);
        
        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                boolean isTicked = citizenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
            
        });
        okBtn = new JButton("OK");
        
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                // Get data input from JTextFields
                String name = nameField.getText();                                  // Get name
                String occupation = occupationField.getText();                      // Get occupation
                AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();      // From JList
                String empCat = (String) empCombo.getSelectedItem();                // From JComboBox
                boolean usCitizen = citizenCheck.isSelected();
                String taxId = taxField.getText();
                
//                System.out.println(ageCat.getId());                                 // Debug
//                System.out.println(empCat);                                         // Debug
                
                // Save input data as EventObject
                FormEvent ev = new FormEvent(this, name, occupation, 
                        ageCat.getId(), empCat, taxId, usCitizen);
                
                // Trigger event in MainFrame
                if (formListener != null) {
                    formListener.formEventOcurred(ev);
                }
            }
            
        });
        
        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        layoutComponents();
    }
    
    private void layoutComponents() {
        
        setLayout(new GridBagLayout());
        
        GridBagConstraints gc = new GridBagConstraints();
        
        gc.fill = GridBagConstraints.NONE;
        
        ///// First row /////
        // 0, 0
        gc.gridy = 0;
        gc.gridx = 0;
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1;                                                           // Relative height of cell
        gc.anchor = GridBagConstraints.LINE_END;                                    // Align cell right
        gc.insets = new Insets(0, 0, 0, 5);                                         // Add spacing between label and field
        add(nameLabel, gc);
        
        // 1, 0
        gc.gridx = 1;                                                             // Not needed, for clarity
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1;                                                           // Relative height of cells
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);                                         // Reset
        add(nameField,gc);
        
        ///// Next row /////
        // 0, 1
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1; 
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(occupationLabel, gc);
        
        // 1, 1
        gc.gridx = 1;
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1; 
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(occupationField, gc);
        
        ///// next row ///// JList
        // 0, 2
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1;                                                           // Relative height of cell
        gc.anchor = GridBagConstraints.FIRST_LINE_END;                                    // Align cell right
        gc.insets = new Insets(7, 0, 0, 5);                                         // Add spacing between label and field
        add(new JLabel("Age:"), gc);
        
        // 1, 2
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 0.1;                                                           // Reative height
        gc.anchor = GridBagConstraints.FIRST_LINE_START;                            // Top right of cell
        gc.insets = new Insets(7, 0, 0, 0);                                        // Space ageList down a little
        add(ageList, gc);
        
        ///// next row ///// JComboBox
        // 0, 3
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1;                                                           // Relative height of cell
        gc.anchor = GridBagConstraints.FIRST_LINE_END;                                    // Align cell right
        gc.insets = new Insets(7, 0, 0, 5);                                         // Add spacing between label and field
        add(new JLabel("Employment:"), gc);
        
        // 1, 3
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 0.1;                                                           // Reative height, bigger cell
        gc.anchor = GridBagConstraints.FIRST_LINE_START;                            // Top right of cell
//        gc.anchor = GridBagConstraints.LAST_LINE_START;                             // Bottom right of cell
        gc.insets = new Insets(0, 0, 0, 0);                                        // Reset
        add(empCombo, gc);
        
        ///// next row ///// JCheckBox
        // 0, 4
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1;                                                           // Relative height of cell
        gc.anchor = GridBagConstraints.FIRST_LINE_END;                                    // Align cell right
        gc.insets = new Insets(2, 0, 0, 5);                                         // Add spacing between label and field
        add(new JLabel("US Citizen:"), gc);
        
        // 1, 4
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 0.1;                                                           // Reative height, bigger cell
        gc.anchor = GridBagConstraints.FIRST_LINE_START;                            // Top right of cell
        gc.insets = new Insets(0, 0, 0, 0);                                        // Reset
        add(citizenCheck, gc);
        
        ///// next row ///// Social
        // 0, 4
        gc.gridy++;
        gc.gridx = 0;
        gc.weightx = 1;                                                             // Relative width of cell
        gc.weighty = 0.1;                                                           // Relative height of cell
        gc.anchor = GridBagConstraints.FIRST_LINE_END;                                    // Align cell right
        gc.insets = new Insets(2, 0, 0, 5);                                         // Add spacing between label and field
        add(taxLabel, gc);
        
        // 1, 4
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 0.1;                                                           // Reative height, bigger cell
        gc.anchor = GridBagConstraints.FIRST_LINE_START;                            // Top right of cell
        gc.insets = new Insets(0, 0, 0, 0);                                        // Reset
        add(taxField, gc);       
        
        ///// next row ///// (OK)
        // 1, 4
        gc.gridy++;
        gc.gridx = 1;
        gc.weightx = 1;
        gc.weighty = 2.0;                                                           // Reative height, bigger cell
        gc.anchor = GridBagConstraints.LINE_START;                            // Top right of cell
//        gc.anchor = GridBagConstraints.LAST_LINE_START;                             // Bottom right of cell
        gc.insets = new Insets(0, 0, 0, 0);                                        // Reset
        add(okBtn, gc);
    }
    
    public void setFormListener(FormListener listener) {
        
        this.formListener = listener;
    }
    
    class AgeCategory {

        private int id;
        private String text;
        
        public AgeCategory(int id, String text) {

            this.id = id;
            this.text = text;
        }
        
        public String toString() {
            
            return text;
        }
        
        public int getId() {
            
            return id;
        }
    }
}
