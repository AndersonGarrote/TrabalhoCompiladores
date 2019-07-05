#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

int constante() {
	int valorA;
	int valorB;
	valorA = 1 * +2 + +5 * -4;
	valorB = 10 / 2 - 20 / -5;
	if (0 || valorB != 10) {
		return valorA;
	} 
	return valorB;
}

int multiplicar(int multA, int multB, char tipo) {
	int total;
	if (tipo == 1) {
		int i;
		i = 1;
		total = 0;
		while (i <= multB) {
			total = total + multA;
			i = i + 1;
		} 
		return total;
	} 
	if (tipo == 0) {
		total = multA * multB;
		return total;
	} 
}

char maiorIgual(int valorAa, int valorBb) {
	if (valorAa >= valorBb) {
		return 1;
	} else {
		return 0;
	}
}

String mensagem(String msg[]) {
	printf("%s\r\n", msg.data);
	return ((String) { "write" });
}

void calc(char oper) {
	int valor;
	int x;
	x = -constante() + 100;
	valor = 1 + constante();
	if (oper == 1 || x > 80 && x < 0 && valor != 2 || valor > 50) {
		valor = valor + 50;
	} 
}

void mensagens(String msgA[], String msgB[]) {
	String aux;
	aux = ((String) { "Ola, sua mensagem:" });
	printf("%s", aux.data);
	printf("%s\r\n", msgA.data);
	printf("%s", aux.data);
	printf("%s\r\n", msgB.data);
}

char portaAnd(char eA, char eB) {
	eA;
	eB;
	char resultado;
	if (eA == 1 && eB == 1) {
		resultado = 1;
	} else {
		resultado = 0;
	}
	return resultado;
}

void comparador(String msgF[], char tipoComp) {
	String com;
	com = ((String) { "Permitido" });
	if (tipoComp) {
		com = ((String) { "Proibido" });
	} 
	if (msgF == com) {
		printf("%s", ((String) { "Acertou" }).data);
	} 
}

void loopInfinito() {
	int expr;
	while (1) {
		
	} 
}

void main() {
	int resultadoM;
	String texto;
	resultadoM = multiplicar(constante(), 10, 1);
	calc(maiorIgual(resultadoM, 10));
	texto = mensagem(((String) { "ola" }));
	mensagens(((String) { "Cuidado" }), ((String) { "Pode ir" }));
	comparador(((String) { "Permitido" }), portaAnd(1, 1));
	loopInfinito();
}

