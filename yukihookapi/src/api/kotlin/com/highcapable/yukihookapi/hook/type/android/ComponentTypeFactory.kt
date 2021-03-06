/*
 * YukiHookAPI - An efficient Kotlin version of the Xposed Hook API.
 * Copyright (C) 2019-2022 HighCapable
 * https://github.com/fankes/YukiHookAPI
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is Created by fankes on 2022/2/2.
 */
@file:Suppress("unused", "KDocUnresolvedReference", "DEPRECATION", "NewApi")

package com.highcapable.yukihookapi.hook.type.android

import android.app.*
import android.appwidget.AppWidgetHost
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.appwidget.AppWidgetProviderInfo
import android.content.*
import android.content.Intent.ShortcutIconResource
import android.content.pm.*
import android.content.pm.LauncherApps.ShortcutQuery
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.TypedArray
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.*
import android.icu.text.SimpleDateFormat
import android.media.MediaPlayer
import android.os.*
import android.provider.Settings
import android.service.notification.StatusBarNotification
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.util.*
import android.view.*
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import com.highcapable.yukihookapi.hook.factory.classOf
import org.w3c.dom.UserDataHandler
import java.awt.Component

/**
 * ?????? [android.R] ??????
 * @return [Class]
 */
val AndroidRClass get() = android.R::class.java

/**
 * ?????? [Context] ??????
 * @return [Class]
 */
val ContextClass get() = Context::class.java

/**
 * ?????? [ContextImpl] ??????
 * @return [Class]
 */
val ContextImplClass get() = classOf(name = "android.app.ContextImpl")

/**
 * ?????? [ContextWrapper] ??????
 * @return [Class]
 */
val ContextWrapperClass get() = ContextWrapper::class.java

/**
 * ?????? [Application] ??????
 * @return [Class]
 */
val ApplicationClass get() = Application::class.java

/**
 * ?????? [ApplicationInfo] ??????
 * @return [Class]
 */
val ApplicationInfoClass get() = ApplicationInfo::class.java

/**
 * ?????? [Instrumentation] ??????
 * @return [Class]
 */
val InstrumentationClass get() = Instrumentation::class.java

/**
 * ?????? [PackageInfo] ??????
 * @return [Class]
 */
val PackageInfoClass get() = PackageInfo::class.java

/**
 * ?????? [ApplicationPackageManager] ??????
 * @return [Class]
 */
val ApplicationPackageManagerClass get() = classOf(name = "android.app.ApplicationPackageManager")

/**
 * ?????? [ActivityThread] ??????
 * @return [Class]
 */
val ActivityThreadClass get() = classOf(name = "android.app.ActivityThread")

/**
 * ?????? [IPackageManager] ??????
 * @return [Class]
 */
val IPackageManagerClass get() = classOf(name = "android.content.pm.IPackageManager")

/**
 * ?????? [LoadedApk] ??????
 * @return [Class]
 */
val LoadedApkClass get() = classOf(name = "android.app.LoadedApk")

/**
 * ?????? [Activity] ??????
 * @return [Class]
 */
val ActivityClass get() = Activity::class.java

/**
 * ?????? [Looper] ??????
 * @return [Class]
 */
val LooperClass get() = Looper::class.java

/**
 * ?????? [Fragment] ?????? - Support
 * @return [Class]
 */
val FragmentClass_AndroidSupport get() = classOf(name = "android.support.v4.app.Fragment")

/**
 * ?????? [Fragment] ?????? - AndroidX
 * @return [Class]
 */
val FragmentClass_AndroidX get() = classOf(name = "androidx.fragment.app.Fragment")

/**
 * ?????? [FragmentActivity] ?????? - Support
 * @return [Class]
 */
val FragmentActivityClass_AndroidSupport get() = classOf(name = "android.support.v4.app.FragmentActivity")

/**
 * ?????? [FragmentActivity] ?????? - AndroidX
 * @return [Class]
 */
