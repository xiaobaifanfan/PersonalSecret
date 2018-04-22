package getTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;



import mode.Grammer;
import mode.Project;
public class getTable {
	 HashMap<String, HashSet> first=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> follow=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> index=new HashMap<String, HashSet>();
	 Vector<Grammer> G = new Vector<Grammer>();//存储文法
	 ArrayList<HashMap<String,String>> Atable=new ArrayList<HashMap<String,String>>();
	 ArrayList<HashMap<String,Integer>> Gtable=new ArrayList<HashMap<String,Integer>>();
	 ArrayList<Project> product=new ArrayList<Project>();
	 ArrayList<HashMap<Integer,ArrayList<Project>>> status=new ArrayList<HashMap<Integer,ArrayList<Project>>>();
	 String[] V= {
				"S","Program","Type","Block","Stmts","Decl","Stmt",
				"ForAssignment","Assignment","Bool", "Rel", "LExpr",
				"HExpr","Factor","Self_op","HLogic_op","LLogic_op",
				"HMath_op","LMath_op","Judge_op","Bool_value","Array",
				"Fora","Forb","Forc","HRel","LArray","M","N","F","T","E","H","M"
			};
		 String[] T= {
				"(" ,")",";","{","}","[","]","a",
				"==","!=",">=","<=",">","<","+","-","*","/","%",
				"||","&&","++","--","!","-",";","=","ε","d"
			};

	 //HashMap<int,Status> statuses;
	 public getTable() {
		
		 
		 HashSet<String> empty_set=new HashSet<String>();
		 HashSet<String> empty_set1=new HashSet<String>();//follow.get("Program").add("#");为了怕覆盖
		 HashSet<String> empty_set_int=new HashSet<String>();
		 HashMap<String,String> emptystatus=new HashMap<String,String>();
		 HashMap<String,Integer> emptystatus_int=new HashMap<String,Integer>();
		 for(int i=0;i<V.length;i++) {
			 first.put(String.valueOf(i), empty_set);
			 follow.put(String.valueOf(i), empty_set);
			 index.put(String.valueOf(i), empty_set_int);
			 
		 }
		 for(int i=0;i<500;i++) {
			Atable.add(emptystatus);
			Gtable.add(emptystatus_int);
		 }
		 
		empty_set1.add("#");
		follow.put("Program", empty_set1);
		//follow.get("Program").add("#");//没有上一行直接用不可以，因为找不到key为Program的映射关系。必须初始化
		
		return;
	}
	
