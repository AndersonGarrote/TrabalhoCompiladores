#include <stdio.h>
#include <string.h>
typedef struct {
	char data[255];
} String; 

String string() {
	return ((String) { "ola" });
}

void main() {
	String x;
	x = ((String) { "ola" });
	x = string();
}

