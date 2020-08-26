#include<iostream>
#include<cstdlib>
using namespace std;
int main()
{
    //1 red 2 green 3 blue 4 white 5 black
    int arr[9][5],i,j;
    int k=0;
    for(i=0;i<9;i++)
    {
        for(j=0;j<5;j++)
        {
            arr[i][j]=rand()%5+1;
        }
    }
    cout<<"Showing the array formed by computer\n";
    for(i=0;i<9;i++)
    {
        for(j=0;j<5;j++)
        {
            cout<<arr[i][j]<<" ";
        }
        cout<<"\n";
    }

    //basic opereation starts from here
    cout<<"\n";
    int l=1;
    int ii,jj;
    int n[10],m[10],o[10];
    int p=0,ko=0;
    for(i=0;i<9;i++)
    {
        k=arr[i][0];
        ii=i;
        for(j=1;j<5;j++)
        {
            if(arr[i][j]==k)
            {
                l++;
                continue;
            }

            else
            {
                if(l>=3)
                {
                    n[p]=ii;
                    m[p]=j-l;
                    o[p]=l;
                    ko++;
                }
                k=arr[i][j];
                l=1;
            }
        }
    }
    int low,key,lew;
    for (i=0;i<ko;i++)
    {
        low=o[i];
        lew=m[i];
        while(low!=0)
        {
            key=n[i];
            while(key>0)
            {
                arr[key][lew]=arr[key-1][lew];
                key--;
            }
            lew=m[i]++;
            low--;
        }
        for(j=m[i];j<=o[i]+m[i];j++)
        {
            arr[0][j]=rand()%5+1;
        }
    }




    int lv=1;
    int iv,jv;
    int nv[10],mv[10],ov[10];
    int pv=0,kov=0;
    for(i=0;i<5;i++)
    {
        k=arr[0][i];
        iv=0;
        jv=i;
        for(j=1;j<9;j++)
        {
            if(arr[j][i]==k)
            {
                lv++;
                continue;
            }
            else
            {
                if(lv>=3)
                {
                    nv[pv]=j-lv;
                    mv[pv]=jv;
                    ov[pv]=l;
                    kov++;
                }
                k=arr[j][i];
                lv=1;
            }
        }
    }
    int lowv,keyv,lewv;
    for (i=0;i<kov;i++)
    {
        lowv=ov[i];
        lewv=nv[i];
        while(lowv!=0)
        {
            keyv=mv[i]+lowv;
            int t=mv[i];
            while(keyv>mv[i])
            {
                arr[lewv][keyv]=arr[lewv][t-1];
                keyv--;
                t--;
            }
            lewv=nv[i]++;
            lowv--;
        }
        for(j=nv[i];j<=ov[i]+m[i];j++)
        {
            arr[0][j]=rand()%5+1;
        }
    }
    cout<<"printing the new one";
    for(i=0;i<9;i++)
    {
        for(j=0;j<5;j++)
        {
            cout<<arr[i][j]<<" ";
        }
        cout<<"\n";
        cout<<"printing this";
    }

    return 0;
}