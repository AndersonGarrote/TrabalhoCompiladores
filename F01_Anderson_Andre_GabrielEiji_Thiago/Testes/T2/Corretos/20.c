#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

void main() {
	int num;
	String texto;
	scanf(" %d", &num);
	scanf(" %254[^\n]s", texto.data);
}

