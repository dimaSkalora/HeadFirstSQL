package head_01.demo_db;

import java.sql.*;

public class Conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);

        statmt = conn.createStatement();
        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {

        statmt.execute("CREATE TABLE users (id INT(20), name VARCHAR(50), phone INT(20));");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO users (id, name, phone) VALUES (12,'Petya', 125453); ");


        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM users");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            String  phone = resSet.getString("phone");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "phone = " + phone );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }
}
