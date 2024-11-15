package com.example.myweatherappchallenge.ui.composables

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun LocationPermissionComponent(
    context: Context,
    permission: String,
    permissionRationale: String,
   // scaffoldState: ScaffoldState,
   // permissionAction: (PermissionAction) -> Unit
) {


    /*val permissionGranted =
        Common.checkIfPermissionGranted(
            context,
            permission
        )

    if (permissionGranted) {
        Log.d(TAG, "Permission already granted, exiting..")
        permissionAction(PermissionAction.OnPermissionGranted)
        return
    }


    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Log.d(TAG, "Permission provided by user")
            // Permission Accepted
            permissionAction(PermissionAction.OnPermissionGranted)
        } else {
            Log.d(TAG, "Permission denied by user")
            // Permission Denied
            permissionAction(PermissionAction.OnPermissionDenied)
        }
    }


    val showPermissionRationale = Common.shouldShowPermissionRationale(
        context,
        permission
    )


    if (showPermissionRationale) {
        Log.d(TAG, "Showing permission rationale for $permission")

        LaunchedEffect(showPermissionRationale) {

            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = permissionRationale,
                actionLabel = "Grant Access",
                duration = SnackbarDuration.Long

            )
            when (snackbarResult) {
                SnackbarResult.Dismissed -> {
                    Log.d(TAG, "User dismissed permission rationale for $permission")
                    //User denied the permission, do nothing
                    permissionAction(PermissionAction.OnPermissionDenied)
                }
                SnackbarResult.ActionPerformed -> {
                    Log.d(TAG, "User granted permission for $permission rationale. Launching permission request..")
                    launcher.launch(permission)
                }
            }
        }
    } else {
        //Request permissions again
        Log.d(TAG, "Requesting permission for $permission again")
        SideEffect {
            launcher.launch(permission)
        }

    }

*/
}
