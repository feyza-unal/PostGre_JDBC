import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol
        Class.forName("org.postgresql.Driver");

        //2. Adım: Database'e baglan
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro","postgres","5055");

        //3. Adım: Statement oluştur
        Statement st = con.createStatement();

        System.out.println("Connection Success");
        boolean sql1 = st.execute("CREATE TABLE workers(worker_id VARCHAR(20),worker_name VARCHAR(20), worker_salary INT)");
        System.out.println("sql1 = " + sql1); // false return eder cunku data cagirmiyoruz

        //--2.Örnek: Table'a worker_address sütunu ekleyerek alter yapın.
        String sql2 = "ALTER TABLE workers ADD worker_address VARCHAR(80)" ;
        st.execute(sql2);


        //3.Örnek: Drop workers table
        String sql3 = "DROP TABLE workers";
        st.execute(sql3);

        //connection'i kapatiyoruz
        con.close();
        st.close();
    }
}

/* execute() methodu  DDL( create drop, alter table) ve DQL (select) icin kullanilabilir.
1) Eger execute methodu DDL icin kullanilirsa 'false' return eder.
2) Eger execute methodu DQL icin kullanilirsa ResultSet alindiginde 'true' aksi halde 'false' verir.

*/