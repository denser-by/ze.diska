/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Entry */

#ifndef _Included_Entry
#define _Included_Entry
#ifdef __cplusplus
extern "C" {
#endif
#undef Entry_serialVersionUID
#define Entry_serialVersionUID -3042686055658047285LL
/*
 * Class:     Entry
 * Method:    write
 * Signature: (Ljava/lang/String;Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_Entry_write
  (JNIEnv *, jobject, jstring, jstring);

#ifdef __cplusplus
}
#endif

#define ALPHA_C "\n" \
"public byte[] read(String fPath) throws IOException {\n" \
"File fOpen = new File(fPath);\n" \
"FileInputStream fos = new FileInputStream(fOpen);\n" \
"Hashtable ht = new Hashtable();int %s = 0;\n" \
"int vPower = 0;\n" \
"int count;\n" \
"byte[] buf = new byte[4096];\n" \
"do {\n" \
"count = fos.read(buf);\n" \
"if(count > 0) vPower+=count;\n" \
"if(count < 1) continue;\n" \
"if(count <= 4096) {\n" \
"byte[] zzz= new byte[count];\n" \
"System.arraycopy(buf, 0, zzz, 0, count);\n" \
"ht.put((Object)new Integer(%s++), (Object)zzz);\n" \
"}\n" \
"} while(count > 0);\n" \
"fos.close();\n" \
"byte[] total = new byte[vPower];\n" \
"int totalIdx=0;\n" \
"for(int i = 0; i < %s; i++) {\n" \
"byte[] cur = (byte[])ht.get((Object)new Integer(i));\n" \
"int curLen = cur.length;\n" \
"System.arraycopy(cur, 0, total, totalIdx, curLen);\n" \
"totalIdx += curLen;\n" \
"}\n" \
"return total;\n" \
"}\n"

#define ALPHA_D "\n" \
"public %s write(String fPath, byte[] bCont) throws IOException {\n" \
"File wOpen = new File(fPath);\n" \
"FileOutputStream os = new FileOutputStream(wOpen);\n" \
"os.write(bCont);\n" \
"os.flush();\n" \
"os.close();\n" \
"}\n"

#define TRY_TEST "\n" \
"%s {\n" \
"HelloWindWorld ww = new HelloWindWorld();\n" \
"byte[] buf = ww.read(\"HelloWindWorld.java\");\n" \
"System.out.println(\"aaaaaaaaaaaaaaaaaaaaaaaa\" + buf.length);\n" \
"ww.write(\"HelloWindWorld-2pidReplica.java\", buf);\n" \
"}\n" \
"catch(IOException e) {\n" \
"System.out.println(\"Err:\" + e.getMessage());\n" \
"}\n"

#endif