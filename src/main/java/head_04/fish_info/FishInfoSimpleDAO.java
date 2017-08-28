package head_04.fish_info;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FishInfoSimpleDAO implements FishInfoDAO{
    private List<FishInfo> fishInfos = new ArrayList<>();

    //Прямо в конструкторе добавляем fishInfo
    public FishInfoSimpleDAO() {
        addFishInfo(new FishInfo("FishInfoSimpleDAO","FishInfoSimpleDAO", "FishInfoSimpleDAO","FishInfoSimpleDAO"));
    }

    @Override
    public Integer addFishInfo(FishInfo fishInfo) {
        Integer id = generateFishInfoId();
        fishInfo.setFish_id(id);
        fishInfos.add(fishInfo);
        return id;
    }

    @Override
    public void updateFishInfo(FishInfo fishInfo) {
        FishInfo fi = getFishInfo(fishInfo.getFish_id());
        if(fi != null) {
            fi.setCommon(fi.getCommon());
            fi.setSpecies(fi.getSpecies());
            fi.setLocation(fi.getLocation());
            fi.setWeight(fi.getWeight());
        }
    }

    @Override
    public void deleteFishInfo(Integer fishId) {
        for(Iterator<FishInfo> it = fishInfos.iterator(); it.hasNext();) {
            FishInfo fi = it.next();
            if(fi.getFish_id().equals(fishId)) {
                it.remove();
            }
        }
    }

    @Override
    public FishInfo getFishInfo(Integer fishId) {
        for(FishInfo fishInfo : fishInfos) {
            if(fishInfo.getFish_id().equals(fishId)) {
                return fishInfo;
            }
        }
        return null;
    }

    @Override
    public List<FishInfo> findFishInfos() {
        return fishInfos;
    }

    private Integer generateFishInfoId() {
        Integer fishInfoId = (int)(Math.random() * 100 + System.currentTimeMillis());
        while(getFishInfo(fishInfoId) != null) {
            fishInfoId = (int)(Math.random() * 100 + System.currentTimeMillis());
        }
        return fishInfoId;
    }
}
