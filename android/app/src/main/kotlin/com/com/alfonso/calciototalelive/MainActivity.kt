package com.alfonso.calciototalelive

import android.content.Intent
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import android.os.Bundle
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)
        java.lang.Thread.sleep(1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.getIntExtra("org.chromium.chrome.extra.TASK_ID", -1) == this.taskId) {
            this.finish()
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        super.onCreate(savedInstanceState)

        val binaryMessenger = flutterEngine?.dartExecutor?.binaryMessenger
        if (binaryMessenger != null) {
            val channel = MethodChannel(binaryMessenger, "com.alfonso.calciototalelive/method_channel")
            channel.invokeMethod("set_intent", intent.action.toString())
        }
    }

    override fun onNewIntent(newIntent: Intent) {
        val binaryMessenger = flutterEngine?.dartExecutor?.binaryMessenger
        if (binaryMessenger != null) {
            val channel = MethodChannel(binaryMessenger, "com.alfonso.calciototalelive/method_channel")
            channel.invokeMethod("set_intent", newIntent.action.toString())
        }
        super.onNewIntent(newIntent)
    }
}
