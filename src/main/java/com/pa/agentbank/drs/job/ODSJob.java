package com.pa.agentbank.drs.job;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pa.agentbank.drs.constant.FTPConstant;
import com.pa.agentbank.drs.utils.platform.SFTPUtilImpl;
import com.pa.agentbank.drs.vo.JobDetailConfig;

public class ODSJob {
	
	private static final Logger logger = LoggerFactory.getLogger(ODSJob.class);
	
	public static void excute(JobExecutionContext context) {
		
		JobDetailConfig ftpTaskConfig = (JobDetailConfig) context.getJobDetail()
				.getJobDataMap().get("jobDetailConfig");
		
		if (FTPConstant.jobExecuteMap.get(ftpTaskConfig.getJobName())!=null
				&& FTPConstant.jobExecuteMap.get(ftpTaskConfig.getJobName()).equalsIgnoreCase("run")) {
			//2:该规则正在被执行中
			logger.error("Job is excuting,the job name is " + ftpTaskConfig.getJobName());
			return;
		}
		
		FTPConstant.jobExecuteMap.put(ftpTaskConfig.getJobName(), "run");
		logger.info("starting the FileOperation,the job name is " + ftpTaskConfig.getJobName());
		
		SFTPUtilImpl sftpUtilImpl = null;
		try {
			sftpUtilImpl = new SFTPUtilImpl(ftpTaskConfig);
		} catch (Exception e) {
			logger.error("FTP connect failed");
			logger.error("",e);
			FTPConstant.jobExecuteMap.remove(ftpTaskConfig.getJobName());
		}
		
		
		try {
			int returnCode = sftpUtilImpl.downLoad();
			if (returnCode != 0) {
				logger.error("FTP File download error, the error code is " + returnCode);
				return;
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("FTP download exception",e);
			return;
		} finally {
			try {
				FTPConstant.jobExecuteMap.remove(ftpTaskConfig.getJobName());
				sftpUtilImpl.destory();
			} catch (Exception e2) {
				FTPConstant.jobExecuteMap.remove(ftpTaskConfig.getJobName());
			}
		}
		
		logger.info("Success finished execut the FileOperationJob,the job name is "+ ftpTaskConfig.getJobName());
	}

}
