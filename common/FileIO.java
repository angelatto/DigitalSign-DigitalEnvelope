package common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

// 파일 생성, 파일 읽기
public class FileIO {
	public String makeFile(byte[] data, String fileName) throws IOException {
	    // byte[]를 저장해 파일 만들기
		
		try {
			if (data == null || fileName == null) {
				throw new MakeFileException();
			}
			
		    Path path = (new File(fileName)).toPath(); 
			Files.write(path, data);
		} catch (MakeFileException e) {
			e.printStackTrace();
		}
		
		return fileName;
	}
	public byte[] readFile(String fileName) throws IOException {
	    // 파일에 있는 byte[] 읽기
		Path path = (new File(fileName)).toPath(); 
		byte[] rslt = Files.readAllBytes(path);
		
		return rslt;
	}
}
