#include <stdio.h>
#include <unistd.h>
char p[100];
int main(){
    setbuf(stdout, p);
    char ptr[100];
    char a[10]={'\0'};
    read(0, a, 3);
    printf("%s\n", a);
    
}
