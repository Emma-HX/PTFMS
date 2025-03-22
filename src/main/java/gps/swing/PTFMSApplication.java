package gps.swing;

import gps.service.GPSDataService;
import gps.service.RouteService;
import gps.service.impl.GPSDataServiceImpl;
import gps.service.impl.RouteServiceImpl;
import gps.swing.model.BusSimulationModel;
import gps.swing.presenter.MainPresenter;
import gps.swing.presenter.MainPresenterImpl;
import gps.swing.view.MainView;

import javax.swing.*;

public class PTFMSApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize services
                GPSDataService gpsDataService = new GPSDataServiceImpl();
                RouteService routeService = new RouteServiceImpl();

                // Initialize MVP components
                MainView view = new MainFrame();
                BusSimulationModel model = new BusSimulationModel(gpsDataService, routeService);
                MainPresenter presenter = new MainPresenterImpl(view, model);

                // Initialize the application
                presenter.initialize();

                // Show the main window
                view.getFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Error starting application: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
} 