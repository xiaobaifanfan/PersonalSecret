package getTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import mode.Grammer;
import mode.Project;
import mode.Status;
public class getTable {
	
	 HashMap<String, HashSet> first=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> follow=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> index=new HashMap<String, HashSet>();
	 Vector<Grammer> G = new Vector<Grammer>();//存储文法
	 HashSet<Status> S;
	 HashMap<Integer,HashSet<Status>> statuses;
	 HashMap<Integer,HashMap<String,String>> Atable;
	 HashMap<Integer,HashMap<String ,Integer>> Gtable;
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
		 for(int i=0;i<V.length;i++) {
			 first.put(String.valueOf(i), empty_set);
			 follow.put(String.valueOf(i), empty_set);
			 index.put(String.valueOf(i), empty_set_int);
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
			System.out.println(G.get(i).getLeft()+G.get(i).getRight());		
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
		System.out.println("first集如下：");
		System.out.println(first.keySet());
		System.out.println(first.values());
		System.out.println("first(E)="+first.get("E"));
		System.out.println("first(F)="+first.get("F"));
		System.out.println("first(T)="+first.get("T"));
		System.out.println("first(M)="+first.get("M"));
		System.out.println("first(H)="+first.get("H"));
		
	}
	public HashSet  judge_first(Vector s,HashSet set) {
		int count=0;
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
		return set;
	}
	public void get_follow() {
		boolean change=true;
		while(change) {
			change=false;
			String search_next=null;
			for(Grammer g:G) {
				int len=g.getRight().size();
				if(follow.get(g.getLeft())==null) {
					HashSet set=new HashSet();
					set.add("#");
					follow.put(g.getLeft(),set);
				}
				for(int i=0;i<len;i++) {
				 String str=g.getRight().get(i).toString();
				 if(!inTV(str))
					 continue;
				 if(follow.get(str)==null) {
					 HashSet tempset=new HashSet();
					 tempset.add("#");
					 follow.put(str, tempset);
				 }
				 if(i+1<len) {
					 search_next=g.getRight().get(i+1).toString();
						Vector tmp=new Vector();
						HashSet stmp=new HashSet();
						for(int k=i+1;k<len;k++)
						{
							tmp.add(g.getRight().get(k).toString());
						}
						HashSet tempset=judge_first(tmp,stmp);
						follow.get(str).addAll(tempset);
						if(tempset.contains("ε"))
						{
							follow.get(str).remove("ε");
							follow.get(str).addAll(follow.get(g.getLeft()));
						}
				}else {
					follow.get(str).addAll(follow.get(g.getLeft()));
				}
			}
		}
			
		follow.remove("S");
		System.out.println("follow集如下：");
		System.out.println(follow.keySet());
		System.out.println(follow.values());
		System.out.println("follow(E)="+follow.get("E"));
		System.out.println("follow(T)="+follow.get("T"));
		System.out.println("follow(F)="+follow.get("F"));
		System.out.println("follow(M)="+follow.get("M"));
		System.out.println("follow(H)="+follow.get("H"));
		}
	}
	public void get_closure(HashSet<Status> StatusSet) {
		boolean change=true;
			HashSet <Status> pptmp=new HashSet<Status>();
		while(change) {
			change=false;
			pptmp=StatusSet;
			for(Status pro:pptmp) {
				int pro_num=pro.getProject().getPro_num();
				int pro_dot_position=pro.getProject().getDot_positon();
				HashSet<String> pro_successor=pro.getProject().getSuccessors();
				if(pro_dot_position==G.get(pro_num).getRight().size())
					continue;
				String strV=G.get(pro_num).getRight().get(pro_dot_position).toString();
				if(!inTV(strV))
					continue;
				HashSet<String> new_successor=new HashSet<String>();
				//求新项目的后继符集
				if(pro_dot_position==G.get(pro_num).getRight().size()-1)
					new_successor=pro_successor;
				else {
					Vector<String> vtmp=new Vector<String>();
					for(int i=pro_dot_position+1;i<G.get(pro_num).getRight().size();i++) {
						vtmp.add(G.get(pro_num).getRight().get(i).toString());
					}
					HashSet temp_successor=judge_first(vtmp,new_successor);
					if(temp_successor.contains("ε")) {
						temp_successor.addAll(pro_successor);
						temp_successor.add("ε");
					}
				}
				Project ptmp = null;
				for(Object i:index.get(strV)) {
					ptmp.setPro_num(Integer.parseInt(i.toString()));
				}
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
		gettable.get_follow();
		gettable.get_follow();//由于产生式的顺序不同，逐次搜索可能会有遗漏，所以需要搜索两次，确保全部添加正确。
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }


}
