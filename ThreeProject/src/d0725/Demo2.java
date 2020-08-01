package d0725;

/**
 * join 
 * @author admin
 *
 */
public class Demo2 {
	public static void main(String[] args) {
		//匿名内部类创建线程
		Thread t1=new Thread("线程1") {
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		};
		
		//匿名内部类创建线程
				Thread t2=new Thread("------------线程2") {
					//类定义，匿名方式
					public void run() {
						for (int i = 0; i < 1000; i++) {
							System.out.println(Thread.currentThread().getName()+":"+i);
							//将t2线程 执行到 i=500时，join到t1中
							try {
								if(i==500) {
									System.out.println("============");
									t1.join();
								}
								
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				};
				t1.start();
				t2.start();
		
		
	};
	
	
}