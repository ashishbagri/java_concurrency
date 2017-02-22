package java_concurrency;

/**
 * A Simple Program that creates and runs 10 threads. Each thread calculates and
 * prints the multiplication table of a number 1 - 10;
 * 
 * @author PaRV
 *
 */
public class Multiplication {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			new Thread(new MultiplicationThread(i)).start();
		}
	}
}

class MultiplicationThread implements Runnable {

	private int number;

	public MultiplicationThread(int n) {
		this.number = n;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.printf(" %s * %s = %s ", number, i, number * i);
			System.out.println();
		}
	}

}