val FragmentActivityClass_AndroidX get() = classOf(name = "androidx.fragment.app.FragmentActivity")

/**
 * ?????? [DocumentFile] ?????? - AndroidX
 * @return [Class]
 */
val DocumentFileClass get() = classOf(name = "androidx.documentfile.provider.DocumentFile")

/**
 * ?????? [Service] ??????
 * @return [Class]
 */
val ServiceClass get() = Service::class.java

/**
 * ?????? [Binder] ??????
 * @return [Class]
 */
val BinderClass get() = Binder::class.java

/**
 * ?????? [IBinder] ??????
 * @return [Class]
 */
val IBinderClass get() = IBinder::class.java

/**
 * ?????? [BroadcastReceiver] ??????
 * @return [Class]
 */
val BroadcastReceiverClass get() = BroadcastReceiver::class.java

/**
 * ?????? [Bundle] ??????
 * @return [Class]
 */
val BundleClass get() = Bundle::class.java

/**
 * ?????? [BaseBundle] ??????
 * @return [Class]
 */
val BaseBundleClass get() = BaseBundle::class.java

/**
 * ?????? [Resources] ??????
 * @return [Class]
 */
val ResourcesClass get() = Resources::class.java

/**
 * ?????? [Configuration] ??????
 * @return [Class]
 */
val ConfigurationClass get() = Configuration::class.java

/**
 * ?????? [ConfigurationInfo] ??????
 * @return [Class]
 */
val ConfigurationInfoClass get() = ConfigurationInfo::class.java

/**
 * ?????? [ContentResolver] ??????
 * @return [Class]
 */
val ContentResolverClass get() = ContentResolver::class.java

/**
 * ?????? [ContentProvider] ??????
 * @return [Class]
 */
val ContentProviderClass get() = ContentProvider::class.java

/**
 * ?????? [Settings] ??????
 * @return [Class]
 */
val SettingsClass get() = Settings::class.java

/**
 * ?????? [Settings.System] ??????
 * @return [Class]
 */
val Settings_SystemClass get() = Settings.System::class.java

/**
 * ?????? [Settings.Secure] ??????
 * @return [Class]
 */
val Settings_SecureClass get() = Settings.Secure::class.java

/**
 * ?????? [TypedArray] ??????
 * @return [Class]
 */
val TypedArrayClass get() = TypedArray::class.java

/**
 * ?????? [TypedValue] ??????
 * @return [Class]
 */
val TypedValueClass get() = TypedValue::class.java

/**
 * ?????? [SparseArray] ??????
 * @return [Class]
 */
val SparseArrayClass get() = SparseArray::class.java

/**
 * ?????? [SparseIntArray] ??????
 * @return [Class]
 */
val SparseIntArrayClass get() = SparseIntArray::class.java

/**
 * ?????? [SparseBooleanArray] ??????
 * @return [Class]
 */
val SparseBooleanArrayClass get() = SparseBooleanArray::class.java

/**
 * ?????? [SparseLongArray] ??????
 * @return [Class]
 */
val SparseLongArrayClass get() = SparseLongArray::class.java

/**
 * ?????? [LongSparseArray] ??????
 * @return [Class]
 */
val LongSparseArrayClass get() = LongSparseArray::class.java

/**
 * ?????? [ArrayMap] ??????
 * @return [Class]
 */
val ArrayMapClass get() = ArrayMap::class.java

/**
 * ?????? [ArraySet] ??????
 * @return [Class]
 *
 * - ?????? Android M (23) ?????????????????????
 */
val ArraySetClass get() = ArraySet::class.java

/**
 * ?????? [Handler] ??????
 * @return [Class]
 */
val HandlerClass get() = Handler::class.java

/**
 * ?????? [Handler.Callback] ??????
 * @return [Class]
 */
val Handler_CallbackClass get() = Handler.Callback::class.java

