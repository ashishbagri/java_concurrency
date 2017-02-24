package java_concurrency;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Develop a program that establish the name and priority for 10 threads and
 * then shows info about their status until they finish. The threads will
 * calculate the multiplication table of a number.
 * 
 * @author PaRV
 *
 */
public class PriorityThreadMain {

	public static void main(String[] args) throws IOException {
		Thread[] threads = new Thread[11];
		Thread.State[] states = new Thread.State[11];
		
		FileWriter fileWriter = new FileWriter("./logs.txt");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		for(int i=1;i<=10;i++){
			MultiplicationThread multiplicationThread = new MultiplicationThread(i);
			threads[i] = new Thread(multiplicationThread);
			threads[i].setName(String.format("Thread[%s]", i));
			threads[i].setPriority(i%2==0?Thread.MAX_PRIORITY:Thread.MIN_PRIORITY);
			states[i] = threads[i].getState();
		}
		for(int i =1;i<=10;i++){
			threads[i].start();
		}
		
		boolean finish = false;
		
		while(!finish){
			for(int i = 1 ; i<=10;i++){
				if(threads[i].getState()!=states[i]){
					writeOnLos(printWriter, threads[i], states[i]);
					states[i]  = threads[i].getState();
				}
			}
			finish = true;
			for(int i = 1;i<=10;i++){
				finish = finish && threads[i].getState()==Thread.State.TERMINATED;
			}
		}
		printWriter.flush();
		printWriter.close();
	}

	 private static void writeOnLos(PrintWriter pw, Thread
			 thread, Thread.State state) {
			 pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
			 pw.printf("Main : Priority: %d\n",thread.getPriority());
			 pw.printf("Main : Old State: %s\n",state);
			 pw.printf("Main : New State: %s\n",thread.getState());
			 pw.printf("Main : ************************************\n");
			  }
}