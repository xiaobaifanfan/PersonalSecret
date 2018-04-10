#ifndef WORDANALYS_H_INCLUDED
#define WORDANALYS_H_INCLUDED
#include<stdbool.h>

//关键字表
extern  char keywordTable[31][15];
//运算符表
extern  char operatorTable[36][10];

//标识符表
extern char tokenTable[1000][20];
extern int cleanprocedure;
extern int pProject;
extern  char procedure[1000];
//存储常数
extern int number[1000][100];
//数字的种别码为100
extern struct globaltalbe;
bool IsLetter(char ch);
bool IsDigit(char digit);
int  TestDot(char s[]);
void filterProcedure(char r[],int preProject);
int  searchKeyword(char keywordTable[][15],char s[]);
int  wordTranslator(char identifyword[],int sortid);
#endif // WORDANALYS_H_INCLUDED
