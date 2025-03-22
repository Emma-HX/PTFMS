package gps.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import gps.dto.RouteDTO;
import gps.dto.StationDTO;
import java.util.List;
/**
 * create the bus panel
 * Professor: Reginald Dyer
 * section: CST8288 032
 * student ID: 041141819
 * @author Xue Han 
 * @version 0.0.01
*/
public class BusPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private BufferedImage busImage;
	private final JTextArea timeRecordArea;
	private final MainFrame mainFrame;
	
	// UI Constants
	private static final int MARGIN = 50;
	private static final int STATION_SPACING = 80;
	private static final int STATION_SIZE = 12;
	private static final int CURRENT_STATION_SIZE = 16;
	/**
	 * initial the bus panel
	 * @param mainFrame
	 */
	public BusPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		// Initialize time record area
		timeRecordArea = new JTextArea(10, 30);
		timeRecordArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(timeRecordArea);
		scrollPane.setPreferredSize(new Dimension(300, 150));
		add(scrollPane, BorderLayout.EAST);
		
		loadBusImage();
		
		// Timer to simulate bus moving
		new Timer(100, e -> mainFrame.onSimulationTick()).start();
	}
	/**
	 * load bus image
	 */
	private void loadBusImage() {
		try {
			busImage = ImageIO.read(new File("src/main/resources/image/bus.gif"));
		} catch (IOException e) {
			busImage = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = busImage.createGraphics();
			g.setColor(Color.BLUE);
			g.fillOval(0, 0, 30, 30);
			g.dispose();
		}
	}
	/**
	 * update time record
	 * @param record
	 */
	public void updateTimeRecord(String record) {
		timeRecordArea.setText(record);
		timeRecordArea.setCaretPosition(timeRecordArea.getDocument().getLength());
	}
	/**
	 * add a time record
	 * @param record
	 */
	public void appendTimeRecord(String record) {
		timeRecordArea.append(record);
		timeRecordArea.setCaretPosition(timeRecordArea.getDocument().getLength());
	}
	/**
	 * print time record at frame
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (mainFrame.getPresenter() == null) return;
		
		RouteDTO currentRoute = mainFrame.getPresenter().getCurrentRoute();
		List<StationDTO> currentPath = mainFrame.getPresenter().getCurrentPath();
		
		if (currentRoute == null || currentPath == null || currentPath.isEmpty()) {
			return;
		}

		int height = getHeight();
		int baseY = height / 2;  // in the middle of frame
		
		int totalWidth = STATION_SPACING * (currentPath.size() - 1);

		// Draw route line
		g.setColor(Color.BLACK);
		g.drawLine(MARGIN, baseY, MARGIN + totalWidth, baseY);

		// Draw stations
		for (int i = 0; i < currentPath.size(); i++) {
			StationDTO station = currentPath.get(i);
			int x = MARGIN + (i * STATION_SPACING);
			
			if (i == mainFrame.getPresenter().getCurrentStationIndex() && 
				mainFrame.getPresenter().isAtStation()) {
				g.setColor(Color.RED);
				g.fillOval(x - CURRENT_STATION_SIZE/2, 
						  baseY - CURRENT_STATION_SIZE/2, 
						  CURRENT_STATION_SIZE, 
						  CURRENT_STATION_SIZE);
			} else {
				g.setColor(Color.BLUE);
				g.fillOval(x - STATION_SIZE/2, 
						  baseY - STATION_SIZE/2, 
						  STATION_SIZE, 
						  STATION_SIZE);
			}

			// Draw station name
			g.setColor(Color.BLACK);
			int nameX = x - 20;
			int nameY = (i % 2 == 0) ? baseY - 20 : baseY + 30;
			g.drawString(station.getName(), nameX, nameY);
		}

		// Draw bus if moving
//		if (mainFrame.getPresenter().isMoving()) {
			drawBus(g, baseY);
//		}
	}
	/**
	 * draw bus on the frame
	 * @param g
	 * @param baseY
	 */
	private void drawBus(Graphics g, int baseY) {
		if (busImage == null) return;
		
		int currentIndex = mainFrame.getPresenter().getCurrentStationIndex();
		int progress = mainFrame.getPresenter().getBusProgress();
		boolean isReturnTrip = mainFrame.getPresenter().isReturnTrip();
		
		// Calculate bus position
		int currentX = MARGIN + (currentIndex * STATION_SPACING);
		int nextX;
		if (isReturnTrip) {
			nextX = currentX - STATION_SPACING;
		} else {
			nextX = currentX + STATION_SPACING;
		}
		
		int busX = currentX + (int)((nextX - currentX) * (progress / 100.0));
		
		// Draw bus image
		int iconWidth = 32;
		int iconHeight = 32;
		
		if (isReturnTrip) {
			// Flip bus image for return trip
			BufferedImage flippedImage = new BufferedImage(
				busImage.getWidth(), busImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = flippedImage.createGraphics();
			g2d.scale(-1, 1);
			g2d.drawImage(busImage, -busImage.getWidth(), 0, null);
			g2d.dispose();
			
			g.drawImage(flippedImage, 
					  busX - iconWidth / 2,
					  baseY - iconHeight - 5,
					  iconWidth, iconHeight,
					  this);
		} else {
			g.drawImage(busImage,
					  busX - iconWidth / 2,
					  baseY - iconHeight - 5,
					  iconWidth, iconHeight,
					  this);
		}
	}
} 