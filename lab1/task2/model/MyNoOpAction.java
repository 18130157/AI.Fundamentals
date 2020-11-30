package task2.model;

public class MyNoOpAction extends MyAction {
	@Override
	protected boolean isNoOp() {
		return true;
	}
}
