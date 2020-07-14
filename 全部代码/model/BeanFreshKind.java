package cn.edu.zucc.fresh.model;

public class BeanFreshKind {
	public static final String[] tableTitles={"类别编号","类别名称","类别描述"};
	private String kind_id;
	private String kind_name;
	private String kind_des;
	public static BeanFreshKind currentLoginUser=null;
	
	public String getCell(int col){
		if(col==0) return ""+this.getKindId();
		else if(col==1) return ""+this.getKindName();
		else if(col==2) return ""+this.getKindDes();
		else return "";
	}

	public String getKindId() {
		return kind_id;
	}
	public void setKindId(String kind_id) {
		this.kind_id = kind_id;
	}
	public String getKindName() {
		return kind_name;
	}
	public void setKindName(String kind_name) {
		this.kind_name = kind_name;
	}
	public String getKindDes() {
		return kind_des;
	}
	public void setKindDes(String kind_des) {
		this.kind_des = kind_des;
	}
}
