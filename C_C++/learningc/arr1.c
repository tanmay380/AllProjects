#include<stdio.h>

int traverse(int arr[],int d,int n){
    int tarr[10];
    for(int i=0;i<d;i++){
        tarr[i]=arr[i];
    }
    for(int i=d;i<n;i++){
        arr[i-d]=arr[i];
    }
    for(int i=0;i<n;i++){
        printf("%d\n",arr[i]);
    }
}

int main()
{
    int arr[7]={1,2,3,4,5,6,7};
    traverse(arr[7],2,7);
}
