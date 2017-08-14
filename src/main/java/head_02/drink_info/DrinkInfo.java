package head_02.drink_info;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DrinkInfo extends JFrame{
    Connection conn;
    Statement statement;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    String header[] = {"DrinkName","Cost","Carbs","Color","Ice","Calories"};
    String data[][];
    DefaultTableModel model = new DefaultTableModel(data, header);
    JTable table = new JTable(model);
    JPanel pane = new JPanel();
    JPanel pane_input = new JPanel();
    JLabel lbl_drinkName = new JLabel(" DrinkName");
    JLabel lbl_cost = new JLabel(" Cost");
    JLabel lbl_carbs = new JLabel(" Carbs");
    JLabel lbl_color = new JLabel(" Color");
    JLabel lbl_ice = new JLabel(" Ice");
    JLabel lbl_calories = new JLabel(" Calories");
    JTextField txt_drinkName = new JTextField(16);
    JTextField txt_cost = new JTextField(11);
    JTextField txt_carbs = new JTextField(11);
    JTextField txt_color = new JTextField(20);
    JTextField txt_ice = new JTextField(1);
    JTextField txt_calories = new JTextField(11);
    JButton btn_add = new JButton("Add");
    JButton btn_loadDB = new JButton("LoadDataBase");


    public DrinkInfo(){
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
        setPreferredSize(new Dimension(500,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JTable DrinkInfo SQL");

        btn_add.setMnemonic(KeyEvent.VK_A);

        pane_input.setLayout(new GridLayout(0,2));
        pane_input.add(lbl_drinkName);
        pane_input.add(txt_drinkName);
        pane_input.add(lbl_cost);
        pane_input.add(txt_cost);
        pane_input.add(lbl_carbs);
        pane_input.add(txt_carbs);
        pane_input.add(lbl_color);
        pane_input.add(txt_color);
        pane_input.add(lbl_ice);
        pane_input.add(txt_ice);
        pane_input.add(lbl_calories);
        pane_input.add(txt_calories);

        pane_input.add(btn_add);
        pane_input.add(btn_loadDB);

        pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
        pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        btn_add.addActionListener(new AddInfoListener());
        btn_loadDB.addActionListener(new LoadDataBase());

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

    String setInsertInto(String drink_name, float cost, float carbs, String color, char ice,
                         int calories){
        String result ="INSERT INTO doughnut_ratings (drink_name, cost, carbs, color, ice," +
                "calories)";
        return result+"VALUES('"+drink_name+"', '"+cost+"', '"+carbs+"', '"+color+"', '"
                +ice+"', '"+calories+"')";
    }

    class AddInfoListener implements ActionListener {
        Info info = new Info();

        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btn_add){
                String drinkName = txt_drinkName.getText();
                String cost = txt_cost.getText();
                String carbs = txt_carbs.getText();
                String color = txt_color.getText();
                String ice = txt_ice.getText();
                String calories = txt_calories.getText();

                txt_drinkName.setText("");
                txt_cost.setText("");
                txt_carbs.setText("");
                txt_color.setText("");
                txt_ice.setText("");
                txt_calories.setText("");
                txt_drinkName.requestFocus();

                info.setDrink_name(drinkName);
                info.setCost(Float.parseFloat(cost));
                info.setCarbs(Float.parseFloat(carbs));
                info.setColor(color);
                info.setIce(ice.charAt(0));
                info.setCalories(Integer.parseInt(calories));

                try {
                    statement.execute(setInsertInto(info.getDrink_name(), info.getCost(), info.getCarbs(),
                            info.getColor(), info.getIce(), info.getCalories()));
                    System.out.println("Запись добавлена!");
                    model.insertRow(model.getRowCount(), new Object[]{drinkName, cost, carbs, color,
                            ice, calories});
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        }
    }

    class LoadDataBase implements ActionListener{
        Info info = new Info();
        String query = "SELECT * FROM  drink_info";

        @Override
        public void actionPerformed(ActionEvent e) {
            //Удаление строк с тоблицы
            while(model.getRowCount()>0){
                model.removeRow(0);
            }

            try {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    info.setDrink_name(resultSet.getString("drink_name"));
                    info.setCost(resultSet.getFloat("cost"));
                    info.setCarbs(resultSet.getFloat("carbs"));
                    info.setColor(resultSet.getString("color"));
                    info.setIce(resultSet.getString("ice").charAt(0));
                    info.setCalories(resultSet.getInt("calories"));
                    model.insertRow(model.getRowCount(), new Object[]{info.getDrink_name(), info.getCost(),
                            info.getCarbs(), info.getColor(), info.getIce(), info.getCalories()});
                }
                System.out.println("Загружена таблица из Базы Даных!");
                System.out.println("Таблица обновлена");
            } catch (SQLException e1) {
                System.out.println("Не удалось загрузится с БД!");
            }
        }
    }
}
