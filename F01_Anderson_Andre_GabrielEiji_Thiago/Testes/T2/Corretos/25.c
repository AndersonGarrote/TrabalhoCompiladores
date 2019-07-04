#include <stdio.h>
#include <string.h>

int recebeDado() {
	int num;
	int numDois;
	char texto[255];
	num = readInt();
	numDois = readInt();
	strcpy(texto, readString());
	printf("%s\r\n", texto);
	return num * numDois;
}

void main() {
	int result;
	char logico;
	logico = 1;
	while ( logico ) {
		result = recebeDado();
		printf("%d\r\n", result);
		if ( result == 0 ) {
			logico = 0;
		} 
	} 
	printf("%s\r\n", "teste sem erro");
}

