#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

int recebeDado() {
	int num;
	int numDois;
	String texto;
	scanf(" %d", &num);
	scanf(" %d", &numDois);
	scanf(" %254[^\n]s", texto.data);
	printf("%s\r\n", texto.data);
	return num * numDois;
}

void main() {
	int result;
	char logico;
	logico = 1;
	while (logico) {
		result = recebeDado();
		printf("%d\r\n", result);
		if () {
			logico = 0;
		} 
	} 
	printf("%s\r\n", ((String) { "teste sem erro" }).data);
}

