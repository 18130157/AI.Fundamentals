package task2.model;

public abstract class MyAction {
	public static final MyAction MOVE_UP = new MyDynamicAction("UP");
	public static final MyAction MOVE_DOWN = new MyDynamicAction("DOWN");
	public static final MyAction MOVE_LEFT = new MyDynamicAction("LEFT");
	public static final MyAction MOVE_RIGHT = new MyDynamicAction("RIGHT");
	public static final MyAction SUCK_DIRT = new MyDynamicAction("SUCK");
	public static final MyAction NO_OP = new MyNoOpAction();

	protected abstract boolean isNoOp();

	public MyAction() {
	}

}
