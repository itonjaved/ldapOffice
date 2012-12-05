package com.iton;

import java.util.List;

import javax.naming.Name;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;

import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

public class Test {
private LdapTemplate ldapTemplate;
	
	public static  String BASE_DN = null;
	
	public LdapTemplate getLdapTemplate() {
		return ldapTemplate;
	}
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
	int autoldapid=0;
	protected Name buildDn(int office) {
		
	    
	    String so=findSubOrganization(office);
		
		
		
		DistinguishedName dn = new DistinguishedName(BASE_DN);
		
		autoldapid=getLdapId(BASE_DN);
		dn.add("cn", autoldapid+"");
		return dn;
		}
		

	

		private int getLdapId(String bASE_DN2) {
	

			UserAttributesMapper mapper=new UserAttributesMapper();
			AndFilter filter=new AndFilter();
			filter.and(new EqualsFilter("objectClass","person"));
			filter.and(new EqualsFilter("objectClass","inetOrgPerson"));
			filter.and(new EqualsFilter("objectClass","organizationalPerson"));
			List<User> users=ldapTemplate.search(bASE_DN2,filter.encode(),mapper);
			/*int max=0;*/
			
		 int size= users.size();
		 User us= users.get(size-1);
		autoldapid=us.getUid();
             /*for(User user:users)
			{
				int uid=user.getUid();
				if(uid>max)
				{
				max=uid;
				
				}
				
			}*/
			//System.out.println(max+"maximummmmm");
			
		//return max+1;
			return autoldapid+1;
	}
		public void create() {
		
		}
		private Attributes buildAttributes(String role,int psw) {
		Attributes attrs = new BasicAttributes();
		BasicAttribute ocattr = new BasicAttribute("objectclass");
		ocattr.add("top");
		ocattr.add("person");
		ocattr.add("inetOrgPerson");
	    ocattr.add("organizationalPerson");
		attrs.put(ocattr);
		//attrs.put("cn", "Some Person");
		
		attrs.put("userPassword",psw+"psw");
		attrs.put("title",role);
		attrs.put("sn","xxx");
		return attrs;
		}
		
		
		

		private String findSubOrganization(int office) {
		  String so=null;
		 
		  if(office==1)
		  {
			  BASE_DN="ou=ms1,o=myoffice";
		  }
		  else if(office==10)
		  {
			  BASE_DN="ou=us10,ou=ms1,o=myoffice";
		  }
		  else if(office==20)
		  {
			  BASE_DN="ou=us1,o=myoffice";
		  }
		  else if(office==30)
		  {
			  BASE_DN="ou=dg,o=myoffice";
		  }
		  else if(office==35)
		  {
			  BASE_DN="ou=dp,ou=dg,o=myoffice";
		  }
			
			return so;
		}
	public void insertLdapUser(String role, int psw,int office) {
		
		
		 
		 
		 
		
	        Name dn = buildDn(office);
		ldapTemplate.bind("", null, buildAttributes(role,psw));
		System.out.println("Successfully created user in LdapId Creatd with ID="+autoldapid);
		 
		 
			
		
	}

}
