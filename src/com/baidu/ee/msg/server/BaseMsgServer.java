package com.baidu.ee.msg.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.SimpleFormatter;

import com.baidu.ee.msg.IMsg;
/**
 * ��Ϣ�������ĳ�����
 * @author Jord
 *
 */
public abstract class BaseMsgServer{
	
	/**
	 * ÿ���ӷ��������Ϣ����,Ĭ��30
	 */
	private static int m = 30;
	

	/**
	 * ʱ����:1����=60000ms
	 */
	private static long ONE_MINUTE = 6000;

	public abstract boolean sendMsg(IMsg msg) throws InterruptedException ;
	
	
	/**
	 * �ڲ���<br>
	 * ����������Ϣ�ķ���
	 * @author Jord
	 *
	 */
	protected static class QueueThread implements Runnable{
		private BlockingQueue<IMsg> queue;
		public QueueThread(){}
		public QueueThread(BlockingQueue<IMsg> queue) {
			this.queue = queue;
		}
		/**
		 * ���Ϳ�ʼʱ��
		 */
		private long startTime;
		/**
		 * ��¼һ��������Ϣ���ʹ���
		 */
		private int sendTimes = 0;
		
		@Override
		public void run() {
			this.startTime = System.currentTimeMillis();
			long endTime = 0;
			while(true){				
				try {
					endTime= this.startTime+ONE_MINUTE;
					while(checkSend(endTime,sendTimes)){
//						Thread.sleep((long) (Math.random()*1000));
						Thread.sleep(1000);
						synchronized(queue){
							System.out.println("***************************************************************************");
							System.out.println(Thread.currentThread().getName() + "׼������Ϣ!");
							IMsg msg = queue.take();
							msg.sendMsg();
							sendTimes++;
							System.out.println(Thread.currentThread().getName() + "�Ѿ�����Ϣ�ˣ�" +"����Ŀǰ��" + queue.size() + "������!");
							System.out.println("���ʹ���:"+sendTimes+",��ǰʱ��:"+ dateToStr(new Date()));
							System.out.println("***************************************************************************");
							System.out.println("\n\n");
						}
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
	
	private static int getM() {
		return m;
	}

	public void setM(int m) {
		BaseMsgServer.m = m;
	}
}