	boolean inTV(String s) {
		for(int i=0;i<V.length;i++) {
			if(V[i].equals(s))
				return true;
		}
		return false;
	}
	public  void get_grammer() throws IOException {	
		Grammer gtmp1 =new Grammer();
		gtmp1.setLeft("S");
		Vector temp1=new Vector();
		temp1.addElement("ε");
		gtmp1.setRight(temp1);	
		G.addElement(gtmp1);
		String fileName="D:/production.txt";
		File file =new File(fileName);
		BufferedReader bufread;
		String read;
		try {
			bufread=new BufferedReader(new FileReader(file));
			while((read=bufread.readLine())!=null) {
				Grammer gtmp =new Grammer();
				Vector temp=new Vector();
				int len=read.length();
				String[] linestr=read.split("");			
				gtmp.setLeft(linestr[0]);
				temp.removeAllElements();
				for(int i=2;i<len;i++)
				{
					temp.addElement(linestr[i]);
				}
				gtmp.setRight(temp);
				G.addElement(gtmp);
				
			}
			bufread.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<G.size();i++) {
			System.out.println(G.get(i).getLeft()+G.get(i).getRight()+"----------------"+G.get(i));		
		}
	//	System.out.println(G.size());
		for(int i=0;i<G.size();i++) {
			HashSet storetemp=new HashSet();
			storetemp.add(i);
			index.put(G.get(i).getLeft(), storetemp);
		} 
	
}
	public void get_first() {
		boolean change=true;
		boolean is_empty;//表示产生式右端为空串
		while(change) {
			change=false;
			for(Grammer g :G) {
				is_empty=true;
				int t=0;
				int len=g.getRight().size();	
				while(is_empty&&t<len) 
				{
					is_empty=false;
					String tempstr=g.getRight().get(t).toString();
					if(first.get(g.getLeft())==null) {
						HashSet set=new HashSet();
						first.put(g.getLeft(), set);
					}
					if(first.get(tempstr)==null) {
						HashSet set=new HashSet();
						first.put(tempstr, set);
					}
					if(!inTV(tempstr)) 
					{	
						if(!first.get(g.getLeft()).contains(tempstr))
						{
							first.get(g.getLeft()).add(tempstr);
							first.get(tempstr).add(tempstr);
							change=true;
						}
						
						continue;
					}else {		
						for(Object i :first.get(tempstr))
						{	
								if(!first.get(g.getLeft()).contains(i))
								{
								first.get(g.getLeft()).add(i);
								change=true;
								}
						}
					
					}
					if(first.get(tempstr).contains("ε")) {
						is_empty=true;
						t=t+1;
					}
				}
				if(first.get(g.getLeft())==null) {
					HashSet set=new HashSet();
					first.put(g.getLeft(), set);
				}
				if(t==g.getRight().size()&&!first.get(g.getLeft()).contains("ε")) 
				{
					first.get(g.getLeft()).add("ε");
					change=true;
				}
				List tempV=Arrays.asList(V);
				first.get(g.getLeft()).removeAll(tempV);
			}
			
		}
	 first.remove("S");
//		System.out.println("first集如下：");
//		System.out.println(first.keySet());
//		System.out.println(first.values());
		System.out.println("first(E)="+first.get("E"));
		System.out.println("first(F)="+first.get("F"));
		System.out.println("first(T)="+first.get("T"));
		System.out.println("first(M)="+first.get("M"));
		System.out.println("first(H)="+first.get("H"));
		
		
	}
	public ArrayList<String>  judge_first(Vector s,ArrayList<String> tmpset) {
		int count=0;
		HashSet<String> set=new HashSet(tmpset);
		for(int i=0;i<s.size();i++)
		{
			String str=s.get(i).toString();
			if(!inTV(str)) {
				set.add(str);
				break;
			}
			if(first.get(str)==null) {
				HashSet settemp=new HashSet();
				first.put(str, set);
			}
			if(!first.get(str).contains("ε")) {
				set.addAll(first.get(str));
				break;
			}
			set.addAll(first.get(str));
			set.remove("ε");
			count++;
		}
		
		if(count==s.size())
			set.add("ε");
		Iterator<String> iterator=set.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next());
		}
		tmpset.clear();
		tmpset=new ArrayList(set);
		return tmpset;
	}
	
	public ArrayList<Project> get_clousre(ArrayList<Project> p){
		boolean change=true;
		ArrayList<Project> tmpP=null;
		while(change) {
			change=false;
			tmpP=p;
			for(int i=0;i<tmpP.size();i++) {
				if(tmpP.get(i).getDot_positon()==G.get(tmpP.get(i).getPro_num()).getRight().size())
					continue;
				String strV=G.get(tmpP.get(i).getPro_num()).getRight().get(tmpP.get(i).getDot_positon()).toString();
				if(!inTV(strV))
					continue;
				ArrayList<String> new_successor=new ArrayList<String>();
				if(tmpP.get(i).getDot_positon()==G.get(tmpP.get(i).getPro_num()).getRight().size()-1)
					{
					new_successor=tmpP.get(i).getSuccessors();
					}else {
						Vector<String> vtmp=new Vector<String>();
						for(int j=tmpP.get(i).getDot_positon()+1;j<G.get(tmpP.get(i).getPro_num()).getRight().size();j++)
						{
							vtmp.add(G.get(tmpP.get(i).getPro_num()).getRight().get(j).toString());
						}
						new_successor=judge_first(vtmp,new_successor);
						if(new_successor.contains("ε")) {
							new_successor.addAll(tmpP.get(i).getSuccessors());
							new_successor=clearrepitmp(new_successor);
							new_successor.remove("ε");
						}
					}
					Project ptmp=new Project();
					for(Object it:index.get(strV)) {
						ptmp.setPro_num(Integer.parseInt(it.toString()));
						ptmp.setDot_positon(0);
						ptmp.setSuccessors(new_successor);
						boolean flag=false;
						for(int j=0;j<p.size();j++) {
							if(ptmp.getPro_num()==p.get(j).getPro_num()&&ptmp.getDot_positon()==p.get(j).getDot_positon()) {
								int newsize=p.get(j).getSuccessors().size();
								ArrayList<String> tempsuc=ptmp.getSuccessors();
								tempsuc.addAll(p.get(j).getSuccessors());
								tempsuc=clearrepitmp(tempsuc);
								p.get(j).setSuccessors(tempsuc);
								flag=true;
								if(newsize<ptmp.getSuccessors().size())
									change=true;
							}
						}
						if(!flag) {
							p.add(ptmp);
							change=true;
						}
					}
				
			}
		}
		for(int i=0;i<p.size();i++) {
			System.out.println(p.get(i).getSuccessors());
		}
		return p;
	}

public ArrayList clearrepitmp(ArrayList list) {
	 HashSet templist = new HashSet(list);
     list.clear();
     list.addAll(templist);
   return list;  
 }
