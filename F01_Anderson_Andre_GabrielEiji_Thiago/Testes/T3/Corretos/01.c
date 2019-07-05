#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

void main() {
	printf("%s%s%s%s", ((String) { "Testando" }).data, ((String) { "com" }).data, ((String) { "varios" }).data, ((String) { "parametros" }).data);
	printf("%s%s%s%s\r\n", ((String) { "Testando" }).data, ((String) { "com" }).data, ((String) { "varios" }).data, ((String) { "parametros" }).data);
	printf("%s%s%d%s", ((String) { "Testando" }).data, ((String) { "com" }).data, 4, ((String) { "parametros" }).data);
	printf("%s%s%d%s\r\n", ((String) { "Testando" }).data, ((String) { "com" }).data, 4, ((String) { "parametros" }).data);
	printf("%s", ((String) { "Um unico parametro String" }).data);
	printf("%s\r\n", ((String) { "Um unico parametro String" }).data);
	printf("%d", 1);
	printf("%d\r\n", 1);
}

