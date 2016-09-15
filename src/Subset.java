

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
	public static void main(String[] args){
		if(args.length == 0) return;
		int size = Integer.valueOf(args[0]);
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		while(!StdIn.isEmpty()){
			rq.enqueue(StdIn.readString());
		}
		for(int i = 0; i < size; i++){
			StdOut.println(rq.dequeue());
		}

	}

}
