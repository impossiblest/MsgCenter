package com.baidu.ee.msg.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.baidu.ee.msg.IMsg;
/**
 * Email��Ϣ������
 * @author Jord
 *
 */
public class EmailMsgServer implements IMsgServer{
	
	/**
	 * ÿ���ӷ���������,getter,setter
	 */
	private static int m = 3;
	

	/**
	 * ʱ����:1����=60000ms
	 */
	private static long ONE_MINUTE = 6000;
	/**
	 * ������Ϣ����
	 */	
	private static BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	private static Thread th = new Thread(new QueueThread());
	static{
		//2.check
		//3.����--����Ϣ
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}
	
	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.����
		queue.put(msg);
		return false;
	}
	
	
	
	private static class QueueThread implements Runnable{
		
		private long startTime;
		private int sendTimes = 0;
		
		@Override
		public void run() {
			this.startTime = System.currentTimeMillis();
			while(true){				
				
				try {
					long endTime = this.startTime+ONE_MINUTE;
					while(checkSend(endTime,sendTimes)){
//						Thread.sleep((long) (Math.random()*100));
						Thread.sleep(1500);
						System.out.println("***************************************************************************");
						System.out.println(Thread.currentThread().getName() + "׼������Ϣ!");
						IMsg msg = queue.take();
						msg.sendMsg();
						sendTimes++;
						System.out.println(Thread.currentThread().getName() + "�Ѿ�����Ϣ�ˣ�" +"����Ŀǰ��" + queue.size() + "������");
						System.out.println("���ʹ���:"+sendTimes+",��ǰʱ��:"+ dateToStr(new Date()));
						System.out.println("***************************************************************************");
						System.out.println("\n\n");
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		/**
		 * ��֤�Ƿ���Է�����Ϣ
		 * @param endTime
		 * @param sendTimes
		 * @return
		 */
		private boolean checkSend(long endTime,long sendTimes){
			boolean flag = false;
			long currentTime = System.currentTimeMillis();//��ǰʱ��
			if(sendTimes<getM()){//
				if(currentTime>endTime){//����һ����
					this.sendTimes = 0;
					flag = true;
				}else if(currentTime>this.startTime)
					flag = true;
			}else{//>M
				
				if(currentTime <=endTime){//һ��������
					this.startTime = endTime;//��һ����,���ܷ���
				}else{
					this.startTime = currentTime;//��ǰʱ����һ����,���ܷ���
				}
				this.sendTimes = 0;
			}
				
			return flag;
		}
		
	}
	/**
	* ����ת�����ַ���
	* @param date 
	* @return str
	*/
	public static String dateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	} 
	public static int getM() {
		return m;
	}

	public void setM(int m) {
		EmailMsgServer.m = m;
	}
}
