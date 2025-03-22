package gps.swing.presenter;

import gps.dto.RouteDTO;
import gps.dto.StationDTO;
import java.util.List;
/**
 * presenter of the GPS simulator swing
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public interface MainPresenter {
    void onStart();
    void onStop();
    void onRouteSelected(RouteDTO route);
    void onSimulationTick();
    void initialize();
    
    // Methods for UI state
    RouteDTO getCurrentRoute();
    List<StationDTO> getCurrentPath();
    int getCurrentStationIndex();
    boolean isAtStation();
    boolean isMoving();
    boolean isReturnTrip();
    int getBusProgress();  // return in 0-100
    String getTripId();
    int getVehicleId();
} 