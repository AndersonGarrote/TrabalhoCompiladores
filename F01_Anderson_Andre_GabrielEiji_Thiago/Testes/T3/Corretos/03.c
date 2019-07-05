#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

int calculaFibo(int n) {
	if (n == 0) {
		return 0;
	} 
	if (n == 1) {
		return 1;
	} 
	return calculaFibo(n - 1) + calculaFibo(n - 2);
}

void main() {
	printf("%s%d", ((String) { "f(5) = " }).data, calculaFibo(5));
}

