package com.pa.agentbank.drs.utils.platform;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.pa.agentbank.drs.job.ODSJob;
import com.pa.agentbank.drs.utils.common.CheckUtil;
import com.pa.agentbank.drs.utils.common.DateUtil;
import com.pa.agentbank.drs.utils.common.FileUtil;
import com.pa.agentbank.drs.utils.common.StringUtil;
import com.pa.agentbank.drs.vo.FileConfig;
import com.pa.agentbank.drs.vo.FileType;
import com.pa.agentbank.drs.vo.JobDetailConfig;

public class SFTPUtilImpl {

	
	private static final Logger logger = LoggerFactory.getLogger(SFTPUtilImpl.class);
	private ChannelSftp client;
	private JobDetailConfig config;
	
	public SFTPUtilImpl(JobDetailConfig config) throws Exception {
		// TODO Auto-generated constructor stub
		this.config = config;
		this.client = createFTPClient();
	}

	private ChannelSftp createFTPClient() throws Exception {
		
		JSch jsch = new JSch();
		
		Session session = null;
		
		try {
			session = jsch.getSession(config.getUserName(), 
					config.getHostIpAddr(), config.getHostPort());
			
			
			
			
		} catch (Exception e) {
			logger.error("Session created failed. hostip:"
					+ config.getHostIpAddr() + " hostport:"
					+ config.getHostPort() + " userName:"
					+ config.getUserName());
		
			logger.error(e.getMessage());
			throw e;
		}//根据用户名，主机IP，端口获取一个Session对象
		
		logger.info("Session created");
		if (!StringUtil.isEmpty(config.getUserPassword())) {
			session.setPassword(config.getUserPassword());
		}
		
		Properties sessionConfig = new Properties();
		sessionConfig.put("StrictHostKeyChecking", "no");
		session.setConfig(sessionConfig);//为Session对象设置properties
		
		try {
			session.setTimeout(60000*1000);
		} catch (JSchException e) {
			logger.error("Session set timeout failed. hostip:"
					+ config.getHostIpAddr() + " hostport:"
					+ config.getHostPort() + " userName:"
					+ config.getUserName());
			logger.error("ERROR", e);
			throw e;//为Session对象设置properties
		}

		try {
			session.connect();//通过Session建立连接
		} catch (JSchException e) {
			logger.error("Session connect failed. hostip:"
					+ config.getHostIpAddr() + " hostport:"
					+ config.getHostPort() + " userName:"
					+ config.getUserName());
			logger.error("ERROR", e);
			throw e;
		}
		
		logger.info("Session connected");
		logger.info("Opening Channel");
		
		Channel channel = null;
		try {
			channel = session.openChannel("sftp");
		} catch (JSchException e) {
			logger.error("Session open sftp Channel failed. hostip:"
					+ config.getHostIpAddr() + " hostport:"
					+ config.getHostPort() + " userName:"
					+ config.getUserName());
			logger.error("ERROR", e);
			throw e;
		}//打开SFTP通道
		
		
		try {
			channel.connect();
		} catch (JSchException e) {
			logger.error("Channel connect failed. hostip:"
					+ config.getHostIpAddr() + " hostport:"
					+ config.getHostPort() + " userName:"
					+ config.getUserName());
			logger.error("ERROR", e);
			throw e;
		}//建立SFTP通道的连接
		logger.info("Connected successfully to ftpHost = "
				+ config.getHostIpAddr() + "ftpPort="
				+ config.getHostPort() + ",as ftpUserName = "
				+ config.getUserName() + ", returning: "
				+ channel);
		
		return (ChannelSftp) channel;
	}
	
