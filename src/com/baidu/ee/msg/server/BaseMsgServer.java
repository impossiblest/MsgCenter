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
 * ��Ϣ�������ĳ�����<br>
 * <p>���յ�<��Ϣ����>���Ⱥ�,<br>
 * һ�ߴ洢ָ�����͵���Ϣ,���ݷ���������Ҫ��<br>
 * ������Ҫ��ʱ,�Դ���ʱ��Ϊ2s/��,������Ϣ.
 * @author Jord
 *
 */
public abstract class BaseMsgServer{
	//��¼log
	private Logger log = Logger.getLogger("Server");
	/**
	 * ÿ���ӷ��������Ϣ����,Ĭ��20
	 */
	private int sendNumPerMin = 20;
	/**
	 * ������Ϣ����
	 */	
	protected  BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	private static Map<String,Thread> threadMap = new ConcurrentHashMap<>();

	/**
	 * ʱ����:1����=60000ms
	 */
	private static long ONE_MINUTE = 60000;

	public abstract boolean sendMsg(IMsg msg) throws InterruptedException ;
	
	protected void startThread(IMsg msg){
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
	 * �ڲ���<br>
	 * ����������Ϣ�ķ���
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
						Thread.sleep(2000);//ģ�ⷢ����Ϣ����ʱ��
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
					
				} catch (InterruptedException e) {
					log.warning(e.getMessage());
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
			if(sendTimes<getSendNumPerMin()){//
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
