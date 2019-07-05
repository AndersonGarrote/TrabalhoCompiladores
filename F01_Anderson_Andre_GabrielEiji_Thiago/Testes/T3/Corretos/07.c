#include <stdio.h>
#include <string.h>
typedef struct { char data[255]; } String; 

int recebeInt() {
	int x;
	scanf("%d", &x);
	return x;
}

String recebeString() {
	String x;
	scanf("%s", x.data);
	return x;
}

void main() {
	int x;
	String y;
	x = recebeInt();
	y = recebeString();
	printf("%s%d\r\n", ((String) { "x = " }).data, x);
	printf("%s%s\r\n", ((String) { "y = " }).data, y.data);
	printf("%s%s\r\n", ((String) { "How about that! " }).data, recebeString().data);
}

