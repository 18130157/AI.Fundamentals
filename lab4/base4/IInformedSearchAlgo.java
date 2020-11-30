package base4;


public interface IInformedSearchAlgo {
	Node execute(Node tree, String goal);

	Node execute(Node tree, String start, String goal);
}