/**
 * ?????? [Message] ??????
 * @return [Class]
 */
val MessageClass get() = Message::class.java

/**
 * ?????? [MessageQueue] ??????
 * @return [Class]
 */
val MessageQueueClass get() = MessageQueue::class.java

/**
 * ?????? [Messenger] ??????
 * @return [Class]
 */
val MessengerClass get() = Messenger::class.java

/**
 * ?????? [AsyncTask] ??????
 * @return [Class]
 */
val AsyncTaskClass get() = AsyncTask::class.java

/**
 * ?????? [SimpleDateFormat] ??????
 *
 * - ?????? Android N (24) ?????????????????????
 * @return [Class]
 */
val SimpleDateFormatClass_Android get() = SimpleDateFormat::class.java

/**
 * ?????? [Base64] ??????
 * @return [Class]
 */
val Base64Class_Android get() = Base64::class.java

/**
 * ?????? [TextUtils] ??????
 * @return [Class]
 */
val TextUtilsClass get() = TextUtils::class.java

/**
 * ?????? [Window] ??????
 * @return [Class]
 */
val WindowClass get() = Window::class.java

/**
 * ?????? [WindowMetrics] ??????
 *
 * - ?????? Android R (30) ?????????????????????
 * @return [Class]
 */
val WindowMetricsClass get() = WindowMetrics::class.java

/**
 * ?????? [WindowInsets] ??????
 * @return [Class]
 */
val WindowInsetsClass get() = WindowInsets::class.java

/**
 * ?????? [WindowInsets.Type] ??????
 *
 * - ?????? Android R (30) ?????????????????????
 * @return [Class]
 */
val WindowInsets_TypeClass get() = WindowInsets.Type::class.java

/**
 * ?????? [WindowManager] ??????
 * @return [Class]
 */
val WindowManagerClass get() = WindowManager::class.java

/**
 * ?????? [WindowManager.LayoutParams] ??????
 * @return [Class]
 */
val WindowManager_LayoutParamsClass get() = WindowManager.LayoutParams::class.java

/**
 * ?????? [ViewManager] ??????
 * @return [Class]
 */
val ViewManagerClass get() = ViewManager::class.java

/**
 * ?????? [Parcel] ??????
 * @return [Class]
 */
val ParcelClass get() = Parcel::class.java

/**
 * ?????? [Parcelable] ??????
 * @return [Class]
 */
val ParcelableClass get() = Parcelable::class.java

/**
 * ?????? [Parcelable.Creator] ??????
 * @return [Class]
 */
val Parcelable_CreatorClass get() = Parcelable.Creator::class.java

/**
 * ?????? [Dialog] ??????
 * @return [Class]
 */
val DialogClass get() = Dialog::class.java

/**
 * ?????? [AlertDialog] ??????
 * @return [Class]
 */
val AlertDialogClass get() = AlertDialog::class.java

/**
 * ?????? [DisplayMetrics] ??????
 * @return [Class]
 */
val DisplayMetricsClass get() = DisplayMetrics::class.java

/**
 * ?????? [Display] ??????
 * @return [Class]
 */
val DisplayClass get() = Display::class.java

/**
 * ?????? [Toast] ??????
 * @return [Class]
 */
val ToastClass get() = Toast::class.java

/**
 * ?????? [Intent] ??????
 * @return [Class]
 */
val IntentClass get() = Intent::class.java

/**
 * ?????? [Component] ??????
 * @return [Class]
 */
val ComponentClass get() = Component::class.java

/**
 * ?????? [ComponentInfo] ??????
 * @return [Class]
 */
val ComponentInfoClass get() = ComponentInfo::class.java

/**
 * ?????? [ComponentName] ??????
 * @return [Class]
 */
val ComponentNameClass get() = ComponentName::class.java

/**
 * ?????? [PendingIntent] ??????
 * @return [Class]
 */
val PendingIntentClass get() = PendingIntent::class.java

