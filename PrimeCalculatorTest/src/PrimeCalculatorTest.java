class PrimeCalculator implements Runnable{
	private int lowerBound;
	private int upperBound;
	private int amount;
	private boolean hasBeenCalcualated;
	
	public PrimeCalculator(int lowerBound, int upperBound) {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		amount = 0;
		hasBeenCalcualated = false;
	}
	
	public void run() {
		int j = 2;
		for(int i = lowerBound; i < upperBound; ++i) {
			for(j = 2; j * j <= i; ++j) {
				if (i % j == 0) break;
			}
			if (i % j != 0) amount++;
		}
		hasBeenCalcualated = true;
	}
	
	public int getAmount() {
		if (hasBeenCalcualated) {
			return amount;
		}
		else return -1;
	}
}

public class PrimeCalculatorTest {
	public static void main(String[] args){
		int result = 0;
		PrimeCalculator pc1 = new PrimeCalculator(1, 100000);
		PrimeCalculator pc2 = new PrimeCalculator(100001, 200000);
		PrimeCalculator pc3 = new PrimeCalculator(200001, 300000);
		PrimeCalculator pc4 = new PrimeCalculator(300001, 400000);
		Thread t1 = new Thread(pc1);
		Thread t2 = new Thread(pc2);
		Thread t3 = new Thread(pc3);
		Thread t4 = new Thread(pc4);
		result = pc1.getAmount() + pc2.getAmount() + pc3.getAmount() + pc4.getAmount();
		System.out.println("The amount of prime number from 1 to 400000 is : " + String.valueOf(result));
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		try{
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		result = pc1.getAmount() + pc2.getAmount() + pc3.getAmount() + pc4.getAmount();
		System.out.println("The amount of prime number from 1 to 400000 is : " + String.valueOf(result));
	}
}