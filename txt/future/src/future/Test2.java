package future;

public class Test2 {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					System.out.println("dddd");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		thread.start();
		synchronized (thread) {
			thread.wait();
			thread.notify();
		}
		System.out.println("end...");
	}
}

