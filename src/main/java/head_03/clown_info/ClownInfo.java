package head_03.clown_info;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Iterator;

public class ClownInfo extends JFrame{
    Connection conn;
    Statement statement;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    // Получаем выделеную строку для редактирование
    Info getSelectedRowUpdate;

    String header[] = {"Name","LastSeen","Appearance","Activities"};
    String data[][];
    DefaultTableModel model = new DefaultTableModel(data, header);
    JTable table = new JTable(model);
    JPanel pane = new JPanel();
    JPanel pane_input = new JPanel();
    JLabel lbl_name = new JLabel(" Name");
    JLabel lbl_lastSeen = new JLabel(" LastSeen");
    JLabel lbl_appearance = new JLabel(" Appearance");
    JLabel lbl_activities = new JLabel(" Activities");
    JTextField txt_name = new JTextField(45);
    JTextField txt_lastSeen = new JTextField(45);
    JTextField txt_appearence = new JTextField(45);
    JTextField txt_activities = new JTextField(45);
    JButton btn_add = new JButton("Add");
    JButton btn_loadDB = new JButton("LoadDataBase");
    JButton btn_clearText = new JButton("ClearText");
    JButton btn_updateText = new JButton("UpdateText");
    JButton btn_deleteRow = new JButton("DeleteRow");


