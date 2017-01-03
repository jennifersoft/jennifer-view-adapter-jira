## Overview
This adapter will open/create JIRA issue when an EVENT occur.

## Getting started
You must modify the configuration file of the view server. (conf/server_view.conf)
```
adapter_class_path = ${ADAPTER_PROJECT_PATH}/dist/jennifer-view-adapter-jira.jar
adapter_config_path = ${ADAPTER_PROJECT_PATH}/dist/adapter.properties
adapter_event_class_name = adapter.jennifer.jira.JiraAdapter
```

## Configuration file
The configuration file looks like the following
```
# SET JIRA URL without ending forward slash
#Example http://issue.mycompany.com or http://192.168.0.192:18080
jira_base_url=JIRA_URL_HERE

# JIRA username that will be used for authentication and creating/posting issues
jira_username=JIRA_USERNAME_HERE

# This value is used to determine if you are providing plain or encrypted password.
# 0: Encrypted (default)
# 1: Plain text password.
# To Encrypt the password, please run the util/krypto.sh
jira_plainpassword=0

# JIRA account password  that will be used for authentication
# Either use plain password or encrypted password depending on the jira_plainpassword value
jira_password=JIRA_PASSWORD_HERE

# JIRA Project KEY where issues will be created
jira_project=PROJECT_KEY_HERE

# JIRA Issue Type. ex Bug, Information,Question
#jira_issue=Bug
jira_issue=ISSUE_TYPE_HERE

```
### Encrypting the password
If you do not want to store JIRA user's password as plain text, you can use the encrypt.sh utility
The utility is located under the util directory

To encrypt password provide the password as the argument for the utility
Example
```
./encrypt.sh MyPassword
```
The result of the utility is encrypted password
Example:
```
rypt: Simple Encryption util
Encryption Result of [MyPassword] is: wJjZXtMH+cAexbWtxgNnMA==
```
then take the encrypted password and set it in the adapter configuration file
```
jira_plainpassword=0
jira_password=wJjZXtMH+cAexbWtxgNnMA==
```