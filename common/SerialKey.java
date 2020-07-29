package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;

import common.SerialKey;

// 객제를 직렬화해서 파일에 저장.
public class SerialKey {
	
	// 객체 직렬화를 이용하여 키를 파일에 저장하는 메소드 (PublicKey)
	public void saveKey(Key key, String fileName) throws FileNotFoundException, IOException {
	    // 직렬화 데이터를 파일에 저장
		try (FileOutputStream output = new FileOutputStream(fileName, false)) {
			try (ObjectOutputStream ostream = new ObjectOutputStream(output)) {
				ostream.writeObject(key);
			}
		} catch (IOException e) {
	        e.printStackTrace();
		}
	}

	// 직렬화 된 데이터를 파일에서 불러와 다시 객체로 저장
	public Key restoreKey(String fileName) throws ClassNotFoundException, IOException {
		Key key = null;
		try (FileInputStream fis = new FileInputStream(fileName)) {
			try (ObjectInputStream ois = new ObjectInputStream(fis)) {
				Object obj = ois.readObject();
				key = (Key) obj;
			}
			fis.close();
        }catch (FileNotFoundException e) {
            e.getStackTrace();
        }catch(IOException e){
            e.getStackTrace();
        }
		return key;
	}
}
