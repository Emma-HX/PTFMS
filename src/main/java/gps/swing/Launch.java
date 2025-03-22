package gps.swing;

import gps.swing.model.BusSimulationModel;
import gps.swing.presenter.MainPresenter;
import gps.swing.presenter.MainPresenterImpl;
import gps.service.GPSDataService;
import gps.service.RouteService;
import gps.service.impl.GPSDataServiceImpl;
import gps.service.impl.RouteServiceImpl;

import javax.swing.*;

public class Launch {
    public static void main(String[] args) {
        // 使用SwingUtilities确保在EDT线程中运行UI代码
        SwingUtilities.invokeLater(() -> {
            try {
                // initial service object
                RouteService routeService = new RouteServiceImpl();
                GPSDataService gpsDataService = new GPSDataServiceImpl();
                
                // initial model object
                BusSimulationModel model = new BusSimulationModel(gpsDataService, routeService);
                model.setVehicleId(1);
                // initial view object
                MainFrame mainFrame = new MainFrame();
                
                // create view object
                MainPresenter presenter = new MainPresenterImpl(mainFrame, model);
                
                // initial presenter
                presenter.initialize();
                
                // show the frame
                mainFrame.setTitle("PTFMS GPS SIMULATION SYSTEM");
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.pack();
                mainFrame.setLocationRelativeTo(null); 
                mainFrame.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, 
                    "Start system error" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        });
    }
} 