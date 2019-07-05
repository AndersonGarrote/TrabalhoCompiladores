#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

void main() {
	String s;
	printf("%s", s.data);
}

