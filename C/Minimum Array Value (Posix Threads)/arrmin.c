#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

typedef struct {
    int n;
    int min;
    int **arrA;
    int **arrB;
    int numThreads;
} MStruct;

MStruct miStrc;
pthread_barrier_t barrier;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void *calculateMin(void* arg){
    int i, j, begin, end, min = 100000000, pos = (int)arg;

    //Begin and end of the array elements to be calculated by a thread
    begin = pos*(miStrc.n/miStrc.numThreads);
    end = begin + (miStrc.n/miStrc.numThreads);

    for(i=begin; i<end; i++){
        for(j=0; j<miStrc.n; j++){
            if(miStrc.arrA[i][j]<min){
                min = miStrc.arrA[i][j];
            }
        }
    }

    pthread_mutex_lock(&mutex);
    if(min<miStrc.min){
        miStrc.min = min;
    }
    pthread_mutex_unlock(&mutex);

    pthread_barrier_wait(&barrier);

    for(i=begin; i<end; i++){
        for(j=0; j<miStrc.n; j++){
            miStrc.arrB[i][j] = miStrc.arrA[i][j]-miStrc.min;
        }
    }

    pthread_exit(NULL);
}


int main(int argc, char const *argv[]) {
    int i, j;

    //User data entry
    printf("Give size of 2D array: ");
    scanf("%d",&miStrc.n);
    printf("\nGive number of threads: ");
    scanf("%d",&miStrc.numThreads);

    //Struct data initialization
    miStrc.min=100000000;
    miStrc.arrA = (int**)malloc(miStrc.n*sizeof(int*));
    miStrc.arrB = (int**)malloc(miStrc.n*sizeof(int*));
    for(i=0; i<miStrc.n; i++){
        miStrc.arrA[i] = (int*)malloc(miStrc.n*sizeof(int));
        miStrc.arrB[i] = (int*)malloc(miStrc.n*sizeof(int));
    }

    for(i=0; i<miStrc.n; i++){
        for(j=0; j<miStrc.n; j++){
            miStrc.arrA[i][j] = (i+1)*(j+1);
        }
    }

    pthread_t threads[miStrc.numThreads];

    pthread_barrier_init(&barrier, NULL, miStrc.numThreads);

    for(i=0; i<miStrc.numThreads; i++){
            pthread_create(&threads[i], NULL, calculateMin, (void *)i);
    }

    for(i=0; i<miStrc.numThreads; i++){
        pthread_join(threads[i],NULL);
    }

    printf("The min of the array is %d\n", miStrc.min);

    pthread_barrier_destroy(&barrier);
    pthread_mutex_destroy(&mutex);

    for(i=0; i<miStrc.n; i++){
        for(j=0; j<miStrc.n; j++){
            printf("%d ",miStrc.arrB[i][j]);
        }
        printf("\n");
    }

    for(i=0; i<miStrc.n; i++){
        free(miStrc.arrA[i]);
        free(miStrc.arrB[i]);
    }
    free(miStrc.arrA);
    free(miStrc.arrB);
}

