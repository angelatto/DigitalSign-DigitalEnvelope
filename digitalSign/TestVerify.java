package digitalSign;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import digitalSign.DigitSign;

public class TestVerify {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		System.out.println("******************************");
		System.out.println("Verify Digital Signature");
		System.out.println("******************************");
		
		System.out.print("Enter File Name For Verify : ");
		String fileName = scan.next();
		
		System.out.print("Enter Signature File Name For Verify : ");
		String sigFile = scan.next();
		
		System.out.print("Enter Public Key File Name For Verify : ");
		String pubFile = scan.next();
	
		try {
			
			DigitSign digitSign = new DigitSign();
			boolean rslt = digitSign.verify(fileName, sigFile, pubFile);
			
			System.out.println("******************************");
			if(rslt) {
				System.out.println("Verify Success !!");
			} else {
				System.out.println("Verify Failed !!");
			}
		} catch (InvalidKeyException | SignatureException | ClassNotFoundException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			e.printStackTrace();
		} finally {
			scan.close();
		}
		
		System.out.println("Finish");
		System.out.println("******************************");
	}

}
