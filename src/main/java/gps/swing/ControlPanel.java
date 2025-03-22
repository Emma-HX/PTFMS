package gps.swing;

import javax.swing.*;
import java.awt.*;
import gps.dto.RouteDTO;
import java.util.List;
/**
 * create control panel, and add listener
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class ControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JComboBox<String> routeSelector;
	private JButton startButton;
	private JButton stopButton;
	private JButton reportButton;
	private List<RouteDTO> routes;
	private final MainFrame mainFrame;
	/**
	 * initial the panel
	 * @param mainFrame
	 */
	public ControlPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout());

		// Create buttons
		startButton = new JButton("Start");
		stopButton = new JButton("Stop");
		reportButton = new JButton("Report log");
		reportButton.setEnabled(false);
		stopButton.setEnabled(false);
		routeSelector = new JComboBox<>();

		// Add listeners
		routeSelector.addActionListener(e -> {
			int selectedIndex = routeSelector.getSelectedIndex();
			if (selectedIndex >= 0 && routes != null) {
				mainFrame.onRouteSelected(routes.get(selectedIndex));
			}
		});

		startButton.addActionListener(e -> {
			mainFrame.onStartButtonClicked();
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
			reportButton.setEnabled(false);
			routeSelector.setEnabled(false);
		});

		stopButton.addActionListener(e -> {
			mainFrame.onStopButtonClicked();
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			reportButton.setEnabled(true);
			routeSelector.setEnabled(false);
		});
		
		reportButton.addActionListener(e -> {
			ReportDialog dialog = new ReportDialog(mainFrame);
			dialog.setVisible(true);
			
			if (dialog.isConfirmed()) {
				String currentTime = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
				String record;
				if ("Break".equals(dialog.getSelectedType())) {
					record = String.format("[%s] Break: %s\n", currentTime, dialog.getBreakReason());
				} else {
					record = String.format("[%s] Out of Service\n", currentTime);
				}
				mainFrame.appendTimeRecord(record);
			}
		});

		// Add components
		add(new JLabel("Choose a Route: "));
		add(routeSelector);
		add(startButton);
		add(stopButton);
		add(reportButton);
	}
	/**
	 * update selected route
	 * @param routes
	 */
	public void updateRouteList(List<RouteDTO> routes) {
		this.routes = routes;
		routeSelector.removeAllItems();
		for (RouteDTO route : routes) {
			routeSelector.addItem(route.getRouteName());
		}
		
		// Set the first route as default if available
		if (!routes.isEmpty()) {
			mainFrame.onRouteSelected(routes.get(0));
		}
	}
	/**
	 * update the bus status
	 * @param status
	 */
	public void updateStatus(String status) {
		startButton.setEnabled("Stopped".equals(status) || "Ready".equals(status) || "Completed".equals(status));
		stopButton.setEnabled("Running".equals(status));
		routeSelector.setEnabled("Ready".equals(status) || "Completed".equals(status));
		reportButton.setEnabled(!"Completed".equals(status));
	}
} 