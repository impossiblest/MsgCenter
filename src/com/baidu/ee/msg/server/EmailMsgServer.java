package com.baidu.ee.msg.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.baidu.ee.msg.IMsg;
/**
 * Email消息服务器
 * @author Jord
 *
 */
public class EmailMsgServer implements IMsgServer{
	
	/**
	 * 每分钟发送最大次数,getter,setter
	 */
	private static int m = 3;
	

	/**
	 * 时间间隔:1分钟=60000ms
	 */
	private static long ONE_MINUTE = 6000;
	/**
	 * 接收消息队列
	 */	
	private static BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	private static Thread th = new Thread(new QueueThread());
	static{
		//2.check
		//3.出列--发消息
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}
	
	@Override
	public boolean sendMsg(IMsg msg) throws InterruptedException {
		//1.入列
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
						System.out.println(Thread.currentThread().getName() + "准备发消息!");
						IMsg msg = queue.take();
						msg.sendMsg();
						sendTimes++;
						System.out.println(Thread.currentThread().getName() + "已经发消息了，" +"队列目前有" + queue.size() + "个数据");
						System.out.println("发送次数:"+sendTimes+",当前时间:"+ dateToStr(new Date()));
						System.out.println("***************************************************************************");
						System.out.println("\n\n");
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
	public static int getM() {
		return m;
	}

	public void setM(int m) {
		EmailMsgServer.m = m;
	}
}
