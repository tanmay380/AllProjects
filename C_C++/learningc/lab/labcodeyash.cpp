#include <iostream>
#include <fstream>
#include<stdlib.h>
#include <string>

using namespace std;

int main()
{
    ifstream dfile;
    dfile.open("pyt.txt");
    string word,fword;
    char s;
    int userpoint=0;
    int turn=0;
    while(1)
    {
		cout<<"Enter Word: ";
    	cin>>word;
    	int previouspoint=userpoint;
    	while(dfile >> fword)
    	{
    		if(turn==0)
    		{
    			turn++;
    			if(word==fword)
    			{
    				userpoint++;
    				s=word[word.length()-1];
    				break;
				}
			}
			if(word==fword && word[0]==s)
    		{
    			userpoint++;
    			s=word[word.length()-1];
    			break;
			}
			
		}
		if(userpoint==previouspoint)
		{
			cout<<"The word entered is invalid. Game Over";
			cout<<"\nYour points are: "<<userpoint;
			exit(0);
		}
		dfile.seekg(0);
		while(dfile >> fword)
		{
			if(fword[0]==s)
			{
				cout<<"The word is: "<<fword;
				s=fword[fword.length()-1];
				break;
			}
		}
	}
}