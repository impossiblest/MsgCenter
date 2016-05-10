package com.baidu.ee.msg.server;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import com.baidu.ee.msg.IMsg;
/**
 * 消息服务器的抽象父类<br>
 * <p>接收到<消息中心>调度后,<br>
 * 一边存储指定类型的消息,一边根据服务器限流要求<br>
 * 当满足要求时,以处理时间为2s/条,发送消息.
 * @author Jord
 *
 */
public abstract class BaseMsgServer{
	//记录log
	private Logger log = Logger.getLogger("Server");
	/**
	 * 每分钟发送最大消息次数,默认20
	 */
	private int sendNumPerMin = 20;
	/**
	 * 接收消息队列
	 */	
	protected  BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	private static Map<String,Thread> threadMap = new ConcurrentHashMap<>();

	/**
	 * 时间间隔:1分钟=60000ms
	 */
	private static long ONE_MINUTE = 60000;

	public abstract boolean sendMsg(IMsg msg) throws InterruptedException ;
	
	/**
	 * 根据消息类型启动相应的线程
	 * @param msg
	 */
	protected void startThreadByMsgType(IMsg msg){
		String msgType = msg.getMsgType();
		Thread thread = threadMap.get(msgType);
		if(thread==null){
			thread = new Thread(new QueueThread(queue));
			threadMap.put(msgType,thread);
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.start();
		}
	}
	/**
	 * 内部类<br>
	 * 用来处理消息的发送
	 * @author Jord
	 *
	 */
	protected class QueueThread implements Runnable{
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
						Thread.sleep(2000);//模拟发送消息所需时间
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
					
				} catch (InterruptedException e) {
					log.warning(e.getMessage());
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
			if(sendTimes<getSendNumPerMin()){//
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
	public String dateToStr(Date date) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String str = format.format(date);
	   return str;
	} 
	
	private int getSendNumPerMin() {
		return sendNumPerMin;
	}

	public void setSendNumPerMin(int sendNumPerMin) {
		this.sendNumPerMin = sendNumPerMin;
	}
}