	public int downLoad() {
		Date current = DateUtil.parseByType("yyyyMMdd", DateUtil.fomatByType("yyyyMMdd", new Date()));
		Date pathDate = null;
		Properties prop = new Properties();
		InputStream stream = null;
		
		try {
			stream = ClassLoader
					.getSystemResourceAsStream("cfg/syscontrol.properties");
			prop.load(stream);
			pathDate = DateUtil.parseByType("yyyyMMdd", prop.getProperty("WillBeProcessedTime"));
		} catch (Exception e) {
			logger.error("control file date {} is illegal");
			pathDate = DateUtil.day(current,-1);
		} finally {
			try {
				if (stream!=null) {
					stream.close();
				}
			} catch (Exception ignored) {
			}
		}
	
		Date forceEndDate = null;
		if (!StringUtil.isEmpty(config.getJobForceEndTime())) {
			forceEndDate = DateUtil.getDateWithHMS(config.getJobForceEndTime());
		}
		Date endDate = current;
		logger.info("pathDate:"+pathDate.toString());
		logger.info("endDate:"+endDate.toString());
		logger.info("pathDate:"+pathDate + ",getTime:" + pathDate.getTime()
				+ "|endDate:" + endDate + ",getTime:" + endDate.getTime()
				);
	
		while(pathDate.before(endDate)) {
			Date date = new Date();
			if (forceEndDate!=null && forceEndDate.before(date)) {
				logger.error("The task reches the mandatory completion time. This task ends");
				//任务强制退出
				return 1;
			}
			
			logger.info("pathDate is "+pathDate);
			String remoteFilePath = this.buildDirWithDate(config.getHostDir()
					.getFilePath(),pathDate);
			logger.info("Current Remote File path:" + remoteFilePath);
			
			OutputStream outputStream = null;
			try {
				
				prop.setProperty("WillBeProcessedTime",
						DateUtil.fomatByType("yyyyMMdd", pathDate));
				outputStream = new FileOutputStream(ClassLoader
						.getSystemResource("cfg/syscontrol.properties")
						.getFile());
				prop.store(outputStream, "WillBeProcessedTime");
			} catch (Exception e) {
				logger.error("Store WillBeProcessedTime" + pathDate
						+ " exception: " + e.getMessage());
				logger.error("",e);
				return 2;
			} finally {
				try {
					if (outputStream!=null) {
						outputStream.close();
					}
				} catch (Exception ignored) {
					logger.error("",ignored);
				}
			}
			
			//进入目录
			try {
				client.cd(remoteFilePath);
			} catch (Exception e) {
				logger.error("Remote file path " + remoteFilePath
						+ " maybe not exist, job stop here.");
				logger.error("",e);
				return 3;
			}
			
			logger.info("ftp client already into the directory:"+remoteFilePath);
			
			String localFilePath = this.buildDirWithDate(config.getLocalDir().getFilePath(), pathDate);
			logger.info("Current local File Path" + localFilePath);
			FileUtil.createPaths(localFilePath);
			logger.info("Success Create local File Path:" + localFilePath);
			Vector<ChannelSftp.LsEntry> list = null;
			
			
			try {
				list = getFileList(pathDate,remoteFilePath);
			} catch (Exception e) {
				logger.error("Failed to get file list by path: " + remoteFilePath);
				logger.error("",e);
				return 3;
			}
			
			logger.info("According to the rule," + list.size()
					+ " files will be downloaded under this directory. "
					+ "directory path is " + remoteFilePath
					);
			
			try {
				downloadFiles(remoteFilePath,localFilePath,list);
			} catch (Exception e) {
				logger.error("FTP download failed, the remote file path:" + remoteFilePath + ":"
						+ "The local file path: " + localFilePath);
				logger.error("",e);

				return 4;
			}
			//当天+1天
			pathDate = DateUtil.day(pathDate, 1);
			
			
		}
		
		
		return 0;
	}

