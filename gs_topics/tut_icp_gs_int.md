# Tutorial scenario and prerequisites

This tutorial introduces you to key features in ODM for Developers. Prepare for the tutorial by reviewing its objectives and prerequisites.

You start by working in the Decision Center Business console to update a decision service that a loan company uses to determine whether borrowers are eligible for loans. The fictitious company makes changes to its loan policies, and you must reflect the changes in the decision service. When you finish updating the decision service, you deploy a rule application, or RuleApp, to the Decision Server console and test a ruleset. 

The tutorial uses a simple decision service that has only one project. The rules are grouped into two folders in the project:

|Folder|Description|
|------|-----------|
|eligibility|These rules determine whether the loan can be approved.|
|validation|These rules make preliminary checks to determine whether data is rejected immediately.|

As you go through the tutorial, you log in as two different users to get a feel for the collaboration that takes place in the Business console. The two users log in with different user names:

-   odmAdmin: A manager who initiates, reviews, and deploys changes.
-   rtsUser1: A rule author who implements the change.

## Prerequisites

Do the following tasks before you start the tutorial:

-   Install [ODM for Developers](https://hub.docker.com/r/ibmcom/odm/), and note the URLs of your instances of the Decision Center Business console and the Rule Execution Server console.
-   Download the Getting Started for ODM for Developers sample project from this [ODMDev 
GitHub repository](https://github.com/ODMDev/odm-for-dev-getting-started) by clicking **Clone or download** and then **Download ZIP**. This action downloads the entire contents of the GitHub repository. You need the following projects to work on this tutorial:

|Project|Description|
|------|-----------|
|Miniloan Service|You work in this decision service in the tutorial. You import it into the Business console, and update and deploy its rules.|
|miniloan-xom|This project contains the Java object model that is used in Miniloan Service.|

## Best practices

This tutorial includes the following best practices:

-   Use the Business console to follow changes to rules, especially when you assign work to other people.
-   Create a snapshot of a branch before modifying it. You can use the snapshot for comparisons or to rollback the branch to a previous state.
-   Post comments for other users. The comments can direct work and serve as part of the history of changes.
-   Use the search feature to find rule artifacts.
-   Test a rule set for REST execution in the Rule Execution Server console.
-   Delete projects from the databases when you no longer use them. 

[**Next** ![Next icon](../gs_images/next.jpg)](../gs_topics/tut_icp_gs_evaluate_changes_lsn.md)

[![](../gs_images/home.jpg) **Back to table of contents**](../README.md)

