#include <stdio.h>
#include <string.h>

void main() {
	printf("%s%s%s%s", "Testando", "com", "varios", "parametros");
	printf("%s%s%s%s\r\n", "Testando", "com", "varios", "parametros");
	printf("%s%s%d%s", "Testando", "com", 4, "parametros");
	printf("%s%s%d%s\r\n", "Testando", "com", 4, "parametros");
	printf("%s", "Um unico parametro String");
	printf("%s\r\n", "Um unico parametro String");
	printf("%d", 1);
	printf("%d\r\n", 1);
}

