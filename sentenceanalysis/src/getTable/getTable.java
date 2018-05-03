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
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import mode.Grammer;
import mode.Project;
import mode.StateTran;
import wordanalyse.wordAnalyse;

public class getTable {
	 String V[];
	 String T[];
	 Vector<String> existV=new Vector<String>();
	 Vector<String>  existT=new Vector<String>();
	 Vector<Grammer> G=new Vector<Grammer>();
	 String[][] table;
	 Vector<StateTran> statrans=new Vector<StateTran>();
	 HashMap<String,Vector> first=new HashMap<String,Vector>();
	 ConcurrentHashMap<Integer, ArrayList<Project>> status=new ConcurrentHashMap<Integer,ArrayList<Project>>();
	 ConcurrentHashMap<String,Integer> staconvect=new  ConcurrentHashMap<String,Integer>();
	 HashMap<String,Vector<Integer>>index=new HashMap<String,Vector<Integer>>();
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
					Vector<String> temp=new Vector<String>();
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
		 Vector<String> temp=new Vector<String>();
		 temp.add(G.get(0).getLeft());
		 //temp.add("��");
		 tmp.setRight(temp);
		 G.add(0, tmp);
		 for(int i1=0;i1<G.size();i1++) {
			 if(index.get(G.get(i1).getLeft())==null) {
				 	Vector<Integer> vtmp=new Vector<Integer>();
					index.put(G.get(i1).getLeft(), vtmp);
			 }
			 index.get(G.get(i1).getLeft()).addElement(i1);
		 }
		 existV.add(0, "Y");
		 bufread.close();
		 System.out.println("index�������£�");
			 System.out.println(index.keySet());
			 System.out.println(index.values());
		 
		 System.out.println("���ս��V��������:");
		 for(int i1=0;i1<V.length;i1++)
		 {
			 System.out.print(V[i1]+" ");
		 }
		 System.out.println("");
		 System.out.println("�ս��T��������: ");
		 for(int i1=0;i1<T.length;i1++)
		 {
			 System.out.print(T[i1]+" ");
		 }
		 System.out.println("");
		 System.out.println("������ķ�G��������:");
		 for(int i1=0;i1<G.size();i1++)
		 {
			 System.out.println(G.get(i1).getLeft()+" "+G.get(i1).getRight());
		 }
		 System.out.println("");
		 System.out.println("�ı��еķ��ս������:");
		 for(int i1=0;i1<existV.size();i1++)
		 {
			 System.out.print(existV.get(i1)+" ");
		 }
		 System.out.println("");
		 System.out.println("�ı��е��ս������:");
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
					 
