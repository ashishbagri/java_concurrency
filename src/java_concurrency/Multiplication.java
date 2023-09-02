package java_concurrency;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * A Simple Program that creates and runs 10 threads. Each thread calculates and
 * prints the multiplication table of a number 1 - 10;
 * 
 * @author PaRV
 *
 */
public class Multiplication {

	public static void main(String[] args) {
		/*for (int i = 1; i <= 10; i++) {
			new Thread(new MultiplicationThread(i)).start();
		}*/
		new Multiplication().reverseBits(43261596);
	}


	public int reverseBits(int n) {
		int result=0;
		while(n!=0){
			int bit = n&1;
			result|=bit;
			n=n>>>1;
			result=result<<1;
		}
		return result;
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

	public int[] sortByBits(int[] arr) {
		Integer[] sortedArr = new Integer[arr.length];
		Arrays.sort(arr);
		for(int i =0;i<arr.length;i++)
			sortedArr[i] = arr[i];
		Arrays.sort(sortedArr, (a,b) -> getNumberOfBits(a) - getNumberOfBits(b));
		for(int i =0;i<arr.length;i++)
			arr[i] = sortedArr[i];
		return arr;
	}

	private int getNumberOfBits(int n){
		int r = 0;
		while(n!=0){
			n&=(n-1);
			r++;
		}
		Integer.toBinaryString(n);
		return r;
	}

}
