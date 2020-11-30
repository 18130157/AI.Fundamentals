package task2.model;

public class MyPercept {
	private Coordinate agentLocation;
	private MyLocationState state;

	public MyPercept(Coordinate agentLocation, MyLocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}

	public MyPercept(byte row, byte col, MyLocationState state) {
		new MyPercept(new Coordinate(row, col), state);
	}

	public Coordinate getAgentLocation() {
		return agentLocation;
	}

	public MyLocationState getLocationState() {
		return state;
	}

}
