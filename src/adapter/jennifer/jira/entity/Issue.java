package adapter.jennifer.jira.entity;

/**
 * JIRA Response when new issue is created
 *
 */
public class Issue {

	/**
	 * The newly created issue ID
	 */
	private String id;
	
	/**
	 * The newly created issue Key
	 */
	private String key;
	
	/**
	 * The newly created issue url (API URL)
	 */
	private String self;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}	
}
