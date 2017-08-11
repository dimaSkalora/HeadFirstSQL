package head_01.doughnut_list;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DoughnutList extends JFrame{
    Connection conn;
    Statement statmt;

    private static final String URL="jdbc:mysql://localhost:3306/headfirstsql";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";

    String header[] = {"Name","Type","Cost"};
    String data[][];
    DefaultTableModel model = new DefaultTableModel(data, header);
    JTable table = new JTable(model);
    JPanel pane = new JPanel();
    JPanel pane_input = new JPanel();
    JLabel lbl_name = new JLabel(" Name");
    JLabel lbl_type = new JLabel(" Type");
    JLabel lbl_cost = new JLabel(" Cost");
    JTextField txt_name = new JTextField(10);
    JTextField txt_type = new JTextField(6);
    JTextField txt_cost = new JTextField(15);
    JButton btn_add = new JButton("Add");

    public DoughnutList(){
        // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
        conn = null;
        try {
            conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            statmt = conn.createStatement();
            System.out.println("База Подключена!");
        } catch (SQLException e) {
            System.out.println("Неудалось подключится к БД!");
        }

        setSize(200,200);
        setPreferredSize(new Dimension(350,300));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JTable to Excel Demo");

        btn_add.setMnemonic(KeyEvent.VK_A);

        pane_input.setLayout(new GridLayout(0,2));
        pane_input.add(lbl_name);
        pane_input.add(txt_name);
        pane_input.add(lbl_type);
        pane_input.add(txt_type);
        pane_input.add(lbl_cost);
        pane_input.add(txt_cost);
        pane_input.add(btn_add);

        pane.setLayout(new BorderLayout());
        pane.add(pane_input, BorderLayout.NORTH);
        pane.add(new JScrollPane(table), BorderLayout.CENTER);

        add(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        btn_add.addActionListener(new AksyonListener());

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

    String setInsertInto(String name, String type, float cost){
        String result ="INSERT INTO doughnut_list (doughnut_name, doughnut_type, doughnut_cost)";
        return result+"VALUES('"+name+"', '"+type+"', "+cost+")";
    }

    class AksyonListener implements ActionListener {
        Doughnut doughnut = new Doughnut();
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == btn_add){
                String name = txt_name.getText();
                String type = txt_type.getText();
                String cost = txt_cost.getText();

                txt_name.setText("");
                txt_type.setText("");
                txt_cost.setText("");
                txt_name.requestFocus();

                doughnut.setName(name);
                doughnut.setType(type);
                doughnut.setCost(Float.parseFloat(cost));

                try {
                    statmt.execute(setInsertInto(doughnut.getName(),doughnut.getType(),doughnut.getCost()));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                System.out.println("Таблица заполнена");

                model.insertRow(model.getRowCount(), new Object[]{name, type, cost});
            }
        }
    }

}
