
package org.projectvoodoo.ovalshapecolorbug;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class Utils {

    public static PackageInfo getPackageInfos(Context context) {

        PackageInfo packageInfo = null;

        try {
            packageInfo = context.getPackageManager()
                    .getPackageInfo(
                            context.getPackageName(),
                            PackageManager.GET_META_DATA);

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return packageInfo;
    }

    public static String getVersionMetas(Context context) {
        PackageInfo pkgInfo = getPackageInfos(context);

        return "v" + pkgInfo.versionName + " #" + pkgInfo.versionCode;
    }
}
