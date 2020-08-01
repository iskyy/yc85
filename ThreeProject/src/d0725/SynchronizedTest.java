package d0725;

public class SynchronizedTest {
	//修饰方法上 多线程调用同一个对象的同步方法会阻塞 (java对象的内存地址是否相同)
	public synchronized void show1() {
		int i=5;
		while(i-->0) {
			System.out.println(Thread.currentThread().getName()+":"+i);
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	//修饰代码块 this 指的是当前对象 多线程调用同一个对象的同步方法会阻塞，调用不同对象的同步方法不会阻塞
	public void show2() {
		synchronized (this) {
			int i=5;
			while(i-->0) {
				System.out.println(Thread.currentThread().getName()+":"+i);
				try {
					Thread.sleep(500);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}
	//修饰代码块str代表字符对象
	public void show3() {
		String str=new String("ok");
		synchronized (str) {
			int i=5;
			while(i-->0) {
				System.out.println(Thread.currentThread().getName()+":"+i);
				try {
					Thread.sleep(500);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	String str=new String("ok");
	public void show4() {
		synchronized (str) {
			int i=5;
			while(i-->0) {
				System.out.println(Thread.currentThread().getName()+":"+i);
				try {
					Thread.sleep(500);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	//修饰静态方法 类锁
	public static synchronized void show5() {
		int i=5;
		while(i-->0) {
			System.out.println(Thread.currentThread().getName()+":"+i);
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	//类锁
	public void show6() {
		synchronized (SynchronizedTest.class) {
			int i=5;
			while(i-->0) {
				System.out.println(Thread.currentThread().getName()+":"+i);
				try {
					Thread.sleep(500);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		SynchronizedTest s=new SynchronizedTest();
		SynchronizedTest s1=new SynchronizedTest();
		new Thread(new Runnable() {

			@Override
			public void run() {
				s.show1();
			}
			
		}).start();
		System.out.println("---------------");
		new Thread(new Runnable() {

			@Override
			public void run() {
				s1.show1();
			}
			
		}).start();
	}
}
