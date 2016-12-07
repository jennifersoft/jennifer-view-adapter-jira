package adapter.jennifer.jira.entity;

import adapter.jennifer.jira.util.Krypto;

/**
 * JIRA Connection properties;
 *
 */
public class JiraProp {

	/**
	 * JIRA URL
	 */
	private String baseUrl;
	
	/**
	 * JIRA Username that will be used to create issues
	 */
	private String userName;
	
	/**
	 * JIRA User password
	 */
	private String password;

	/**
	 * Used to determine if the password provided is encrypted or plain text
	 */
	private boolean plainPassword;

	
	/**
	 * JIRA Project Key
	 */
	private String projectKey;
	
	/**
	 * The type of the issue to be created
	 */
	private String issueType;
	
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		if(isPlainPassword())
			return password;
		else
			return new Krypto().decrypt(password);
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProjectKey() {
		return projectKey;
	}
	public void setProjectKey(String projectKey) {
		this.projectKey = projectKey;
	}
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}
	public String getIssueType() {
		return issueType;
	}
	public boolean isPlainPassword() {return plainPassword;}
	public void setPlainPassword(boolean plainPassword) {this.plainPassword = plainPassword;}
}
