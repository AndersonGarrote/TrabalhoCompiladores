#include <stdio.h>
#include <string.h>

char * string() {
	return "ola";
}

void main() {
	char x[255];
	strcpy(x, "ola");
	strcpy(x, string());
}

