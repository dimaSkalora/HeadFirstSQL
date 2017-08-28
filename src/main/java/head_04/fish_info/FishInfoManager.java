package head_04.fish_info;

import java.util.List;

//Класс для реализации функций над списком fishInfo
public class FishInfoManager {
    private FishInfoDAO dao;

    public FishInfoManager() {
        dao = FishInfoDAOFactory.getFishInfoDAO();
    }

    public Integer addFishInfo(FishInfo fishInfo){
        return  dao.addFishInfo(fishInfo);
    }

    public void updateFishInfo(FishInfo fishInfo){
        dao.updateFishInfo(fishInfo);
    }

    public void deleteFishInfo(Integer fishInfoId){
        dao.deleteFishInfo(fishInfoId);
    }

    public FishInfo getFishInfo(Integer fishInfoId){
        return dao.getFishInfo(fishInfoId);
    }

    //Получение списка fishInfo
    public List<FishInfo> findFishInfos(){
        return dao.findFishInfos();
    }
}
