#include <stdio.h>
#include <string.h>

char * palavra() {
	return "arou";
}

int constante() {
	return 2;
}

void main() {
	printf("%s\r\n", "ola");
	printf("%s", "ola");
	printf("%s%d\r\n", "ola ", 2);
	printf("%s%d", "ola ", 2);
	int x;
	x = 3;
	printf("%d%s%d\r\n", x, " ", constante());
	printf("%d%s%d", x, " ", constante());
	char y[255];
	strcpy(y, "aaaaarou");
	printf("%s%s%s\r\n", palavra(), " ", y);
	printf("%s%s%s", palavra(), " ", y);
}

