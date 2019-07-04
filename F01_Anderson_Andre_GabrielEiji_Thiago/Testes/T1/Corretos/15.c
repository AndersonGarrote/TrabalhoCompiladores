#include <stdio.h>
#include <string.h>

void imprime(int q, int w, int e, char nomeFunc[]) {
	printf("%s\r\n", "**************");
	printf("%d\r\n", q);
	printf("%d\r\n", w);
	printf("%d\r\n", e);
	printf("%s\r\n", nomeFunc);
	printf("%s\r\n", "***Fim***");
}

void aninhamento(int k, int l) {
	int i;
	int j;
	char alternaLogico;
	char a;
	int b;
	b = 4000;
	i = 0;
	j = k * l;
	a = 0;
	while ( i <= k ) {
		alternaLogico = 1;
		while ( j >= l ) {
			if ( a == alternaLogico ) {
				b = b / 2;
			} else {
				if ( b < 2 || b != 4 && b > 5 ) {
					b = b - 1;
				} 
			}
			alternaLogico = 0;
			j = j - 2;
		} 
		i = i + 1;
	} 
	imprime(i, j, b, " aninhamento");
}

void main() {
	int h;
	int g;
	aninhamento(h, g);
	char n[255];
	strcpy(n, "variavel de texto da main");
	imprime(90, 30, 30, "chamada de funcao da main");
	int i;
	i = 0;
	while ( i != 3 ) {
		imprime(i, i + 1, i + 2, "i, i+1, i+2");
		i = i + 1;
	} 
}