	private void downloadFiles(String remoteFilePath, String localFilePatch, Vector<LsEntry> list) throws SftpException {
		// TODO Auto-generated method stub
		for (ChannelSftp.LsEntry entry : list) {
			String fileName = entry.getFilename();
			logger.info(fileName);
			SftpATTRS attr = client.stat(fileName);
			
			if (attr.isDir()==true) {
				logger.info(config.getHostIpAddr() + ":" + config.getHostPort()
						+"|download|" + remoteFilePath + fileName
						+ " is directory!");
				continue;
			}
			client.get(fileName, localFilePatch+"/"+fileName);
			logger.info(config.getHostIpAddr() + ":" + config.getHostPort()
					+ "|download|" + remoteFilePath + "/" + fileName
					+ "download success");
		}
		
	}

	private Vector<LsEntry> getFileList(Date pathDate, String currentFilePath) throws Exception {
		// TODO Auto-generated method stub
		Vector<ChannelSftp.LsEntry> list = new Vector<ChannelSftp.LsEntry>();
		List<FileConfig> fileList = config.getFileList();
		for (int i = 0; i < fileList.size(); i++) {
			
			String hostFileName = "";
			FileConfig fileConfig = fileList.get(i);
			Vector ls = null;
			switch (FileType.geFileType(fileConfig.getFileType())) {
			case SPECIFY_FILE_NAME:
				hostFileName = fileConfig.getFileNameFormat();
				break;
			case DATE_FILE_NAME:
				
				hostFileName = this.buildNameWithDate(fileConfig.getFileNameFormat(),pathDate);
				logger.info("Host File Name is " + hostFileName);
				try {
					ls = client.ls(hostFileName);
				} catch (Exception e) {
					logger.error(" do not find the file: " + hostFileName +
							" on sfpt server in the path");
					throw e;
				}
				
				if (ls.isEmpty()) {
					logger.error(" do not find the file: " + hostFileName
							+ "on sfpt server in the path");
				}
				
				break;
			case REGULAR_FILE_NAME:
				hostFileName = fileConfig.getFileNameFormat();
				break;
			
				
			}
			
			list.addAll(ls);
		}
		
		if (!list.isEmpty()) {
			logger.info(config.getHostIpAddr() + ":" + config.getHostPort()
					+"|download|" + currentFilePath + ":"
					+ list.size() + " files found!"
					);
		}
		
		
		
		
		return list;
	}

	private String buildNameWithDate(String fileName, Date nameDate) {
		
		String[] fileNameSeg = fileName.split("[\\{.*\\}]");
		StringBuilder currentFileName = new StringBuilder();
		for (int i = 0; i < fileNameSeg.length;) {
			currentFileName.append(fileNameSeg[i]);
			currentFileName.append(DateUtil.fomatByType(fileNameSeg[i+1], nameDate));
			i = i+2;
		}
		return currentFileName.toString();
	}

	private String buildDirWithDate(String dir, Date pathDate) {
		// TODO Auto-generated method stub
		
		StringBuilder currentFilePath = new StringBuilder();
		String[] pathSeg = dir.split("[\\{.*\\}]");
		
		for (int i = 0; i < pathSeg.length;) {
			currentFilePath.append(pathSeg[i]);
			currentFilePath.append(DateUtil.fomatByType(pathSeg[i+1],pathDate));
			i = i+2;
		}
		return currentFilePath.toString();
	}

	public void destory() {
		// TODO Auto-generated method stub
		if (CheckUtil.valid(client)) {
			client.disconnect();
			logger.info("SFTP client destoryed.");
		}
		
	}
	
	public void putFile(File localFile,String hostDir,String uploadFileName) throws SftpException, IOException {
		
		client.cd(hostDir);
		OutputStream outputStream = client.put(uploadFileName);
		InputStream inputStream = new FileInputStream(localFile);
		
		byte[] bytes = new byte[1024];
		int i;
		while((i=inputStream.read(bytes))!=-1) {
			outputStream.write(bytes);
		}
		
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		
	}
	
	
	public void putEmpFile(String hostDir,String uploadFileName) throws SftpException, IOException {
		
		client.cd(hostDir);
		OutputStream outputStream = client.put(uploadFileName);
		
		byte[] bytes = new byte[0];
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
		
		
	}
	
	
	
}