					 if(first.get(str).contains("��")) {
						 isempty=true;
						 t++;
					 }
				 }
				 if((t==g.getRight().size())&&(!first.get(g.getLeft()).contains("��"))) {
					 first.get(g.getLeft()).add("��");
					 change=true;
				 }
			 }
			
			 
		 }
	//	first.remove("Y");
		 System.out.println("");
	for(int i=0;i<existV.size();i++) {
		System.out.println(existV.get(i)+"��first ��Ϊ"+first.get(existV.get(i)));
	}
		
		 return;
	 }
	 public HashSet<String> judge_first(Vector<String> s,HashSet<String> result) {
		 int count=0;
		 for(String i:s) {
			 if((!inVT(i))&&(!result.contains(i))) {
				 result.add(i);
				 break;
			 }
			 if(!first.get(i).contains("��")) {
				 result.addAll(first.get(i));
				 
				 break;
			 }
			 result.addAll(first.get(i));
			 result.remove("��");
			 count++;
		 }
		 if(count==s.size())
			 result.add("��");

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
						HashSet<String> tmppsuc=new HashSet<String>();
						tmppsuc=pro.getSuccessors();
						if(tmppdot==G.get(tmppnum).getRight().size()) {
							continue;
						}
							
						String V=G.get(tmppnum).getRight().get(pro.getDot_positon()).toString();
						if(!inVT(V)) {
							continue;
						}
							
						HashSet<String> new_successor=new HashSet<String> ();
						new_successor.add("#");
						if(pro.getDot_positon()==G.get(tmppnum).getRight().size()-1)
						{
							new_successor=pro.getSuccessors();
						}
						else {
							Vector<String> vtmp=new Vector<String>();
							for(int k=tmppdot+1;k<G.get(tmppnum).getRight().size();k++) {
								vtmp.add(G.get(tmppnum).getRight().get(k).toString());
							}
							new_successor=judge_first(vtmp,new_successor);
							if(new_successor.contains("��")) {
								new_successor.addAll(tmppsuc);
								new_successor.remove("��");
							}
						}
						Iterator<Integer> it = index.get(V).listIterator(); 
				      while(it.hasNext()) { 
				    	  Project ptmp=new Project();
				    	  HashSet<String> new_suc=new HashSet<String>();
				    	  new_suc=(HashSet<String>) new_successor.clone();
				    	  
				    	  int tempit=it.next();
							ptmp.setPro_num(tempit);
							ptmp.setDot_positon(0);
							ptmp.setSuccessors(new_suc);
							int point=isContainPro(p,ptmp);
							if(point==-1) {
								pros.add(ptmp);
								change=true;	
							}else {
								int temporigi=p.get(point).getSuccessors().size();
								HashSet<String> tmp=new HashSet<String>();
								tmp.add("#");
								tmp=ptmp.getSuccessors();
								tmp.addAll(p.get(point).getSuccessors());
								ptmp.setSuccessors(tmp);
								p.set(point, ptmp);
								if(temporigi<ptmp.getSuccessors().size())
									change=true;
							}
			      }  
				     
					}
		        
					}
			p=delrepeat(p);
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
		 ArrayList<Integer> record=new ArrayList<Integer>();
	for(int j=0;j<statuss2.size();j++) {
		for(int i=0;i<statuss1.size();i++) {
			if(hashsetequal(statuss1.get(i).getSuccessors(),statuss2.get(j).getSuccessors())&&statuss2.get(j).getPro_num()==statuss1.get(i).getPro_num()&&statuss2.get(j).getDot_positon()==statuss1.get(i).getDot_positon())
				{
				record.add(j);
				break;
				}
		}
		
	}
	if(record.size()==statuss2.size()&&statuss2.size()==statuss1.size())
		return true;
	return false;
		
	}
	 public boolean hashsetequal(HashSet<String> set1,HashSet<String> set2 ) {
		 ArrayList<String> slist1=new ArrayList<String>(set1);
		 ArrayList<String> slist2=new ArrayList<String>(set2);
		 if(slist1.containsAll(slist2)&&slist1.size()==slist2.size())
			 return true;
		 return false;
	 }
 	public void get_stauts() {	
		int t=0;
		Project ptmp=new Project();
		ptmp.setDot_positon(0);
		ptmp.setPro_num(0);
		HashSet<String> suc=new HashSet<String> ();
		suc.add("#");
		ptmp.setSuccessors(suc);
		ArrayList<Project> tmp_status=new ArrayList<Project>();
		tmp_status.add(ptmp);
		tmp_status=get_clousre(tmp_status);
		status.put(t, tmp_status);
		boolean change=true;
		HashSet<Integer> record=new HashSet<Integer>();
		ConcurrentHashMap<Integer, ArrayList<Project>> sstmp=new ConcurrentHashMap<Integer, ArrayList<Project>>();
		while(change) {
			change=false;
			 sstmp=status;
			for(int sta:sstmp.keySet()) {
				if(record.contains(sta))
					continue;
				record.add(sta);
				HashSet<String> record_status=new HashSet<String>();
				tmp_status=new ArrayList<Project>();
				tmp_status=sstmp.get(sta);
				for(int i=0;i<tmp_status.size();i++) {
					Project pro=tmp_status.get(i);
					int pronum=pro.getPro_num();
					int prodot=pro.getDot_positon();
					HashSet<String> prosuc=pro.getSuccessors();
					int lenGsize=G.get(pronum).getRight().size();
					if(lenGsize==prodot||G.get(pronum).getRight().get(0).equals("��"))
						continue;
					String trans=G.get(pronum).getRight().get(prodot).toString();
					if(record_status.contains(trans))
						continue;
					record_status.add(trans);
					Project tmpro=new Project();
					tmpro.setDot_positon(prodot+1);
					tmpro.setPro_num(pronum);
					tmpro.setSuccessors(prosuc);
					ArrayList<Project> ttmp=new ArrayList<Project> ();
					ArrayList<Project> ttmpstore=new ArrayList<Project> ();
					ttmp.add(tmpro);
					for(Project protmp:sstmp.get(sta)) {
						if(protmp.getDot_positon()<G.get(protmp.getPro_num()).getRight().size()&&G.get(protmp.getPro_num()).getRight().get(protmp.getDot_positon()).equals(trans)&&!(protmp==pro)) 
							{
								tmpro=new Project();
								tmpro.setPro_num(protmp.getPro_num());
								tmpro.setDot_positon(protmp.getDot_positon()+1);
								tmpro.setSuccessors(protmp.getSuccessors());
								ttmp.add(tmpro);
							
							}
						
						
					}
					
					ttmp=get_clousre(ttmp);
					boolean flag=true;
					for(int s:sstmp.keySet())
					{
						
						if(judge_repeat(sstmp.get(s),ttmp))
						{
							GO(sta,s,trans);
							flag=false;
							break;
						}
					}
					if(!flag)
						continue;
					
					status.put(++t, ttmp);
					GO(sta,t,trans);
					change=true;
									
				}
			}
			
			
		}
		System.out.println(status.keySet());
		
		System.out.println(status.size());
		System.out.println(statrans.size());
		for(int sta:status.keySet()) {
			System.out.println("states:"+sta+"���£�");
			for(Project pro:status.get(sta)) {
				System.out.println(G.get(pro.getPro_num()).getLeft()+"----"+G.get(pro.getPro_num()).getRight()+"С����λ�ã�"+pro.getDot_positon()+"------"+pro.getSuccessors());
			}
		}
		
	}

	public void GO(int i,int j,String str) {
		 StateTran tmpstatran=new StateTran();
		 tmpstatran.setConstring(str);
		 tmpstatran.setEndstatus(j);
		 tmpstatran.setStartstatus(i);
		 statrans.add(tmpstatran);
		 
	}
	public ArrayList<Project> delrepeat( ArrayList<Project>  ttmp) {
		Vector<String> record=new Vector<String>();
		ArrayList<Project> temp=new ArrayList<Project> ();
		for(Project pro:ttmp) {
			String str=String.valueOf(pro.getPro_num())+String.valueOf(pro.getDot_positon());
			if(!record.contains(str))
			{
				temp.add(pro);
				record.add(str);
				
			}
				
		}
		 return temp;
	 }
	public void generateLR() {
		for(int i=0;i<statrans.size();i++) {
			System.out.println(statrans.get(i).getStartstatus()+"-----------"+statrans.get(i).getConstring()+"------------"+statrans.get(i).getEndstatus());
		}
		
		
		initstavect();

		for(int sta:status.keySet()) {
			for(Project pro:status.get(sta)) {
				int pronum=pro.getPro_num();
				int prodot=pro.getDot_positon();
				HashSet<String> prosuc=pro.getSuccessors();
				if(prodot==G.get(pronum).getRight().size()) {
					if(G.get(pronum).getLeft().equals("Y"))
					{
						table[sta][staconvect.get("#")]="acc";
					}else {
						for(String str:prosuc) {
							table[sta][staconvect.get(str)]="r"+pronum;
						}
					}
					continue;
				}
				String trans=G.get(pronum).getRight().get(prodot).toString();
				if(inVT(trans)) {
					table[sta][staconvect.get(trans)]=String.valueOf(findGOlink(sta,trans));
				}else {
					if(sta!=findGOlink(sta,trans))
					table[sta][staconvect.get(trans)]="S"+findGOlink(sta,trans);
				}
			}
		}

		String[] tmpstaconvect=new String[staconvect.size()];
		for(String str:staconvect.keySet()) {
			tmpstaconvect[staconvect.get(str)]=str;
		}
		System.out.print("  \t ");
		for(int i=0;i<tmpstaconvect.length;i++) {
			System.out.print(tmpstaconvect[i]+" \t");
		}
		System.out.println("");
		for(int k:status.keySet()) {
			System.out.print(k+" \t");			
			for(int i=0;i<staconvect.size();i++) 
			{
				if(i<existT.size())
				System.out.print(table[k][i]+"\t");
				else {
					System.out.print(table[k][i]+" \t");	
				}
			}
			System.out.println("  ");
			}
			
		
		
	}
	public int findGOlink(int start,String str) {
		for(int i=0;i<statrans.size();i++) {
			if(statrans.get(i).getStartstatus()==start&&statrans.get(i).getConstring().equals(str))
				return statrans.get(i).getEndstatus();
		}
		return -1;
	}
	public void initstavect() {
		int t=0;
		for(int i=0;i<existT.size();i++) {
			String str=existT.get(i);
			if(!str.equals("��"))
			{
				staconvect.put(str, t);
				t=t+1;
			}
		}
		staconvect.put("#", t);
		t=t+1;
		for(int i=0;i<existV.size();i++) {
			String str=existV.get(i);
			if(!str.equals("Y"))
			{
				staconvect.put(str, t);
				t=t+1;
			}
		}
	System.out.println(staconvect.keySet());
	System.out.println(staconvect.values());
		int len=staconvect.size();
		table=new String[status.size()][len];
		for(int i=0;i<status.size();i++) {
			for(int j=0;j<len;j++)
				table[i][j]="--";
		}
		
	}
	public void stack_parser() {
		Stack<String> wstack=new Stack<String>();
		Scanner in=new Scanner(System.in);
		Stack<String> parser1=new Stack<String>();
		Stack<Integer> parser2=new Stack<Integer>();
		System.out.println("��������Ҫ��������䣺");
		String sentence=in.next();
		System.out.println(sentence);
		String[] tempw=sentence.split("");
		 for(int i=tempw.length-1;i>-1;i--) {
			 wstack.push(tempw[i]);
		 }
		parser1.push("#");
		parser2.push(0);
		prints(parser2,parser1);
		while(parser1.size()>0) {
			String strtoken=null;
			if(wstack.peek().equals("#")) 
				strtoken="#";
			else
				strtoken=wstack.pop();
			
			int  curstate=parser2.peek();
			String lrinfo=table[curstate][staconvect.get(strtoken)];
			if(lrinfo.contains("S")) {
				int getstate=Integer.parseInt(lrinfo.replaceAll("[^(0-9)]", ""));
				System.out.print("--- w:"+strtoken+"    action["+curstate+"]["+strtoken+"]=" +lrinfo+" �ƽ�״̬"+getstate+"���������"+strtoken);
				parser1.push(strtoken);	
				parser2.push(getstate);
				}
			if(lrinfo.contains("r")) {
				System.out.print("--- w:"+strtoken+"    action["+curstate+"]["+strtoken+"]=" +lrinfo+"  ���յ�");
				int getstate=Integer.parseInt(lrinfo.replaceAll("[^(0-9)]", ""));
				for(int i=0;i<G.get(getstate).getRight().size();i++) {
					parser1.pop();
					parser2.pop();
				}
				System.out.print(getstate+"����ʽ: "+G.get(getstate).getLeft()+"->"+G.get(getstate).getRight()+"��Լ��");
				parser1.push(G.get(getstate).getLeft());
				prints(parser2,parser1);
				getstate=parser2.peek();
				int getstate2=Integer.parseInt(table[getstate][staconvect.get(parser1.peek())]);
				System.out.print("goto["+getstate+"]["+parser1.peek()+"]="+getstate2+" ,��״̬"+getstate2+"ѹ��ջ��");
				parser2.push(getstate2);
				if(!strtoken.equals("#"))
				wstack.push(strtoken);
			}
			
			if(lrinfo.equals("acc")) {
				System.out.print("--- w:"+strtoken+"    action["+curstate+"]["+strtoken+"]=" +lrinfo+"���ܣ�");
				parser1.pop();
				parser2.pop();
				prints(parser2,parser1);
				System.out.println("�﷨�������");
				return;
				}
			
			prints(parser2,parser1);
		}
		
	}
	public void prints(Stack sta1,Stack sta2) {
		Stack sta11=new Stack();
		Stack sta22=new Stack();
		sta11=(Stack) sta1.clone();
		sta22=(Stack) sta2.clone();
		System.out.println("");
		while(!sta11.empty()) {
			System.out.print(sta11.pop()+" ");
		}
		while(!sta22.isEmpty()) {
			System.out.print(sta22.pop()+" ");
		}
	}
	public static void main(String[] args) throws IOException { 

		wordAnalyse TI=new wordAnalyse();
		TI.read_procedure();
		TI.wordresult();
		 getTable ti=new getTable();
		 try {
			ti.get_grammer();
			ti.initfirst();
			ti.get_first();
			ti.get_stauts();
			ti.generateLR();
			ti.stack_parser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);

		 
	 }
	 
}