package java_concurrency.interuption;

/**
 * Develop a program that creates a Thread and after 5 seconds will force its
 * finalization using the interruption mechnaism.
 * 
 * @author PaRV
 *
 */
public class InterruptingThreadMain {
	public static void main(String[] args) {
		PrimeGenerator primeGenerator = new PrimeGenerator();
		primeGenerator.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		primeGenerator.interrupt();
	}
}
