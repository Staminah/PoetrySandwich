# PoetrySandwich
Website about poems. Realised in JEE with JSF Framework.


# Installation

**Warning : Be sure to use at least Glassfish 4.1.2 or higher**

 1. Clone this repo `git clone https://github.com/Staminah/PoetrySandwich.git`
 2. Create the database using the file named **database.sql**. It should create a new DB named *db_poetrysandwich* with tables and data.
 3. The database must be accessible via a user named **root** with an **empty password**
 4. Open NetBeans and do *File > Open Project > Choose your freshly cloned directory*
 5. Locate your Glassfish folder and go to the config folder of your domain (*C:\Users\YourName\glassfish-4.1.2\glassfish4\glassfish\domains\domain1\config*), then replace the file named **domain.xml** by the one at the root of this repo.
 6. You should now be able to run the **index.xhtml** file and access to the website of this project.
 7. You should be able to login with the following users :

| Username | Password | Role |
|--|--|--|
| admin | okok | Admin |
| anthony| okok | Editor|
| steve| okok | Author|
