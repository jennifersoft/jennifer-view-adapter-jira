package adapter.jennifer.jira.util;

public class Version {

	public String getVersion(){
		return "1.0";
	}
	
	public static void main(String[] args) {
		System.out.format("JIRA Adapter Version %s\n", new Version().getVersion());
	}
}