/**
 * ?????? [ColorStateList] ??????
 * @return [Class]
 */
val ColorStateListClass get() = ColorStateList::class.java

/**
 * ?????? [ContentValues] ??????
 * @return [Class]
 */
val ContentValuesClass get() = ContentValues::class.java

/**
 * ?????? [SharedPreferences] ??????
 * @return [Class]
 */
val SharedPreferencesClass get() = SharedPreferences::class.java

/**
 * ?????? [SpannableStringBuilder] ??????
 * @return [Class]
 */
val SpannableStringBuilderClass get() = SpannableStringBuilder::class.java

/**
 * ?????? [MediaPlayer] ??????
 * @return [Class]
 */
val MediaPlayerClass get() = MediaPlayer::class.java

/**
 * ?????? [ProgressDialog] ??????
 * @return [Class]
 */
val ProgressDialogClass get() = ProgressDialog::class.java

/**
 * ?????? [Log] ??????
 * @return [Class]
 */
val LogClass get() = Log::class.java

/**
 * ?????? [Build] ??????
 * @return [Class]
 */
val BuildClass get() = Build::class.java

/**
 * ?????? [Xml] ??????
 * @return [Class]
 */
val XmlClass get() = Xml::class.java

/**
 * ?????? [ContrastColorUtil] ??????
 * @return [Class]
 */
val ContrastColorUtilClass get() = classOf(name = "com.android.internal.util.ContrastColorUtil")

/**
 * ?????? [StatusBarNotification] ??????
 * @return [Class]
 */
val StatusBarNotificationClass get() = StatusBarNotification::class.java

/**
 * ?????? [Notification] ??????
 * @return [Class]
 */
val NotificationClass get() = Notification::class.java

/**
 * ?????? [Notification.Builder] ??????
 * @return [Class]
 */
val Notification_BuilderClass get() = Notification.Builder::class.java

/**
 * ?????? [Notification.Action] ??????
 * @return [Class]
 */
val Notification_ActionClass get() = Notification.Action::class.java

/**
 * ?????? [DialogInterface] ??????
 * @return [Class]
 */
val DialogInterfaceClass get() = DialogInterface::class.java

/**
 * ?????? [DialogInterface.OnClickListener] ??????
 * @return [Class]
 */
val DialogInterface_OnClickListenerClass get() = DialogInterface.OnClickListener::class.java

/**
 * ?????? [DialogInterface.OnCancelListener] ??????
 * @return [Class]
 */
val DialogInterface_OnCancelListenerClass get() = DialogInterface.OnCancelListener::class.java

/**
 * ?????? [DialogInterface.OnDismissListener] ??????
 * @return [Class]
 */
val DialogInterface_OnDismissListenerClass get() = DialogInterface.OnDismissListener::class.java

/**
 * ?????? [Environment] ??????
 * @return [Class]
 */
val EnvironmentClass get() = Environment::class.java

/**
 * ?????? [Process] ??????
 * @return [Class]
 */
val ProcessClass get() = Process::class.java

/**
 * ?????? [Vibrator] ??????
 * @return [Class]
 */
val VibratorClass get() = Vibrator::class.java

/**
 * ?????? [VibrationEffect] ??????
 *
 * - ?????? Android O (26) ?????????????????????
 * @return [Class]
 */
val VibrationEffectClass get() = VibrationEffect::class.java

/**
 * ?????? [VibrationAttributes] ??????
 *
 * - ?????? Android R (30) ?????????????????????
 * @return [Class]
 */
val VibrationAttributesClass get() = VibrationAttributes::class.java

/**
 * ?????? [SystemClock] ??????
 * @return [Class]
 */
val SystemClockClass get() = SystemClock::class.java

/**
 * ?????? [PowerManager] ??????
 * @return [Class]
 */
val PowerManagerClass get() = PowerManager::class.java

/**
 * ?????? [PowerManager.WakeLock] ??????
 * @return [Class]
 */
