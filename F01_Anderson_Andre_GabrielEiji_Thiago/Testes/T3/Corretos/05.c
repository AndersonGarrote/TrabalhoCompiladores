#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

String palavra() {
	return ((String) { "arou" });
}

int constante() {
	return 2;
}

void main() {
	printf("%s\r\n", ((String) { "ola" }).data);
	printf("%s", ((String) { "ola" }).data);
	printf("%s%d\r\n", ((String) { "ola " }).data, 2);
	printf("%s%d", ((String) { "ola " }).data, 2);
	int x;
	x = 3;
	printf("%d%s%d\r\n", x, ((String) { " " }).data, constante());
	printf("%d%s%d", x, ((String) { " " }).data, constante());
	String y;
	y = ((String) { "aaaaarou" });
	printf("%s%s%s\r\n", palavra().data, ((String) { " " }).data, y.data);
	printf("%s%s%s", palavra().data, ((String) { " " }).data, y.data);
}

