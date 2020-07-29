package common;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

// RSA 키를 생성하는 메인 클래스.
public class GenerateRSAKey {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("******************************");
		System.out.println("Make RSA Key Class");
		System.out.println("******************************");
		System.out.print("Enter Your Public Key File Name : ");
		String pubFile = scan.next();
		
		System.out.print("Enter Your Private Key File Name : ");
		String priFile = scan.next();
		
		MyKeyPair myKeyPair;
		SerialKey serialKey;
		try {
			myKeyPair = MyKeyPair.getInstance();
			myKeyPair.createKeys();
			
			// 키 파일 만들기
			serialKey = new SerialKey();
			serialKey.saveKey(myKeyPair.getPublicKey(), pubFile);
			serialKey.saveKey(myKeyPair.getPrivateKey(), priFile);
		} catch (NoSuchAlgorithmException e) {
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