val PowerManager_WakeLockClass get() = PowerManager.WakeLock::class.java

/**
 * ?????? [UserHandle] ??????
 * @return [Class]
 */
val UserHandleClass get() = UserHandle::class.java

/**
 * ?????? [UserDataHandler] ??????
 * @return [Class]
 */
val UserDataHandlerClass get() = UserDataHandler::class.java

/**
 * ?????? [ShortcutInfo] ??????
 *
 * - ?????? Android N_MR1 (25) ?????????????????????
 * @return [Class]
 */
val ShortcutInfoClass get() = ShortcutInfo::class.java

/**
 * ?????? [ShortcutManager] ??????
 *
 * - ?????? Android R (30) ?????????????????????
 * @return [Class]
 */
val ShortcutManagerClass get() = ShortcutManager::class.java

/**
 * ?????? [ShortcutQuery] ??????
 *
 * - ?????? Android N_MR1 (25) ?????????????????????
 * @return [Class]
 */
val ShortcutQueryClass get() = ShortcutQuery::class.java

/**
 * ?????? [KeyboardShortcutInfo] ??????
 * @return [Class]
 */
val KeyboardShortcutInfoClass get() = KeyboardShortcutInfo::class.java

/**
 * ?????? [KeyboardShortcutGroup] ??????
 * @return [Class]
 */
val KeyboardShortcutGroupClass get() = KeyboardShortcutGroup::class.java

/**
 * ?????? [ShortcutIconResource] ??????
 * @return [Class]
 */
val ShortcutIconResourceClass get() = ShortcutIconResource::class.java

/**
 * ?????? [AppWidgetManager] ??????
 * @return [Class]
 */
val AppWidgetManagerClass get() = AppWidgetManager::class.java

/**
 * ?????? [AppWidgetProvider] ??????
 * @return [Class]
 */
val AppWidgetProviderClass get() = AppWidgetProvider::class.java

/**
 * ?????? [AppWidgetProviderInfo] ??????
 * @return [Class]
 */
val AppWidgetProviderInfoClass get() = AppWidgetProviderInfo::class.java

/**
 * ?????? [AppWidgetHost] ??????
 * @return [Class]
 */
val AppWidgetHostClass get() = AppWidgetHost::class.java

/**
 * ?????? [ActivityInfo] ??????
 * @return [Class]
 */
val ActivityInfoClass get() = ActivityInfo::class.java

/**
 * ?????? [ResolveInfo] ??????
 * @return [Class]
 */
val ResolveInfoClass get() = ResolveInfo::class.java

/**
 * ?????? [Property] ??????
 * @return [Class]
 */
val PropertyClass get() = Property::class.java

/**
 * ?????? [IntProperty] ??????
 * @return [Class]
 */
val IntPropertyClass get() = IntProperty::class.java

/**
 * ?????? [FloatProperty] ??????
 * @return [Class]
 */
val FloatPropertyClass get() = FloatProperty::class.java

/**
 * ?????? [SQLiteDatabase] ??????
 * @return [Class]
 */
val SQLiteDatabaseClass get() = SQLiteDatabase::class.java

/**
 * ?????? [StrictMode] ??????
 * @return [Class]
 */
val StrictModeClass get() = StrictMode::class.java

/**
 * ?????? [AccessibilityManager] ??????
 * @return [Class]
 */
val AccessibilityManagerClass get() = AccessibilityManager::class.java

/**
 * ?????? [AccessibilityEvent] ??????
 * @return [Class]
 */
val AccessibilityEventClass get() = AccessibilityEvent::class.java

/**
 * ?????? [AccessibilityNodeInfo] ??????
 * @return [Class]
 */
val AccessibilityNodeInfoClass get() = AccessibilityNodeInfo::class.java

/**
 * ?????? [IInterface] ??????
 * @return [Class]
 */
val IInterfaceClass get() = IInterface::class.java