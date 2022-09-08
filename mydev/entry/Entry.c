#include "Entry.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define _TR_ 1
#define PRIN(x,y) fprintf(pVile,(x),(y))
#define PRIN3(x,y,z,a) fprintf(pVile,(x),(y),(z),(a))

JNIEXPORT jint JNICALL Java_Entry_write
  (JNIEnv * pEnv, jobject jObj, jstring fName, jstring jIdea)
{
#ifdef _TR_
printf("JNI_start\n");
#endif

jboolean iz = 0;
const char * psName = (*pEnv)->GetStringUTFChars(pEnv, fName, &iz);
const char * psIdea = (*pEnv)->GetStringUTFChars(pEnv, jIdea, &iz);

int ll = strlen(psName);
char * buf = (char *)malloc(sizeof(char)*(ll+1));
memcpy(buf, psName, ll*sizeof(char));
int i,pp=0;
for(i = 0; i < ll; i++)
{
if(buf[i] == '.')
{
pp=i;break;
}
}
if(pp==0) pp=ll;

buf[pp] = '\0';

#ifdef _TR_
printf("___%s___%s___\n", psName, psIdea);
#endif

FILE * pVile;

if((pVile = fopen(psName, "w")) == NULL)
{
printf("JNI_file_error\n");
}
else
{
PRIN("import %s;\n","java.io.File");
PRIN("import %s;\n","java.io.FileInputStream");
PRIN("import %s;\n","java.io.FileOutputStream");
PRIN("import %s;\n","java.io.IOException");
PRIN("import %s;\n\n","java.util.Hashtable");
PRIN("public class %s {\n\n", buf);
PRIN("public static void main(String[] %s) {\n","args");
PRIN("System.out.println(%s);\n","\"Go hell\"");
PRIN(TRY_TEST, "try");
PRIN("}%s", "\n");
PRIN3(ALPHA_C, "htKey","htKey", "htKey");
PRIN(ALPHA_D, "void");
PRIN("}%s", "\n");

fclose(pVile);
}

free(buf);
(*pEnv)->ReleaseStringUTFChars(pEnv, fName, psName);
(*pEnv)->ReleaseStringUTFChars(pEnv, jIdea, psIdea);

#ifdef _TR_
printf("JNI_stop\n");
#endif

return 1;//OK
//return 2;//FAIL
}
