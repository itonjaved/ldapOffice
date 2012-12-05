package com.iton;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
 
import org.springframework.ldap.core.AttributesMapper;
 
public class UserAttributesMapper implements AttributesMapper {
 
	@Override
	public User mapFromAttributes(Attributes attributes) throws NamingException {
 
		User userObject = new User();
   
	String commonName = (String)attributes.get("cn").get();
	 int uid=Integer.parseInt(commonName);
	/*System.out.println(commonName+"comm");
	System.out.println(uid);*/
		userObject.setUid(uid);
		
		return userObject;
	}
 
}