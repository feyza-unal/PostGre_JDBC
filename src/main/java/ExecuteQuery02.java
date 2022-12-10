import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro","postgres","5055");
        Statement st = con.createStatement();

        String sql1 = "SELECT company, number_of_employees from companies ORDER by number_of_employees desc offset 1 limit 1";
        ResultSet rs1 = st.executeQuery(sql1);

        while ((rs1.next())){
            System.out.println(rs1.getString("company") + "--" + rs1.getString("number_of_employees"));
        }

        con.close();
        st.close();
        rs1.close();






    }
}

