import javafx.scene.paint.Paint;

public class StationButton extends javafx.scene.shape.Circle {
	public int assignLine; 
	public int assignStation;
	
	public StationButton(double centerX, double centerY,  Paint fill) {
		super(centerX, centerY, 7, fill);
	}

}
