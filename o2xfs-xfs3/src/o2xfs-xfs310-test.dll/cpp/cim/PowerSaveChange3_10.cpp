#include "cim/at_o2xfs_xfs_cim_v3_10_PowerSaveChange3_10Test.h"

#include <Windows.h>
#include <XFSCIM.H>
#include "at.o2xfs.win32.h"

WFSCIMPOWERSAVECHANGE PowerSaveChange;

JNIEXPORT jobject JNICALL Java_at_o2xfs_xfs_cim_v3_110_PowerSaveChange3_110Test_buildPowerSaveChange3_110(JNIEnv *env, jobject obj) {
	PowerSaveChange.usPowerSaveRecoveryTime = 30;
	return NewBuffer(env, &PowerSaveChange, sizeof(WFSCIMPOWERSAVECHANGE));
}