package head_02.doughnut_ratings;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DoughnutRatings extends JFrame{
    Connection conn;
    Statement statement;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    String header[] = {"Location","Time","Date","Type","Rating","Comments"};
    String data[][];
    DefaultTableModel model = new DefaultTableModel(data, header);
    JTable table = new JTable(model);
    JPanel pane = new JPanel();
    JPanel pane_input = new JPanel();
    JLabel lbl_location = new JLabel(" Location");
    JLabel lbl_time = new JLabel(" Time(hh:mm:ss)");
    JLabel lbl_date = new JLabel(" Date(yyyy-MM-dd)");
    JLabel lbl_type = new JLabel(" Type");
    JLabel lbl_rating = new JLabel(" Rating");
    JLabel lbl_comments = new JLabel(" Comments");
    JTextField txt_location = new JTextField(16);
    JTextField txt_time = new JTextField(20);
    JTextField txt_date = new JTextField(10);
    JTextField txt_type = new JTextField(20);
    JTextField txt_rating = new JTextField(10);
    JTextField txt_comments = new JTextField(250);
    JButton btn_add = new JButton("Add");
    JButton btn_loadDB = new JButton("LoadDataBase");


    public DoughnutRatings(){
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
        setPreferredSize(new Dimension(550,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JTable DoughnutRatings SQL");

        btn_add.setMnemonic(KeyEvent.VK_A);

        pane_input.setLayout(new GridLayout(0,2));
        pane_input.add(lbl_location);
        pane_input.add(txt_location);
        pane_input.add(lbl_time);
        pane_input.add(txt_time);
        pane_input.add(lbl_date);
        pane_input.add(txt_date);
        pane_input.add(lbl_type);
        pane_input.add(txt_type);
        pane_input.add(lbl_rating);
        pane_input.add(txt_rating);
        pane_input.add(lbl_comments);
        pane_input.add(txt_comments);

        pane_input.add(btn_add);
        pane_input.add(btn_loadDB);

        pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
        pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        btn_add.addActionListener(new AddDoughnutListener());
        btn_loadDB.addActionListener(new DoughnutRatings.LoadDataBase());

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

    String setInsertInto(String location, Time time, Date date, String type, int rating,
                         String comments){
        String result ="INSERT INTO doughnut_ratings (location, time, date, type, rating," +
                "comments)";
        return result+"VALUES('"+location+"', '"+time+"', '"+date+"', '"+type+"', '"
                +rating+"', '"+comments+"')";
    }

    class AddDoughnutListener implements ActionListener {
        Doughnut doughnut = new Doughnut();

        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btn_add){
                String location = txt_location.getText();
                String time = txt_time.getText();
                String date = txt_date.getText();
                String type = txt_type.getText();
                String reting = txt_rating.getText();
                String comments = txt_comments.getText();

                txt_location.setText("");
                txt_time.setText("");
                txt_date.setText("");
                txt_type.setText("");
                txt_rating.setText("");
                txt_comments.setText("");
                txt_location.requestFocus();

                doughnut.setLocation(location);
                doughnut.setTime(Time.valueOf(time));
                doughnut.setDate(Date.valueOf(date));
                doughnut.setType(type);
                doughnut.setRating(Integer.parseInt(reting));
                doughnut.setComments(comments);

                try {
                    statement.execute(setInsertInto(doughnut.getLocation(), doughnut.getTime(), doughnut.getDate(),
                            doughnut.getType(), doughnut.getRating(), doughnut.getComments()));
                    System.out.println("Запись добавлена!");
                    model.insertRow(model.getRowCount(), new Object[]{location, time, date, type,
                            reting, comments});
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        }
    }

    class LoadDataBase implements ActionListener{
        Doughnut doughnut = new Doughnut();
        String query = "SELECT * FROM  doughnut_ratings";

        @Override
        public void actionPerformed(ActionEvent e) {
            //Удаление строк с тоблицы
            while(model.getRowCount()>0){
                model.removeRow(0);
            }

            try {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    doughnut.setLocation(resultSet.getString("location"));
                    doughnut.setTime(resultSet.getTime("time"));
                    doughnut.setDate(resultSet.getDate("date"));
                    doughnut.setType(resultSet.getString("type"));
                    doughnut.setRating(resultSet.getInt("rating"));
                    doughnut.setComments(resultSet.getString("comments"));
                    model.insertRow(model.getRowCount(), new Object[]{doughnut.getLocation(), doughnut.getTime(),
                            doughnut.getDate(), doughnut.getType(), doughnut.getRating(), doughnut.getComments()});
                }
                System.out.println("Загружена таблица из Базы Даных!");
                System.out.println("Таблица обновлена");
            } catch (SQLException e1) {
                System.out.println("Не удалось загрузится с БД!");
            }
        }
    }
}
