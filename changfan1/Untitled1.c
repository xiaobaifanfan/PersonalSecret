
#include <stdio.h>

int main()
{
	int i,sum,a[10];
	float avg;
	sum=0;
	for(i=0;i<10;i++)
	{
		sum=sum+a[i];
    }
	avg=float(sum/i);
	printf("sum=%3d,avg=%.2f\n",sum,avg);
	return 0;
}
