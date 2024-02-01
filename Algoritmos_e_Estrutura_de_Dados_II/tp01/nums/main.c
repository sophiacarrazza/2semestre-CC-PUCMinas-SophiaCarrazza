#include <stdio.h>
#include <stdlib.h>

int main()
{
    char cutoff [6];
    scanf(" %[^\n.]", &cutoff);

    printf ("%s", cutoff);

}
