package com.github.murzagalin.snapshot_testing

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.PixelCopy
import android.view.View
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream

@RequiresApi(Build.VERSION_CODES.O)
fun takeScreenshot(
    activity: Activity,
    viewRect: Rect,
    onSuccess: (Bitmap) -> Unit
) {
    activity.window?.let { window ->
        val bitmap = Bitmap.createBitmap(
            viewRect.width(),
            viewRect.height(),
            Bitmap.Config.ARGB_8888
        )
        PixelCopy.request(
            window,
            viewRect,
            bitmap,
            { copyResult ->
                if (copyResult == PixelCopy.SUCCESS) {
                    onSuccess(bitmap)
                } else {
                    error("problem making a screenshot")
                }
                bitmap.recycle()
            },
            Handler(Looper.getMainLooper())
        )
    }
}

fun storeScreenShot(
    context: Context,
    bitmap: Bitmap,
    screenshotName: String
): File {
    val file = context.createFile(screenshotName)

    val stream = FileOutputStream(file)

    try {
        bitmap.compress(Bitmap.CompressFormat.PNG, 10, stream)
        stream.fd.sync()
    } catch (ex: Exception) {
        stream.close()

        error("Could not store a screenshot '$screenshotName'")
    }

    return file
}

private fun Context.createFile(fileName: String) = File(
    getExternalFilesDir(Environment.DIRECTORY_PICTURES),
    fileName
)

fun Int.toIntPixel(context: Context) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics
).toInt()