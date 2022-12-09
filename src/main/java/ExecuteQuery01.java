import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro","postgres","5055");
        Statement st = con.createStatement();

    //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.

        String sql1 = "select country_name from countries where region_id=1";
        boolean sql1Sonuc = st.execute(sql1);
        System.out.println("sql1Sonuc = " + sql1Sonuc);

    // Record'lari gormek icin executeQuery() methodunu kullanmaliyiz
        ResultSet rs1 = st.executeQuery("select country_name from countries where region_id=1");

        while ((rs1.next())){
            System.out.println(rs1.getString(1));
        }

        System.out.println();

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        String sql2 = "select country_name,country_id from countries where region_id>2";
        ResultSet rs2 = st.executeQuery(sql2);

        while ((rs2.next())){
            System.out.println(rs2.getString("country_name")+"--"+rs2.getString("country_id"));
        }

        System.out.println();

        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        String sql3 = "select * from companies where number_of_employees = (select min(number_of_employees) from companies)";
        ResultSet rs3 = st.executeQuery(sql3);

        while ((rs3.next())){
            System.out.println(rs3.getInt(1)+"--"+rs3.getString(2)+"--"+rs3.getInt(3));
        }

    }
}
