package getTable;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import mode.Grammer;
import mode.Project;

public class getTable {
	 String V[];
	 String T[];
	 Vector<String> existV=new Vector<String>();
	 Vector<String>  existT=new Vector<String>();
	 Vector<Grammer> G=new Vector<Grammer>();
	 HashMap<String,Vector> first=new HashMap<String,Vector>();
	 ArrayList<HashMap<String,String>> Atable=new ArrayList<HashMap<String,String>>();
	 ArrayList<HashMap<String,Integer>> Gtable=new ArrayList<HashMap<String,Integer>>();
	 ArrayList<Project> itemsta=new ArrayList<Project>();
	 HashMap<String,Vector<Integer>>index=new HashMap<String,Vector<Integer>>();
	 ArrayList<HashMap<Integer,ArrayList>> status=new ArrayList<HashMap<Integer,ArrayList>>();
	 public void get_grammer() throws IOException {
		 int i=0;
		 Grammer tmp=new Grammer();
		 tmp.setLeft("Y");
		 String filename="D:/production.txt";
		 File file=new File(filename);
		 BufferedReader bufread;
		 String read=null;
		 bufread=new BufferedReader(new FileReader(file));
		 read=bufread.readLine();
		 V=read.split("");
		 read=bufread.readLine();
		 T=read.split("");
		 try {
			while((read=bufread.readLine())!=null) {
				String[] linestr=read.split("");
				int len=read.length();
					Grammer gtmp=new Grammer();
					Vector temp=new Vector();
					if(inVT(linestr[0])) {
						if(!existV.contains(linestr[0]))
						existV.add(linestr[0]);
					}
					else {
						if(!existT.contains(linestr[0]))
						existT.add(linestr[0]);
					}
					for(int j=3;j<len;j++) {
						if(inVT(linestr[j])) {
							if(!existV.contains(linestr[j]))
							existV.add(linestr[j]);
						}
						else {
							if(!existT.contains(linestr[j]))
							existT.add(linestr[j]);
						}	
							temp.add(linestr[j]);
					}
					gtmp.setLeft(linestr[0]);
					gtmp.setRight(temp);
					G.add(gtmp);
					i=i+1;
				}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Vector temp=new Vector();
		 temp.add(G.get(0).getLeft());
		 //temp.add("ε");
		 tmp.setRight(temp);
		 G.add(0, tmp);
		 for(int i1=0;i1<G.size();i1++) {
			 if(index.get(G.get(i1).getLeft())==null) {
				 	Vector vtmp=new Vector();
					index.put(G.get(i1).getLeft(), vtmp);
			 }
			 index.get(G.get(i1).getLeft()).addElement(i1);
		 }
		 existV.add(0, "Y");
		 System.out.println("index集合如下：");
			 System.out.println(index.keySet());
			 System.out.println(index.values());
		 
		 System.out.println("非终结符V集合如下:");
		 for(int i1=0;i1<V.length;i1++)
		 {
			 System.out.print(V[i1]+" ");
		 }
		 System.out.println("");
		 System.out.println("终结符T集合如下: ");
		 for(int i1=0;i1<T.length;i1++)
		 {
			 System.out.print(T[i1]+" ");
		 }
		 System.out.println("");
		 System.out.println("读入的文法G向量如下:");
		 for(int i1=0;i1<G.size();i1++)
		 {
			 System.out.println(G.get(i1).getLeft()+" "+G.get(i1).getRight());
		 }
		 System.out.println("");
		 System.out.println("文本中的非终结符如下:");
		 for(int i1=0;i1<existV.size();i1++)
		 {
			 System.out.print(existV.get(i1)+" ");
		 }
		 System.out.println("");
		 System.out.println("文本中的终结符如下:");
		 for(int i1=0;i1<existT.size();i1++)
		 {
			 System.out.print(existT.get(i1)+" ");
		 }
	 }
	 public boolean inVT(String str)
	 {
		 for(int i=0;i<V.length;i++) {
			 if(str.equals(V[i]))
				 return true;
		 }
		 return false;
	 }
	 public void initfirst()
	 {
		 for(int i=0;i<existT.size();i++) {
			 Vector<String> vtmp=new Vector<String>();
			 vtmp.add(existT.get(i));
			 first.put(existT.get(i), vtmp); 
		 }
		 for(int i=0;i<existV.size();i++) {
			 Vector<String> vtmp=new Vector<String>();
			 first.put(existV.get(i), vtmp);
		 }
	 }
	 public void get_first() {
		 
		 boolean change=true;
		 boolean isempty;
		 int t;
		 while(change) {
			 change=false;
			 for(Grammer g:G) {
				 isempty=true;;
				 t=0;
				 int len=g.getRight().size();
				 while(isempty&&t<len) {
					 isempty=false;
					 String str=g.getRight().get(t).toString();
					 if(!inVT(str)) {
						 if(!first.get(g.getLeft().toString()).contains(str))
						 {
							 first.get(g.getLeft().toString()).add(str);
							 change=true;
						 }
						 continue;	
					 }
					 for(Object i:first.get(str)) {
							 if(!first.get(g.getLeft()).contains(i)) {
								 first.get(g.getLeft()).add(i);
								 change=true;
							 }
						 }
					 
					 if(first.get(str).contains("ε")) {
						 isempty=true;
						 t++;
					 }
				 }
				 if((t==g.getRight().size())&&(!first.get(g.getLeft()).contains("ε"))) {
					 first.get(g.getLeft()).add("ε");
					 change=true;
				 }
			 }
			
			 
		 }
	//	first.remove("Y");
		 System.out.println("");
	for(int i=0;i<existV.size();i++) {
		System.out.println(existV.get(i)+"的first 集为"+first.get(existV.get(i)));
	}
		
		 return;
	 }
	 public ArrayList<String> judge_first(Vector<String> s,ArrayList<String> result) {
		 int count=0;
		 for(String i:s) {
			 if((!inVT(i))&&(!result.contains(i))) {
				 result.add(i);
				 break;
			 }
			 if(!first.get(i).contains("ε")) {
				 result.addAll(first.get(i));
				 result=delrepeat(result);
				 break;
			 }
			 result.addAll(first.get(i));
			 result=delrepeat(result);
			 result.remove("ε");
			 count++;
		 }
		 if(count==s.size())
			 result.add("ε");
		 return result;
	 }
	 public ArrayList<Project> get_clousre(ArrayList<Project> p){
			boolean change=true;
			ArrayList<Project> pptmp=p;
			itemsta=p;
			while(change) {
				change=false;
				pptmp=itemsta;
				System.out.println(itemsta);
				for(Project pro:pptmp) {
					System.out.println(pptmp.size());
					int tmppnum=pro.getPro_num();
					int tmppdot=pro.getDot_positon();
					System.out.println(G.get(tmppnum).getRight());
					
					ArrayList<String> tmppsuc=pro.getSuccessors();
					if(tmppdot==G.get(tmppnum).getRight().size())
						continue;
					String V=G.get(tmppnum).getRight().get(pro.getDot_positon()).toString();
					System.out.println(V);
					if(!inVT(V))
						continue;
					ArrayList<String> new_successor=new ArrayList<String> ();
					if(pro.getDot_positon()==G.get(tmppnum).getRight().size()-1)
					{

						new_successor=pro.getSuccessors();
						System.out.println(new_successor);
					}
					else {
						System.out.println("ddddddddddddd");
						Vector vtmp=new Vector();
						for(int k=tmppdot+1;k<G.get(tmppnum).getRight().size();k++) {
							vtmp.add(G.get(tmppnum).getRight().get(k).toString());
						}
						System.out.println("产生式G："+G.get(tmppnum)+"的小圆点后面的字符为："+vtmp);
						new_successor=judge_first(vtmp,new_successor);
						System.out.println("233-----new_successor:"+new_successor);
						if(new_successor.contains("ε")) {
							new_successor.addAll(tmppsuc);
							new_successor=delrepeat(new_successor);
							new_successor.remove("ε");
						}
						
					}
					System.out.println("------251");
					Iterator<Integer> it = index.get(V).iterator();  
			      while(it.hasNext()) {  
			    	  Project ptmp=new Project();
						ptmp.setPro_num(it.next());
						ptmp.setDot_positon(0);
						ptmp.setSuccessors(new_successor);
						int point=isContainPro(itemsta,ptmp);
						System.out.println(point+"=============");
						if(point==-1) {
							System.out.println("-----------259");
							itemsta.add(ptmp);
							change=true;	
						}else {
							int temporigi=itemsta.get(point).getSuccessors().size();
							ArrayList<String> tmp=ptmp.getSuccessors();
							tmp.addAll(itemsta.get(point).getSuccessors());
							tmp=delrepeat(tmp);
							ptmp.setSuccessors(tmp);
							itemsta.set(point, ptmp);
							if(temporigi<ptmp.getSuccessors().size())
								change=true;
						}
						System.out.println("以该终结符产生的产生式");
		      }  
//					for(int i:index.get(V)) {
//						Project ptmp=new Project();
//						ptmp.setPro_num(i);
//						ptmp.setDot_positon(0);
//						ptmp.setSuccessors(new_successor);
//						int point=isContainPro(itemsta,ptmp);
//						System.out.println(point+"=============");
//						if(point==-1) {
//							System.out.println("-----------259");
//							itemsta.add(ptmp);
//							change=true;	
//						}else {
//							int temporigi=itemsta.get(point).getSuccessors().size();
//							ArrayList<String> tmp=ptmp.getSuccessors();
//							tmp.addAll(itemsta.get(point).getSuccessors());
//							tmp=delrepeat(tmp);
//							ptmp.setSuccessors(tmp);
//							itemsta.set(point, ptmp);
//							if(temporigi<ptmp.getSuccessors().size())
//								change=true;
//						}
//						System.out.println("以该终结符产生的产生式");
//					}
					System.out.println("已经查找当前产生式后面元素为右部的完所有的产生式");
				}
				System.out.println("已经查找当前状态集合I0的所有产生式");
					}
		
			return p;
	 }
	 public int isContainPro(ArrayList<Project> s,Project tmp) {
		 for(int i=0;i<s.size();i++) {
			 if(s.get(i).getPro_num()==tmp.getPro_num()&&s.get(i).getDot_positon()==tmp.getDot_positon())
				 return i;
		 }
		 return -1;
		 
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
		ptmp.setDot_positon(0);
		ptmp.setPro_num(4);
		ArrayList<String> suc=new ArrayList<String> ();
		ptmp.setSuccessors(suc);
		ArrayList<Project> tmp_status=new ArrayList<Project>();
		tmp_status.add(ptmp);
		get_clousre(tmp_status);
	}
	 public ArrayList delrepeat( ArrayList  list) {
		 ArrayList  temparr= new ArrayList();
		 HashSet set=new HashSet(list);
		 temparr.addAll(set);
		 return temparr;
	 }
public static void main(String[] args) { 
		 getTable ti=new getTable();
		 try {
			ti.get_grammer();
			ti.initfirst();
			ti.get_first();
			ti.get_stauts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
}