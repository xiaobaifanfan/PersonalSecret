package wordanalyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import mode.Grammer;

public class wordAnalyse{
	public String tmpstr=new String();
	public int Propoint;
	String procedure=new String();
	int cleanprocedure=0;
	static String keywordTable[]= {"auto", "break", "case", "char", "const", "continue","default", "do", "double", "else", "enum", "extern",
			"float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
			"struct", "switch", "typedef", "union", "unsigned", "void", "while"};
	static String operatorTable[]= {
			"+", "-", "*", "/", "<", "<=", ">", ">=", "=", "==","!=", ";", "(", ")", "^", ",", "\"", "\'", "#", "&",
			"&&", "|", "||", "%", "~", "<<", ">>", "[", "]", "{","}", "\\", ".", "?", ":", "!"};
   static ArrayList<String> tokenTable=new ArrayList<String> ();
   public void read_procedure() throws IOException {
	   BufferedReader reader = new BufferedReader(new FileReader("D:/avgscore.txt"));
	   String buf;
	   while ((buf=reader.readLine()) != null) {
	       if (buf.isEmpty()) {continue;}
	       buf=buf.trim();
	      procedure=procedure+"\n"+buf;
	   }
	   procedure=procedure+"@";
	   System.out.println("读入的文本是：");
	   System.out.println(procedure);
	  procedure=removeComments(procedure);
	  System.out.println("过滤注释后的文本：：");
	   System.out.println(procedure);
	   reader.close();
	   
   }
   public static String removeComments(String code)  
   {  
       StringBuilder sb = new StringBuilder();  
       int cnt = 0;  
       for (int i = 0; i < code.length(); i++)  
       {  
           if(cnt == 0)  
           {  
               if(i+1 < code.length() && code.charAt(i) == '/' && code.charAt(i+1) == '*')  
               {  
                   cnt++;  
                   i++;  
                   continue;  
               }  
           }  
           else  
           {  
               if(i+1 < code.length() && code.charAt(i) == '*' && code.charAt(i+1) == '/')  
               {  
                   cnt--;  
                   i++;  
                   continue;  
               }  
               if(i+1 < code.length() && code.charAt(i) == '/' && code.charAt(i+1) == '*')  
               {  
                   cnt++;  
                   i++;  
                   continue;  
               }  
           }  
           if(cnt == 0)  
           {  
               sb.append(code.charAt(i));  
           }  
       } 
       code=sb.toString();
       StringBuffer sb1=new StringBuffer();
       for(int i=0;i<code.length();i++) {
    	   if(i+1 < code.length() && code.charAt(i) == '/' && code.charAt(i+1) == '/')  
           {  
               while(i<code.length()&&code.charAt(i) != '\n')  
               {  
                   i++;  
               }  
               continue;  
           } 
    	   if(code.charAt(i)!='\n')
    	   sb1.append(code.charAt(i));
       }
       
       return sb1.toString();  
   }  
     
