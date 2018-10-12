package com.pa.agentbank.drs.utils.common;

import java.io.File;

public class FileUtil {

	public static boolean createPaths(String paths) {
		
		File dir = new File(paths);
		return !dir.exists()&&dir.mkdir();
	}

}
