package digitalEnvelope;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.SecretKey;

import common.SerialKey;

// AES 키를 생성하는 메인 클래스.
public class GenerateAESKey {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("******************************");
		System.out.println("Make AES Key Class");
		System.out.println("******************************");
		
		try {
			System.out.print("Enter Your Secret Key File Name : ");
			String secretFile = scan.next();
			
			MySecretKey mySecretKey = new MySecretKey();
			SecretKey secretKey = mySecretKey.generateKey();
			
			// 키 파일 만들기
			SerialKey serialKey = new SerialKey();
			serialKey.saveKey(secretKey, secretFile);
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			scan.close();			
		}
		
		System.out.println("******************************");
		System.out.println("Finish");
		System.out.println("******************************");
	}

}
