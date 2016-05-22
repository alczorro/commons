/*
 * Copyright (c) 2008-2016 Computer Network Information Center (CNIC), Chinese Academy of Sciences.
 * 
 * This file is part of Duckling project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *
 */
package cn.vlabs.duckling.common.transmission;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import cn.vlabs.duckling.common.crypto.impl.RSAKey;

import com.thoughtworks.xstream.XStream;

public class SignedEnvelope {
	public SignedEnvelope(String content){
		this.content=content;
	}
	public SignedEnvelope(String content, String signature){
		this.content=content;
		this.signature=signature;
	}
	public String getContent(){
		return content;
	}
	public String getSignature(){
		return signature;
	}
	
	public void genSignature(RSAKey key){
		if (content!=null){
			try {
				byte[] bytes = content.getBytes("UTF-8");
				byte[] signedbytes = key.sign(bytes);
				BASE64Encoder encoder = new BASE64Encoder();
				signature=encoder.encode(signedbytes);
			} catch (UnsupportedEncodingException e) {
			}
		}
	}
	public boolean verify(RSAKey key){
		if (content!=null && signature!=null){
			try {
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] sigbytes=decoder.decodeBuffer(signature);
				return key.verify(content.getBytes("UTF-8"), sigbytes);
			} catch (UnsupportedEncodingException e) {
				return false;
			} catch (IOException e) {
				return false;
			}
		}
		return false;
	}
	public String toXML(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<SignedEnvelope><content><![CDATA[");
		buffer.append(content);
		buffer.append("]]></content><signature>");
		buffer.append(signature);
		buffer.append("</signature></SignedEnvelope>");
		return buffer.toString();
	}
	
	public static SignedEnvelope valueOf(String xml){
		XStream stream = getXStream();
		return (SignedEnvelope) stream.fromXML(xml);
	}
	private static XStream getXStream() {
		XStream stream = new XStream();
		stream.alias("SignedEnvelope", SignedEnvelope.class);
		return stream;
	}
	private String content;
	private String signature;
}
