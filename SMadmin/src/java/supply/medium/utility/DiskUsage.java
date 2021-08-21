/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.utility;

import java.io.File;

/**
 *
 * @author Intel
 */
public class DiskUsage {

    public static long getFileFolderSize(File dir) {
		long size = 0;
		if (dir.isDirectory()) {
			for (File file : dir.listFiles()) {
				if (file.isFile()) {
					size += file.length();
				} else
					size += getFileFolderSize(file);
			}
		} else if (dir.isFile()) {
			size += dir.length();
		}
		return size;
	}

}
