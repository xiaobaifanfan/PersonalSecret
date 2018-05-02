package wordanalyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class wordAnalyse{
	public int Propoint;
	String procedure=new String();
	int cleanprocedure=0;
	static String keywordTable[]= {"auto", "break", "case", "char", "const", "continue","default", "do", "double", "else", "enum", "extern",
			"float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
			"struct", "switch", "typedef", "union", "unsigned", "void", "while"};
	static String operatorTable[]= {
			"+", "-", "*", "/", "<", "<=", ">", ">=", "=", "==","!=", ";", "(", ")", "^", ",", "\"", "\'", "#", "&",
			"&&", "|", "||", "%", "~", "<<", ">>", "[", "]", "{","}", "\\", ".", "?", ":", "!"};
   static String tokenTable[]= {};
   public void read_procedure() throws IOException {
	   BufferedReader reader = new BufferedReader(new FileReader("D:/avgscore.txt"));
	   String buf;
	   while ((buf=reader.readLine()) != null) {
	       if (buf.isEmpty()) {continue;}
	       buf=buf.trim();
	      procedure=procedure+"\n"+buf;
	   }
	   procedure=procedure+"@";
	   System.out.println("������ı��ǣ�");
	   System.out.println(procedure);
	  procedure=removeComments(procedure);
	  System.out.println("����ע�ͺ���ı�����");
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
    public void wordresult() {
    	int sortid=-1;
	    String identifyword=new String();
	    while(sortid!=0) 
	    {
	    	sortid=wordTranslator(identifyword, sortid);
	    	if(sortid==100)
	    	{
	    		
	    		for(int i=0;i<tokenTable.length;i++) {
	    			if(tokenTable[i].equals(identifyword))
	    			{
	    				break;
	    			}
	    			if(tokenTable[i]==null) {
	    				tokenTable[i]=identifyword;
	    			}
	    		}
	    		System.out.println("(100,"+identifyword+")��ʶ��");
	    	}
	    	else if(sortid==99) 
	    	{
	    		System.out.println("(99,"+identifyword+")����");
	    	}
	    	else if(sortid>31&&sortid<68)
	    	{
	    		System.out.println("("+sortid+","+operatorTable[sortid-32]+")�����/���");
	    	}
	    	else if(sortid>0&&sortid<32)
	    	{
	    		System.out.println("("+sortid+","+keywordTable[sortid-1]+")�ؼ���");
	    	}
	    }
    }
   public int wordTranslator(String identifyword,int sortid) 
   {
	   int i,count=0;
	   identifyword="";
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
//   public static void main(String[] args) throws Exception  {
//		
//		wordAnalyse TI=new wordAnalyse();
//		TI.read_procedure();
//		TI.wordresult();
//	    
//	}


}
