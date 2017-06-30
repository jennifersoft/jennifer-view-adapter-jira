## Important Notice
Please use the **`old`** branch if you are using JENNIFER Server prior to **`5.2.3`**

## Overview
This adapter will open/create JIRA issue when an EVENT occur.


## Getting started

The first step is to register the adapter: 
1. In JENNIFER Client, open the management area and Navigate to  **Extension and Notice** > **Adapter and Plugin**
2. Make sure the adapter tab is selected then click the **[+Add]** button
3. Select **[Event]** from the classifications dropdown.
4. Enter **``jira``** as the adapter ID.
5. Enter the path to the adapter JAR file ``jennifer-view-adapter-jira.jar`` or upload the JAR file from you local machine.
6. Enter the adapter class name ``adapter.jennifer.jira.JiraAdapter`` and save the settings.

<img width="798" alt="jira_adapter_registration" src="https://user-images.githubusercontent.com/3861725/27725097-d887e442-5daf-11e7-8b01-944b0a3adbb8.png">

## Options ##
The following table shows the required options for this adapter

| Key           | Required      | Description |
| ------------- |:-------------:|:-------------:|
| jira_base_url | YES           | SET JIRA URL without ending forward slash|
| jira_username | YES           | JIRA username that will be used for authentication and creating/posting issues|
| jira_password | YES           | JIRA account password  that will be used for authentication | 
| jira_project  | YES           | JIRA Project KEY where issues will be created|
| jira_issue    | NO            | JIRA Issue Type. ex ``Bug,Information,Question``.<br/>Default Issue type is ``Information``|

<img width="800" alt="jira_adapter_options" src="https://user-images.githubusercontent.com/3861725/27725096-d8874f32-5daf-11e7-901b-c8b86ff32aac.png">