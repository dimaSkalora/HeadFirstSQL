package head_04.fish_info;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FishInfoModel extends AbstractTableModel {
    // Список загловков для колонок в таблице
    private static final String[] headers = {"FishId", "Common", "Species", "Location", "Weight"};

    // Здесь мы храним список fishInfo, которые будем отображать в таблице
    private List<FishInfo> fishInfos;

    public FishInfoModel(List<FishInfo> fishInfos) {
        this.fishInfos = fishInfos;
    }

    // Получить количество строк в таблице - у нас это размер коллекции
    @Override
    public int getRowCount() {
        return fishInfos.size();
    }

    // Получить количество столбцов - их у нас стольк же, сколько полей
    @Override
    public int getColumnCount() {
        return 5;
    }

    // Вернуть загловок колонки - мы его берем из массива headers
    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    // Получить объект для тображения в кокнретной ячейке таблицы
    // В данном случае мы отдаем строковое представление поля
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FishInfo contact = fishInfos.get(rowIndex);
        // В зависимости от номера колонки возвращаем то или иное поле контакта
        switch (columnIndex) {
            case 0:
                return contact.getFish_id().toString();
            case 1:
                return contact.getCommon();
            case 2:
                return contact.getSpecies();
            case 3:
                return contact.getLocation();
            default:
                return contact.getWeight();
        }
    }
}
