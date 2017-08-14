package head_02.black_book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BlackBook extends JFrame{
    Connection conn;
    Statement statement;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    String header[] = {"DateName","Rating"};
    String data[][];
    DefaultTableModel model = new DefaultTableModel(data, header);
    JTable table = new JTable(model);
    JPanel pane = new JPanel();
    JPanel pane_input = new JPanel();
    JLabel lbl_date_name = new JLabel(" DateName");
    JLabel lbl_rating = new JLabel(" Rating");

    JTextField txt_date_name = new JTextField(15);
    JTextField txt_rating = new JTextField(15);
    JButton btn_add = new JButton("Add");
    JButton btn_loadDB = new JButton("LoadDataBase");


    public BlackBook(){
        // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
        conn = null;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statement = conn.createStatement();
            System.out.println("База Подключена!");
        } catch (SQLException e) {
            System.out.println("Неудалось подключится к БД!");
        }

        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        setSize(700,600);
        setPreferredSize(new Dimension(400,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JTable BlackBook SQL");


        pane_input.setLayout(new GridLayout(0,2));
        pane_input.add(lbl_date_name);
        pane_input.add(txt_date_name);
        pane_input.add(lbl_rating);
        pane_input.add(txt_rating);

        pane_input.add(btn_add);
        pane_input.add(btn_loadDB);

        pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
        pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        btn_add.addActionListener(new AddBookkListener());
        btn_loadDB.addActionListener(new BlackBook.LoadDataBase());

        addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent event) {
            }
            public void windowClosed(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
                event.getWindow().setVisible(false);
                try {
                    conn.close();
                    statement.close();
                    if(conn.isClosed()){
                        System.out.println("Соединение с БД Закрыто!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("Програма завершена!");
                System.exit(0);
            }

            public void windowDeactivated(WindowEvent event) {
            }
            public void windowDeiconified(WindowEvent event) {
            }
            public void windowIconified(WindowEvent event) {
            }
            public void windowOpened(WindowEvent event) {
            }
        });
    }

    String setInsertInto(String date_name, String rating){
        String result ="INSERT INTO black_book (date_name, rating)";
        return result+"VALUES('"+date_name+"', '"+rating+"')";
    }

    class AddBookkListener implements ActionListener {
        Book book = new Book();
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btn_add){
                String date_name = txt_date_name.getText();
                String rating = txt_rating.getText();

                txt_date_name.setText("");
                txt_rating.setText("");
                txt_date_name.requestFocus();

                book.setDate_name(date_name);
                book.setRating(rating);


                try {
                    statement.execute(setInsertInto(book.getDate_name(), book.getRating()));
                    model.insertRow(model.getRowCount(), new Object[]{date_name, rating});
                    System.out.println("Запись добавлена!");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    class LoadDataBase implements ActionListener{
        Book book = new Book();
        String query = "SELECT * FROM  black_book";

        @Override
        public void actionPerformed(ActionEvent e) {
            //Удаление строк с тоблицы
            while(model.getRowCount()>0){
                model.removeRow(0);
            }

            try {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    book.setDate_name(resultSet.getString("date_name"));
                    book.setRating(resultSet.getString("rating"));
                    model.insertRow(model.getRowCount(), new Object[]{book.getDate_name(), book.getRating()});
                }
                System.out.println("Загружена таблица из Базы Даных!");
                System.out.println("Таблица обновлена");
            } catch (SQLException e1) {
                System.out.println("Не удалось загрузится с БД!");
            }
        }
    }
}
