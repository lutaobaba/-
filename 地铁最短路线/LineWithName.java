import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//用来获取带线路名的路线和总站点数
public class LineWithName {

    public static HashSet<List<Station>> lineSet = new HashSet<List<Station>>();//所有线的集合
    public static int totalStaion = 0;//总站点数

    public static void readFile(String file) throws IOException{
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String str = null;
        while((str = bufferedReader.readLine()) != null)
        {
            List<Station> line = new ArrayList<Station>();
            String[] lineInfor = str.split(" ");
            for(String s : lineInfor){
                line.add(new Station(s));
            }
            for(int i =0;i<line.size();i++){
     
                if(i<line.size()-1){
                    line.get(i).next = line.get(i+1);
                    line.get(i+1).before = line.get(i);
                }
            }
            lineSet.add(line);
            totalStaion+=line.size();

        }
        fileReader.close();
        bufferedReader.close();
    }

}