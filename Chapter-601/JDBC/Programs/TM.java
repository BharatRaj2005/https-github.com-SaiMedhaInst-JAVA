import java.sql.*;
import java.io.*;
class TM {
    public static void main(String args[]) {
        String dburl = "jdbc:mysql://localhost:3306/emp";
        String username = "root";
        String passwd = "SqlAk@18";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(dburl, username, passwd);
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement("insert into user420 values(?,?)");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {

                System.out.println("enter id");
                String s1 = br.readLine();
                int id = Integer.parseInt(s1);

                System.out.println("enter name");
                String name = br.readLine();


                ps.setInt(1, id);
                ps.setString(2, name);
             
                ps.executeUpdate();

                System.out.println("commit/rollback");
                String answer = br.readLine();
                if (answer.equals("commit")) {
                    con.commit();
                }
                if (answer.equals("rollback")) {
                    con.rollback();
                }


                System.out.println("Want to add more records y/n");
                String ans = br.readLine();
                if (ans.equals("n")) {
                    break;
                }

            }
            con.commit();
            System.out.println("record successfully saved");

            con.close(); //before closing connection commit() is called  
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}