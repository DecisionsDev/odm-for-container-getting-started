# Tutorial scenario and prerequisites

This tutorial introduces you to key features in the Operational Decision Manager (ODM) product. Prepare for the tutorial by reviewing its objectives and prerequisites.

You start by working in the Decision Center Business console to update a decision service that a loan company uses to determine whether borrowers are eligible for loans. The fictitious company makes changes to its loan policies, and you must reflect the changes in the decision service. When you finish updating the decision service, you deploy a rule application, or RuleApp, to the Decision Server console and test a ruleset.

The tutorial uses a simple decision service that has only one project. The rules are grouped into two folders in the project:

|Folder|Description|
|------|-----------|
|eligibility|These rules determine whether the loan can be approved.|
|validation|These rules make preliminary checks to determine whether data is rejected immediately.|

## Prerequisites

1. Install ODM.

    Install ODM and note the URLs of the Decision Center Business console and the Decision Server console instances.

    * To install **ODM for Developers**, follow this [documentation](https://hub.docker.com/r/ibmcom/odm/).
    The Decision Center Business console will be available at the URL http://localhost:9060/decisioncenter. The Decision Server console will be deployed at the URL http://localhost:9060/res.

    * To install **ODM for Developers on Certified Kubernetes**, follow this [documentation](https://artifacthub.io/packages/helm/ibm-odm-charts/ibm-odm-dev/24.0.0#installing-the-chart).
    Refer to [Accessing ODM](https://artifacthub.io/packages/helm/ibm-odm-charts/ibm-odm-dev/24.0.0#accessing-odm) to retrieve the URLs.

    * To install **ODM for production on Certified Kubernetes**, follow this [documentation](https://www.ibm.com/docs/en/odm/9.0.0?topic=production-installing-helm-release-odm).
    Refer to [Configuring external access](https://www.ibm.com/docs/en/odm/9.0.0?topic=tasks-configuring-external-access) to retrieve the URLs.

    * To install **ODM with IBM Cloud Pak for Business Automation in starter mode**, follow this [documentation](https://www.ibm.com/docs/en/cloud-paks/cp-biz-automation/24.0.0?topic=deployments-installing-cp4ba-multi-pattern-starter-deployment).
    Refer to [Retrieve URLs for ODM in CP4BA starter mode and configure users](../topics/tut_icp_gs_odm_cp4ba_prereqs.md).

2. Get the users and passwords.

    As you go through the tutorial, you log in as two different users to get a feel for the collaboration that takes place. The two users log in with different roles:

      -   An **admin user** with the *ODM Administrator* role: A manager who initiates, reviews, and deploys changes in the Business Console, and manage and execute ruleapp/ruleset in the Decision Server console.
      -   A **business user** with the *ODM Business User* role: A rule author who implements the change in the Business Console.

    To retrieve the users and passwords, refer to the following documentation depending on the type of deployment:

    * In **ODM for Developers**, the following users/passwords are defined:
      * As admin user:`odmAdmin`/`odmAdmin`
      * As business user: `rtsUser1`/`rtsUser1`

    * In **ODM for Developers on Certified Kubernetes**, by default, you can use `odmAdmin` as the admin user and `rtsUser1` as the business user.
    Both users implement the defined **usersPassword** parameter, if used, or a custom configuration, if you choose to manually [Configure user access](https://artifacthub.io/packages/helm/ibm-odm-charts/ibm-odm-dev/24.0.0#configuring-user-access).

    * In **ODM for production on Certified Kubernetes**, by default, you can use `odmAdmin` as the admin user and `rtsUser1` as the business user.
    Both users implement the defined **usersPassword** parameter, if used, or the custom configuration, if you choose to manually [Configure user access](https://www.ibm.com/docs/en/odm/9.0.0?topic=production-configuring-user-access).

    * In **ODM with IBM Cloud Pak for Business Automation in starter mode**, you can use `cp4admin` as the admin user and add the *ODM Business User* role to `user1`.
    Refer to [Retrieve URLs for ODM in CP4BA starter mode and configure users](../topics/tut_icp_gs_odm_cp4ba_prereqs.md).

3. Clean out the databases

    * Login to the Decision Center Business console as **admin user**.

      If you see that *Miniloan Service* is already available in the Library tab, you must remove it before you can import it again. Refer to the following page to learn how to remove a decision service from Decision Center: [Step 1: Deleting the decision service from Decision Center](../topics/tut_icp_gs_clean_db_lsn.md#step-1-deleting-the-decision-service-from-decision-center).

    * Login to the Decision Server console as **admin user**.

      If you see that the *mydeployment* RuleApp is already deployed under Navigator, you should remove it before deploying it again. Refer to the following page to learn how to remove a RuleApp from Decision Server console: [Step 2: Removing the RuleApp from the Decision Server console](../topics/tut_icp_gs_clean_db_lsn.md#step-2-removing-the-ruleapp-from-the-decision-server-console).

4. Download the following files before you start the tutorial.

    - [MiniLoan Service.zip](../../Miniloan%20Service.zip)
    - [miniloan-test.xlsx](../../miniloan-test.xlsx)
    - [execution-payload.json](../../execution-payload.json)
      As this file is a text file, you need to right-click the link, and then click `Save link as`.

## Best practices

This tutorial includes the following best practices:

-   Use the Business console to follow the changes to rules, especially when you assign work to other people.
-   Create a snapshot of a branch before modifying it. You can use the snapshot for comparisons or to rollback the branch to a previous state.
-   Post comments for other users. The comments can direct work and help track the changes.
-   Use the search feature to find rule artifacts.
-   Test a ruleset for REST execution in the Decision Server console.
-   Delete projects from the databases when you no longer use them.

[**Next** ![Next icon](../images/next.jpg)](../topics/tut_icp_gs_evaluate_changes_lsn.md)

[![](../images/home.jpg) **Back to table of contents**](../../README.md)
