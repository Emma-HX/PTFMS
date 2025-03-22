package gps.swing.presenter;

import gps.swing.model.BusSimulationModel;
import gps.swing.view.MainView;
import gps.dto.RouteDTO;
import gps.dto.StationDTO;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 * MainPresenter's concrete class
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class MainPresenterImpl implements MainPresenter {
    private final MainView view;
    private final BusSimulationModel model;
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    public MainPresenterImpl(MainView view, BusSimulationModel model) {
        this.view = view;
        this.model = model;
        view.setPresenter(this);
    }
    /**
     * initialize the route info
     */
    @Override
    public void initialize() {
        try {
            view.showRoutes(model.getAllRoutes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * handle bus start event
     */
    @Override
    public void onStart() {
        if (model.getCurrentRoute() == null) return;
        
        // reset state at each new trip
        if (!model.isMoving() && model.getCurrentStationIndex() == 0 && model.isReturnTrip()) {
            model.resetState();
            handleStationArrival();
        }
        
        model.setMoving(true);
        model.updateSimulationTime();
        
        // start moving
        if (!model.isMovingBetweenStations()) {
            try {
                handleStationDeparture();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        view.updateBusStatus("Running");
        view.refreshDisplay();
    }
    /**
     * handle bus stop event
     */
    @Override
    public void onStop() {
        model.setMoving(false);
        if (model.isAtStation()) {
            view.appendTimeRecord(String.format("=== Stopped at station %s ===\n", 
                model.getCurrentPath().get(model.getCurrentStationIndex()).getName()));
        } else if (model.isMovingBetweenStations() && model.getCurrentStationIndex() < model.getCurrentPath().size() - 1) {
            StationDTO current = model.getCurrentPath().get(model.getCurrentStationIndex());
            StationDTO next = model.isReturnTrip() ? 
                model.getCurrentPath().get(model.getCurrentStationIndex() - 1) :
                model.getCurrentPath().get(model.getCurrentStationIndex() + 1);
            view.appendTimeRecord(String.format("=== Stopped between Station %s and Station %s ===\n",
                current.getName(), next.getName()));
        }        
        view.updateBusStatus("Stopped");
        view.refreshDisplay();
    }
    /**
     * handle change route event
     */
    @Override
    public void onRouteSelected(RouteDTO route) {
        model.setRoute(route);
        view.refreshDisplay();
    }
    /**
     * update bus current position
     */
    private void updateBusPosition() {
        if (!model.isMoving()) return;

        model.updateSimulationTime();
        int remainingSteps = model.getRemainingSteps();
        
        if (remainingSteps > 0) {
            model.setRemainingSteps(remainingSteps - 1);
            view.refreshDisplay();
        } else {
            // check if arrive destination
            if (!model.isReturnTrip() && model.getCurrentStationIndex() >= model.getCurrentPath().size() - 2) {
                model.setCurrentStationIndex(model.getCurrentPath().size() - 1);
                handleEndStation();
                return;
            } else if (model.isReturnTrip() && model.getCurrentStationIndex() <= 1) {
                model.setCurrentStationIndex(0);
                handleEndStation();
                return;
            }
            
            // update current station index
            if (!model.isReturnTrip()) {
                model.setCurrentStationIndex(model.getCurrentStationIndex() + 1);
            } else {
                model.setCurrentStationIndex(model.getCurrentStationIndex() - 1);
            }
            
            model.setMovingBetweenStations(false);
            model.setAtStation(true);
            model.setStationWaitTime(0);
            handleStationArrival();
        }
    }
    /**
     * update station wait
     */
    private void updateStationWait() {
        if (!model.isMoving()) return;

        model.incrementStationWaitTime();
        if (model.getStationWaitTime() >= 30) {
            try {
                handleStationDeparture();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * handle bus arrive station event
     */
    private void handleStationArrival() {
        model.updateSimulationTime();
        try {
            model.recordGPSData(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        String timeStr = model.getSimulationTime().format(TIME_FORMATTER);
        String stationName = model.getCurrentPath().get(model.getCurrentStationIndex()).getName();
        view.appendTimeRecord(String.format("%s - %s Station : Arrived\n", timeStr, stationName));
        
        model.setStationWaitTime(0);
        view.refreshDisplay();
    }
    /**
     * handle bus departure from a station
     * @throws SQLException
     */
    private void handleStationDeparture() throws SQLException {
    	if (!model.isMoving()) return;
        
        model.updateSimulationTime();
        model.recordGPSData(false);
        
        String timeStr = model.getSimulationTime().format(TIME_FORMATTER);
        String stationName = model.getCurrentPath().get(model.getCurrentStationIndex()).getName();
        view.appendTimeRecord(String.format("%s - %s Station : Departure\n", timeStr, stationName));
        
        model.setAtStation(false);
        model.setMovingBetweenStations(true);
        model.setRemainingSteps(BusSimulationModel.getTotalSteps());
        view.refreshDisplay();
    }
    /**
     * handle bus arrive the destination
     */
    private void handleEndStation() {
        if (!model.isReturnTrip()) {
            view.appendTimeRecord("=== Arrive destination ===\n");
            model.setReturnTrip(true);
            model.setMovingBetweenStations(false);
            model.setAtStation(true);
            model.setStationWaitTime(0);
            handleStationArrival();
        } else {
            view.appendTimeRecord("=== Complete one round, trip finished ===\n");
            try {
                model.recordGPSData(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            model.setMoving(false);
            model.setMovingBetweenStations(false);
            model.setAtStation(true);
            model.setStationWaitTime(0);
            model.setCurrentStationIndex(0);            
            view.updateBusStatus("Completed");
            view.refreshDisplay();
        }
    }

    // UI State Methods
    @Override
    public RouteDTO getCurrentRoute() {
        return model.getCurrentRoute();
    }

    @Override
    public List<StationDTO> getCurrentPath() {
        return model.getCurrentPath();
    }

    @Override
    public int getCurrentStationIndex() {
        return model.getCurrentStationIndex();
    }

    @Override
    public boolean isAtStation() {
        return model.isAtStation();
    }

    @Override
    public boolean isMoving() {
        return model.isMoving();
    }

    @Override
    public boolean isReturnTrip() {
        return model.isReturnTrip();
    }

    @Override
    public int getBusProgress() {
        if (!model.isMovingBetweenStations()) return 0;
        return (int)((BusSimulationModel.getTotalSteps() - model.getRemainingSteps()) * 100.0 / BusSimulationModel.getTotalSteps());
    }

    @Override
    public void onSimulationTick() {
        if (!model.isMoving()) return;
        
        model.updateSimulationTime();
        
        if (model.isAtStation()) {
            updateStationWait();
        } else if (model.isMovingBetweenStations()) {
            updateBusPosition();
        }
        
        view.refreshDisplay();
    }
	@Override
	public String getTripId() {		
		return model.getTripId();
	}
	@Override
	public int getVehicleId() {
		return model.getVehicleId();
	}
} 