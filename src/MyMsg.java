import java.awt.Color;
import teachnet.view.renderer.Shape;

public class MyMsg {
	Color color;
	int rand;
	Shape shape = Shape.CIRCLE;
	int id;
	int forward = 0; // 0 for forward 1 for replies
	int roundNumber = 0;
	
	public MyMsg(int id, int forward, int roundNumber) {
		this.color = forward == 0 ? Color.BLUE : Color.GREEN;
		this.id = id;
		this.forward = forward;
		this.roundNumber = roundNumber;
		
	}

	public int getInt() {
		return rand;
	}
	
	public Color getColor() {
		return color;
	}

	public String toString() {
		return "#" + id;
	}

	public int getRand() {
		return rand;
	}

	public void setRand(int rand) {
		this.rand = rand;
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}
	
	
}