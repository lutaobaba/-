import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

//站点的类结构
public class Station {

    private String name;//站名
    public Station before;//前一站
    public Station next;//后一站
    
    //本站到某一目标站所经过的所有站的集合
    private Map<Station, LinkedHashSet<Station>> orderSetMap = new HashMap<Station, LinkedHashSet<Station>>();
    
	public Map<Station, LinkedHashSet<Station>> getOrderSetMap() {
	        return orderSetMap;
	}
	
    public Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //获取所有经过station的站点
    public LinkedHashSet<Station> getAllPassed(Station station) {
        if (orderSetMap.get(station) == null) {
            LinkedHashSet<Station> set = new LinkedHashSet<Station>();
            set.add(this);
            orderSetMap.put(station, set);
        }
        return orderSetMap.get(station);
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Station) {
            Station s = (Station) obj;
            if (s.getName().equals(this.getName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }
}