#ifndef WORDANALYS_H_INCLUDED
#define WORDANALYS_H_INCLUDED
#include<stdbool.h>

//�ؼ��ֱ�
extern  char keywordTable[31][15];
//�������
extern  char operatorTable[36][10];

//��ʶ����
extern char tokenTable[1000][20];
extern int cleanprocedure;
extern int pProject;
extern  char procedure[1000];
//�洢����
extern int number[1000][100];
//���ֵ��ֱ���Ϊ100
extern struct globaltalbe;
bool IsLetter(char ch);
bool IsDigit(char digit);
int  TestDot(char s[]);
void filterProcedure(char r[],int preProject);
int  searchKeyword(char keywordTable[][15],char s[]);
int  wordTranslator(char identifyword[],int sortid);
#endif // WORDANALYS_H_INCLUDED
