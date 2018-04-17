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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import mode.Grammer;
import mode.Project;
import mode.Status;
public class getTable {
	
	 HashMap<String, HashSet> first=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> follow=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> index=new HashMap<String, HashSet>();
	 Vector<Grammer> G = new Vector<Grammer>();//存储文法
	 HashSet<Status> S=new HashSet<Status>();
	 HashMap<Integer,HashSet<Status>> statuses=new HashMap<Integer,HashSet<Status>>();
	 HashMap<Integer,HashMap<String,String>> Atable=new HashMap<Integer,HashMap<String,String>> ();
	 HashMap<Integer,HashMap<String ,Integer>> Gtable=new HashMap<Integer,HashMap<String ,Integer>>();
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
		System.out.println("first(Program)="+first.get("Program"));
		
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
//		System.out.println("follow集如下：");
//		System.out.println(follow.keySet());
//		System.out.println(follow.values());
		System.out.println("follow(E)="+follow.get("E"));
		System.out.println("follow(T)="+follow.get("T"));
		System.out.println("follow(F)="+follow.get("F"));
		System.out.println("follow(M)="+follow.get("M"));
		System.out.println("follow(H)="+follow.get("H"));
		System.out.println("follow(Program)="+follow.get("Program"));
		}
	}
	public HashSet<Status> get_closure(HashSet<Status> p) {
		boolean change=true;
		HashSet<Status> pptmp=new HashSet<Status>();
		System.out.println("----------------280");
		while(change) {
			change=false;
			pptmp=p;
			System.out.println("----------------280");
			for(Status pro: pptmp) {
				int Pnum=pro.getProject().getPro_num();
				int Pdotposi=pro.getProject().getDot_positon();
				HashSet<String> Psuccessor=pro.getProject().getSuccessors();
				System.out.println("----------------280");
				System.out.println(Pnum);
				if(Pdotposi==G.get(Pnum).getRight().size()) 
					continue;
				String strV=G.get(Pnum).getRight().get(Pdotposi).toString();
				System.out.println("----------------291"+strV);
				if(!inTV(strV)) {
					System.out.println("----------------290");
					continue;		
				}
					
				//HashSet<String> new_successor = new HashSet<String>();
				HashSet<String> new_successor =null;
				System.out.println("----------------290");
				if(Pdotposi==G.get(Pnum).getRight().size()-1) {
					new_successor=Psuccessor;
				}else {
					Vector<String> vtmp=new Vector<String>();
					for(int i=Pdotposi+1;i<G.get(Pnum).getRight().size();i++) {
						vtmp.add(G.get(Pnum).getRight().get(i).toString());
					}
					new_successor=judge_first(vtmp,new_successor);
					if(new_successor.contains("ε")) {
						new_successor.addAll(Psuccessor);
						new_successor.remove("ε");
					}
				}
				Project ptmp=new Project();
				for(Object i:index.get(strV)) {
					ptmp.setPro_num(Integer.parseInt(i.toString()));
					ptmp.setDot_positon(0);
					ptmp.setSuccessors(new_successor);
					Status tempStatus=null;
					tempStatus.setProject(ptmp);
					if(!p.contains(tempStatus)) {
						p.add(tempStatus);
						change=true;
					}else {
						
						Iterator<Status> it=p.iterator();
						int  tempStatusSize=0;
						while(it.hasNext()) {
							if(it.next().getProject().getDot_positon()==tempStatus.getProject().getDot_positon()&&it.next().getProject().getPro_num()==tempStatus.getProject().getPro_num())
								{
								 tempStatusSize=it.next().getProject().getSuccessors().size();
								break;
								}
						}
						tempStatus.getProject().getSuccessors().addAll(it.next().getProject().getSuccessors());
						p.remove(tempStatus);
						p.add(tempStatus);
						if( tempStatusSize<tempStatus.getProject().getSuccessors().size())
							change=true;
					}
				}
				}
		}
		System.out.println("----------------343");
		return p;
	}
	public boolean judge_repeat(HashSet<Status> s1,HashSet<Status> s2) {
		if(s1.size()==s2.size()) {
			for(Status pros:s1) {
				if(!s2.contains(pros))
				{
					return false;		
				}else {
				for(Status Ppro:s2)
				{
					Status tempstatus=null;
					if(Ppro.getProject().getDot_positon()==pros.getProject().getDot_positon()&&Ppro.getProject().getPro_num()==pros.getProject().getPro_num())
					{
						tempstatus=Ppro;
						if(!(pros==tempstatus))
							return false;
					}
				}
				
			}	
		}
		return true;
		}
		return false;
	}
	public boolean judge_conflict(HashSet<Status> s,HashSet<String> result) {
		boolean flag=false;
		HashSet<String> tmp=null;
		for(Status pro:s) {
			if(pro.getProject().getDot_positon()==G.get(pro.getProject().getPro_num()).getRight().size())
				tmp.addAll(pro.getProject().getSuccessors());
		}
		for(Status pro:s) {
			if(pro.getProject().getDot_positon()<G.get(pro.getProject().getPro_num()).getRight().size()) {
				String next=G.get(pro.getProject().getPro_num()).getRight().get(pro.getProject().getDot_positon()).toString();
				if(tmp.contains(next)) {
					result.add(next);
					flag=true;
				}
				
			}
		}
		return flag;
		
	}
	public void get_status() {
		int t=0;
		Project ptmp=new Project();
		ptmp.setDot_positon(0);
		ptmp.setPro_num(0);
		//HashSet<String>set=null;
		HashSet<String>set=new HashSet<String>();
		System.out.println("----------------381");
		set.add("#");
		ptmp.setSuccessors(set);
		Status ptmpstatus=new Status();
		ptmpstatus.setProject(ptmp);
		HashSet<Status> tmp_status=new HashSet<Status>();
		tmp_status.add(ptmpstatus);
		System.out.println("----------------387");
		tmp_status=get_closure(tmp_status);
		System.out.println("----------------290");
		statuses.put(t, tmp_status);
		System.out.println("----------------398");
		boolean change=true;
		HashSet<Integer> record = new HashSet<Integer>();
		HashMap<Integer,HashSet<Status>> sstmp=new HashMap<Integer,HashSet<Status>>();
		HashSet<String> conflict;
		System.out.println("----------------398");
		while(change) {
			change=false;
			sstmp=statuses;
			Iterator sta=sstmp.keySet().iterator();
			while(sta.hasNext()) {
				int key=Integer.parseInt(sta.next().toString());
				HashSet<Status> valstatus=sstmp.get(key);
				if(record.contains(key))
					continue;
				record.add(key);
				HashSet<String> record_status=new HashSet<String>();
				for(Status pros:valstatus) {
					if(G.get(pros.getProject().getPro_num()).getRight().get(0)=="ε"||pros.getProject().getDot_positon()==G.get(pros.getProject().getPro_num()).getRight().size()) {
						for(String sucess:pros.getProject().getSuccessors()) {
							System.out.println("----------------408");
							if(Atable.get(key)==null) {
								HashMap<String, String> tempatable=new HashMap<String ,String>();
								Atable.put(key, tempatable);
							}
							if(!Atable.get(key).containsKey(sucess))
								Atable.get(key).put(sucess, "r"+pros.getProject().getPro_num());
							System.out.println(Atable.size());
						}
						System.out.println("----------------435");
						String trans=G.get(pros.getProject().getPro_num()).getRight().get(pros.getProject().getDot_positon()).toString();
						if(record_status.contains(trans))
							continue;
						record_status.add(trans);
						tmp_status.clear();
						System.out.println("----------------435");
						ptmp.setPro_num(pros.getProject().getPro_num());
						ptmp.setDot_positon(pros.getProject().getDot_positon());
						ptmp.setSuccessors(pros.getProject().getSuccessors());
						Status tmpS=new Status();
						tmpS.setProject(ptmp);
						tmp_status.add(tmpS);
						System.out.println("----------------435");
						for(Status protmp:valstatus) {
							if(G.get(protmp.getProject().getPro_num()).getRight().get(protmp.getProject().getDot_positon())==trans&&!(protmp==pros)) {
								ptmp.setPro_num(protmp.getProject().getPro_num());
								ptmp.setDot_positon(protmp.getProject().getDot_positon());
								ptmp.setSuccessors(protmp.getProject().getSuccessors());
								Status tmpSS=new Status();
								tmpSS.setProject(ptmp);
								tmp_status.add(tmpSS);
							}
						}
						System.out.println("----------------435");
						tmp_status=get_closure(tmp_status);
						boolean flag=true;
						Iterator s=sstmp.keySet().iterator();
						while(s.hasNext()) {
							int tipkey=Integer.parseInt(s.next().toString());
							HashSet<Status> tempstatus=sstmp.get(tipkey);
							if(judge_repeat(tempstatus,tmp_status)) {
								if(inTV(trans))
								{
									if(Gtable.get(key)==null) {
										Gtable.put(key, null);
									}
									Gtable.get(key).put(trans, tipkey);
									}
								else {
									if(Atable.get(key)==null) {
										Atable.put(key, null);
									}
									Atable.get(key).put(trans, "s"+tipkey);
								}
								flag=false;
								break;
							}
						}
						if(!flag)
							continue;
						statuses.put(++t, tmp_status);
						change=true;
						if(inTV(trans))
						{
							if(Gtable.get(key)==null) {
								Gtable.put(key, null);
							}
							Gtable.get(key).put(trans, t);
						}
						else {
							if(Atable.get(key)==null) {
								Atable.put(key, null);
							}
							Atable.get(key).put(trans, "s"+t);
						}
							
				
						}
					}
				}
			if(Atable.get(Gtable.get(0).get("Program"))==null)
			{
				Atable.put(Gtable.get(0).get("Program"),null);
			}
			Atable.get(Gtable.get(0).get("Program")).put("#", "acc");
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
		gettable.get_status();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }


}
