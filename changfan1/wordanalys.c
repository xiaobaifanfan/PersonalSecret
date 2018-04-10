#include<stdio.h>
#include<stdbool.h>
#include<string.h>
#include "wordanalys.h"

char  procedure[1000];
int  cleanprocedure=0;
struct globaltable{
int syn;
char s[20];
void *next;
}globaltable;
//关键字表
 char keywordTable[31][15]={
"auto", "break", "case", "char", "const", "continue","default", "do", "double", "else", "enum", "extern",
"float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static",
"struct", "switch", "typedef", "union", "unsigned", "void", "while"
};
//运算符表
 char operatorTable[36][10]={
"+", "-", "*", "/", "<", "<=", ">", ">=", "=", "==","!=", ";", "(", ")", "^", ",", "\"", "\'", "#", "&",
"&&", "|", "||", "%", "~", "<<", ">>", "[", "]", "{","}", "\\", ".", "\?", ":", "!"
};

//标识符表
 char tokenTable[1000][20]={""};
//判断读入的字符是否是字母
bool IsLetter(char ch){
if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z'))
    return true;
else
    return false;
}

//判断输入的字符是否是数字
bool IsDigit(char digit)
{
    if(digit>='0'&&digit<='9')
        return true;
    else
        return false;
}
//


int  TestDot(char s[])
{
    int count=0;
    int len=strlen(s);
    for(int i=0;i<len;i++){
        if(s[i]=='.'){
           count=count+1;
        }
    }
    return count;
}
//预处理，清理程序中的注释和空行
void filterProcedure(char r[],int preProject){
   char tempArray[10000];
   int count=0;
   for(int i=0;i<preProject+1;i++){
    if(r[i]=='/'&&r[i+1]=='/'){
        while(r[i]!='\n')
        {
            i++;
        }
    }
    if(r[i]=='/'&&r[i+1]=='*'){
        i=i+2;
        while(r[i]!='*'||r[i+1]!='/'){
            i=i+1;
            if(r[i]=='@')
            {
                printf("错误!出现不封闭错误,请检查程序……");
                exit(0);
            }
        }
        i=i+2;
    }
  if(r[i]!='\n'&&r[i]!='\t'&&r[i]!='\v'&&r[i]!='\r')
   {
       tempArray[count++]=r[i];
    }
}

    tempArray[count]='\0';
    strcpy(procedure,tempArray);
    cleanprocedure=count;
}


int  searchKeyword(char keywordTable[][15],char s[]){
int i=0;
for(i=0;i<31;i++){
    if(strcmp(keywordTable[i],s)==0){
        return i+1;
    }
}
return -1;
};
int pProject=0;
int  wordTranslator(char identifyword[],int sortid){
    int i,count=0;
    char ch;
    ch=procedure[pProject];
    while(ch==' '){
    pProject++;
    ch=procedure[pProject];
    }
    for(i=0;i<20;i++)
    {
        identifyword[i]='\0';
    }
    if(IsLetter(procedure[pProject]))
    {
        identifyword[count++]=procedure[pProject];
        pProject++;
        while(IsLetter(procedure[pProject])||IsDigit(procedure[pProject])){
            identifyword[count++]=procedure[pProject];
            pProject++;
        }

    identifyword[count]='\0';
    sortid=searchKeyword(keywordTable,identifyword);
    if(sortid==-1){
        sortid=100;
    }
    return sortid;
}
else if(IsDigit(procedure[pProject])){
    while(IsDigit(procedure[pProject])||procedure[pProject]=='.'){
        identifyword[count++]=procedure[pProject];
        pProject++;
    }
    identifyword[count++]='\0';
    switch(TestDot(identifyword)){
        case 0:
            sortid=98;
            break;
        case 1:
            sortid=99;
            break;
        default:
             printf("error!数字%s输入错误\n",identifyword);
             sortid=101;
             break;
    }
     return sortid;
}
else if(ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == ';' || ch == '(' || ch == ')' || ch == '^'
  || ch == ',' || ch == '\"' || ch == '\'' || ch == '~' || ch == '#' || ch == '%' || ch == '['
 || ch == ']' || ch == '{' || ch == '}' || ch == '\\' || ch == '.' || ch == '\?' || ch == ':')
 {

     identifyword[0]=procedure[pProject];
     identifyword[1]='\0';
     for(i=0;i<36;i++){
        if(strcmp(identifyword,operatorTable[i])==0)
        {
        sortid=32+i;
            break;
        }
     }
     pProject++;
     return sortid;
 }
 else if(procedure[pProject]=='<'){
    pProject++;
    if(procedure[pProject]=='='){
        sortid=37;
    }else if(procedure[pProject]=='<'){
    sortid=57;
    }else{
    pProject--;
    sortid=36;
    }
    pProject++;
    return sortid;
 }
 else if(procedure[pProject]=='>'){
     pProject++;
    if(procedure[pProject]=='='){
        sortid=39;
    }else if(procedure[pProject]=='>'){
    sortid=58;
    }else{
    pProject--;
    sortid=38;
    }
    pProject++;
    return sortid;

 }
 else if(procedure[pProject]=='='){
    pProject++;
    if(procedure[pProject]=='='){
        sortid=41;
    }
    else{
        pProject--;
        sortid=40;
    }
    pProject++;
    return sortid;
 }
 else if(procedure[pProject]=='!'){
    pProject++;
    if(procedure[pProject]=='=')
    {
        sortid=42;
    }else{
    sortid=67;
    pProject--;
    }
    pProject++;
    return sortid;
 }
 else if(procedure[pProject]=='&')
 {
    pProject++;
    if(procedure[pProject]=='&')
    {
        sortid=52;
    }else{
    pProject--;
    sortid=51;
    }
    pProject++;
    return sortid;
 }
 else if(procedure[pProject]=='|')
 {
    pProject++;
    if(procedure[pProject]=='|')
    {
        sortid=54;
    }else{
    pProject--;
    sortid=53;
    }
    pProject++;
    return sortid;
 }
 else if(procedure[pProject]=='@')
{

    sortid=0;
return sortid;
 }
 else {
    printf("error:this is illegal  character  %d\n",ch);
    exit(0);

 }

};


