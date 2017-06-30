package adapter.jennifer.jira.util;

import adapter.jennifer.jira.entity.JiraProp;
import com.jennifersoft.view.extension.util.PropertyUtil;

/**
 * Load adapter configuration
 */
public class ConfUtil {

	/**
	 * The adapter ID
	 */
	private static final String ADAPTER_ID = "jira";

	/**
	 * Default JIRA Issue Type
	 */
	private static final String DEFAULT_ISSUE_TYPE = "Information";


	private static final JiraProp jiraProp = new JiraProp();


	/**
	 * Get a configuration value using the provided key
	 * @param key configuration key. Set this key value in the adapter configuration menu in JENNIFER client.
	 * @return String configuration value
	 */
	public static String getValue(String key){
		return PropertyUtil.getValue(ADAPTER_ID, key);
	}

	private static String getValue(String key, String defaultValue) {
		return PropertyUtil.getValue(ADAPTER_ID, key, defaultValue);
	}
	

	/**
	 * Get JIRA properties
	 * @return JiraProp jira properties
	 */
	public static JiraProp getJiraProperites(){
		jiraProp.setBaseUrl(getValue("jira_base_url"));
		jiraProp.setUserName(getValue("jira_username"));
		jiraProp.setPassword(getValue("jira_password"));
		jiraProp.setProjectKey(getValue("jira_project"));
		jiraProp.setIssueType(getValue("jira_issue", DEFAULT_ISSUE_TYPE));
		return jiraProp;
	}

}
