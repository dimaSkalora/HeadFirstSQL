package head_01.demo_db;

import java.sql.SQLException;

public class DateBaseDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Conn.Conn();
      //  Conn.CreateDB();
      //  Conn.WriteDB();
      //  Conn.WriteDB();
        Conn.ReadDB();
        Conn.CloseDB();
    }
}
