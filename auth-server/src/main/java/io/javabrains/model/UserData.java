package io.javabrains.model;

import java.io.Serializable;
import java.util.List;


public class UserData implements Serializable {

	    private SessionDetails sessionDetails;
	    private List<String> userPermissions;
	    public String getExp() {
			return exp;
		}

		public void setExp(String exp) {
			this.exp = exp;
		}

		public String getIat() {
			return iat;
		}

		public void setIat(String iat) {
			this.iat = iat;
		}

		private String exp;
	    private String iat;
	    
	    
	    public SessionDetails getSessionDetails() {
			return sessionDetails;
		}
	    
		public void setSessionDetails(SessionDetails sessionDetails) {
			this.sessionDetails = sessionDetails;
		}
		
		public List<String> getUserPermissions() {
			return userPermissions;
		}
		
		public void setUserPermissions(List<String> userPermissions) {
			this.userPermissions = userPermissions;
		}
}



