package com.siebel.md5.soap.convert;

import java.security.MessageDigest;

import javax.jws.WebService;

@WebService(endpointInterface = "com.siebel.md5.soap.convert.Md5Converter",
        serviceName = "Conver2Md5",
        targetNamespace = "com.siebel.md5.soap.convert")
public class Md5ConverterImpl implements Md5Converter {

	@Override
	public String convertToMd5(String inputString) {
		try{
		byte[] bytesOfMessage = inputString.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < thedigest.length; i++)
	        sb.append(Integer.toString((thedigest[i] & 0xff) + 0x100, 16).substring(1));
		return sb.toString();
		}catch(Exception e){
			return null;
		}
	}

}
