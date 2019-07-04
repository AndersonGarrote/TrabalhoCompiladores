#include <stdio.h>
#include <string.h>

int calculaFibo(int n) {
	if ( n == 0 ) {
		return 0;
	} 
	if ( n == 1 ) {
		return 1;
	} 
	return calculaFibo(n - 1) + calculaFibo(n - 2);
}

void main() {
	printf("%s%d", "f(5) = ", calculaFibo(5));
}

