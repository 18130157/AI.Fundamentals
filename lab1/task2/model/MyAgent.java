package task2.model;

public class MyAgent {
	private MyAgentProgram program;

	public MyAgent() {
	}

	public MyAgent(MyAgentProgram program) {
		this.program = program;
	}

	public MyAction execute(MyPercept p) {
		if (program != null) 
			return program.execute(p);
		return MyAction.NO_OP;
	}

}