boolean judge_repeat(ArrayList<Project> status1,ArrayList<Project> status2) {
	if(status1.size()!=status2.size())
		return false;
	for(int i=0;i<status1.size();i++) {
		if(!status2.contains(status.get(i)))
			return false;
	}
	return true;
}
public void get_stauts() {
	int t=0;
	Project ptmp=new Project();
	ArrayList<String> intisuc=new ArrayList<String>();
	intisuc.add("#");
	ptmp.setDot_positon(0);
	ptmp.setPro_num(0);
	ptmp.setSuccessors(intisuc);
	product.clear();
	product.add(ptmp);
	product=get_clousre(product);
	HashMap<Integer,ArrayList<Project>> temp=new HashMap<Integer,ArrayList<Project>>();
	ArrayList<Project> iniarray=new ArrayList<Project> ();
	iniarray.add(ptmp);
	temp.put(t, iniarray);
	status.add(temp);
	
	boolean change=true;
	Set record=new HashSet();
	 ArrayList<HashMap<Integer,ArrayList<Project>>> sstmp=new ArrayList<HashMap<Integer,ArrayList<Project>>>();
	Set conflict=new HashSet();
	while(change) {
		change=false;
		sstmp=status;
		for(int i=0;i<sstmp.size();i++) {
			if(record.contains(sstmp.get(i).keySet()))
				continue;
			Iterator<Integer> it = sstmp.get(i).keySet().iterator();
			int storestatus=0;
			while(it.hasNext())//判断是否有下一个
			{storestatus=it.next().intValue();}
			System.out.println(sstmp.get(i).keySet().size());
			record.add(sstmp.get(i).keySet());
			
			Set<String> record_status=new HashSet<String>();

		Collection<ArrayList<Project>> pros = sstmp.get(i).values();
			for(ArrayList<Project> proitem:pros) {
				for(int j=0;j<proitem.size();j++) {
					int pro_num=proitem.get(j).getPro_num();
					int pro_dotposi=proitem.get(j).getDot_positon();
					ArrayList<String> pro_succor=proitem.get(j).getSuccessors();
					if(G.get(pro_num).getRight().get(0)=="ε"||pro_dotposi==G.get(pro_num).getRight().size()){
						for(String str:pro_succor){
							HashMap<String,String> tempAitem=new HashMap<String,String>();
							tempAitem.put(str, "r"+pro_num);
							Atable.set(storestatus, tempAitem);
							}
					}
					String trans=G.get(pro_num).getRight().get(pro_dotposi).toString();
					if(record_status.contains(trans))
						continue;
					record_status.add(trans);
					iniarray.clear();
					ptmp.setPro_num(pro_num);
					ptmp.setDot_positon(pro_dotposi);
					ptmp.setSuccessors(pro_succor);
					iniarray.add(ptmp);
					for(ArrayList<Project> protmp:pros) {
						for(int k=0;k<protmp.size();k++) {
							if(G.get(protmp.get(k).getPro_num()).getRight().get(protmp.get(k).getDot_positon())==trans&&!(protmp.get(k)==proitem.get(j)))
							{
								ptmp.setPro_num(protmp.get(k).getPro_num());
								ptmp.setDot_positon(protmp.get(k).getDot_positon());
								ptmp.setSuccessors(protmp.get(k).getSuccessors());
								iniarray.add(ptmp);
								
							}
						}
					}
					iniarray=get_clousre(iniarray);
					boolean flag=true;
					for(HashMap<Integer, ArrayList<Project>> s:sstmp) {
						for(ArrayList<Project> newsta:s.values()) {
							if(judge_repeat(newsta,iniarray)) {
							if(inTV(trans)) {
								HashMap<String,Integer> tempGitem=new HashMap<String,Integer>();
								storestatus=0;
								for(int sStatus:s.keySet()) {
									storestatus=sStatus;
								}
								
								tempGitem.put(trans, storestatus);
								Gtable.set(storestatus,tempGitem);
							}else {
								HashMap<String,String> tempAitem=new HashMap<String,String>();
								tempAitem.put(trans, "s"+s.keySet());
								Atable.set(storestatus, tempAitem);
							}
							flag=false;
							break;
							
								
							}
						}
						if(!flag)
							continue;
						HashMap<Integer,ArrayList<Project>> tempitemsta=new HashMap<Integer,ArrayList<Project>>();
						tempitemsta.put(++t, iniarray);
						change=true;
						
					}
					
				}
			}
		}
		for(int h=0;h<Gtable.size();h++) {
			System.out.print(Gtable.get(h).keySet()+" ");
			System.out.print(Gtable.get(h).values()+" ");
			System.out.println(Atable.get(h).keySet()+" ");
			System.out.print(Atable.get(h).values()+" ");
		}

	}
	
}
	public static  void  main(String[] args) 
	{
	
	getTable gettable=new getTable();
	try {
		gettable.get_grammer();
		gettable.get_first();
		gettable.get_first();
		gettable.get_stauts();
		
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }


}
