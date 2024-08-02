package com.allstars.lucroniu.ui.fragments

import android.content.Intent
import androidx.fragment.app.DialogFragment

open class PermissionDialogFragment(var permissionIntent :Intent? = null ) : DialogFragment() {

}
sealed class PermissionResultForRationaleDialog(){
     class ContinueWithoutPermission()
     class ProceedtoPermisiion(intent: Intent)
}