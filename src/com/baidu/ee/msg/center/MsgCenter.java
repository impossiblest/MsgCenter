package com.baidu.ee.msg.center;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.baidu.ee.msg.IMsg;
import com.baidu.ee.msg.factory.ServerFactory;
import com.baidu.ee.msg.server.IMsgServer;

public class MsgCenter {
	private static MsgCenter singleton = null;
	private MsgCenter() {}
	
	private BlockingQueue<IMsg> queue = new LinkedBlockingQueue<IMsg>();
	private Thread th = new Thread(new QueueThread());
	{
		//边存边取
		//设置次线程比主线程优先级低
		th.setPriority(Thread.MIN_PRIORITY);
		th.start();
	}
	public void send(IMsg msg) throws InterruptedException{
		queue.put(msg);
	}
	/**
	 * 单例
	 * @return
	 */
	public static MsgCenter getInstance(){
		if(singleton==null){
			synchronized(MsgCenter.class){
				if(singleton==null){
					singleton = new MsgCenter();
				}
			}
		}
		return singleton;
	}
	
	private class QueueThread implements Runnable{

		@Override
		public void run() {
			while(!queue.isEmpty()){
				try {
					Thread.sleep((long)Math.random()*1000);
					IMsg msg = queue.take();
					dispatch(msg);//调度,指派给相应的消息Server
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		/**
		 * 消息调度<br>
		 * 解析信息，指派给相应的消息Server
		 * @param msg
		 * @throws InterruptedException 
		 */
		private void dispatch(IMsg msg) throws InterruptedException{
			String name = msg.getClass().getSimpleName();
			IMsgServer msgServer = ServerFactory.getInstance(name);
			msgServer.setM(3);
			msgServer.sendMsg(msg);		
		}
	}
}
