package adapter.jennifer.jira.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import adapter.jennifer.jira.entity.JiraProp;
import com.jennifersoft.view.adapter.util.LogUtil;
import com.jennifersoft.view.config.ConfigValue;

/**
 * Configuration utility to load adapter data from configuration file
 *
 */
public class ConfUtil {

	/**
	 * Properties instances to load the configuration
	 */
	private static Properties properites = null;
	
	/**
	 * Initialize the Properties object and load the configuration from the configuration file 
	 * The configuration file name and path must be set in <b>VIEW_SERVER_HOME/conf/server_view.conf<b> using the
	 * <b>adapter_config_path</b> option
	 */
	public static void load(){
		properites = new Properties();
		FileInputStream in = null;
		String path = ConfigValue.adapter_config_path;
		try{
			if(path != null){
				in = new FileInputStream(path);
				properites.load(in);
			}
		}catch(IOException io){
			LogUtil.error("Failed to load configuration file: " + io.toString());
		}
	}
	
	/**
	 * Get a configuration value using the provided key
	 * @param key configuration key
	 * @return String configuration value
	 */
	public static String getValue(String key){
		if(properites == null)
			load();
		return properites.getProperty(key);
	}
	
	/**
	 * Getter for the properties object.
	 * @return the properties object
	 */
	public static Properties getProperites() {
		if(properites == null)
			load();
		return properites;
	}
	
	/**
	 * Cast the properties into <b>JiraProp</b> class
	 * The following properties must be set in the configuration file
	 * <b>jira_base_url</b> The URL for the JIRA installation
	 * <b>jira_username</b> The JIRA username that will be used to create issues
	 * <b>jira_password</b> The user password used for authentication
	 * <b>jira_project</b> The JIRA Project key where issues will be created
	 * <b>jira_issue</b> The JIRA issue type to be created
	 * <b>jira_plainpassword</b> Flag indicates if the password in the properties file is set as plain password on encrypted
	 * @return
	 */
	public static JiraProp getJiraProperites(){
		String plainPassword = getValue("jira_plainpassword");
		JiraProp jira = new JiraProp();
		jira.setBaseUrl(getValue("jira_base_url"));
		jira.setUserName(getValue("jira_username"));
		if(plainPassword != null && plainPassword.equals("1")) {
			jira.setPlainPassword(true);
		}
		else {
			jira.setPlainPassword(false);
		}
		jira.setPassword(getValue("jira_password"));
		jira.setProjectKey(getValue("jira_project"));
		jira.setIssueType(getValue("jira_issue"));
		return jira;
	}
}
