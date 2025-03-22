package gps.swing.view;

import javax.swing.JFrame;
import gps.dto.RouteDTO;
import gps.swing.presenter.MainPresenter;

import java.util.List;
/**
 * 
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface MainView {
    void showRoutes(List<RouteDTO> routes);
    void updateBusStatus(String status);
    void updateTimeRecord(String record);
    void appendTimeRecord(String record);
    void refreshDisplay();
    JFrame getFrame();
    void setPresenter(MainPresenter presenter);
} 