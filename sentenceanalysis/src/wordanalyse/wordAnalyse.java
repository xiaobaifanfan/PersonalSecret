package wordanalyse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class wordAnalyse{
	char procedure[];
	int cleanprocedure=0;
	String keywordTable[]= {"auto", "break", "case", "char", "const", "continue","default", "do", "double", "else", "enum", "extern",
			"float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
			"struct", "switch", "typedef", "union", "unsigned", "void", "while"};
	String operatorTable[]= {
			"+", "-", "*", "/", "<", "<=", ">", ">=", "=", "==","!=", ";", "(", ")", "^", ",", "\"", "\'", "#", "&",
			"&&", "|", "||", "%", "~", "<<", ">>", "[", "]", "{","}", "\\", ".", "/?", ":", "!"};
   ArrayList<String> tmppros=new ArrayList<String>();
    public int globalpoint;
   public void read_procedure() throws IOException {
	   BufferedReader reader = new BufferedReader(new FileReader("D:/avgscore.txt"));
	   String buf;
	   while ((buf=reader.readLine()) != null) {
	       if (buf.isEmpty()) {continue;}
	      
	      buf = buf.replaceAll("\\s+(.*)", "$1"); //È¥µôÇ°ÃæµÄ¿Õ¸ñ
	      String[] linestr=buf.split("");
			int len=buf.length();
	      for(int i=0;i<buf.length();i++) {
	    	  if(!linestr[i].equals(null))
	    	  tmppros.add(linestr[i]);
	    	  System.out.println(linestr[i]+"--------"+linestr[i].length());
	      }
	   }
	   tmppros.add("@");
	   System.out.println(tmppros);
	   reader.close();
	   
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
   public boolean IsLetter(String ch) {
	   if(Character.isLetter(ch.charAt(0)))
		   return true;
	   else
		   return false;
   }
   public void filterProcedure() throws Exception {
	   ArrayList<String> temp=tmppros;
	   ArrayList<String> temptmp=new ArrayList<String>();
	   temp.add("\0");
	   temp.add("\0");
	   System.out.println(temp);
	   for(int i=0;i<tmppros.size();i++) {
		   if(temp.get(i).equals("/")&&temp.get(i+1).equals("/"))
		   {
			   System.out.println("Ë«Ð±¸Ü×¢ÊÍ");
			   while(!temp.get(i).equals("/n")&&i<temp.size()) {
				   i++;
			   }
		   }
		   if(temp.get(i).equals("/")&&temp.get(i+1).equals("*")) {
			   i=i+2;
			   while(!temp.get(i).equals("*")||!temp.get(i+1).equals("/")) {
				   i=i+1;
				   if(temp.get(i).equals("@")) {
					   System.out.println("´íÎó£¡³öÏÖ²»·â±Õ´íÎó£¬Çë¼ì²é³ÌÐò¡­¡­");
					   return;
				   }
			   }
			   i=i+2;
		   }
		   if(!temp.get(i).equals("\n")&&!temp.get(i).equals("\t")&&!temp.get(i).equals("/v")&&temp.get(i).equals("\r")) {
			  temptmp.add(tmppros.get(i));
		   }
	   }
	   System.out.println(temptmp);
   }
//   public static void main(String[] args) throws Exception  {
//		
//		wordAnalyse TI=new wordAnalyse();
//		TI.read_procedure();
//		//TI.filterProcedure();
//	
//	}


}
