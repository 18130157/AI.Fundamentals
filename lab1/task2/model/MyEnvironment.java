package task2.model;

import task2.view.MainFrame;

public class MyEnvironment {
	public static final byte MAX_N = 30;
	private static byte m = 10;
	private static byte n = 10;
	private MyEnvironmentState envState;
	public static boolean isDone = false;
	private MyAgent agent;

	public MyEnvironment(byte m, byte n) {
		MyEnvironment.m = m;
		MyEnvironment.n = n;
		envState = new MyEnvironmentState(m, n);
	}

	public void addAgent(MyAgent agent, Coordinate coor) {
		this.agent = agent;
		envState.setAgentLocation(coor);
	}

	public void addAgent(MyAgent agent, int row, int col) {
		this.agent = agent;
		envState.setAgentLocation(new Coordinate(row, col));
	}

	public MyEnvironmentState getCurrentState() {
		return this.envState;
	}

	public MyEnvironmentState executeAction(MyAction action) {
		if (action == MyAction.SUCK_DIRT) {
			MyEnvironmentState.setLocationState(envState.getAgentLocation(), MyLocationState.CLEAN);
//			System.out.println(MyAction.SUCK_DIRT + " AT " + envState.getAgentLocation());
		} else {
			envState.setAgentLocationAfter(action);
		}
		MainFrame.updateView(action);
		return envState;
	}

	public MyPercept getPerceptSeenBy() {
		return new MyPercept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
	}

	private MyAction anAction;

	public void step() {
		anAction = agent.execute(getPerceptSeenBy());
		MyEnvironmentState es = executeAction(anAction);
//		System.out.println(anAction);
		if (es.isClean())
			isDone = true;
	}

	public static int nSteps;
	public static long stepDelay = 200;

	public void stepUntilDone() {
		nSteps = 0;
		while (!isDone) {
//			System.out.println("step: " + nSteps++);
			step();
			try {
				Thread.sleep(stepDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		MainFrame.setText();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			try {
				Thread.sleep(stepDelay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static byte getM() {
		return m;
	}

	public void setM(byte m) {
		MyEnvironment.m = m;
		MyEnvironment.n = m;
		envState.random(m, m);
	}

	public static byte getN() {
		return n;
	}

	public void setN(byte n) {
		MyEnvironment.n = n;
		MyEnvironment.m = n;
		envState.random(n, n);
	}

	public void random() {
		envState.random(n, n);
	}
	
	public void restore() {
		envState.restore();
	}

}
