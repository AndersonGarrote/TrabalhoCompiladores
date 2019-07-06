#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

void imprime(int q, int w, int e, String nomeFunc) {
	printf("%s\r\n", ((String) { "**************" }).data);
	printf("%d\r\n", q);
	printf("%d\r\n", w);
	printf("%d\r\n", e);
	printf("%s\r\n", nomeFunc.data);
	printf("%s\r\n", ((String) { "***Fim***" }).data);
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
	while (i <= k) {
		alternaLogico = 1;
		while (j >= l) {
			if () {
				b = b / 2;
			} else {
				if (b < 2 ||  && b > 5) {
					b = b - 1;
				} 
			}
			alternaLogico = 0;
			j = j - 2;
		} 
		i = i + 1;
	} 
	imprime(i, j, b, ((String) { " aninhamento" }));
}

void main() {
	int h;
	int g;
	aninhamento(h, g);
	String n;
	n = ((String) { "variavel de texto da main" });
	imprime(90, 30, 30, ((String) { "chamada de funcao da main" }));
	int i;
	i = 0;
	while () {
		imprime(i, i + 1, i + 2, ((String) { "i, i+1, i+2" }));
		i = i + 1;
	} 
}

