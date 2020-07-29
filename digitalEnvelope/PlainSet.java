package digitalEnvelope;

import java.io.Serializable;
import java.security.PublicKey;

public class PlainSet implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	private String dataFileName;
	private byte[] signature;
	private String pubFileName;
	
	// Constructor
	public PlainSet() {
	}
	
	public PlainSet(String dataFileName, byte[] signature, String pubFileName) {
		this.dataFileName = dataFileName;
		this.signature = signature;
		this.pubFileName = pubFileName;
	}
	
	// Setter & Getter
	public String getDataFileName() {
		return dataFileName;
	}
	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}
	public byte[] getSignature() {
		return signature;
	}
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}
	public String getPubFileName() {
		return pubFileName;
	}
	public void setPubFileName(String pubFileName) {
		this.pubFileName = pubFileName;
	}
}
