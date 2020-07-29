package digitalSign;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Scanner;

import common.*;
import digitalSign.DigitSign;

// 전자서명 생성을 테스트하는 클래스
public class TestSign {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("******************************");
		System.out.println("Make Digital Signature");
		System.out.println("******************************");
		
		DigitSign digitSign = new DigitSign();
		
		//파일 생성
		System.out.print("Enter Your File Name For Sign : ");
		String fileName = scan.next();
		
		FileOutputStream output = null;
		BufferedReader br = null;
		try {
			output = new FileOutputStream(fileName);
			System.out.print("Enter Your Data For Sign : ");
			br = new BufferedReader(new InputStreamReader(System.in));
			output.write(br.readLine().getBytes());
		
			System.out.print("Enter Your Signature File Name : ");
			String sigFile = scan.next();
			
			System.out.print("Enter Your Private Key File Name For Sign : ");
			String priFile = scan.next();
		
			FileIO fileTool = new FileIO();
			byte[] sign = digitSign.sign(fileName, sigFile, priFile);
			String makeFile = fileTool.makeFile(sign, sigFile);
			
			if(makeFile == null) {
				throw new MakeFileException();
			}
			
			System.out.println("******************************");
			System.out.println("Finish : " + makeFile);
			System.out.println("******************************");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			System.out.println("정상적인 파일 이름이 아닙니다.");
		} catch (NoSuchAlgorithmException | ClassNotFoundException | SignatureException e) {
			e.printStackTrace();
		} catch (MakeFileException e) {
			System.out.println(e.getMessage() + "는 없는 파일 입니다.");
		} finally {
			try {
				output.close();
				br.close();
				scan.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
