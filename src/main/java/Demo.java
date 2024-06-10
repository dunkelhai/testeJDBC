import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Demo {

    private static final String URL = "jdbc:mysql://localhost:3306/senacjdbc";
    private static final String USER = "developer";
    private static final String PASSWORD = "1234567";

    public static void main(String[] args) {
        Demo db = new Demo();

        db.insertStudent(1, "Jonas Tadeu", 20);

        db.listStudents();

//        db.updateStudentAge(1, 21);
//
//        db.deleteStudent(1);
    }

    public void insertStudent(int id, String name, int age) {
        String query = "INSERT INTO Students (student_id, name, age) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listStudents() {
        String query = "SELECT * FROM Students";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("student_id") + ", Name: " + rs.getString("name") + ", Age: " + rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudentAge(int id, int newAge) {
        String query = "UPDATE Students SET age = ? WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, newAge);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int id) {
        String query = "DELETE FROM Students WHERE student_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

