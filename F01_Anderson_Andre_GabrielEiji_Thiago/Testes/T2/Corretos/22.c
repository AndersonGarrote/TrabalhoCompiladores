#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

int fatorial(int n) {
	if (n <= 0) {
		return 1;
	} else {
		return n * fatorial(n - 1);
	}
}

void imprima(int before, int valor, String after) {
	int i;
	i = 0;
	while (i < before) {
		printf("%s", ((String) { "*" }).data);
		i = i + 1;
	} 
	printf("%s\r\n", ((String) { "" }).data);
	printf("%d\r\n", valor);
	printf("%s\r\n", after.data);
}

void main() {
	imprima(50, fatorial(5) * 2 * fatorial(3), ((String) { "        fim" }));
	printf("%s\r\n", ((String) { "teste sem erro" }).data);
}

