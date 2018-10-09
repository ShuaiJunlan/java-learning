#include <jni.h>
#include "cn_shuaijunlan_java_basic_learning_jni_JNITest.h"
JNIEXPORT jstring JNICALL Java_cn_shuaijunlan_java_basic_learning_jni_JNITest_testJNI(JNIEnv *env, jclass cls)
{
	return env->NewStringUTF("hello_JNI");
}