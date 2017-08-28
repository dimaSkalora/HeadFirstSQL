package head_04.fish_info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditFishInfoDialog extends JDialog implements ActionListener {

    // Заголовки кнопок
    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";

    // Размер отступа
    private static final int PAD = 10;
    // Ширина метки
    private static final int W_L = 100;
    //Ширина поля для ввода
    private static final int W_T = 300;
    // Ширина кнопки
    private static final int W_B = 120;
    // высота элемента - общая для всех
    private static final int H_B = 25;

    // Поле для ввода Имени
    private final JTextPane txtCommon = new JTextPane();
    // Поле для ввода Фамилии
    private final JTextPane txtSpecies = new JTextPane();
    // Поле для ввода Телефона
    private final JTextPane txtLocation = new JTextPane();
    // Поле для ввода E-mail
    private final JTextPane txtWeight = new JTextPane();

    // Поле для хранения ID контакта, если мы собираемся редактировать
    // Если это новый контакт - cjntactId == null
    private Integer fishInfoId = null;
    // Надо ли записывать изменения после закрытия диалога
    private boolean save = false;

    public EditFishInfoDialog() {
        this(null);
    }

    public EditFishInfoDialog(FishInfo fishInfo) {
        // Убираем layout - будем использовать абсолютные координаты
        setLayout(null);

        // Выстраиваем метки и поля для ввода
        buildFields();
        // Если нам передали контакт - заполняем поля формы
        initFields(fishInfo);
        // выстариваем кнопки
        buildButtons();

        // Диалог в модальном режиме - только он активен
        setModal(true);
        // Запрещаем изменение размеров
        setResizable(false);
        // Выставляем размеры формы
        setBounds(300, 300, 450, 200);
        // Делаем форму видимой
        setVisible(true);
    }

    // Размещаем метки и поля ввода на форме
    private void buildFields() {
        // Набор метки и поля для
        JLabel lblFirstName = new JLabel("Common:");
        // Выравнивание текста с правой стороны
        lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        // Выставляем координаты метки
        lblFirstName.setBounds(new Rectangle(PAD, 0 * H_B + PAD, W_L, H_B));
        // Кладем метку на форму
        add(lblFirstName);
        // Выставляем координаты поля
        txtCommon.setBounds(new Rectangle(W_L + 2 * PAD, 0 * H_B + PAD, W_T, H_B));
        // Делаем "бордюр" для поля
        txtCommon.setBorder(BorderFactory.createEtchedBorder());
        // Кладем поле на форму
        add(txtCommon);

        // Набор метки и поля для
        JLabel lblLastName = new JLabel("Species:");
        lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblLastName.setBounds(new Rectangle(PAD, 1 * H_B + PAD, W_L, H_B));
        add(lblLastName);
        txtSpecies.setBounds(new Rectangle(W_L + 2 * PAD, 1 * H_B + PAD, W_T, H_B));
        txtSpecies.setBorder(BorderFactory.createEtchedBorder());
        add(txtSpecies);

        // Набор метки и поля для
        JLabel lblPhone = new JLabel("Location:");
        lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPhone.setBounds(new Rectangle(PAD, 2 * H_B + PAD, W_L, H_B));
        add(lblPhone);
        txtLocation.setBounds(new Rectangle(W_L + 2 * PAD, 2 * H_B + PAD, W_T, H_B));
        txtLocation.setBorder(BorderFactory.createEtchedBorder());
        add(txtLocation);

        // Набор метки и поля для
        JLabel lblEmail = new JLabel("Weight:");
        lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
        lblEmail.setBounds(new Rectangle(PAD, 3 * H_B + PAD, W_L, H_B));
        add(lblEmail);
        txtWeight.setBounds(new Rectangle(W_L + 2 * PAD, 3 * H_B + PAD, W_T, H_B));
        txtWeight.setBorder(BorderFactory.createEtchedBorder());
        add(txtWeight);
    }

    // Если нам епередали контакт - заполняем поля из контакта
    private void initFields(FishInfo fishInfo) {
        if (fishInfo != null) {
            fishInfoId = fishInfo.getFish_id();
            txtCommon.setText(fishInfo.getCommon());
            txtSpecies.setText(fishInfo.getSpecies());
            txtWeight.setText(fishInfo.getLocation());
            txtLocation.setText(fishInfo.getWeight());
        }
    }

    // Размещаем кнопки на форме
    private void buildButtons() {
        JButton btnSave = new JButton("SAVE");
        btnSave.setActionCommand(SAVE);
        btnSave.addActionListener(this);
        btnSave.setBounds(new Rectangle(PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);
        btnCancel.setBounds(new Rectangle(W_B + 2 * PAD, 5 * H_B + PAD, W_B, H_B));
        add(btnCancel);
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        // Если нажали кнопку SAVE (сохранить изменения) - запоминаем этой
        save = SAVE.equals(action);
        // Закрываем форму
        setVisible(false);
    }

    // Надо ли сохранять изменения
    public boolean isSave() {
        return save;
    }

    // Создаем fishInfo из заполенных полей, который можно будет записать
    public FishInfo getContact() {
        FishInfo fishInfo = new FishInfo(fishInfoId, txtCommon.getText(),
                txtSpecies.getText(), txtLocation.getText(), txtWeight.getText());
        //  System.out.println(contact);
        return fishInfo;

    }
}
