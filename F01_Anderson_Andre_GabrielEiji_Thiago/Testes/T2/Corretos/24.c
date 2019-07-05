#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

int fatorial(int n) {
	if (n <= 0) {
		return 1;
	} else {
		return n * fatorial(n - 1);
	}
}

void main() {
	int fat;
	fat = fatorial(5) * 2 * fatorial(3);
	printf("%s\r\n", ((String) { "teste sem erro" }).data);
}

