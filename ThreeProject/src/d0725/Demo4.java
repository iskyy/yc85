package d0725;

import java.util.ArrayList;
import java.util.List;

public class Demo4 {
	public static void main(String[] args) {
		ProducerConsumer pc=new ProducerConsumer();
		new Thread() {
			public void run() {
				try {
					pc.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		new Thread() {
			public void run() {
				try {
					pc.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
	}
	
}
class ProducerConsumer{
	private List<Integer>list =new ArrayList<>();
	/*
	 * 生产方法
	 */
	public synchronized void producer() throws InterruptedException {
		while(true) {
			if(list.isEmpty()) {
				for (int i = 0; i < 10; i++) {
					list.add(i);
					System.out.println("生成一个产品"+i);
					Thread.sleep(200);
				}
			}else {
				//如果集合list不为空则等待
				//通知所有等待的线程（消费线程）这一个停止等待 进入锁池状态 进行资源的竞争
				notifyAll();
				//当前线程等待
				wait();
			}
		}
	}
	
	/*
	 * 消费方法
	 */
	public synchronized void consumer() throws InterruptedException {
		while(true) {
			if(list.isEmpty()==false) {
				for (int i = 0; i < 10; i++) {
					list.remove(0);
					System.out.println("===消费了一个产品"+i);
					Thread.sleep(100);
				}
			}else {
				//如果集合list不为空则等待
				//通知所有等待的线程（生产线程）
				notifyAll();
				//当前线程等待（）
				wait();
			}
		}
	}

}
