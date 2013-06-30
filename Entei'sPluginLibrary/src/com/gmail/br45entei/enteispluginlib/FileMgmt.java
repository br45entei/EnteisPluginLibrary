package com.gmail.br45entei.enteispluginlib;
// TODO try making a function that reads all the lines of a file (like readAllLines(Path path, Charset cs)
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

/**Hosts functions and methods that are used in <a href="http://enteisislandsurvival.no-ip.org/javadoc/enteischatmanager/com/gmail/br45entei/enteischatmanager/MainChatClass.html">MainChatClass.class</a>.
 * @since 0.1
 * @author <a href="http://enteisislandsurvival.no-ip.org/about/author.html">Brian_Entei</a>*/
public class FileMgmt {
	public static FileMgmt plugin;
	public static boolean WriteToFile(String filename, String message, boolean wipeOnWrite, String folder, String dataFolderName) {
		boolean writeSuccess = false;
		try {
			File dataFolder = FileMgmt.getPluginFolder(dataFolderName);
			if(!(dataFolder.exists())) {
				dataFolder.mkdir();
			}
			File newFolder = null;
			if(folder.equals("") == false) {
				newFolder = new File(dataFolder, folder);
				if(newFolder.exists() != true) {
					newFolder.mkdir();
				}
			} else {
				newFolder = dataFolder;
			}
			File saveTo = null;
			if(filename.contains(".")) {
				saveTo = new File(newFolder, filename);
			} else {
				saveTo = new File(newFolder, (filename + ".txt"));
			}
			if (!(saveTo.exists())) {
				saveTo.createNewFile();
			} else {
				if(wipeOnWrite == true) {
					//saveTo.delete(); This is a temporary workaround until I learn how to truncate the file rather than to delete it...
					PrintWriter writer = new PrintWriter(saveTo);writer.print("");writer.close();
				}
				saveTo.createNewFile();
			}
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(message);
			pw.flush();pw.close();
			writeSuccess = true;
		} catch (IOException e) {e.printStackTrace();/*WriteToFile("crash-reports", "--------------------------", false, "");WriteToFile("crash-reports", e.getMessage(), false, "");*/}// <--That makes an infinite loop of errors, because you are calling a function inside of itself...
		return writeSuccess;
	}
	public static String ReadFromFile(File filetoread, String dataFolderName) {
		//MainChatClass.broadcastMsg("Testing ReadFromFile() in /nick...", true, "Operators");
		FileInputStream fs = null;
		try {
			fs = new FileInputStream(filetoread);
			BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			String line = br.readLine();
			//MainChatClass.broadcastMsg("The result: " + line, true, "Operators");
			fs.close();
			return line;
		} catch (IOException x) {}
		if(fs != null) {
			try {
				fs.close();
			} catch (IOException e) {
				FileMgmt.LogCrash(e, "ReadFromFile()", "Unable to close resource; was it ever open?", false, dataFolderName);
			}
		}
		return "";
	}
	public static boolean LogCrash(Exception e, String functionName, String msg, boolean printStackTrace, String dataFolderName) {
		if(msg.equals(null) || msg == null) {
			msg = "";
		}
		WriteToFile("crash-reports.txt", "--------------------------", false, "", dataFolderName);
		WriteToFile("crash-reports.txt", "An error occurred when processing function '" + functionName + "'. Exception thrown:  \"" + e.getClass().getName() + "\"; Caused by: \"" + e.getMessage(), false, "", dataFolderName);
		WriteToFile("crash-reports.txt", "\"; Message sent when error occurred: \"" + msg + "\"; Please contact Brian_Entei at br45entei@gmail.com if you would like to personally tell him about this error, so that he may fix it!", false, "", dataFolderName);
		if(printStackTrace) {
			e.printStackTrace();
		}
		return true;
	}
	public static File getPluginFolder(String folderName) {
		File dataFolder = new File(folderName);
		if(!(dataFolder.exists())) {
			dataFolder.mkdir();
		}
		return dataFolder;
	}
	/*public static File getFile(String path, String FileName) {
		File getFile = null;
		if(FileName.equals("")) return getFile;
		if(path.equals("")) {
			getFile = new File(MainChatClass.dataFolderName, FileName);
		} else {
			getFile = new File(path, FileName);
		}
		if(!(getFile.exists())) {
			try {
				getFile.createNewFile();
			} catch (IOException e) {
				FileMgmt.LogCrash(e, "getFile(\"" + path + "\", \"" + FileName + "\")", "Failed to create new file \"" + FileName + "\" in the directory: \"" + path + "\"", true);
			}
		}
		return getFile;
	}*/
	public static void copy(InputStream in, File file, String dataFolderName) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while((len=in.read(buf))>0){
				out.write(buf,0,len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			LogCrash(e, "copy()", "Failed to copy from InputStream to file \"" + file.getName() + "\".", true, dataFolderName);
		}
	}

