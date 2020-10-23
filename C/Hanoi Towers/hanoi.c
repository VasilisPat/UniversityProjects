#include <stdio.h>
#include <stdlib.h>

char pos[40];
long trans=0;

void Move(int x, int y){
    trans++;
    if (y>0){
	    pos[x]++;
    }
    else{
	    pos[x]--;
	}
    if (pos[x]>'C'){
    	pos[x]='A';
	}
    if (pos[x]<'A'){
    	pos[x]='C';
	}
 printf("\n%d:Disk %2d at position %2c",trans,x,pos[x]);
}

void Hanoi(int k, int z){

    if (k==0){
	    return;
    }
    Hanoi(k-1,-z);
    Move(k,z);
    Hanoi(k-1,-z);
}

int main(int argc, char *argv[]) {

	int disks,i;
    printf("Number of disks:");
    scanf("%d",&disks);
    for (i=1;i<=disks;i++){
    pos[i]='A';
    }
    Hanoi(disks,1);
	return 0;
}
