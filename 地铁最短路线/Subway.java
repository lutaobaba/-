import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

//实现迪杰斯特拉算法，找到换乘路线
public class Subway {

    private List<Station> allPassedStation = new ArrayList<Station>();//放读取过的站点的信息
    String Shortest = "";//最短路径经过的站点名字

    
    public String Dijkstra(Station startStation, Station endStation) {
        
        //初始化起点站到某一站的集合
        if (startStation.getOrderSetMap().isEmpty()) {
            List<Station> Linkedstations = getAllLinked(startStation);
            for (Station s : Linkedstations) {
                startStation.getAllPassed(s).add(s);
            }
        }
        
        //如果找完了全部的路径，则返回路线
        if (allPassedStation.size() == LineNoName.totalStaion) {
        	Shortest+="一共经过" + (startStation.getAllPassed(endStation).size() - 1) + "站"+"\n"+"路线如下："+"\n";
          
        	
            for (Station station : startStation.getAllPassed(endStation)) {
            	Shortest+=station.getName() + " ";
            }
            return Shortest;
        }
        if (!allPassedStation.contains(startStation)) {
            allPassedStation.add(startStation);
        }
        
        Station short1 = getShortestPath(startStation);//获取距离起点站最近的一个站

        //如果找到的最近的站就是终点站，则返回路线
        if (short1 == endStation) {
        	Shortest+="一共经过" + (startStation.getAllPassed(endStation).size() - 1) + "站"+"\n"+"路线如下："+"\n";
            
            for (Station station : startStation.getAllPassed(endStation)) {
            	Shortest+=station.getName() + " ";
            }
            return Shortest;
        }
        
        //将现在的最短路线进行比较
        for (Station short2 : getAllLinked(short1)) {
        	//如果经过的站点里有最短路线,则跳出循环
            if (allPassedStation.contains(short2)) {
                continue;
            }
            int shortestPath = (startStation.getAllPassed(short1).size() - 1) + 1;
            //如果之前的最短路径里有该路线，比较出最短线路
            if (startStation.getAllPassed(short2).contains(short2)) {
            	//如果当前的最短路线比先前的短，则将最短路线改为现在的
                if ((startStation.getAllPassed(short2).size() - 1) > shortestPath) {
                	//重置最小路径
                    startStation.getAllPassed(short2).clear();
                    startStation.getAllPassed(short2).addAll(startStation.getAllPassed(short1));
                    startStation.getAllPassed(short2).add(short2);
                }
            } else {
            	//如果之前的最短路线没有该路线，则加上该路线
                startStation.getAllPassed(short2).addAll(startStation.getAllPassed(short1));
                startStation.getAllPassed(short2).add(short2);
            }
        }
        allPassedStation.add(short1);
        //循环直到符合判断条件
        Dijkstra(startStation, endStation);
        
        return Shortest;
    }

    //使用getAllPassed的方法来得到station所经过的所有站点的集合
    private Station getShortestPath(Station station) {
        int minPath = Integer.MAX_VALUE;
        Station rets = null;
        for (Station s : station.getOrderSetMap().keySet()) {
            if (allPassedStation.contains(s)) {
                continue;
            }
            LinkedHashSet<Station> set = station.getAllPassed(s);
            if (set.size() < minPath) {
                minPath = set.size();
                rets = s;
            }
        }
        return rets;
    }

    //循环遍历从文件中读取到的所有线路信息, 如果所求的Station包含在该线路中, 将这一线路返回
    private List<Station> getAllLinked(Station station) {
        List<Station> linkedStaions = new ArrayList<Station>();
        for (List<Station> line : LineNoName.lineSet) {
            if (line.contains(station)) {
                Station s = line.get(line.indexOf(station));
                if (s.before != null) {
                    linkedStaions.add(s.before);
                }
                if (s.next != null) {
                    linkedStaions.add(s.next);
                }
            }
        }
        return linkedStaions;
    }  
}