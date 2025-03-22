package gps.swing;

import javax.swing.*;
import java.awt.*;
import gps.dto.RouteDTO;
import gps.swing.view.MainView;
import gps.swing.presenter.MainPresenter;
import java.util.List;
/**
 * create the frame
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class MainFrame extends JFrame implements MainView {
	private static final long serialVersionUID = 1L;
	private final BusPanel busPanel;
	private final ControlPanel controlPanel;
	private MainPresenter presenter;

	public MainFrame() {
		setTitle("Public Transit Fleet Management Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		busPanel = new BusPanel(this);
		controlPanel = new ControlPanel(this);

		add(busPanel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
	}

	@Override
	public void showRoutes(List<RouteDTO> routes) {
		controlPanel.updateRouteList(routes);
	}

	@Override
	public void updateBusStatus(String status) {
		controlPanel.updateStatus(status);
	}

	@Override
	public void updateTimeRecord(String record) {
		busPanel.updateTimeRecord(record);
	}

	@Override
	public void refreshDisplay() {
		busPanel.repaint();
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public void setPresenter(MainPresenter presenter) {
		this.presenter = presenter;
	}

	// Methods to handle user actions
	public void onStartButtonClicked() {
		if (presenter != null) {
			presenter.onStart();
		}
	}

	public void onStopButtonClicked() {
		if (presenter != null) {
			presenter.onStop();
		}
	}

	public void onRouteSelected(RouteDTO route) {
		if (presenter != null) {
			presenter.onRouteSelected(route);
		}
	}

	public void onSimulationTick() {
		if (presenter != null) {
			presenter.onSimulationTick();
		}
	}

	public MainPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void appendTimeRecord(String record) {
		busPanel.appendTimeRecord(record);
	}
} 