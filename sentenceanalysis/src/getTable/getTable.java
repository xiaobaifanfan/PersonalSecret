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
		temp1.addElement("Program");
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
		int t;
		while(change) {
			change=false;
			for(Grammer g :G) {
				is_empty=true;
				t=0;
				System.out.println(g.getRight().size());
				int len=g.getRight().size();
				while(len<10) 
				{
					len=len+1;
					is_empty=false;
//					if(!inTV(g.getRight().get(t).toString())) 
//					{
//						if(!first.get(g.left).contains(g.getRight().get(t).toString()))
//						{
//							first.get(g.left).add(g.getRight().get(t).toString());
//							change=true;
//						}
//						continue;
//					}
//					for(Object i :first.get(g.getRight().get(t)))
//					{
//						if(!first.get(g.getLeft()).contains(i)) {
//							first.get(g.getLeft()).add(i);
//							change=true;
//						}
//					}
//					if(first.get(g.getRight().get(t)).contains("ε")) {
//						is_empty=true;
//						t=t+1;
//					}
				}
				if(t==g.getRight().size()&&!first.get(g.getLeft()).contains("ε")) 
				{
					first.get(g.getLeft()).add("ε");
					change=true;
				}
				
			}
			
		}
//		 first.remove("S");
//		 for(int i=0;i<first.size();i++) {
//			 System.out.println(first.values());
//		 }
	}
	public static  void  main(String[] args) 
	{
	
	getTable gettable=new getTable();
	try {
		gettable.get_grammer();
		gettable.get_first();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    }


}
