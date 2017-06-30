package adapter.jennifer.jira.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import adapter.jennifer.jira.entity.Issue;
import adapter.jennifer.jira.entity.JiraProp;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Simple JIRA Client to create issues using JIRA REST API with Basic Authentications
 *
 */
public class JIRAClient {


	/**
	 * Called by the Event adapter to create a JIRA issue. 
	 * This method first calls <b>dataToJson</b> to parse the payload to JSON format then send the data to JIRA
	 * @param jiraProp JIRA properties object
	 * @param issueTitle Title of the issue to be created
	 * @param issueDescription Description of the issue to be created
	 * @return JIRA response about the newly created issue or null
	 */
	public static Issue createIssue(JiraProp jiraProp, String issueTitle, String issueDescription){
		Issue createdIssue = null;
		try{
			String url = jiraProp.getBaseUrl() + "/rest/api/2/issue/";
			HttpClient httpClient = new DefaultHttpClient();			
			HttpPost postRequest = new HttpPost(url);
			postRequest.addHeader("content-type", "application/json");
			String credentials = jiraProp.getUserName() + ":" + jiraProp.getPassword();
			String encodedCred = "Basic " + new String(Base64.encodeBase64(credentials.getBytes("UTF-8")),"UTF-8");
			postRequest.addHeader("Authorization",encodedCred);
			String data = dataToJson(jiraProp, issueTitle, issueDescription);
			postRequest.setEntity(new StringEntity(data));
			HttpResponse response = httpClient.execute(postRequest);
			int statusCode = response.getStatusLine().getStatusCode(); 
			if(statusCode == 200 || statusCode == 201 ){
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line = reader.readLine();
				reader.close();
				createdIssue = jsonToIssue(line);
			}
			return createdIssue;
		}catch(Exception ex){
			return null;
		}
	}
	
	
	/**
	 * Parse the data into payload data for creating issue as JSON and return JSON String
	 * @param jiraProp JIRA properties object 
	 * @param issueTitle Title of the issue to be created
	 * @param issueDescription Description of the issue to be created
	 * @return JSON String representation
	 */
	private static String dataToJson(JiraProp jiraProp,String issueTitle,String issueDescription){
		JSONObject payLoad = new JSONObject();
		JSONObject project = new JSONObject();
		project.put("key", jiraProp.getProjectKey());
		payLoad.put("project", project);
		payLoad.put("summary", issueTitle);
		payLoad.put("description", issueDescription);
		JSONObject type = new JSONObject();
		type.put("name", jiraProp.getIssueType());
		payLoad.put("issuetype", type);
		JSONObject mainObject = new JSONObject();
		mainObject.put("fields", payLoad);
		return mainObject.toString();
	}
	
	/**
	 * Parse the JSON response from JIRA into Issue.class Object
	 * @param jsonString JIRA response as JSON string 
	 * @return Issue class object or null if exception occurred
	 */
	private static Issue jsonToIssue(String jsonString){
		try{
			Issue createdIssue = new Issue();
			JSONObject object = new JSONObject(jsonString);
			createdIssue.setId(object.getString("id"));
			createdIssue.setKey(object.getString("key"));
			createdIssue.setSelf(object.getString("self"));
			return createdIssue;
		}catch(JSONException ex){
			return null;
		}
	}
}
