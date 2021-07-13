package SynchronizedMethodsandStatements;

public class TestSyn {
	public static void main(String[] args) {
		final SynObj obj = new SynObj();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				obj.methodA();
//				obj.methodB();
//				obj.methodC();
			}
		});
		t1.start();

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
//				obj.methodA();
				obj.methodB();
//				obj.methodC();
			}
		});
		t2.start();

		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
//				obj.methodA();
//				obj.methodB();
				obj.methodC();
			}
		});
		t3.start();
	}
}

class SynObj {
	public synchronized void methodA() {
		System.out.println("methodA starting .....");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("methodA ending .....");
	}

	public void methodB() {
		synchronized (this) {
			System.out.println("methodB starting.....");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("methodB ending .....");
		}
	}

	public void methodC() {
		String str = "sss";
		synchronized (str) {
			System.out.println("methodC starting.....");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("methodC ending .....");
		}
	}
}