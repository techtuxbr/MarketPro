package helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileHelper<T>{
	private File file;
	private String fileName;
	private FileInputStream fis;
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	
	public FileHelper(String filePath) {
		this.file = new File(filePath);
		fileName = filePath;
		if(!this.file.exists()) {
			try {
				this.file.createNewFile();
			}catch(IOException e) {	
				e.printStackTrace();
			}
		}
	}
	
	public void writeData(List<T>data) {
		if(this.file.canWrite() && this.file.isFile()) {
			try {
				fos = new FileOutputStream(this.file);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(data);
				oos.flush();
				fos.close();
				oos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<T> readData() {
		List<T> returnData = new ArrayList<T>();
		if(this.file.canRead() && this.file.isFile()) {
			try {
				fis = new FileInputStream(this.file);
				ois = new ObjectInputStream(fis);
				returnData = (List<T>)ois.readObject();
				fis.close();
				ois.close();
			}catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return returnData;	
	}	
}
