#include <stdio.h>
#include <stdlib.h>
#include <time.h>

char pin[100][100];

int InputM(){

	int iso;
	printf("\n");
	do{
	printf("Give dimension M of rectangle: ");
	scanf("%d",&iso);
    }
    while(iso>100);
	return iso;
}

int InputN(){

	int iso;
	do{
	printf("Give dimension N of rectangle: ");
	scanf("%d",&iso);
	}
	while(iso>100);
	return iso;
}

int InputK(int g,int h){

	int iso;
	do{
	printf("Give number of bombs to be placed: ");
	scanf("%d",&iso);
	}
	while(iso>(g*h));
	return iso;
}

void CreateBoard(int x ,int y)
{

    int row, col;
    for(row = 0; row < x; row++){
        for(col = 0; col < y; col++){
            pin[row][col] = 'o';
        }
    }

}

void FinalBoard(int x ,int y)
{
    int row, col;
    for(col = 0; col < y; col++){
        printf("%d ", col + 1);
    }
    printf("\n\n");
    for(row = 0; row < x; row++){
        for(col = 0; col < y; col++){
            printf("%c ", pin[row][col]);
        }
        printf(" %d ", row + 1);
        printf("\n");
    }
}

void InsertMines(int x,int u,int p)
{
    int i, random,random1;
    srand(time(NULL));
    for (i = 0; i < x; i++){
        random = rand() % (u);
        random1 = rand() % (p);
        pin[random][random1] = '*';
    }

}

char FindNearby(int r,int c){

    int i=r,j=c,found=0,k;
    char C;
    if(pin[r][c] == '*'){
	    return;
    }
    else{
    if(pin[i-1][j-1] == '*'){
        found++;
    }
    if(pin[i-1][j] == '*'){
        found++;
    }
    if(pin[i-1][j+1] == '*'){
        found++;
    }
    if(pin[i][j-1] == '*'){
        found++;
	}
    if(pin[i][j+1] == '*'){
        found++;
	}
    if(pin[i+1][j-1] == '*'){
        found++;
	}
    if(pin[i+1][j] == '*'){
        found++;
	}
    if(pin[i+1][j+1] == '*'){
        found++;
	}
    C = (char)(((int)'0')+found);
    return C;
	}
}

int main(int argc, char *argv[]) {

	int r_current,c_current,m,n,k,i,j;
	printf("\nMaximum number of rows and columns that can be entered is 100\n\n");
	m=InputM();
	n=InputN();
	k=InputK(n,m);
	CreateBoard(n,m);
	InsertMines(k,n,m);
	for(i=0; i<=n; i++){
		r_current=i;
		for(j=0; j<=m; j++){
			c_current=j;
			pin[r_current][c_current]=(char)FindNearby(r_current,c_current);
	    }
	}
	printf("\n\n");
	FinalBoard(n,m);
	return 0;
}
