package com.liuxing.activitystalk;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Author：流星
 * DateTime：2025/3/28 9:07
 * Description：检测前台界面服务
 */
public class MyService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            String packageName = event.getPackageName() != null ? event.getPackageName().toString() : "";
            String className = event.getClassName() != null ? event.getClassName().toString() : "";
            MainActivity.activityListAdapter.updateData(packageName, className);
            Log.d("Accessibility", "当前前台应用包名: " + packageName + ", 界面: " + className);
        }
    }

    @Override
    public void onInterrupt() {

    }
}