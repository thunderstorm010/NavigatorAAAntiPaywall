package thunderstorm010.xposed.yandexnavi.aa.antipaywall;

import android.annotation.SuppressLint;
import android.app.Application;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class OnLoadPackage implements IXposedHookLoadPackage {

    private static final String packageName = "ru.yandex.yandexnavi";
    private static final String className = packageName + ".projected.paywall.PayWallDelegateImpl";

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
        if (!lpparam.packageName.equals(packageName)) return;
        XposedHelpers.findAndHookMethod(
                className,
                lpparam.classLoader,
                "getHasPlus",
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Exception {
                        XposedBridge.log("[NavigatorAAAntiPaywall] Hooking " + className + "$getHasPlus...");
                        return true;
                    }
                }
        );
    }
}
