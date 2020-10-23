#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>

typedef struct {
    int *a;
    int *b;
    int length;
    int numThreads;
    int totalSum;
} DotVectors;

sem_t sem;
DotVectors dVect;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

void *calculateDotProductMux(void* arg){
    int i, begin, end, localSum = 0, pos = (int)arg ;

    //Begin and end of the array elements to be calculated by a thread
    begin = pos*(dVect.length/dVect.numThreads);
    end = begin + (dVect.length/dVect.numThreads);

    for(i=begin; i<end; i++){
        localSum += dVect.a[i] * dVect.b[i];
    }

    //Lock sensitive area for data entry
    pthread_mutex_lock(&mutex);
    dVect.totalSum += localSum;
    pthread_mutex_unlock(&mutex);

    pthread_exit(NULL);
}

void *calculateDotProductSem(void* arg){
    int i, begin, end, localSum = 0, pos = (int)arg ;

    //Begin and end of the array elements to be calculated by a thread
    begin = pos*(dVect.length/dVect.numThreads);
    end = begin + (dVect.length/dVect.numThreads);

    for(i=begin; i<end; i++){
        localSum += dVect.a[i] * dVect.b[i];
    }

    //Lock sensitive area for data entry
    sem_wait(&sem);
    dVect.totalSum += localSum;
    sem_post(&sem);

    pthread_exit(NULL);
}


int main(int argc, char const *argv[]) {
    int i, input;

    //User data entry
    printf("Give length of array: ");
    scanf("%d",&dVect.length);
    printf("\nGive number of threads: ");
    scanf("%d",&dVect.numThreads);

    //User choice for calculation with mutex or semaphores
    printf("\nSelect way to execute calculation:\n\t1.With Mutex\n\t2.With Semaphores\nYour Selection: ");
    scanf("%d",&input);

    //Struct data initialization
    dVect.totalSum = 0;
    dVect.a = (int*) malloc(dVect.length*sizeof(int));
    dVect.b = (int*) malloc(dVect.length*sizeof(int));
    for(i=0; i<dVect.length; i++){
        dVect.a[i] = i+1;
        dVect.b[i] = i+1+dVect.length;
    }

    //Thread array initialization
    pthread_t threads[dVect.numThreads];

    if(input=1){
        for(i=0; i<dVect.numThreads; i++){
            pthread_create(&threads[i], NULL, calculateDotProductMux, (void *)i);
        }
    }else{
        sem_init(&sem, 0, 0);
        for(i=0; i<dVect.numThreads; i++){
            pthread_create(&threads[i], NULL, calculateDotProductSem, (void *)i);
        }
    }

    //Thread wait
    for(i=0; i<dVect.numThreads; i++){
        pthread_join(threads[i],NULL);
    }

    //Mutex and semaphore destruction
    pthread_mutex_destroy(&mutex);
    sem_destroy(&sem);

    printf("The dot product of the vectors is %d\n", dVect.totalSum);

    free(dVect.a);
    free(dVect.b);
}

