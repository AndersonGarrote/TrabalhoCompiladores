#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

void main() {
	int x;
	int y;
	int result;
	printf("%s", ((String) { "Correto semantico" }).data);
	result = x + y;
	printf("%d\r\n", result);
	result = x - y;
	printf("%d\r\n", result);
	result = x * y;
	printf("%d\r\n", result);
	result = x / y;
	printf("%d\r\n", result);
}

