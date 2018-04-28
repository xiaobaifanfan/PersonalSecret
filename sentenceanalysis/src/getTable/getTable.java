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
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.javafx.collections.MappingChange.Map;
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
//	 ArrayList<HashMap<String,String>> Atable=new ArrayList<HashMap<String,String>>();
//	 ArrayList<HashMap<String,Integer>> Gtable=new ArrayList<HashMap<String,Integer>>();
	 HashMap<Integer,HashMap<String,String>>Atable=new HashMap<Integer,HashMap<String,String>>();
	 HashMap<Integer,HashMap<String,Integer>>Gtable=new HashMap<Integer,HashMap<String,Integer>>();
	 ArrayList<Project> itemsta=new ArrayList<Project>();;
	 HashMap<String,Vector<Integer>>index=new HashMap<String,Vector<Integer>>();
	 HashMap<Integer,ArrayList<Project>> status1=new HashMap<Integer,ArrayList<Project>>();
	 HashMap<ArrayList<Project>,Integer> status2=new HashMap<ArrayList<Project>,Integer>();
	 
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
			ArrayList<Project> pptmp=new ArrayList<Project>();
			while(change) {
				change=false;
				pptmp=p;
				ListIterator<Project> pros = p.listIterator();  
		        while(pros.hasNext()) {  
		        		Project pro= pros.next();
						int tmppnum=pro.getPro_num();
						int tmppdot=pro.getDot_positon();
						ArrayList<String> tmppsuc=pro.getSuccessors();
						if(tmppdot==G.get(tmppnum).getRight().size())
							continue;
						String V=G.get(tmppnum).getRight().get(pro.getDot_positon()).toString();
						if(!inVT(V))
							continue;
						ArrayList<String> new_successor=new ArrayList<String> ();
						if(pro.getDot_positon()==G.get(tmppnum).getRight().size()-1)
						{

							new_successor=pro.getSuccessors();
						}
						else {
							Vector vtmp=new Vector();
							for(int k=tmppdot+1;k<G.get(tmppnum).getRight().size();k++) {
								vtmp.add(G.get(tmppnum).getRight().get(k).toString());
							}
							new_successor=judge_first(vtmp,new_successor);
							if(new_successor.contains("ε")) {
								new_successor.addAll(tmppsuc);
								new_successor=delrepeat(new_successor);
								new_successor.remove("ε");
							}
							
						}
						Iterator<Integer> it = index.get(V).listIterator(); 
				      while(it.hasNext()) {  
				    	  Project ptmp=new Project();
				    	  int tempit=it.next();
							ptmp.setPro_num(tempit);
							ptmp.setDot_positon(0);
							ptmp.setSuccessors(new_successor);
							int point=isContainPro(p,ptmp);
							if(point==-1) {
								pros.add(ptmp);
								
								change=true;	
							}else {
								int temporigi=p.get(point).getSuccessors().size();
								ArrayList<String> tmp=ptmp.getSuccessors();
								tmp.addAll(p.get(point).getSuccessors());
								tmp=delrepeat(tmp);
								ptmp.setSuccessors(tmp);
								p.set(point, ptmp);
								if(temporigi<ptmp.getSuccessors().size())
									change=true;
							}
			      }  
					}
					}
			System.out.println(p.size());
			return p;
	 }
	 public int isContainPro(ArrayList<Project> s,Project tmp) {
		 for(int i=0;i<s.size();i++) {
			 if(s.get(i).getPro_num()==tmp.getPro_num()&&s.get(i).getDot_positon()==tmp.getDot_positon())
				 return i;
		 }
		 return -1;
		 
	 }

	 boolean judge_repeat(ArrayList<Project> statuss1,ArrayList<Project> statuss2) {
		if(statuss1.size()!=statuss2.size())
			return false;
		for(int i=0;i<statuss1.size();i++) {
			if(!statuss2.contains(statuss1.get(i)))
				return false;
		}
		return true;
	}
	public void get_stauts() {	
		int t=0;
		Project ptmp=new Project();
		ptmp.setDot_positon(0);
		ptmp.setPro_num(0);
		ArrayList<String> suc=new ArrayList<String> ();
		ptmp.setSuccessors(suc);
		ArrayList<Project> tmp_status=new ArrayList<Project>();
		tmp_status.add(ptmp);
		tmp_status=get_clousre(tmp_status);
		ArrayList<ArrayList<Project>> tmpsta=new ArrayList<ArrayList<Project>>();
		tmpsta.add(tmp_status);
		status1.put(t, tmp_status);
		status2.put(tmp_status, t);
		boolean change=true;
		HashSet<Integer> record=new HashSet<Integer>();
		ArrayList<ArrayList<Project>> sstmp=new ArrayList<ArrayList<Project>>();
		HashSet<String> conflict=new HashSet<String>();
		

		while(change) {
			change=false;
			sstmp=tmpsta;
			ListIterator<ArrayList<Project>> sta = sstmp.listIterator(); 
			while(sta.hasNext()) {
				 int stafirst=status2.get(sta.next());
				System.out.println(stafirst);
				if(record.contains(stafirst))
					continue;
				HashSet<String> record_status=new HashSet<String>();
				ListIterator<Project> prossecond = sstmp.get(stafirst).listIterator();  
				while(prossecond.hasNext()) {
					Project pros=prossecond.next();
					int prosnum=pros.getPro_num();
					int prosdot=pros.getDot_positon();
					ArrayList<String> prossuc=pros.getSuccessors();
					if(G.get(prosnum).getRight().get(0).equals("ε") ||prosdot==G.get(prosnum).getRight().size()){
						for(String sucess:prossuc) {
						 if(!Atable.get(stafirst).containsKey(sucess))
							 Atable.get(stafirst).put(sucess, "r"+prosnum);						
						 }
						continue;
					}
					String trans=G.get(prosnum).getRight().get(prosdot).toString();
					if(record_status.contains(trans))
						continue;
					record_status.add(trans);
					tmp_status=new ArrayList<Project>();
					//tmp_status.clear();
					ptmp.setPro_num(prosnum);
					ptmp.setDot_positon(prosdot+1);
					ptmp.setSuccessors(prossuc);
					tmp_status.add(ptmp);
					ListIterator<Project> protmp = sstmp.get(stafirst).listIterator(); 
					while(protmp.hasNext()) {
						Project protmptemp=protmp.next();
						int protnum=protmptemp.getPro_num();
						int protdot=protmptemp.getDot_positon();
						ArrayList<String> protsuc=protmptemp.getSuccessors();
						if(protdot<G.get(protnum).getRight().size())
						if(G.get(protnum).getRight().get(protdot).equals(trans)&&!(protmptemp==pros))
						{
							ptmp.setPro_num(protnum);
							ptmp.setDot_positon(protdot);
							ptmp.setSuccessors(protsuc);
							tmp_status.add(ptmp);
						}
					}
					tmp_status=get_clousre(tmp_status);
					boolean flag=true;
					ListIterator<ArrayList<Project>> s = sstmp.listIterator();
					while(s.hasNext()) {
						System.out.println("pass");
						int sfirst=status2.get(s.next());
						System.out.println("pass11111");
						ArrayList<Project> sSecond=status1.get(sfirst);
						if(judge_repeat(sSecond,tmp_status)) {
								if(inVT(trans))
								{
									if(Gtable.get(stafirst)==null) {
										HashMap<String,Integer> gtmp=new HashMap<String,Integer>();
										System.out.println("Gtable add element failed");
										Gtable.put(stafirst, gtmp);
									}
									System.out.println("Gtable add element failed");
									Gtable.get(stafirst).put(trans, stafirst);
									}
								else {
									if(Atable.get(stafirst)==null) {
										HashMap<String,String> atmp=new HashMap<String,String>();
										System.out.println("Gtable add element failed");
										Atable.put(stafirst, atmp);
									}
									System.out.println("Atable add element failed");
									Atable.get(stafirst).put(trans, "s"+stafirst);
								}
								flag=false;
								break;	
						}
					}
					sta.add(tmp_status);
					status1.put(t, tmp_status);
					status2.put(tmp_status, t);
				}
				System.out.println("dddddddddddddddddd");
			}
			
				}
		System.out.println(status1.size());
		System.out.println(status2.size());
		System.out.println(sstmp.size());
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
		System.exit(0);

		 
	 }
	 
}