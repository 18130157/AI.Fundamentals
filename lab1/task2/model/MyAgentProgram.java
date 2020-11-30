package task2.model;

import java.util.Random;

public class MyAgentProgram {
	private Random generator = new Random();
	private int random;

	public MyAction execute(MyPercept p) {
		if(p.getLocationState() == MyLocationState.DIRTY)
			return MyAction.SUCK_DIRT;
		while (true) {
			random = generator.nextInt(4);
			switch (random) {
			case 0:
				if (MyEnvironmentState.canMove("UP"))
					return MyAction.MOVE_UP;

			case 1:
				if (MyEnvironmentState.canMove("DOWN"))
					return MyAction.MOVE_DOWN;
				
			case 2:
				if (MyEnvironmentState.canMove("LEFT"))
					return MyAction.MOVE_LEFT;
			case 3:
				if (MyEnvironmentState.canMove("RIGHT"))
					return MyAction.MOVE_RIGHT;
			}
			continue;
		}
	}

}
