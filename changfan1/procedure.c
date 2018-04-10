#include<stdio.h>
#include "wordanalys.h"
#include<stdbool.h>
#include<string.h>
int main(){
int i=0,preProject=0;
char  temp[1000]={};
char identifyword[20]={0};
FILE *fp;
fp=fopen("D://avgscore.txt","r");
if(fp==NULL)
{
    printf("Sorry! open the file failture……\n");
    return 0;
}
char ch=fgetc(fp);
while(ch!=EOF){
     temp[i]=ch;
     printf("%c",ch);
     i++;
    ch=fgetc(fp);

}
temp[i]='@';
 printf("%c\n",temp[i]);
preProject=i;
filterProcedure(temp,preProject);
for(i=0;i<cleanprocedure;i++){
    printf("%c",procedure[i]);
}

FILE *fpwfile=fopen("D:\\changfan.txt","w+");
if(fpwfile==NULL){
    printf("error! Cannot open this file");
    exit(0);
}
int sortid=-1;
while(sortid!=0){
        sortid=wordTranslator(identifyword,sortid);
   if(sortid==100){
    for(i=0;i<20;i++){
        if(strcmp(tokenTable[i],identifyword)==0){
            break;
        }
        if(strcmp(tokenTable[i],"")==0)
        {
            strcpy(tokenTable[i],identifyword);
            break;
        }
    }
    printf("(100,%s)标识符\n",identifyword);
    fprintf(fpwfile,"(%d,%s)\n",100,identifyword);
   }
   else if(sortid==99){
    printf("(99，%s)小数\n",identifyword);
    fprintf(fpwfile,"(99,%s)小数\n",identifyword);
   }else if(sortid==98){
       printf("(98,%s)整数\n",identifyword);
       fprintf(fpwfile,"(98,%s)整数\n",identifyword);
    }else if(sortid>31&&sortid<68)
   {
       printf("(%d,-)%s\n",sortid,operatorTable[sortid-32]);
        fprintf(fpwfile,"(%d,-)%s\n",sortid,operatorTable[sortid-32]);
   }else if(sortid>0&&sortid<32)
   {
       printf("(%d,%s)关键字\n",sortid,keywordTable[sortid-1]);
       fprintf(fpwfile,"(%d,%s)\n",sortid,keywordTable[sortid-1]);
   }

}
fclose(fpwfile);
fclose(fp);
return 0;

}

