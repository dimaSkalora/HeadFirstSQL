package head_04.fish_info;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class FishInfoFrame extends JFrame implements ActionListener {
    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private static final String SAVE = "SAVE";
    private static final String LOADING = "LOADING";

    private final FishInfoManager fishInfoManager = new FishInfoManager();
    private final JTable contactTable = new JTable();

    // Создаем панель для кнопок
    JPanel btnPanel = new JPanel();

    // В конструкторе мы создаем нужные элементы
    public FishInfoFrame() {
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        contactTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Используем layout manager
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);



        // усанавливаем у него layout
        btnPanel.setLayout(gridbag);
        // Создаем кнопки
        btnPanel.add(createButton(gridbag, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gridbag, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gridbag, gbc, "Исправить", EDIT));
        btnPanel.add(createButton(gridbag, gbc, "Удалить", DELETE));

        btnPanel.add(createButton(gridbag, gbc, "Сохранить", SAVE));
        btnPanel.add(createButton(gridbag, gbc, "Загрузить", LOADING));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);

        // Кладем панель со скролингом, внутри которой нахоится наша таблица
        // Теперь таблица может скроллироваться
        add(new JScrollPane(contactTable), BorderLayout.CENTER);

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Загружаем контакты
        loadContact();
    }

    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gridbag, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп роверяться в обработчике и мы будем знать, какую
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gridbag.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
            // Перегрузка данных
            case LOAD:
                loadContact();
                break;
            // Добавление записи
            case ADD:
                addContact();
                break;
            // Исправление записи
            case EDIT:
                editContact();
                break;
            // Удаление записи
            case DELETE:
                deleteContact();
                break;
            case SAVE:
                saveContactFile();
                break;
            case LOADING:
                loadingContactFile();
                break;
        }
    }


    // Загрухить список fishInfo
    private void loadContact() {
        // Обращаемся к классу для загрузки списка fishInfo
        List<FishInfo> fishInfos =  fishInfoManager.findFishInfos();
        // Создаем модель, которой передаем полученный список
        FishInfoModel fim = new FishInfoModel(fishInfos);
        // Передаем нашу модель таблице - и она может ее отображать
        contactTable.setModel(fim);
    }

    // Добавление контакта
    private void addContact() {
        // Создаем диалог для ввода данных
        EditFishInfoDialog efid = new EditFishInfoDialog();
        // Обрабатываем закрытие диалога
        saveContact(efid);
    }

    // Редактирование контакта
    private void editContact() {
        // Получаем выделеннуб строку
        int sr = contactTable.getSelectedRow();
        // если строка выделена - можно ее редактировать
        if (sr != -1) {
            // Получаем ID fishInfo
            Integer id = Integer.parseInt(contactTable.getModel().getValueAt(sr, 0).toString());
            // получаем данные fishInfo по его ID
            FishInfo fi = fishInfoManager.getFishInfo(id);
            // Создаем диалог для ввода данных и передаем туда fishInfo
            EditFishInfoDialog efid = new EditFishInfoDialog(fishInfoManager.getFishInfo(id));
            // Обрабатываем закрытие диалога
            saveContact(efid);
        } else {
            // Если строка не выделена - сообщаем об этом
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для редактирования");
        }
    }

    // Удаление контакта
    private void deleteContact() {
        // Получаем выделеннуб строку
        int sr = contactTable.getSelectedRow();
        if (sr != -1) {
            // Получаем ID fishInf
            Integer id = Integer.parseInt(contactTable.getModel().getValueAt(sr, 0).toString());
            // Удаляем контакт
            fishInfoManager.deleteFishInfo(id);
            // перегружаем список fishInfo
            loadContact();
        } else {
            JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
        }
    }

    private void saveContactFile(){
        try{
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(btnPanel);
            File myFile = fileSave.getSelectedFile();
            //FileReader - поток соединения для символоЮ который подключается к текстовому файлу
            FileReader fileReader = new FileReader(myFile);
            //Для более эффективного чтеения соединим FileReader с BufferedReader.
            //Тогда FileReader будет обращатся к файлу в том случаем если буфер будет пуст
            //(потому что програма прочла все, что там находилось)
            BufferedReader reader = new BufferedReader(fileReader);

            //Создаем строковую переменую для временного хранения каждой строки
            //в процессе чтения
            String line=null;


            while((line=reader.readLine())!=null){
                //Этот код говорит: "Прочитай строку текста и присвой ее строковой переменной line
                //Пока эта переменая не пуста(так как там было что прочитать)выводи на
                // экран только что прочтенную строку"

                System.out.println(line);
            }

            //boolean isContain1 = line.contains(sw);
            //System.out.println(isContain1);

            reader.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void loadingContactFile(){
        try{
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(btnPanel);
            File myFile = fileOpen.getSelectedFile();
            //FileReader - поток соединения для символоЮ который подключается к текстовому файлу
            FileReader fileReader = new FileReader(myFile);
            //Для более эффективного чтеения соединим FileReader с BufferedReader.
            //Тогда FileReader будет обращатся к файлу в том случаем если буфер будет пуст
            //(потому что програма прочла все, что там находилось)
            BufferedReader reader = new BufferedReader(fileReader);

            //Создаем строковую переменую для временного хранения каждой строки
            //в процессе чтения
            String line=null;


            while((line=reader.readLine())!=null){
                //Этот код говорит: "Прочитай строку текста и присвой ее строковой переменной line
                //Пока эта переменая не пуста(так как там было что прочитать)выводи на
                // экран только что прочтенную строку"

                System.out.println(line);
            }

            //boolean isContain1 = line.contains(sw);
            //System.out.println(isContain1);

            reader.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // Общий метод для добавления и изменения fishInfo
    private void saveContact(EditFishInfoDialog efid) {
        // Если мы нажали кнопку SAVE
        if (efid.isSave()) {
            // Получаем контакт из диалогового окна
            FishInfo fishInfo = efid.getContact();
            if (fishInfo.getFish_id() != null) {
                // Если ID у контакта есть, то мы его обновляем
                fishInfoManager.updateFishInfo(fishInfo);
            } else {
                // Если у контакта нет ID - значит он новый и мы его добавляем
                fishInfoManager.addFishInfo(fishInfo);
            }
            loadContact();
        }
    }
}
