package com.baidu.ee.msg.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.SimpleFormatter;

import com.baidu.ee.msg.IMsg;
/**
 * 消息服务器的抽象父类
 * @author Jord
 *
 */
public abstract class BaseMsgServer{
	
	/**
	 * 每分钟发送最大消息次数,默认30
	 */
	private static int m = 30;
	

	/**
	 * 时间间隔:1分钟=60000ms
	 */
	private static long ONE_MINUTE = 6000;

	public abstract boolean sendMsg(IMsg msg) throws InterruptedException ;
	
	
	/**
	 * 内部类<br>
	 * 用来处理消息的发送
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
		 * 发送开始时间
		 */
		private long startTime;
		/**
		 * 记录一分钟内消息发送次数
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
							System.out.println(Thread.currentThread().getName() + "准备发消息!");
							IMsg msg = queue.take();
							msg.sendMsg();
							sendTimes++;
							System.out.println(Thread.currentThread().getName() + "已经发消息了，" +"队列目前有" + queue.size() + "个数据!");
							System.out.println("发送次数:"+sendTimes+",当前时间:"+ dateToStr(new Date()));
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
		 * 验证是否可以发送消息
		 * @param endTime
		 * @param sendTimes
		 * @return
		 */
		private boolean checkSend(long endTime,long sendTimes){
			boolean flag = false;
			long currentTime = System.currentTimeMillis();//当前时间
			if(sendTimes<getM()){//
				if(currentTime>endTime){//超出一分钟
					this.sendTimes = 0;
					flag = true;
				}else if(currentTime>this.startTime)
					flag = true;
			}else{//>M
				if(currentTime <=endTime){//一分钟以内
					this.startTime = endTime;//下一分钟,才能发送
				}else{
					this.startTime = currentTime;//当前时间下一分钟,才能发送
				}
				this.sendTimes = 0;
			}
				
			return flag;
		}
		
	}
	/**
	* 日期转换成字符串
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
