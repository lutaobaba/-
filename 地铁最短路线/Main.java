import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Scanner;

//主函数，在命令框进行交互
public class Main {
	public static void main(String[] args) throws IOException {
		
    	Scanner input = new Scanner(System.in);
        String file = "D:\\Eclipse\\ruan\\station.txt";
        
        System.out.println("请选择操作（输入对应数字即可）：1.查看全部路线。2.查询单条路线。3.查询两站最短路线。");
        String choose = input.nextLine();
        
        if(choose.equals("1")) {
        	Reader fr = null;
    		int length = 0;
    		char ch [] = null;
    		try {
    			fr =new FileReader(file);
    			ch = new char[2048];
    			length = fr.read(ch);
    			System.out.println(new String(ch ,0,length));
    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}finally{
    			if(null!=fr){
    				try {
    					fr.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		}
        }
        else if (choose.equals("2")) {
        	System.out.println("请输入路线名：");
        	String lineName = input.nextLine();
        	LineWithName.readFile(file);
        	Station station = new Station(lineName);
        	String allStations = "";
        	int flag = 0;
        	for (List<Station> linename : LineWithName.lineSet) {
        		if (linename.contains(station)) {
        			allStations+=linename.get(0).getName() + "包括的站点:"+"\n";
        			for (int i = 1; i < linename.size(); i++) {
        				allStations+=linename.get(i).getName() + " ";
        			}
        			flag=1;
        		}
        	}
        	if(flag==0){
        		System.out.println("---该路线不存在！---");

        	}
        	else{
        		System.out.println(allStations);
        	}
		}
        else if (choose.equals("3")) {
        	System.out.println("请输入起点：");
        	String start = input.nextLine();
        	System.out.println("请输入终点：");
        	String end = input.nextLine();
        	LineNoName.readFile(file);
        	
        	Station station1 = new Station(start);
        	Station station2 = new Station(end);
        	int flag1 = 0,flag2 = 0;
        	for (List<Station> linename : LineNoName.lineSet) {
        		if (linename.contains(station1)) {
        			flag1=1;
        			break;
        		}
        		else {
					flag1=0;
				}
        	}
        	for (List<Station> linename : LineNoName.lineSet) {
        		if (linename.contains(station2)) {
        			flag2=1;
        			break;
        		}
        		else {
					flag2=0;
				}
        	}
        	if(flag1==1&&flag2==1) {
	        	Subway sw = new Subway();
	        	String allStations = sw.Dijkstra(new Station(start), new Station(end));
	        	System.out.println(allStations);
        	}
        	else if (flag1==0&&flag2==1) {
        		System.out.println("---没有该起点！---");
			}
        	else if (flag1==1&&flag2==0) {
        		System.out.println("---没有该终点！---");
			}
        	else {
        		System.out.println("---输入参数有误！---");
			}
       
	     }
        else{
	          System.out.println("---输入参数有误！---");
	      }
        input.close();
	}
}
