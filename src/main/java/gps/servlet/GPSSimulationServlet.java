package gps.servlet;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gps.dto.UserVehicleDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import gps.service.GPSDataService;
import gps.service.RouteService;
import gps.service.impl.GPSDataServiceImpl;
import gps.service.impl.RouteServiceImpl;
import gps.service.impl.UserVehicleServiceImpl;
import gps.swing.MainFrame;
import gps.swing.presenter.MainPresenter;
import gps.swing.presenter.MainPresenterImpl;
import gps.swing.model.BusSimulationModel;
import gps.service.UserVehicleService;
/**
 * This Servlet will invoking the GPS system
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class GPSSimulationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException,IOException{
        
        // 获取参数
        String userId = request.getParameter("userId");
        
        
        if (userId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
            return;
        }
        UserVehicleService uvService = new UserVehicleServiceImpl();
        List<UserVehicleDTO> uvList = uvService.getVehiclesByUser(Integer.parseInt(userId));
        UserVehicleDTO uvDTO = uvList.get(0);
        int vehicleId = uvDTO.getVehicleId();

        SwingUtilities.invokeLater(() -> {
            try {
                // initial service object
                RouteService routeService = new RouteServiceImpl();
                GPSDataService gpsDataService = new GPSDataServiceImpl();
                
                // initial model object
                BusSimulationModel model = new BusSimulationModel(gpsDataService, routeService);
                model.setVehicleId(vehicleId);
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
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
} 