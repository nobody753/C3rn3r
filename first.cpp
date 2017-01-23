#include <iostream>
#include <string.h>
using namespace std;
int main(){
	char s[100],t[100];
	cout<<"Enter String S :";
	cin>>s;
	cout<<endl<<"Enter String t :";
	cin>>t;
	cout<<endl;
	if(strlen(t)-1!=strlen(s) && strlen(t)+1!=strlen(s) && strlen(t)!=strlen(s))
	{
		cout<<"IMPOSSIBLE";
	}
	//Checks to see if the input strings are equal to one another
	if(strlen(s)==strlen(t))
	{
		if(strcmp(s,t)==0)
		{
			cout<<"NOTHING";
		}
	}
	
	//This block of code checks if letters need to be added.
	if(strlen(t)+1==strlen(s))
	{
		int flg=0,test;
		int m=0,n=0;
		for(int i=0;i<strlen(t);i++)
		{
			if(t[m]==s[n])
			{
				m++;n++;	
			}
			if(t[m]!=s[n])
			{
				test=n;
				n++;
				flg++;
			}
			if(flg==2)
			{
				cout<<"IMPOSSIBLE";
			}
		}
		if(flg==1)
		{
			
			cout<<"INSERT "<<s[test];
		}
	}
	//This block of code checks if letters need to be deleted.	
	if(strlen(t)-1==strlen(s))
	{
		
		int m=0,n=0,flg=0,test=0;
		for(int i=0;i<strlen(s);i++)
		{
			if(t[m]==s[n])
			{
				m++;
				n++;
			}
			if(t[m]!=s[n])
			{
				flg++;
				test=m;
				m++;
			}
			
		}
		if(flg>1)
		{
			cout<<"IMPOSSIBLE";
		}
		else
		{
			cout<<"DELETE "<<t[test];
		}
	}
	//This Block of code cheks if letters need to be swapped
	if(strlen(s)==strlen(t))
	{
		if(strcmp(s,t)!=0)
		{
			int m[100]={0},flg=0;
			for(int i=0;i<strlen(s);i++)
			{
				if(s[i]!=t[i])
				{
					m[flg]=i;
					flg++;
				}
				
			}
			if(flg>2)
			cout<<"IMPOSSIBLE";
			else if((t[m[0]]==s[m[1]]) && (t[m[1]]==s[m[0]]))
			cout<<"SWAP "<<t[m[0]]<<" "<<t[m[1]];
			else
			cout<<"IMPOSSIBLE";
		}
	}
}
