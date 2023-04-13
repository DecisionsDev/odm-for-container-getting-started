## Retrieve URLs for ODM in CP4BA starter mode and configure users

### Get ODM urls and admin user's password

You can find URLs to access ODM from the OCP console:

1. Go to **Workloads** > **ConfigMaps**, and

2. Select the `<CR_NAME>-cp4ba-access-info` configmap.

3. In the **Data** section you will find the `odm-access-info` information:

    ```console
    CloudPak dashboard: https://cpd-odm.apps.<ocp-cluster>.com
    ODM Decision Center URL: https://cpd-odm.apps.<ocp-cluster>.com/odm/decisioncenter
    ODM Decision Runner URL: https://cpd-odm.apps.<ocp-cluster>.com/odm/DecisionRunner
    ODM Decision Server Console URL: https://cpd-odm.apps.<ocp-cluster>.com/odm/res
    ODM Decision Server Runtime URL: https://cpd-odm.apps.<ocp-cluster>.com/odm/DecisionService
    Default administrator username: cp4admin
    Default administrator password: ****
    ```

4. Take notes of the ODM URLs.

5. Take notes of the default administrator password.

    You will use the `cp4admin` user and its corresponding password to login as the **admin user** in the rest of the tutorial.

    > **Note**
    > To login to ODM URLs using `cp4admin` user you must select Log in with **IBM provided credentials (admin only)**.

### Configure the business user

1. Open the **CloudPak dashboard** URL.

    > **Note**
    > The CloudPak dashboard URL can be found in the `<CR_NAME>-cp4ba-access-info` configmap as describe above.

2. Login using the *IBM provided admin user*

    You must log in with **IBM provided credentials (admin only)** instead of *Entreprise LDAP*.

    > **Note**
    > You can find the IBM provided admin user and password in the **platform-auth-idp-credentials** secret. Click on the *Reveal values* button.

3. Assign the role to `user1`

    - Click **Manage users** in the administrator's UI.
    - In the Users tab on the *Access control* page, select **user1**.
    - Click **Assign roles** on the user's page.
    - Select the **ODM Business User** role on the *Assign roles* page, and then click **Assign 1 role**.

    Now the user `user1` is well configured to be used as the **business user** in the rest of the tutorial.

4. Retrieve `user1` password

    - Go to **Workloads** > **Secrets**
    - Select the `<CR_NAME>-openldap-customldif` secret.
    - Click on the *Reveal values* button.
      ```
      ...
      dn: uid=user1,dc=example,dc=org
      uid: user1
      cn: user1
      sn: user1
      userpassword: ****
      ...
      ```
    - Take notes of the `user1` password

    > **Note**
    > To login to ODM URLs using `user1` user you must select Log in with **Entreprise LDAP**.
