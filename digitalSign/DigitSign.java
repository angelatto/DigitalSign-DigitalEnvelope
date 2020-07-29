package digitalSign;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import common.FileIO;
import common.SerialKey;

public class DigitSign {

	protected static final String signAlgorithm = "SHA1withRSA";
	private static SerialKey serialKeyImpl = new SerialKey();
	
	protected FileIO fileTool = new FileIO();
	
	public byte[] sign(String dataFileName, String signFileName, String keyFileName) 
			throws NoSuchAlgorithmException, IOException, ClassNotFoundException, SignatureException, InvalidKeyException {
		
		// Create an instance
		Signature signature = Signature.getInstance(signAlgorithm);
	
		// 공개 키 파일을 읽어 key 가져오기
		PrivateKey privateKey = (PrivateKey)serialKeyImpl.restoreKey(keyFileName);
		
		// Initialize the signer with private or public key
		signature.initSign(privateKey);
		
		byte[] datafile = fileTool.readFile(dataFileName);
		if (datafile == null) {
			System.out.println("DataFile not exist");
			return null;
		}
		
		// add data for verification
		signature.update(datafile);
		
		byte[] signatureData = signature.sign();
		return signatureData;
	}
	
	//Overloading
	public boolean verify(String dataFileName, String sigFileName, String keyFileName) 
			throws SignatureException, ClassNotFoundException, IOException, NoSuchAlgorithmException, 
					NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
		
		// Create an instance
		Signature signature = Signature.getInstance(signAlgorithm);
		
		// 공개 키 파일을 읽어 key 가져오기
		PublicKey publicKey = (PublicKey)serialKeyImpl.restoreKey(keyFileName);

		// Initialize the signer with private or public key
		signature.initVerify(publicKey);
		
		// Add data for signing
		byte[] data = fileTool.readFile(dataFileName);
		signature.update(data);
		
		// Verify the signature
		byte[] sigData = fileTool.readFile(sigFileName);
		return signature.verify(sigData);
	}
	
	//Overloading
	public boolean verify(String dataFileName, byte[] sigData, String keyFileName) 
			throws SignatureException, ClassNotFoundException, IOException, NoSuchAlgorithmException, 
					NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {	
		
		// Create an instance
		Signature signature = Signature.getInstance(signAlgorithm);
		
		// 공개 키 파일을 읽어 key 가져오기
		PublicKey publicKey = (PublicKey)serialKeyImpl.restoreKey(keyFileName);

		// Initialize the signer with private or public key
		signature.initVerify(publicKey);
		
		// Add data for signing
		byte[] data = fileTool.readFile(dataFileName);
		signature.update(data);
		
		// Verify the signature
		return signature.verify(sigData);
	}
}
