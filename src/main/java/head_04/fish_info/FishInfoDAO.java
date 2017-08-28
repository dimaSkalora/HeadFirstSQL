package head_04.fish_info;

import java.util.List;

//Интерфейс для определения функций хранлиза данных о FishInfo
public interface FishInfoDAO {
     Integer addFishInfo(FishInfo fishInfo);
     void updateFishInfo(FishInfo fishInfo);
     void deleteFishInfo(Integer fishId);
     FishInfo getFishInfo(Integer fishId);
     List<FishInfo> findFishInfos();
}
