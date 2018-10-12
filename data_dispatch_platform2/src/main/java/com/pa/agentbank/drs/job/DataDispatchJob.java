package com.pa.agentbank.drs.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pa.agentbank.drs.utils.platform.SFTPUtilImpl;
import com.pa.agentbank.drs.vo.JobDetailConfig;

public class DataDispatchJob implements Job{

	private static final Logger logger = LoggerFactory.getLogger(DataDispatchJob.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		//获取JobDetailConfig对象
		JobDetailConfig jobDetailConfig = (JobDetailConfig) context.getJobDetail().getJobDataMap().get("jobDetailConfig");
		
		logger.info("任务：" + jobDetailConfig.getJobName() + "开始执行");
		
		try {
			jobExe(jobDetailConfig);
			logger.info("任务：" + jobDetailConfig.getJobName() + "执行成功");
		} catch (Exception e) {
			logger.info("任务：" + jobDetailConfig.getJobName() + "执行失败，" + e.getMessage() +"5分钟后开始第一次重试");
			try {
				Thread.sleep(5*60*1000);
				jobExe(jobDetailConfig);
				logger.info("任务：" + jobDetailConfig.getJobName() + "执行成功");
			} catch (Exception e1) {
				logger.info("任务：" + jobDetailConfig.getJobName() + "执行失败，" + e.getMessage() +"10分钟后开始第二次重试");
				try {
					Thread.sleep(10*60*1000);
					jobExe(jobDetailConfig);
					logger.info("任务：" + jobDetailConfig.getJobName() + "执行成功");
				} catch (Exception e2) {
					logger.info("任务：" + jobDetailConfig.getJobName() + "执行失败，" + e.getMessage() +"15分钟后开始第三次重试");
					try {
						Thread.sleep(15*60*1000);
						jobExe(jobDetailConfig);
						logger.info("任务：" + jobDetailConfig.getJobName() + "执行成功");
					} catch (Exception e3) {
						logger.info("任务：" + jobDetailConfig.getJobName() + "三次重试，执行失败" + e.getMessage() +"请检查原因");
					}
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void jobExe(JobDetailConfig jobDetailConfig) throws Exception {
		
		//1.获取目标文件夹下的File对象数组
		File[] files = new File(jobDetailConfig.getLocalDir().getFilePath()).listFiles();
		
		//2.ftp上传
		logger.info(jobDetailConfig.getJobName() + "开始上传文件");
		SFTPUtilImpl sftpUtil = new SFTPUtilImpl(jobDetailConfig);
		String hostDir = jobDetailConfig.getHostDir().getFilePath();
		//上传前判断是否生成.ok文件
		if ("enable".equals(jobDetailConfig.getHasOk())) {
			for (File file : files) {
				sftpUtil.putFile(file, hostDir, file.getName());
				sftpUtil.putEmpFile(hostDir, file.getName()+".ok");
			}
		} else {
			for (File file : files) {
				sftpUtil.putFile(file, hostDir, file.getName());
			}
		}
		logger.info(jobDetailConfig.getJobName()+"文件上传成功");
		
		//3.关闭ftp连接
		sftpUtil.destory();
		
		//4.备份至目标文件夹(文件夹地址写死)
		logger.info("开始备份本地文件");
		for (File file : files) {
			String descFile = "E:/tmp/"+jobDetailConfig.getJobName();
			File tmpFile = new File(descFile);
			
			if (!tmpFile.exists()) {
				tmpFile.mkdirs();
			}
			
			if (!tmpFile.isDirectory()) {
				tmpFile.delete();
				tmpFile.mkdirs();
			}
			
			InputStream inputStream = null;
			OutputStream outputStream = null;
			inputStream = new FileInputStream(file);
			outputStream = new FileOutputStream(descFile+"/"+file.getName());
			
			byte[] bytes = new byte[1024];
			int num = 0;
			while ((num=inputStream.read(bytes))!=-1) {
				outputStream.write(bytes);
			}
			
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		}
		
		logger.info("文件备份本地结束");
		
		//5.删除本地文件
		logger.info("开始删除本地文件");
		for (File file : files) {
			file.delete();
		}
		logger.info("本地文件删除成功");
		
		
	}

}
