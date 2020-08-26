#include <stdio.h>
#include <stdbool.h>
#define N 8

bool MATRIX[N][N] = {{0, 0, 1, 0}, 
                    {0, 0, 1, 0}, 
                    {0, 0, 0, 0}, 
                    {0, 0, 1, 0}};

bool knows(int a, int b)
{
    return MATRIX[a][b];
}

int main()
{
    int n=4;
    int indegree[4]={0},outdegree[4]={0}; 
    //query for all edges 
    for(int i=0; i<n; i++) 
    { 
        for(int j=0; j<n; j++) 
        { 
            int x = knows(i,j); 
            //  printf("value of x  is %d\n",x);
            //set the degrees 
            outdegree[i]+=x; 
            indegree[j]+=x; 
            printf("value of out at %d is %d\t",i,outdegree[i]);
            printf("value ot in  at %d is %d\n",j,indegree[j]);
        } 
    }
    for(int i=0; i<n; i++) {
        printf("\nvalue of out at %d is %d\t\n",i,outdegree[i]);
        printf("value ot in  at %d is %d\n",i,indegree[i]);
    if(indegree[i] == n-1 && outdegree[i] == 0) {
        printf("\n%d %d %d %d\n",i,n-1,outdegree[i],indegree[i]); }}
    
}