    public ClownInfo(){
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
        setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JTable ClownInfo SQL");

        btn_add.setMnemonic(KeyEvent.VK_A);

        pane_input.setLayout(new GridLayout(0,2));
        pane_input.add(lbl_name);
        pane_input.add(txt_name);
        pane_input.add(lbl_lastSeen);
        pane_input.add(txt_lastSeen);
        pane_input.add(lbl_appearance);
        pane_input.add(txt_appearence);
        pane_input.add(lbl_activities);
        pane_input.add(txt_activities);


        pane_input.add(btn_add);
        pane_input.add(btn_loadDB);
        pane_input.add(btn_clearText);
        pane_input.add(btn_updateText);
        pane_input.add(btn_deleteRow);

        pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
        pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        btn_add.addActionListener(new AddInfoListener());
        btn_loadDB.addActionListener(new LoadDataBase());
        btn_updateText.addActionListener(new UpdateText());
        btn_deleteRow.addActionListener(new DereleRow());

        btn_clearText.addActionListener(new ClearText());

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

    String setInsertInto(String name, String last_seen, String appearance, String activities){
        String result ="INSERT INTO clown_info (name, last_seen, appearance, activities)";
        return result+"VALUES('"+name+"', '"+last_seen+"', '"+appearance+"', '"+activities+"')";
    }

    String setUpdateText(String name, String last_seen, String appearance, String activities){
        String result ="UPDATE clown_info SET name ='"+name+"', last_seen ='"+last_seen
                +"', appearance = '"+appearance+"', activities = '"+activities+"'";
        return result+"WHERE name ='"+getSelectedRowUpdate.getName()+"'AND last_seen ='"+getSelectedRowUpdate.getLast_seen()
                +"' AND appearance = '"+getSelectedRowUpdate.getAppearance()+"' AND activities = '"+getSelectedRowUpdate.getActivities()+"'";
    }

    String setDeleteRow(String name, String last_seen, String appearance, String activities){
        String result ="DELETE FROM clown_info ";
        return result+"WHERE name ='"+name+"'AND last_seen ='"+last_seen+"' AND appearance = '"+
                appearance+"' AND activities = '"+activities+"'";
    }

    //Общий метод для добавления и изменения контакта
    private void  saveInfo(EditInfoDialog eid){
        // Если мы нажали кнопку SAVE
        if (eid.isSave()) {
            // Получаем контакт из диалогового окна
            Info info = eid.getContact();

            if (info.getName() != null) {
                // Если ID у контакта есть, то мы его обновляем
                try {
                    statement.executeUpdate(setUpdateText(info.getName(),info.getLast_seen(),info.getAppearance(),
                            info.getActivities()));
                    System.out.println("Строка успешно изменена");
                } catch (SQLException e) {
                    System.out.println("Изменение строки не возможно!");
                }
            }
        }
    }

    class AddInfoListener implements ActionListener {
        Info info = new Info();

        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btn_add){
                String name = txt_name.getText();
                String last_seen = txt_lastSeen.getText();
                String appearance = txt_appearence.getText();
                String activities = txt_activities.getText();

                txt_name.setText("");
                txt_lastSeen.setText("");
                txt_appearence.setText("");
                txt_activities.setText("");
                txt_name.requestFocus();

                info.setName(name);
                info.setLast_seen(last_seen);
                info.setAppearance(appearance);
                info.setActivities(activities);


                try {
                    statement.execute(setInsertInto(info.getName(), info.getLast_seen(), info.getAppearance(),
                            info.getActivities()));
                    System.out.println("Запись добавлена!");
                    model.insertRow(model.getRowCount(), new Object[]{name, last_seen, appearance, activities});
                } catch (SQLException e1) {
                    System.out.println("Невозможно добавить новую запись!");
                }
            }
        }
    }

    class LoadDataBase implements ActionListener{
        Info info = new Info();
        String query = "SELECT * FROM  clown_info";

        @Override
        public void actionPerformed(ActionEvent e) {
            //Удаление строк с тоблицы
            while(model.getRowCount()>0){
                model.removeRow(0);
            }

            try {

                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    info.setName(resultSet.getString("name"));
                    info.setLast_seen(resultSet.getString("last_seen"));
                    info.setAppearance(resultSet.getString("appearance"));
                    info.setActivities(resultSet.getString("activities"));

                    model.insertRow(model.getRowCount(), new Object[]{info.getName(), info.getLast_seen(),
                            info.getAppearance(), info.getActivities()});
                }

                System.out.println("Загружена таблица из Базы Даных!");
                System.out.println("Таблица обновлена");
            } catch (SQLException e1) {
                System.out.println("Не удалось загрузится с БД!");
            }
        }
    }

    class ClearText implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            txt_name.setText("");
            txt_lastSeen.setText("");
            txt_appearence.setText("");
            txt_activities.setText("");
            txt_name.requestFocus();
        }
    }

    class UpdateText implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Получаем выделеннуб строку
            int sr = table.getSelectedRow();
            // если строка выделена - можно ее редактировать
            if (sr != -1) {
                // Получаем ID контакта
                String name = (table.getModel().getValueAt(sr, 0).toString());
                String last_seen = (table.getModel().getValueAt(sr, 1).toString());
                String appearance = (table.getModel().getValueAt(sr, 2).toString());
                String activities = (table.getModel().getValueAt(sr, 3).toString());
                // получаем данные контакта по его ID
                getSelectedRowUpdate = new Info(name,last_seen,appearance,activities);

                // Создаем диалог для ввода данных и передаем туда контакт
                EditInfoDialog eid = new EditInfoDialog(getSelectedRowUpdate);
                // Обрабатываем закрытие диалога
                saveInfo(eid);
                model.insertRow(model.getRowCount(), new Object[]{name, last_seen, appearance, activities});

            } else {
                // Если строка не выделена - сообщаем об этом
                JOptionPane.showMessageDialog(pane, "Вы должны выделить строку для редактирования");
            }
        }
    }

    class DereleRow implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // Получаем выделеннуб строку
            int sr = table.getSelectedRow();
            if (sr != -1) {
                // Получаем ID контакта
                String name = (table.getModel().getValueAt(sr, 0).toString());
                String last_seen = (table.getModel().getValueAt(sr, 1).toString());
                String appearance = (table.getModel().getValueAt(sr, 2).toString());
                String activities = (table.getModel().getValueAt(sr, 3).toString());
                // получаем данные контакта по его ID
                getSelectedRowUpdate = new Info(name,last_seen,appearance,activities);

                try {
                    statement.execute(setDeleteRow(getSelectedRowUpdate.getName(),getSelectedRowUpdate.getLast_seen(),
                            getSelectedRowUpdate.getAppearance(), getSelectedRowUpdate.getActivities()));
                    System.out.println("Строка успешно удалена!");

                } catch (SQLException e1) {
                    System.out.println("Невозможно удалить строку!");
                }
            } else {
                JOptionPane.showMessageDialog(pane, "Вы должны выделить строку для удаления");
            }
        }
    }

}
