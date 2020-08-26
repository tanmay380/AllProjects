#include<iostream>
#include<stdlib.h>
#include<string>
#include<utility>
#include<time.h>

#define n 5
#define m 5

using namespace std;

int score=0;

char valid_keys[5]={'r', 'g', 'b', 'w'};

bool is_blank_above(char grid[n][m], int a, int b){
    for(; a>=0; --a){
        if(grid[a][b]!='0'){
            return false;
        }
    }
    return true;
}

bool any_match_found(char grid[n][m]){
    for(int j=0; j<m; j++){
        for(int i=0; i<n; i++){
            int count=0, k=i;
            do{
                count++;
                k++;
            }while(grid[i][j]==grid[k][j]);
            if(count>=3){
                return true;
            }
        }
    }

    for(int j=0; j<m; j++){
        for(int i=0; i<n; i++){
            int count=0, k=i;
            do{
                count++;
                k++;
            }while(grid[i][j]==grid[k][j]);
            if(count>=3){
                return true;
            }
        }
    }
    return false;
}

bool is_adj(char grid[n][m], int tx, int ty, int txi, int tyi){
    if( ((tx-1)==txi)||((tx+1)==txi)||((ty-1)==tyi)||((ty+1)==tyi) ) return true;
    else return false;
}

void clear(){
    getchar();
    system("cls");
}

void input_grid(char grid[n][m]){
    cout<<"Enter elements for 5x5 grid:\n";
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin>>grid[i][j];
        }
    }
}

void output_grid(char grid[n][m]){
    // char alpha='A';
    cout<<"   ";
    for(int i=0; i<n; i++){
        // cout<<alpha++<<" ";
        cout<<i<<" ";
    }
    cout<<"\n  ";
    for(int i=0; i<n; i++) cout<<"__";
    for(int i=0; i<n; i++){
        cout<<"\n"<<i<<" |";
        for(int j=0; j<m; j++){
            cout<<grid[i][j]<<" ";
        }
        cout<<"\n";
    }
    cout<<"\n\nSCORE: "<<score<<"\n";
}

void collapse_blank(char grid[n][m]){
    for(int j=0; j<m; j++){
        for(int i=n-1; i>=0; i--){
            if(!is_blank_above(grid, i, j)){
                while(grid[i][j]=='0'){
                    for(int k=i; k>0; k--){
                        grid[k][j]=grid[k-1][j];
                        grid[k-1][j]='0';
                    }
                }
            }
        }
    }
}

void rand_fill(char grid[n][m]){
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(grid[i][j]=='0'){
                grid[i][j]=valid_keys[rand()%4];
            }
        }
    }
}

void clear_cell(char grid[n][m], int count, int i, int j, char mode){
    switch(mode){
        case 'h': while(count--){
                grid[i][j++]='0';
                score++;
            }
            break;

        case 'v': while(count--){
                grid[i++][j]='0';
                score++;
            }
            break;
    }
}

void h_check(char grid[n][m]){
    // if(any_match_found(grid))
    //   clear_cell(grid, count, i, j, 'h');
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            int count=0, k=j;
            do{
                count++;
                k++;
            }while(grid[i][j]==grid[i][k]);
            if(count>=3){
                clear_cell(grid, count, i, j, 'h');
            }
        }
    }
}

void v_check(char grid[n][m]){
    // if(any_match_found(grid))
    //   clear_cell(grid, count, i, j, 'h');
    for(int j=0; j<m; j++){
        for(int i=0; i<n; i++){
            int count=0, k=i;
            do{
                count++;
                k++;
            }while(grid[i][j]==grid[k][j]);
            if(count>=3){
                clear_cell(grid, count, i, j, 'v');
            }
        }
    }
}

void swap_blocks(char grid[n][m]){
    char a, ai;
    int x, y, xi, yi;
    cout<<"\nEnter coordinates of block to be swapped (Sample Input: 0 2) : ";
    cin>>x>>y;
    // toupper(a);
    // x=(int)a;
    // x-=17;
    //A(65)->0(48)
    cout<<"\nEnter coordinates of block to be swapped (Sample Input: 0 1) : ";
    cin>>xi>>yi;

    if(is_adj(grid, x, y, xi,yi)){
        swap(grid[x][y], grid[xi][yi]);
    }
    else{
        cout<<"\nERROR: BLOCKS ARE NOT ADJACENT!";
    }
}

void reducto(char grid[n][m]){
    int flag=0;
    do{
        h_check(grid);
        v_check(grid);
        collapse_blank(grid);
        rand_fill(grid);
        if(any_match_found(grid)) flag=1;
        else flag=0;
    }while(flag!=0);
}

int main(){
    srand(time(NULL));
    char grid[n][m];
    input_grid(grid);
    char ch;
    do{
        clear();
        cout<<"Current grid!\n";
        output_grid(grid);
        swap_blocks(grid);
        cout<<"\n\nPress ENTER to clear blocks!\n";
        h_check(grid);
        v_check(grid);
        clear();
        cout<<"\nCleared matches!\n";
        output_grid(grid);
        cout<<"\n\nPress ENTER to scramble blocks!\n";
        collapse_blank(grid);
        rand_fill(grid);
        clear();
        cout<<"\nScrambled matches!\n";
        output_grid(grid);
        cout<<"\n\nPress ENTER to reduce all matching blocks!\n";
        clear();
        reducto(grid);
        output_grid(grid);
        cout<<"\n\nContinue?(Y/N) ";
        cin>>ch;
    }while(toupper(ch)=='Y');
    clear();
    output_grid(grid);
    cout<<"\n\nFINAL SCORE: "<<score;
    return 0;
}