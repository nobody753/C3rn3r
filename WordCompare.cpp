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
		cout<<"impossible";
	}
	//Checks to see if the input strings are equal to one another
	if(strlen(s)==strlen(t))
	{
		if(strcmp(s,t)==0)
		{
			cout<<"NOTHING strings are equal";
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
				cout<<"cant be done";
			}
		}
		if(flg==1)
		{
			cout<<s[test];
			cout<<" should be added at "<<test+1;
		}
	}
	//This block of code checks if letters need to be deleted.	
	if(strlen(t)-1==strlen(s))
	{
		
		int m=0,n=0,flg=0,test=0;
		for(int i=0;i<strlen(s);i++)
		{
			if(t[m]==s[n])	//
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
			cout<<"Not possible";
		}
		else
		{
			cout<<t[test]<<" should be deleted at "<<test+1;
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
			cout<<"Not Possible";
			else if((t[m[0]]==s[m[1]]) && (t[m[1]]==s[m[0]]))
			cout<<t[m[0]]<<" needs to be swapped with "<<t[m[1]];
			else
			cout<<"Not Possible";
		}
	}
}
