#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

void main() {
	if (1 < 2) {
		printf("%s", ((String) { "oi" }).data);
	} else {
		printf("%s", ((String) { "oiii" }).data);
	}
	while (1) {
		printf("%s", ((String) { "vish" }).data);
	} 
}

