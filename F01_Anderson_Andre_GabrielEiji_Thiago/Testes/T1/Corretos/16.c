#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

void imprime(int q, int w, int e, String nomeFunc[]) {
	printf("%s\r\n", ((String) { "**************" }).data);
	printf("%d\r\n", q);
	printf("%d\r\n", w);
	printf("%d\r\n", e);
	printf("%s\r\n", nomeFunc.data);
	printf("%s\r\n", ((String) { "***Fim***" }).data);
}

int aninhamento(int k, int l) {
	int i;
	int j;
	char alternaLogico;
	char a;
	int b;
	int final;
	final = 0;
	b = 4000;
	i = 0;
	j = k * l;
	a = 0;
	while (i <= k) {
		alternaLogico = 1;
		while (j >= l) {
			if (a == alternaLogico) {
				b = b / 2;
				final = final + 1;
			} else {
				if (b < 2 || b != 4 && b > 5) {
					b = b - 1;
				} 
			}
			alternaLogico = 0;
			j = j - 2;
			final = final - 1;
		} 
		i = i + 1;
	} 
	imprime(i, j, b, ((String) { " aninhamento" }));
	return final;
}

void main() {
	int h;
	int g;
	int res;
	res = aninhamento(h, g);
	String n;
	n = ((String) { "variavel de texto da main" });
	imprime(90, 30, 30, ((String) { "chamada de funcao da main" }));
	int i;
	i = 0;
	int resDois;
	while (i != 3) {
		imprime(i, i + 1, i + 2, ((String) { "i, i+1, i+2" }));
		i = i + 1;
		if (i == 2) {
			resDois = aninhamento(i, i + 1);
		} 
	} 
}

