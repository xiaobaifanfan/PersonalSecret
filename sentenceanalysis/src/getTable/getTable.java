package getTable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import mode.Grammer;
public class getTable {
	
	 HashMap<String, HashSet> first=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> follow=new HashMap<String, HashSet>();
	 HashMap<String, HashSet> index=new HashMap<String, HashSet>();
	  Vector<Grammer> G = new Vector<Grammer>();//存储文法
	 
	 String[] V;
	 String[] T;
	 //HashMap<int,Status> statuses;
	 public getTable() {
		String[] V= {
				"S","Program","Type","Block","Stmts","Decl","Stmt",
				"ForAssignment","Assignment","Bool", "Rel", "LExpr",
				"HExpr","Factor","Self_op","HLogic_op","LLogic_op",
				"HMath_op","LMath_op","Judge_op","Bool_value","Array",
				"Fora","Forb","Forc","HRel","LArray","M","N"
			};
		 String[] T= {
				"(" ,")","main","int","bool","return",";","{","}","if",
				"else","while","for","Identifier","Num","[","]","true",
				"false","==","!=",">=","<=",">","<","+","-","*","/","%",
				"||","&&","++","--","!","-",";","=","ε"
			};

		 
		 HashSet<String> empty_set=new HashSet<String>();
		 HashSet<String> empty_set_int=new HashSet<String>();
		 for(int i=0;i<V.length;i++) {
			 first.put(String.valueOf(i), empty_set);
			 follow.put(String.valueOf(i), empty_set);
			 index.put(String.valueOf(i), empty_set_int);
		 }
		//follow.get("Program").add("#");
		return;
	}
	
	boolean inTV(String s) {
		for(int i=0;i<V.length;i++) {
			if(V[i]==s)
				return true;
		}
		return false;
	}
	
	
	public  void get_grammer() throws IOException {	
		Grammer gtmp1 =new Grammer();
		gtmp1.setLeft("S");
		Vector temp1=new Vector();
		temp1.addElement("#");
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
		for(int i=0;i<G.size();i++) {
			HashSet storetemp=new HashSet();
			storetemp.add(i);
			index.put(G.get(i).getLeft(), storetemp);
		} 

	Object[] b=index.keySet().toArray();

		for(int i=0;i<b.length;i++) {
			System.out.println(b[i]);		
		}		
	}
//	public void get_first() {
//		boolean change=true;
//		boolean is_empty;//表示产生式右端为空串
//		int t;
//		while(change) {
//			change=false;
//			for(Grammer g :G) {
//				is_empty=false;
//				
//				
//			}
//		}
//	}
	public static  void  main(String[] args) 
	{
	
	getTable gettable=new getTable();
	try {
		gettable.get_grammer();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }


}
