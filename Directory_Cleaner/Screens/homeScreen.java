package Screens;

import Application.Constants;
import Application.Dimensions;
import Logic.DuplicateFile_Cleaner;
import Logic.EmptyFile_Cleaner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class homeScreen {
    public homeScreen() {
        initView();
    }

    private void initView() {
        JFrame windowTitle = new JFrame(Constants.windowScreenTitle);
        JLabel headerLabel = new JLabel();
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setText(Constants.homeScreenTitle);
        headerLabel.setForeground(Color.RED);
        headerLabel.setFont(new Font(Constants.homeScreenTitleFont, Font.BOLD, Constants.homeScreenTitleFontSize));
        headerLabel.setBounds(Dimensions.headerLabelBoundX, Dimensions.headerLabelBoundY, headerLabel.getPreferredSize().width, Dimensions.headerLabelHeight);

        JButton btnCleanDuplicateFile = new JButton(Constants.buttonCleanDuplicate);
        btnCleanDuplicateFile.setBounds(Dimensions.duplicateFileButtonBoundX, Dimensions.duplicateFileButtonBoundY, Dimensions.duplicateFileButtonWidth, Dimensions.duplicateFileButtonHeight);
        
        JButton btnCleanEmptyFile = new JButton(Constants.buttonCleanEmpty);
        btnCleanEmptyFile.setBounds(Dimensions.emptyFileButtonBoundX, Dimensions.emptyFileButtonBoundY, Dimensions.emptyFileButtonWidth, Dimensions.emptyFileButtonHeight);

        JLabel directoryInputLabel = new JLabel(Constants.inputLabel);
        directoryInputLabel.setBounds(Dimensions.directoryInputLabelBoundX, Dimensions.directoryInputLabelBoundY, Dimensions.directoryInputLabelWidth, Dimensions.directoryInputLabelHeight);

        JTextField directoryField = new JTextField();
        directoryField.setBounds(Dimensions.inputTextFieldBoundX, Dimensions.inputTextFieldBoundY, Dimensions.inputTextFieldWidth, Dimensions.inputTextFieldHeight);
        
        windowTitle.add(directoryInputLabel);
        windowTitle.add(btnCleanDuplicateFile);
        windowTitle.add(directoryField);
        windowTitle.add(btnCleanEmptyFile);
        windowTitle.add(headerLabel);

        windowTitle.setSize(Dimensions.windowTitleWidth, Dimensions.windowTitleHeight);
        windowTitle.setLayout(null);
        windowTitle.setVisible(true);
        windowTitle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btnCleanDuplicateFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                try {
                if (!new File(directoryField.getText()).isDirectory()) {
                JOptionPane.showMessageDialog(new JFrame(), "Directory does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                directoryField.setText("");
                directoryField.requestFocus();
                return;
                }
                new DuplicateFile_Cleaner(directoryField.getText());
                JOptionPane.showMessageDialog(new JFrame(), "Duplicate files removed successfully");
                directoryField.setText("");
                directoryField.requestFocus();
                windowTitle.setVisible(true);
                }catch (Exception e) {}
            }
        });

        btnCleanEmptyFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eobj) {
                try {
                if (!new File(directoryField.getText()).isDirectory()) {
                JOptionPane.showMessageDialog(new JFrame(), "Directory does not exist", "Error", JOptionPane.ERROR_MESSAGE);
                directoryField.setText("");
                directoryField.requestFocus();
                return;
                }
                new EmptyFile_Cleaner(directoryField.getText());
                JOptionPane.showMessageDialog(new JFrame(), "Empty files removed successfully");
                directoryField.setText("");
                directoryField.requestFocus();
                windowTitle.setVisible(true);
                }catch(Exception e){}
            }
        });
    }
}
