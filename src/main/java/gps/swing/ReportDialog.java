package gps.swing;

import javax.swing.*;

import gps.dto.ManualLogDTO;
import gps.dto.ManualLogDTOBuilder;
import gps.dto.StationDTO;
import gps.service.ManualLogService;
import gps.service.impl.ManualLogServiceImpl;

import java.awt.*;
import java.sql.SQLException;
/**
 * This program implement the manual log dialog
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class ReportDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JRadioButton breakRadio;
    private JRadioButton outOfServiceRadio;
    private JTextField breakTimeField;
    private JTextField breakReasonField;
    private JButton confirmButton;
    private JButton cancelButton;
    private boolean confirmed = false;
    private String selectedType;
    private int breakTime;
    private String breakReason;
    private final MainFrame mainFrame;

    public ReportDialog(JFrame parent) {
        super(parent, "Report Status", true);
        this.mainFrame = (MainFrame) parent;
        setLayout(new BorderLayout(10, 10));
        setSize(400, 200);
        setLocationRelativeTo(parent);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));        
        ButtonGroup group = new ButtonGroup();
        breakRadio = new JRadioButton("Break");
        outOfServiceRadio = new JRadioButton("Out of Service");
        group.add(breakRadio);
        group.add(outOfServiceRadio);
        radioPanel.add(breakRadio);
        radioPanel.add(outOfServiceRadio);
        add(radioPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 

        
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        timePanel.add(new JLabel("Break Time (mins)"));
        breakTimeField = new JTextField(10);
        breakTimeField.setEditable(false);
        timePanel.add(breakTimeField);
        centerPanel.add(timePanel, BorderLayout.CENTER);
        
        JPanel reasonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        reasonPanel.add(new JLabel("Reason:"));
        breakReasonField = new JTextField(20);  
        reasonPanel.add(breakReasonField);
        centerPanel.add(reasonPanel, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);

        breakRadio.addActionListener(e -> {
            breakTimeField.setEditable(true);
        });

        outOfServiceRadio.addActionListener(e -> {
        	breakTimeField.setEditable(false);
        });

        confirmButton.addActionListener(e -> {
        	if (breakRadio.isSelected() && breakTimeField.getText().trim().isEmpty()) {
        		JOptionPane.showMessageDialog(this, 
        				"Please enter break time",
        				"Input Error",
        				JOptionPane.ERROR_MESSAGE);
        	}
            if (breakReasonField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Please enter break or out of service reason", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (breakRadio.isSelected() && !isInteger(breakTimeField.getText().trim())) {
            	JOptionPane.showMessageDialog(this, 
        				"Break Time must be an integer",
        				"Input Error",
        				JOptionPane.ERROR_MESSAGE);
            }
            confirmed = true;
            selectedType = breakRadio.isSelected() ? "Break" : "Out of Service";
            if(breakRadio.isSelected()) {
            	breakTime = Integer.parseInt(breakTimeField.getText().trim());
            }            
            breakReason = breakReasonField.getText().trim();
            // insert data to database
            StationDTO currentStation = mainFrame.getPresenter().getCurrentPath().get(mainFrame.getPresenter().getCurrentStationIndex());
            ManualLogDTO logDTO = new ManualLogDTOBuilder()
            		.tripId(mainFrame.getPresenter().getTripId())
            		.vehicleId(mainFrame.getPresenter().getVehicleId())
            		.stationId(currentStation.getStationId())
            		.reportType(selectedType)
            		.breakTime(breakTime)
                        .reason(breakReason)
            		.build();
            
            ManualLogService mls = new ManualLogServiceImpl();
            try {
				mls.addManualLog(logDTO);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
            dispose();
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        getRootPane().setDefaultButton(confirmButton);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public String getSelectedType() {
        return selectedType;
    }
    
    public int getBreakTime() {
    	return breakTime;
    }
    
    public String getBreakReason() {
        return breakReason;
    }
    
    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str); 
            return true;  
        } catch (NumberFormatException e) {
            return false;  
        }
    }

} 
