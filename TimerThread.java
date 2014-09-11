
public class TimerThread implements Runnable{
	int temp = 0;
	TimerThread(){
					Thread b = new Thread(this);
					b.start();
	}	
	public int getTemp(){
		return temp;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(100);
				temp += 100;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