	public static String ReadFromFile(String filename, String folder, String dataFolderName, boolean loadIfNoFile) {
		String rtrn = "";
		try {
			File dataFolder = FileMgmt.getPluginFolder(dataFolderName);
			if(!(dataFolder.exists())) {
				dataFolder.mkdir();
			}
			File newFolder = null;
			if(folder.equals("") == false) {
				newFolder = new File(dataFolder, folder);
				if(newFolder.exists() != true) {
					newFolder.mkdir();
				}
			} else {
				newFolder = dataFolder;
			}
			File saveTo = null;
			if(filename.contains(".")) {
				saveTo = new File(newFolder, filename);
			} else {
				saveTo = new File(newFolder, (filename + ".txt"));
			}
			if(loadIfNoFile == true) {
				saveTo.createNewFile();
			} else {if(saveTo.exists() == false) {return null;}
				saveTo.createNewFile();
			}
			rtrn = ReadFromFile(saveTo, dataFolderName);
		} catch (IOException e) {LogCrash(e, "ReadFromFile(fileName, folder, dataFolderName)", "An error occurred when attempting to read the file. Check the crash-reports.txt file for more info.", true, dataFolderName);}// <--That makes an infinite loop of errors, because you are calling a function inside of itself...
		return rtrn;
	}

	public static String ReadFromFile(String filename, String folder, String dataFolderName) {
		String rtrn = "";
		try {
			File dataFolder = FileMgmt.getPluginFolder(dataFolderName);
			if(!(dataFolder.exists())) {
				dataFolder.mkdir();
			}
			File newFolder = null;
			if(folder.equals("") == false) {
				newFolder = new File(dataFolder, folder);
				if(newFolder.exists() != true) {
					newFolder.mkdir();
				}
			} else {
				newFolder = dataFolder;
			}
			File saveTo = null;
			if(filename.contains(".")) {
				saveTo = new File(newFolder, filename);
			} else {
				saveTo = new File(newFolder, (filename + ".txt"));
			}
			saveTo.createNewFile();
			rtrn = ReadFromFile(saveTo, dataFolderName);
		} catch (IOException e) {LogCrash(e, "ReadFromFile(fileName, folder, dataFolderName)", "An error occurred when attempting to read the file. Check the crash-reports.txt file for more info.", true, dataFolderName);}// <--That makes an infinite loop of errors, because you are calling a function inside of itself...
		return rtrn;
	}




	public static boolean logToFile(String filename, String message, String folder, String dataFolderName) {
		boolean writeSuccess = false;
		try {
			File dataFolder = FileMgmt.getPluginFolder(dataFolderName);
			if(!(dataFolder.exists())) {
				dataFolder.mkdir();
			}
			File newFolder = null;
			if(folder.equals("") == false) {
				newFolder = new File(dataFolder, folder);
				if(newFolder.exists() != true) {
					newFolder.mkdir();
				}
			} else {
				newFolder = dataFolder;
			}
			File saveTo = null;
			if(filename.contains(".") == false) {
				filename = filename + ".html";
			}
			saveTo = new File(newFolder, filename);
			saveTo.createNewFile();
			if(saveTo.length() >= 2500000) {//2.5 KiloBytes
				
				int num = 1;
				File newFile = new File(filename + "_" + num);
				while(newFile.exists()) {
					num++;
					newFile = new File(filename + "_" + num);
				}
				boolean success = saveTo.renameTo(newFile);
				if(!success) {/*File was not successfully renamed*/}
				saveTo = new File(newFolder, filename);
				saveTo.createNewFile();
			}
			FileWriter fw = new FileWriter(saveTo, true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(message);
			pw.flush();pw.close();
			writeSuccess = true;
		} catch (IOException e) {e.printStackTrace();/*WriteToFile("crash-reports", "--------------------------", false, "");WriteToFile("crash-reports", e.getMessage(), false, "");*/}// <--That makes an infinite loop of errors, because you are calling a function inside of itself...
		return writeSuccess;
	}







}