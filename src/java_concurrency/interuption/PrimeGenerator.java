package java_concurrency.interuption;

public class PrimeGenerator extends Thread{
	
	@Override
	public void run(){
		long number = 1l;
		while(true){
			if(isPrime(number)){
				System.out.printf("Number %s is a Prime.\n",number);
			}else{
				//System.out.printf("Number %s is not a prime.\n",number);
			}
			if(isInterrupted()){
				System.out.println("Thread is intrupted");
				return;
			}
			number++;
		}
	}

	private boolean isPrime(long number) {
		if(number<2){
			return true;
		}
		for(int i = 2;i*i<number;i++){
			if(number%i==0)
				return false;
		}
		return true;
	}
}
