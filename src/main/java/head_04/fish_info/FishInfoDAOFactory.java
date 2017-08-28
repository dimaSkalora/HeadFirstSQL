package head_04.fish_info;

//Фабрика для создания экземпляра FishInfoDAO
public class FishInfoDAOFactory {
    public static FishInfoDAO getFishInfoDAO() {
        return new FishInfoSimpleDAO();
    }
}
