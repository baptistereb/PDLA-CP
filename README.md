# PDLA-CP

## Prerequisites
To run the project, ensure you have the following:
- Access to the **INSA VPN** (required to connect to the database).
- **Maven** installed on your machine.

## Run the Project

1. **Connect to the INSA VPN**  
   Ensure that you are connected to the INSA VPN to have access to the database.

2. **Build the Project**  
   Open a terminal and navigate to the project directory. Run the following command to clean, compile and run the project :
   ```bash
   mvn clean compile
   mvn exec:java -Dexec.mainClass="org.pdla.Main"
   ```

## Run Tests

There are two ways to run the tests: **locally** or **remotely**.

### 1. Test Locally
To test locally, follow these steps:

1. **Install a local SQL database**  
Set up a local SQL database with the following configuration:
   - Database name: `test_database`
   - Username: `root`
   - Password: `root`

2. **Load the table schema**  
Execute the SQL commands in the file `./create_table.sql` to set up the required tables.

3. **Run the tests**  
Use the following Maven command to execute the tests locally:
```bash
mvn test -P local
```
### 2. Test Remotely (Not Recommended)
To test remotely, ensure you are connected to the **INSA VPN**, as it requires access to the production database.

Run the following command:
```bash
mvn test -P remote
```
⚠️ **Warning:** This will test against the production database and is discouraged.

### 3. Alternative: Use GitHub CI
A **GitHub CI workflow** is already configured to handle `mvn test -P local` with a clean and automated setup.  
You can use it as a reference or for running tests.

## Structure SQL
- users (<u>user_id</u>, pseudo, password, user_type {user, volunteer, moderator})
- missions (<u>mission_id</u>, description, #user_id, mission_state {waiting, valid, realized, refused}, motif, mission_type {help, need_help})
- connection (<u>connection_id</u>, #mission_id, #user_id, connection_type {help, need_help})