   public int searchKeyword(String s) {
	   int i=0;
	   for(i=0;i<31;i++) {
		  if(keywordTable[i].equals(s))
			  return i+1;
	   }
	   return -1;
   }
   

public boolean IsDigit(String digit) {
	if(Character.isDigit(digit.charAt(0)))
		return true;
	else
		return false;
   }
    public void wordresult() throws IOException {
    	int sortid=-1;
	    String identifyword=new String();
	    FileOutputStream testfile = new FileOutputStream("D:/changfan.txt");
   	 	testfile.write(new String("").getBytes());
	   
	    while(sortid!=0) 
	    {
	    	sortid=wordTranslator(sortid);
	    	 String filename="D:/changfan.txt";
	 	    RandomAccessFile randomFile=new RandomAccessFile(filename,"rw");
	    	if(sortid==100)
	    	{
	    		if(!tokenTable.contains(tmpstr))
	    			tokenTable.add(tmpstr);
	    		//System.out.println(tmpstr+"->identify");
	    		try {
	    			long fileLength = randomFile.length();
	    			randomFile.seek(fileLength);
					randomFile.writeBytes("( "+tmpstr+" "+100+" )\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	else if(sortid==99) 
	    	{
	    		//System.out.println(tmpstr+"->number");
	    		try {
	    			long fileLength = randomFile.length();
	    			randomFile.seek(fileLength);
					randomFile.writeBytes("( "+tmpstr+" "+99+" )\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	else if(sortid>31&&sortid<68)
	    	{
	    		//System.out.println(operatorTable[sortid-32]+"->结符");
	    		try {
	    			long fileLength = randomFile.length();
	    			randomFile.seek(fileLength);
					randomFile.writeBytes("( "+operatorTable[sortid-32]+" "+sortid+" )\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    	else if(sortid>0&&sortid<32)
	    	{
	    		tmpstr=keywordTable[sortid-1];
	    		tmpstr=tmpstr.toUpperCase();
	    		//System.out.println(keywordTable[sortid-1].toUpperCase()+"->keyword");
	    		try {
	    			long fileLength = randomFile.length();
	    			randomFile.seek(fileLength);
					randomFile.writeBytes("( "+keywordTable[sortid-1].toUpperCase()+" "+sortid+" )\r\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		
	    	}
	    	randomFile.close();
	    }
    }
   public int wordTranslator(int sortid) 
   {
	   int i,count=0;
	   String identifyword="";
	   char ch=procedure.charAt(Propoint);
	   while(ch==' ') 
	   {
		   Propoint++;
		   ch=procedure.charAt(Propoint);
	   }
	   if(Character.isLetter(procedure.charAt(Propoint))) 
	   {
		   identifyword=identifyword+procedure.charAt(Propoint);
		   Propoint++;
		   while(Character.isLetter(procedure.charAt(Propoint))||Character.isDigit(procedure.charAt(Propoint))) 
		   {
			   identifyword=identifyword+procedure.charAt(Propoint);
			   Propoint++;
		   }
		   sortid=searchKeyword(identifyword);
		   if(sortid==-1) 
		   {
			   sortid=100;
		   }
		   tmpstr=identifyword;
		   return sortid;
	   }
	   else if(Character.isDigit(procedure.charAt(Propoint)))
	   {
		   while(Character.isDigit(procedure.charAt(Propoint))||procedure.charAt(Propoint)=='.')
		   {
			   identifyword=identifyword+procedure.charAt(Propoint);
			   Propoint++;
		   }
		   sortid=99;
		   tmpstr=identifyword;
		   return sortid;
	   }
	   else if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ';' || ch == '(' || ch == ')' || ch == '^'
			   || ch == ',' || ch == '\"' || ch == '\'' || ch == '~' || ch == '#' || ch == '%' || ch == '['
			   || ch == ']' || ch == '{' || ch == '}' || ch == '\\' || ch == '.' || ch == '?' || ch == ':') 
	   {
		   identifyword=identifyword+procedure.charAt(Propoint);
		   for(i=0;i<36;i++) 
		   {
			if(identifyword.equals(operatorTable[i])) {
				sortid=32+i;
				break;
			}
		   }
		   Propoint++;
		   return sortid;
	   }
	   else if(procedure.charAt(Propoint)=='<')
	   {
		   Propoint++;
		   if(procedure.charAt(Propoint)=='=') 
		   {
			   sortid=37;
		   }else if(procedure.charAt(Propoint)=='<')
		   {
			   sortid=57;
		   }else 
		   {
			   Propoint--;
			   sortid=36;
		   }
		   Propoint++;
		   return sortid;
	   }
	   else if(procedure.charAt(Propoint)=='>')
	   {
		   Propoint++;
		   if(procedure.charAt(Propoint)=='=')
		   {
			   sortid=39;
		   }else if(procedure.charAt(Propoint)=='>')
		   {
			   sortid=58;
		   }else 
		   {
			   Propoint--;
			   sortid=38;
		   }
		   Propoint++;
		   return sortid;
	   }
	   else if(procedure.charAt(Propoint)=='=')
	   {
		  Propoint++;
		  if(procedure.charAt(Propoint)=='=')
		  {
			  sortid=41;
		  }else
		  {
			  Propoint--;
			  sortid=40;
		  }
		  Propoint++;
		  return sortid;
	   }
	   else if(procedure.charAt(Propoint)=='!')
	   {
		   Propoint++;
		   if(procedure.charAt(Propoint)=='=')
		   {
			   sortid=42;
		   }else
		   {
			   Propoint--;
			   sortid=67;
		   }
		   Propoint++;
		   return sortid;
	   }
	   else if(procedure.charAt(Propoint)=='&')
	   {
		   Propoint++;
		   if(procedure.charAt(Propoint)=='&')
		   {
			   sortid=52;
		   }else
		   {
			   Propoint--;
			   sortid=51;
		   }
		   Propoint++;
		   return sortid;
	   }
	   else if(procedure.charAt(Propoint)=='|')
	   {
		   Propoint++;
		   if(procedure.charAt(Propoint)=='|')
		   {
			   sortid=54;
		   }else {
			   Propoint--;
			   sortid=53;
		   }
		   Propoint++;
		   return sortid;
	   }
	   else if(procedure.charAt(Propoint)=='@')
	   {
		   sortid=0;
		   return sortid;
	   }
	   else {
		   System.out.println("error:this is illegal  character :"+procedure.charAt(Propoint));
		    System.exit(0);
	   }
	   return sortid;
   }
   public Stack<String> get_wstack() throws IOException{
	 Stack wstack=new Stack<String>();
	 ArrayList<String> warray=new ArrayList<String>();
	 String filename="D:/changfan.txt";
	 File file=new File(filename);
	 BufferedReader bufread;
	 String read=null;
	 String left=null;
	 String[] right=null;
	 try {
		bufread=new BufferedReader(new FileReader(file));
		while((read=bufread.readLine())!=null)
		{
			String[] tmp=read.trim().split(" ");
			int sortnum=Integer.parseInt(tmp[2]);
			switch(sortnum)
			{
			case 100:{
				warray.add("IDENTIFIER");
				continue;
			}
			case 99:{
				warray.add("CONSTANT");
				continue;
			}
			default :{
				warray.add(tmp[1]);
				continue;
			}
			}
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println(warray);
		wstack.add("#");
		for(int i=warray.size()-1;i>-1;i--)
			wstack.push(warray.get(i));
		System.out.println(wstack);
		System.out.println(wstack.peek()+"---------------------------------------");
	 return wstack;
   }
//   public static void main(String[] args) throws Exception  {
//		
//		wordAnalyse TI=new wordAnalyse();
//		TI.read_procedure();
//		TI.wordresult();
//		TI.get_wstack();
//		
//	}


}
