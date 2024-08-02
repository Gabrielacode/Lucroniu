package com.allstars.lucroniu.ui.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity

object PermissionHandler {
fun requestPermission(permission: String, fragmentActivity: FragmentActivity, onShowRationalDialog:()->Unit) {
    val permissionLauncherContract =
        fragmentActivity.registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            when (it) {
                true -> {}
                false -> {
                    ActivityCompat.shouldShowRequestPermissionRationale(
                        fragmentActivity,
                        permission
                    ).also {
                        if (it == true) {
                            onShowRationalDialog.invoke()
                        }else{
                            onShowRationalDialog.invoke()
                        }
                    }
                }

            }


        }

    if ( !checkPermission(permission,fragmentActivity)){permissionLauncherContract.launch(permission)}else return

    }
}



fun checkPermission(permission: String, context: Context):Boolean{
     return when(ActivityCompat.checkSelfPermission(context,permission)){
        PackageManager.PERMISSION_GRANTED->true
         PackageManager.PERMISSION_DENIED->false
         else -> false
    }
}

