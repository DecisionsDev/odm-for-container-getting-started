## Retrieve the URLs for ODM in CP4BA starter mode and configure the users

### Get the ODM URLs and the admin user's password

You can find the URLs to access ODM from the OCP console:

1. Go to **Workloads** > **ConfigMaps**.

2. Select the `<CR_NAME>-cp4ba-access-info` configmap.

3. In the **Data** section, go to the `odm-access-info` information.

    ```console
    CloudPak dashboard: https://cpd-<RELEASE>.apps.<ocp-cluster>.com
    ODM Decision Center URL: https://cpd-<RELEASE>.apps.<ocp-cluster>.com/odm/decisioncenter
    ODM Decision Runner URL: https://cpd-<RELEASE>.apps.<ocp-cluster>.com/odm/DecisionRunner
    ODM Decision Server Console URL: https://cpd-<RELEASE>.apps.<ocp-cluster>.com/odm/res
    ODM Decision Server Runtime URL: https://cpd-<RELEASE>.apps.<ocp-cluster>.com/odm/DecisionService
    Default administrator username: cp4admin
    Default administrator password: ****
    ```

4. Take note of the ODM URLs.

5. Take note of the default administrator password.

    In the rest of the tutorial, you will use the `cp4admin` user and its corresponding password to log in as the **admin user**.

    > **Note**
    > To log in to the ODM URLs with the `cp4admin` user, you must select Log in with **IBM provided credentials (admin only)**.

### Configure the business user

1. Open the **CloudPak dashboard** URL.

    > **Note**
    > The CloudPak dashboard URL can be found in the `<CR_NAME>-cp4ba-access-info` configmap as described above.

2. Log in with the *IBM provided admin user*.

    You must log in with **IBM provided credentials (admin only)** instead of *Entreprise LDAP*.

    > **Note**
    > You can find the IBM provided admin user and password in the **platform-auth-idp-credentials** secret. Click the *Reveal values* button.

3. Assign the role to `user1`.

    - In the administrator's UI, click **Manage users**.
    - In the Users tab of the *Access control* page, select **user1**.
    - On the user's page, click **Assign roles** .
    - On the *Assign roles* page, select the **ODM Business User** role, and then click **Assign 1 role**.

    Now the user `user1` is well configured as the **business user** for the rest of the tutorial.

4. Retrieve the `user1` password.

    - Go to **Workloads** > **Secrets**.
    - Select the `<CR_NAME>-openldap-customldif` secret.
    - Click the *Reveal values* button.
      ```
      ...
      dn: uid=user1,dc=example,dc=org
      uid: user1
      cn: user1
      sn: user1
      userpassword: ****
      ...
      ```
    - Take note of the `user1` password.

    > **Note**
    > To log in to the ODM URLs as `user1`, you must select Log in with **Entreprise LDAP**.
