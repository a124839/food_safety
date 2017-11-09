package com.ichinait.food.service.system;

import com.ichinait.food.cache.InstrumentCache;
import com.ichinait.food.cache.ProjectCache;
import com.ichinait.food.cache.SampleCache;
import com.ichinait.food.queue.Calculator;
import com.ichinait.food.thread.UpdateCacheThread;
import com.ichinait.food.thread.UpdateStandardsExpiredThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Component
@Lazy(false)
public class SystemService implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger logger = LoggerFactory.getLogger(SystemService.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){
			logger.info("==============初始化系统===================");
			initCache();
            cleanCache();
			UpdateStandardsExpiredThread();
			//startWorker();
		}
	}
	
	/**
	 * 初始化系统缓存
	 * @description: 
	 * @author:zhangbo@ichinait.com
	 * @version 1.0
	 * @date 2016年3月17日 下午4:44:29
	 */
	private void initCache(){
		SampleCache.getInstance().update();
		InstrumentCache.getInstance().update();
		ProjectCache.getInstance().update();
	}

	private void cleanCache(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				logger.info("清理缓存线程启动...");
				UpdateCacheThread updateCacheThread = new UpdateCacheThread();
				Thread thread = new Thread(updateCacheThread);
				thread.start();
			}
		},1000,1800000);
	}


	/**
	 * 标准文件过期处理
	 * @author xiao 2016-4-12
	 */
	private void UpdateStandardsExpiredThread(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 01); //凌晨1点
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		Date date = calendar.getTime();
		//如果第一次执行定时任务的时间 小于当前的时间
		//此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。
		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				logger.info("标准文件过期处理开启线程...");
				UpdateStandardsExpiredThread standardsExpiredThread = new UpdateStandardsExpiredThread();
				Thread thread = new Thread(standardsExpiredThread);
				thread.start();
			}
		}, date, 86400000);
	}


	// 增加或减少天数
	private Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

	//启动线程
	public void startWorker(){
		Calculator.getInstance().start();
		Calculator.getInstance().countDown();
	}

}
