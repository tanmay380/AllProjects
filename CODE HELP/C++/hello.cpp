#include<iostream>
#include <bits/stdc++.h>
using namespace std;

 bool isPossible(long long int arr[], long int m, long long int mid, int size){
        long long int curheght=0;

        for(int i=0;i<size;i++){
            if(arr[i]>mid){
                curheght+=(arr[i]-mid);
            }
        }
        
        if(curheght>=m){
            return true;
        }
        else 
            return false;
    }
int main(){
        int size;
        long int m;
        scanf("%d", &size);
        scanf("%ld", &m);

        long long int arr[size] ;
        
        long long int end=0;

        for (int i = 0; i <size; i++) {
            scanf("%lld", &arr[i]);
        }
        long long int ans=-1;
        long long int start=0;
        
        for (int i = 0; i < size; i++) {
            end+=arr[i];
        }

        long long int mid = (start+end)/2;
        
        while(start<=end){
            if(isPossible(arr, m , mid, size)){
                ans=mid;
                start=mid+1;
            }else{
                end=mid-1;
            }
            mid = (start+end)/2;
        }
        printf("%lld",ans);
}

   
    
    

   

