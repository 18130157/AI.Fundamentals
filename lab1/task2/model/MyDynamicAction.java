package task2.model;

public class MyDynamicAction extends MyAction {
	private String name;

	public MyDynamicAction(String name) {
		super();
		this.name = name;
	}

	@Override
	protected boolean isNoOp() {
		return false;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
