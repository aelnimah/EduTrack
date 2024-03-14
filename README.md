# EduTrack
EduTrack is a Java application tailored for easy and efficient student database management. Utilizing PostgreSQL, it enables operations that manipulate student records. Designed with academic administrators in mind, EduTrack offers a simple yet powerful tool for handling student information effortlessly.

For Testing:

## Database Setup
1. Install PostgreSQL.
2. Create a database named `A3`.
3. Run the scripts located in the `/db` directory to create the schema and populate initial data:
   - psql -U username -d A3 -a -f db/schema.sql
   - psql -U username -d A3 -a -f db/sampledata.sql

## Application Configuration
1. Configure Database Connection: Open the Main.java file and update the url (if database not named 'A3'), user, and password variables with your PostgreSQL connection details.
2. Within Main you may test the functions (the purpose is to make changes and recompile):
    - Add Students: Insert new student records into the database.
    - Update Student Emails: Modify the email addresses of existing students.
    - Delete Students: Remove student records from the database.
    - View All Students: List all student records stored in the database.

## Running the Application
1. Compile the Application:
   - Open a terminal or command prompt
   - Naviagate to the project's root directory
   - Compile the code using: javac -d bin src/*.java
2. Run EduTrack:
   - Once compiled, run the application with: java -cp bin StudentManager
