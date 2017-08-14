package head_02.easy_drinks;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class EasyDrinks extends JFrame{
    Connection conn;
    Statement statement;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    String header[] = {"DrinkName","Main","Amount1","Second","Amount2","Directions"};
    String data[][];
    DefaultTableModel model = new DefaultTableModel(data, header);
    JTable table = new JTable(model);
    JPanel pane = new JPanel();
    JPanel pane_input = new JPanel();
    JLabel lbl_drink_name = new JLabel(" DrinkName");
    JLabel lbl_main = new JLabel(" Main");
    JLabel lbl_amount1 = new JLabel(" Amount1");
    JLabel lbl_second = new JLabel(" Second");
    JLabel lbl_amount2 = new JLabel(" Amount2");
    JLabel lbl_directions = new JLabel(" Directions");
    JTextField txt_drink_name = new JTextField(16);
    JTextField txt_main = new JTextField(20);
    JTextField txt_amount1 = new JTextField(10);
    JTextField txt_second = new JTextField(20);
    JTextField txt_amount2 = new JTextField(10);
    JTextField txt_directions = new JTextField(250);
    JButton btn_add = new JButton("Add");
    JButton btn_loadDB = new JButton("LoadDataBase");


    public EasyDrinks(){
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
        setPreferredSize(new Dimension(700,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JTable EasyDrink SQL");

        btn_add.setMnemonic(KeyEvent.VK_A);

        pane_input.setLayout(new GridLayout(0,2));
        pane_input.add(lbl_drink_name);
        pane_input.add(txt_drink_name);
        pane_input.add(lbl_main);
        pane_input.add(txt_main);
        pane_input.add(lbl_amount1);
        pane_input.add(txt_amount1);
        pane_input.add(lbl_second);
        pane_input.add(txt_second);
        pane_input.add(lbl_amount2);
        pane_input.add(txt_amount2);
        pane_input.add(lbl_directions);
        pane_input.add(txt_directions);

        pane_input.add(btn_add);
        pane_input.add(btn_loadDB);

        pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
        pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        btn_add.addActionListener(new EasyDrinks.AddDrinkListener());
        btn_loadDB.addActionListener(new EasyDrinks.LoadDataBase());

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

    String setInsertInto(String drink_name, String main, float amount1, String second, float amount2,
                         String directions){
        String result ="INSERT INTO easy_drinks (drink_name, main, amount1, second, amount2," +
                "directions)";
        return result+"VALUES('"+drink_name+"', '"+main+"', '"+amount1+"', '"+second+"', '"
                +amount2+"', '"+directions+"')";
    }

    class AddDrinkListener implements ActionListener {
        Drink drink = new Drink();
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btn_add){
                String drink_name = txt_drink_name.getText();
                String main = txt_main.getText();
                String amount1 = txt_amount1.getText();
                String second = txt_second.getText();
                String amount2 = txt_amount2.getText();
                String directions = txt_directions.getText();


                txt_drink_name.setText("");
                txt_main.setText("");
                txt_amount1.setText("");
                txt_second.setText("");
                txt_amount2.setText("");
                txt_directions.setText("");
                txt_drink_name.requestFocus();

                drink.setDrink_name(drink_name);
                drink.setMain(main);
                drink.setAmount1(Float.parseFloat(amount1));
                drink.setSecond(second);
                drink.setAmount2(Float.parseFloat(amount2));
                drink.setDirections(directions);

                try {
                    statement.execute(setInsertInto(drink.getDrink_name(), drink.getMain(), drink.getAmount1(),
                            drink.getSecond(), drink.getAmount2(), drink.getDirections()));
                    System.out.println("Запись добавлена!");
                    model.insertRow(model.getRowCount(), new Object[]{drink_name, main, amount1, second,
                            amount2, directions});
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        }
    }

    class LoadDataBase implements ActionListener{
        Drink drink = new Drink();
        String query = "SELECT * FROM  easy_drinks";

        @Override
        public void actionPerformed(ActionEvent e) {
            //Удаление строк с тоблицы
            while(model.getRowCount()>0){
                model.removeRow(0);
            }

            try {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    drink.setDrink_name(resultSet.getString("drink_name"));
                    drink.setMain(resultSet.getString("main"));
                    drink.setAmount1(resultSet.getFloat("amount1"));
                    drink.setSecond(resultSet.getString("second"));
                    drink.setAmount2(resultSet.getFloat("amount2"));
                    drink.setDirections(resultSet.getString("directions"));
                    model.insertRow(model.getRowCount(), new Object[]{drink.getDrink_name(),drink.getMain(),
                    drink.getAmount1(),drink.getSecond(),drink.getAmount2(),drink.getDirections()});
                }
                System.out.println("Загружена таблица из Базы Даных!");
                System.out.println("Таблица обновлена");
            } catch (SQLException e1) {
                System.out.println("Не удалось загрузится с БД!");
            }
        }
    }
}
