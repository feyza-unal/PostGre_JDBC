import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/techpro","postgres","5055");
        Statement st = con.createStatement();
/*
        PreparedStatement interface, birden cok kez calistirilabilen onceden derlenmis bir SQL kodunu temsil eder.
        Parametrelendirilmis SQL sorgulari(query) ile calisir. Bu sorguyu 0 veya daha fazla parametre ile kullanabiliriz
*/

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        // 1. adim preparedStatement query'sini olustur  (IBM VE 9999 yerine ? koyarak parametretize ediyoruz)
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ? ";

        //2. adim preparedStatement objesini olustur
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. adim setInt(), setString, ... metodlarini kullanarak soru isaretleri yerlerine deger gir.
        pst1.setInt(1,9999);
        pst1.setString(2, "IBM");

        //4. adim execute query -> sorguyu calistir
        int guncellenenSatirSayisi = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        String sql2 = "SELECT * FROM companies";

        ResultSet rs1 = st.executeQuery(sql2);
        while (rs1.next()){
            System.out.println(rs1.getInt(1) + "--" + rs1.getString(2) + "--" + rs1.getInt(3)); // kac tane sutun(column) varsa
        }


        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5555 olarak güncelleyin.
        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");


        int guncellenenSatirSayisi2 = pst1.executeUpdate();
        System.out.println("guncellenenSatirSayisi2 = " + guncellenenSatirSayisi2);

        ResultSet rs2 = st.executeQuery(sql2);
        while (rs2.next()){ // rs1 sona geldigi icin (next kullanilmaz) yeni bir rs degeri olusturup kullandik
            System.out.println(rs2.getInt(1) + "--" + rs2.getString(2) + "--" + rs2.getInt(3));
        }

        con.close();
        st.close();
        rs1.close();
        rs2.close();
        pst1.close();
    }
}
