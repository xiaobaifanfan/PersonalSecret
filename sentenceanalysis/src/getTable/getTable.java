package getTable;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import mode.Grammer;

public class getTable {
	 String V[];
	 String T[];
	 Vector<String> existV=new Vector<String>();
	 Vector<String>  existT=new Vector<String>();
	 Vector<Grammer> G=new Vector<Grammer>();
	 HashMap<String,Vector> first=new HashMap<String,Vector>();
	 public void get_grammer() throws IOException {
		 int i=0;
		 Grammer tmp=new Grammer();
		 tmp.setLeft("Y");
		 String filename="D:/production.txt";
		 File file=new File(filename);
		 BufferedReader bufread;
		 String read=null;
		 try {
			bufread=new BufferedReader(new FileReader(file));
			while((read=bufread.readLine())!=null) {
				String[] linestr=read.split("");
				int len=read.length();
				if(i==0) {
					V=linestr;
				}else if(i==1){
					T=linestr;
				}else if(i>1){
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
				}
				i=i+1;
				}
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Vector temp=new Vector();
		 temp.add(G.get(0).getLeft());
		 //temp.add("��");
		 tmp.setRight(temp);
		 G.add(0, tmp);
		 existV.add(0, "Y");
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
	 public static void main(String[] args) { 
		 getTable ti=new getTable();
		 try {
			ti.get_grammer();
			ti.initfirst();
			ti.get_first();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
}