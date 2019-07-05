#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

void soma(int x, int y) {
	int a;
	a = x + y;
	printf("%d", a);
}

void main() {
	soma(3, 2);
}

