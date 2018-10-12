package com.pa.agentbank.drs.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.pa.agentbank.drs.vo.FileConfig;
import com.pa.agentbank.drs.vo.FilePath;
import com.pa.agentbank.drs.vo.FtpOpt;
import com.pa.agentbank.drs.vo.JobConfig;
import com.pa.agentbank.drs.vo.JobDetailConfig;

public class LoadJobConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(LoadJobConfig.class);
	
	public static List<JobConfig> LoadXMLConfig(){
		
		List<JobConfig> jobConfigList = null;
		
		try {
			jobConfigList = loadConfig();
			logger.info("Load job config success!");
		} catch (Exception e) {
			logger.error("Load"+e.getMessage());
		}
		
		return jobConfigList;
		
		
		
	}

	public static List<JobConfig> loadConfig() throws Exception{

		List<JobConfig> jobs = loadJobConfig();
		loadJobDetailConfig(jobs);
		
		return jobs;
	}
	
	
	
	
	

	private static void loadJobDetailConfig(List<JobConfig> jobs) throws ParserConfigurationException, SAXException, IOException {
		for (JobConfig jobConfig : jobs) {
			String jobDetailConfigPath = jobConfig.getJobDetailConfigPath();
			//根据jobs.xml中配置的jobDetail的路径加载配置文件
			InputStream inputStream = LoadJobConfig.class.getClassLoader()
					.getResourceAsStream(jobDetailConfigPath);
			/*InputStream inputStream = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(jobDetailConfigPath);*/
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(inputStream);
			
			
			NodeList jobNodeList = doc.getElementsByTagName("job");
			//TODO:必须字段check
			for (int i = 0; i < jobNodeList.getLength(); i++) {
				
				JobDetailConfig detail = new JobDetailConfig();
				Node jobNode = jobNodeList.item(i);
				NamedNodeMap attributes = jobNode.getAttributes();
				for (int j = 0; j < attributes.getLength(); j++) {
					Node item = attributes.item(j);
					if (item.getNodeName().equalsIgnoreCase("name")) {
						detail.setJobName(item.getNodeValue());
					}
				}
				
				detail.setHostIpAddr(doc.getElementsByTagName("hostIpAddr")
						.item(i).getFirstChild().getNodeValue());
				detail.setHostPort(Integer.valueOf(doc.getElementsByTagName("hostPort")
						.item(i).getFirstChild().getNodeValue()));
				
				
				//获取job强制结束时间
				Node jobForceEndTimeNode = doc.getElementsByTagName("jobForceEndTime").item(i);
				if (jobForceEndTimeNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) jobForceEndTimeNode;
					
					
					Node item = eElement.getElementsByTagName("endTime")
							.item(i);
					if (item!=null) {
						Node endTimeNode = item.getFirstChild();
						detail.setJobForceEndTime(endTimeNode.getNodeValue());
					}
				}
				
				//获取weekparam
				Node weekParam = doc.getElementsByTagName("weekparam").item(i);
				if (weekParam.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) weekParam;
					Node interval = eElement.getElementsByTagName("interval")
							.item(i).getFirstChild();
					detail.setInterval(interval.getNodeValue());
				}
				
				//获取ftp上文件路径
				Node hostDirNode = doc.getElementsByTagName("hostDir").item(i);
				if (hostDirNode.getNodeType() == Node.ELEMENT_NODE) {
					FilePath hostDir = new FilePath();
					Element eElement = (Element) hostDirNode;
					Node hostPathNode = eElement.getElementsByTagName("path")
							.item(i).getFirstChild();
					hostDir.setFilePath(hostPathNode.getNodeValue());
					
					detail.setHostDir(hostDir);
					
				}
				
				detail.setUserName(doc.getElementsByTagName("userName").item(i)
						.getFirstChild().getNodeValue());
				detail.setUserPassword(doc.getElementsByTagName("userPassword")
						.item(i).getFirstChild().getNodeValue());
				
				
				//获取hasOk,以确定是否生成.ok文件
				detail.setHasOk(doc.getElementsByTagName("hasOk").item(i)
						.getFirstChild().getNodeValue());
				
				
				Node localDirNode = doc.getElementsByTagName("localDir").item(i);
				if (localDirNode.getNodeType() == Node.ELEMENT_NODE) {
					FilePath localDir = new FilePath();
					Element eElement = (Element) localDirNode;
					Node localPathNode = eElement.getElementsByTagName("path")
							.item(i).getFirstChild();
					localDir.setFilePath(localPathNode.getNodeValue());
					
					detail.setLocalDir(localDir);
					
				}

				detail.setDateDir(doc.getElementsByTagName("dateDir").item(i).getFirstChild()
						.getNodeValue());
				
				detail.setListDir(doc.getElementsByTagName("listDir").item(i).getFirstChild()
						.getNodeValue());
				
				detail.setFtpOpt(FtpOpt.getByType(doc.getElementsByTagName("ftpOpt").item(i).getFirstChild()
						.getNodeValue()));
				
				/*生成需要下载的文件名称规则列表*/
				List<FileConfig> fileConfigList = new ArrayList<FileConfig>();
				//TODO point2
				Node fileListNode = doc.getElementsByTagName("fileList").item(i);
				fileListNode.getFirstChild();
				fileListNode.getChildNodes().item(0).getChildNodes().item(0);
				fileListNode.getChildNodes().item(1);
				NodeList fileNodes = fileListNode.getChildNodes();
				for (int j = 0; j < fileNodes.getLength(); j++) {
					//定义FileConfig对象
					FileConfig fileConfig = new FileConfig();
					
					Node fileNode = fileNodes.item(j);
					if (fileNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) fileNode;
						fileConfig.setFileType(Integer.valueOf(eElement.getElementsByTagName("fileType").item(0).getFirstChild().getNodeValue()));
						fileConfig.setFileNameFormat(eElement.getElementsByTagName("fileNameFormat").item(0).getFirstChild().getNodeValue());
						fileConfigList.add(fileConfig);
					}
						
				}
				
				detail.setFileList(fileConfigList);
				jobConfig.setJobDetailConfig(detail);	
			}		
		}
	}

	private static List<JobConfig> loadJobConfig() throws ParserConfigurationException, SAXException, IOException {
	
		List<JobConfig> jobs = new ArrayList<JobConfig>();
		
		//TODO point1
		InputStream resourceAsStream = ClassLoader.getSystemClassLoader()
				.getResourceAsStream("cfg/jobs.xml");
		DocumentBuilderFactory	factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(resourceAsStream);
		NodeList jobNodeList = doc.getElementsByTagName("job");
		
		for (int i = 0; i < jobNodeList.getLength(); i++) {
			
			String able = doc.getElementsByTagName("enable").item(i).getFirstChild().getNodeValue();
			
			if ("disable".equals(able)) {
				continue;
			}
			
			JobConfig job = new JobConfig();
			job.setName(doc.getElementsByTagName("name").item(i)
					.getFirstChild().getNodeValue());
			Node groupItem = doc.getElementsByTagName("group").item(i);
			if (groupItem.getNodeValue()!=null) {
				String groupValue = groupItem.getFirstChild().getNodeValue();
				job.setGroup(groupValue);
			}
			
			job.setCronExpression(doc.getElementsByTagName("cornExpression")
					.item(i).getFirstChild().getNodeValue());
			
			job.setJobClass(doc.getElementsByTagName("jobClass")
					.item(i).getFirstChild().getNodeValue());
			job.setJobDescription(doc.getElementsByTagName("jobDescription")
					.item(i).getFirstChild().getNodeValue());
			job.setJobDetailConfigPath(doc.getElementsByTagName("jobDetailPath")
					.item(i).getFirstChild().getNodeValue());
			
			jobs.add(job);
			
		}
		return jobs;
	}

}
