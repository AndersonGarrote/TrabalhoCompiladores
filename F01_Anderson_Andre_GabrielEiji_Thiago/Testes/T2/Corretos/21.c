#include <stdio.h>
#include <string.h>

void main() {
	int x;
	int y;
	int result;
	printf("%s", "Correto semantico");
	result = x + y;
	printf("%d\r\n", result);
	result = x - y;
	printf("%d\r\n", result);
	result = x * y;
	printf("%d\r\n", result);
	result = x / y;
	printf("%d\r\n", result);
}

