import java.sql.*;

public class StudentManager {
    // Database URL
    static String url = "jdbc:postgresql://localhost:5432/A3";
    // Database user
    static String user = "postgres";
    // Database password
    static String password = "admin";


    public static void main(String[] args) throws Exception {
        // Test functions here
    }

    // Gets a connection to the database
    public static Connection getConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);

        if (connection == null) {
            System.out.println("Connection to server failed.");
        }

        return connection;
    }

    // Prints all students from the 'students' table
    public static void printAllStudents() {
        try{
            Connection connection = getConnection();

            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * from students");

            ResultSet rs = statement.getResultSet();

            System.out.printf("%-10s \t %-20s \t %-20s \t %-30s \t %-15s%n", "Student ID", "First Name", "Last Name", "Email", "Enrollment Date");
            while(rs.next()) {
                System.out.printf("%-10d \t %-20s \t %-20s \t %-30s \t %-15s%n",
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDate("enrollment_date").toString());
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Adds a new student to the 'students' table
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date) throws Exception {
        Connection connection = getConnection();

        String insertSQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection. prepareStatement(insertSQL)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollment_date));

            int rowInsert = pstmt.executeUpdate();
            if (rowInsert > 0) {
                System.out.println("\n" + first_name + " " + last_name + " was inserted successfully!" + "\n");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Updates the email for a specified students
    public static void updateStudentEmail(int student_id, String new_email) throws Exception {
        Connection connection = getConnection();

        String updateSQL = "UPDATE students SET email = ? WHERE student_id = ?";
        try (PreparedStatement pstmt = connection. prepareStatement(updateSQL)) {
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);

            int rowChange = pstmt.executeUpdate();
            if (rowChange > 0) {
                System.out.println("\n" + "Email updated successfully for student with student ID: " + student_id + "\n");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Deletes a student
    public static void deleteStudent(int student_id) throws Exception {
        Connection connection = getConnection();

        String deleteSQL = "DELETE FROM students WHERE student_id = ?;";
        try (PreparedStatement pstmt = connection. prepareStatement(deleteSQL)) {
            pstmt.setInt(1, student_id);

            int rowDelete = pstmt.executeUpdate();
            if (rowDelete > 0) {
                System.out.println("\n" + "Student with Student ID: " + student_id + " deleted successfully.");
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
