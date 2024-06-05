package ui;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Window;

import javax.swing.JFrame;

public class CommonUIData {
	//window's screen middle location
	public static int SCREEN_CENTER_X;
	public static int SCREEN_CENTER_Y;

	public static boolean subWindowOpened;

	public static void setScreenLocation(JFrame frame,
                                         int windowWidth,
                                         int windowHight) {
		GraphicsEnvironment ge
           = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Window window = new Window(frame);
		Point centerPoint = ge.getCenterPoint();

		SCREEN_CENTER_X = centerPoint.x - windowWidth / 2;
		SCREEN_CENTER_Y = centerPoint.y - windowHight / 2;
	}
}
