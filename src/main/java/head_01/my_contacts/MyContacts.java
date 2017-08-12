package head_01.my_contacts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MyContacts extends JFrame{
    Connection conn;
    Statement statmt;
    Statement statement;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    String header[] = {"ListName","FirstName","Email","Gender","BirthDay","Profession",
                        "location","Status","Interests","Seeking"};
    String data[][];
    DefaultTableModel model = new DefaultTableModel(data, header);
    JTable table = new JTable(model);
    JPanel pane = new JPanel();
    JPanel pane_input = new JPanel();
    JLabel lbl_listName = new JLabel(" ListName");
    JLabel lbl_firstName = new JLabel(" FirstName");
    JLabel lbl_email = new JLabel(" Email");
    JLabel lbl_gender = new JLabel(" Gender");
    JLabel lbl_birthDay = new JLabel(" BirthDay");
    JLabel lbl_profession = new JLabel(" Profession");
    JLabel lbl_locatiion = new JLabel(" Location");
    JLabel lbl_status = new JLabel(" Status");
    JLabel lbl_interests = new JLabel(" Interests");
    JLabel lbl_seeking = new JLabel(" Seeking");
    JTextField txt_listName = new JTextField(30);
    JTextField txt_firstName = new JTextField(20);
    JTextField txt_email = new JTextField(50);
    JTextField txt_genger = new JTextField(1);
    JTextField txt_birthDay = new JTextField(15);
    JTextField txt_profession = new JTextField(50);
    JTextField txt_location = new JTextField(50);
    JTextField txt_status = new JTextField(20);
    JTextField txt_interests = new JTextField(100);
    JTextField txt_seeking = new JTextField(100);
    JButton btn_add = new JButton("Add");
    JButton btn_updateDB = new JButton("UpdateDataBase");


    public MyContacts(){
        // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
        conn = null;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statmt = conn.createStatement();
            statement = conn.createStatement();
            System.out.println("База Подключена!");
        } catch (SQLException e) {
            System.out.println("Неудалось подключится к БД!");
        }

        setSize(700,600);
        setPreferredSize(new Dimension(700,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JTable MyContacts SQL");

        btn_add.setMnemonic(KeyEvent.VK_A);

        pane_input.setLayout(new GridLayout(0,2));
        pane_input.add(lbl_listName);
        pane_input.add(txt_listName);
        pane_input.add(lbl_firstName);
        pane_input.add(txt_firstName);
        pane_input.add(lbl_email);
        pane_input.add(txt_email);
        pane_input.add(lbl_gender);
        pane_input.add(txt_genger);
        pane_input.add(lbl_birthDay);
        pane_input.add(txt_birthDay);
        pane_input.add(lbl_profession);
        pane_input.add(txt_profession);
        pane_input.add(lbl_locatiion);
        pane_input.add(txt_location);
        pane_input.add(lbl_status);
        pane_input.add(txt_status);
        pane_input.add(lbl_interests);
        pane_input.add(txt_interests);
        pane_input.add(lbl_seeking);
        pane_input.add(txt_seeking);

        pane_input.add(btn_add);
        pane_input.add(btn_updateDB);

        pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
        pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        btn_add.addActionListener(new AksyonListener());
        btn_updateDB.addActionListener(new UpdateDataBase());

        addWindowListener(new WindowListener() {
            public void windowActivated(WindowEvent event) {
            }
            public void windowClosed(WindowEvent event) {
            }

            public void windowClosing(WindowEvent event) {
              event.getWindow().setVisible(false);
                try {
                    conn.close();
                    statmt.close();
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

    String setInsertInto(String listName, String firstName, String email, String gender, String birthday,
                         String profession, String location, String status, String interests,
                            String seeking){
        String result ="INSERT INTO my_contacts (list_name, first_name, email, gender, birthday," +
                "profession,location, status, interests, seeking)";
        return result+"VALUES('"+listName+"', '"+firstName+"', '"+email+"', '"+gender+"', '"
                +birthday+"', '"+profession+"', '"+location+"', '"+status+"', '"
                +interests+"', '"+seeking+"')";
    }

    class AksyonListener implements ActionListener {
        Contact contact = new Contact();
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btn_add){
                String listName = txt_listName.getText();
                String firstName = txt_firstName.getText();
                String email = txt_email.getText();
                String gender = txt_genger.getText();
                String birthDay = txt_birthDay.getText();
                String profession = txt_profession.getText();
                String location = txt_location.getText();
                String status = txt_status.getText();
                String interests = txt_interests.getText();
                String seeking = txt_seeking.getText();

                txt_listName.setText("");
                txt_firstName.setText("");
                txt_email.setText("");
                txt_genger.setText("");
                txt_birthDay.setText("");
                txt_profession.setText("");
                txt_location.setText("");
                txt_status.setText("");
                txt_interests.setText("");
                txt_seeking.setText("");
                txt_listName.requestFocus();

                contact.setListName(listName);
                contact.setFirstName(firstName);
                contact.setEmail(email);
                contact.setGender(gender);
                contact.setBirthDay(birthDay);
                contact.setProfession(profession);
                contact.setLocation(location);
                contact.setStatus(status);
                contact.setInterests(interests);
                contact.setSeeking(seeking);

                try {
                    statmt.execute(setInsertInto(contact.getListName(), contact.getFirstName(), contact.getEmail(), contact.getGender(),
                            contact.getBirthDay(),contact.getProfession(),contact.getLocation(), contact.getStatus(),
                            contact.getInterests(), contact.getSeeking()));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                System.out.println("Таблица заполнена");

                model.insertRow(model.getRowCount(), new Object[]{listName, firstName, email,gender,
                birthDay,profession,location,status,interests,seeking});
            }
        }
    }

    class UpdateDataBase implements ActionListener{
        Contact contact = new Contact();
        String query = "SELECT * FROM  my_contacts";

        @Override
        public void actionPerformed(ActionEvent e) {
            //Удаление строк с тоблицы
            while(model.getRowCount()>0){
                model.removeRow(0);
            }

            try {
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next()){
                    contact.setListName(resultSet.getString("list_name"));
                    contact.setFirstName(resultSet.getString("first_name"));
                    contact.setEmail(resultSet.getString("email"));
                    contact.setGender(resultSet.getString("gender"));
                    contact.setBirthDay(resultSet.getString("birthday"));
                    contact.setProfession(resultSet.getString("profession"));
                    contact.setLocation(resultSet.getString("location"));
                    contact.setStatus(resultSet.getString("status"));
                    contact.setInterests(resultSet.getString("interests"));
                    contact.setSeeking(resultSet.getString("seeking"));
                    model.insertRow(model.getRowCount(), new Object[]{contact.getListName(),contact.getFirstName(),
                        contact.getEmail(),contact.getGender(),contact.getBirthDay(),contact.getProfession(),
                    contact.getLocation(),contact.getStatus(),contact.getInterests(),contact.getSeeking()});
                }
            } catch (SQLException e1) {
                System.out.println("Не удалось загрузится с БД!");
            }

            System.out.println("Таблица обновлена");
        }
    }
}
