# Tutorial scenario and prerequisites

This tutorial introduces you to key features in ODM product. Prepare for the tutorial by reviewing its objectives and prerequisites.

You start by working in the Decision Center Business console to update a decision service that a loan company uses to determine whether borrowers are eligible for loans. The fictitious company makes changes to its loan policies, and you must reflect the changes in the decision service. When you finish updating the decision service, you deploy a rule application, or RuleApp, to the Decision Server console and test a ruleset.

The tutorial uses a simple decision service that has only one project. The rules are grouped into two folders in the project:

|Folder|Description|
|------|-----------|
|eligibility|These rules determine whether the loan can be approved.|
|validation|These rules make preliminary checks to determine whether data is rejected immediately.|

As you go through the tutorial, you log in as two different users to get a feel for the collaboration that takes place in the Business console. The two users log in with different user names:

-   `odmAdmin`: A manager who initiates, reviews, and deploys changes.
-   `rtsUser1`: A rule author who implements the change.

## Prerequisites

1. Download the following files before you start the tutorial:

  - [MiniLoan Service.zip](../../Miniloan%20Service.zip?raw=1)
  - [miniloan-test.xlsx](../../miniloan-test.xlsx?raw=1)
  - [execution-payload.json](../../execution-payload.json?raw=1). As it is a text file, you need to right-click on the link and click on `Save link as`.

2. Install ODM, and note the URLs of your instances of the Decision Center Business console and the Decision Server console.

  - For [ODM for Developers](https://hub.docker.com/r/ibmcom/odm/), Decision Center Business console will be available at http://localhost:9060/decisioncenter and Decision Server console will be deployed at http://localhost:9060/res.

  - For [ODM for Developers on Certified Kubernetes](https://artifacthub.io/packages/helm/ibm-odm-charts/ibm-odm-dev/22.2.0), refer to [Accessing ODM](https://artifacthub.io/packages/helm/ibm-odm-charts/ibm-odm-dev/22.2.0#accessing-odm) section to retrieve the URLs.

  * For [ODM for production on Certified Kubernetes](https://www.ibm.com/docs/en/odm/8.11.1?topic=production-installing-helm-release-odm), refer to [Completing post-deployment tasks](https://www.ibm.com/docs/en/odm/8.11.1?topic=production-completing-post-deployment-tasks) to retrieve the URLs.

  * For [ODM in IBM Cloud Pak for Business Automation](https://www.ibm.com/docs/en/cloud-paks/cp-biz-automation/22.0.2?topic=deployment-creating-production), refer to [Identifying the services](https://www.ibm.com/docs/en/cloud-paks/cp-biz-automation/22.0.2?topic=manager-identifying-services)to retrieve the URLs.

## Best practices

This tutorial includes the following best practices:

-   Use the Business console to follow changes to rules, especially when you assign work to other people.
-   Create a snapshot of a branch before modifying it. You can use the snapshot for comparisons or to rollback the branch to a previous state.
-   Post comments for other users. The comments can direct work and serve as part of the history of changes.
-   Use the search feature to find rule artifacts.
-   Test a rule set for REST execution in the Decision Server console.
-   Delete projects from the databases when you no longer use them.

[**Next** ![Next icon](../images/next.jpg)](../topics/tut_icp_gs_evaluate_changes_lsn.md)

[![](../images/home.jpg) **Back to table of contents**](../../README.md)
