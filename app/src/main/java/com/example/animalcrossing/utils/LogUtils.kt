package com.example.animalcrossing.utils

import android.util.Log
import com.example.animalcrossing.BuildConfig

class LogUtils {

    companion object{
        private val TAG = BuildConfig.APPLICATION_ID
        private val isDebugMode = true

        fun d(message: Any) {
            if (isDebugMode) {
                Log.d(TAG, getMessage(message))
            }
        }

        fun w(message: Any) {
            if (isDebugMode) {
                Log.d(TAG, getMessage(message))
            }
        }

        fun i(message: Any) {
            if (isDebugMode) {
                Log.d(TAG, getMessage(message))
            }
        }

        fun v(message: Any) {
            if (isDebugMode) {
                Log.d(TAG, getMessage(message))
            }
        }

        private fun getMessage(message: Any): String =
            "[${getCallerClassName()}] $message"

        private fun getCallerClassName(): String {
            val stackTrackElements = Thread.currentThread().stackTrace
            return traceCallerClassName(stackTrackElements)
        }

        private fun traceCallerClassName(stackTraceElements: Array<StackTraceElement>) : String {
            val logClassFirstAppearIndex = getLogClassFirstAppearIndex(stackTraceElements)
            if (isLogClassAppearInStack(logClassFirstAppearIndex)) {
                return getLogClassCallerName(stackTraceElements, logClassFirstAppearIndex)
            }
            return ""
        }

        private fun getLogClassCallerName(stackTraceElements: Array<StackTraceElement>, firstLogClassIndex: Int) : String {
            if (firstLogClassIndex >= 0) {
                for(i in firstLogClassIndex until stackTraceElements.size) {
                    val className = stackTraceElements[i].className
                    if (isLogUtilsClass(className).not()) {
                        val stackTrace = stackTraceElements[i].toString()
                        return stackTrace.substring(getLeftBoundary(stackTrace), getRightBoundary(stackTrace))
                    }
                }
            }
            return ""
        }

        private fun getLeftBoundary(stackTrace: String): Int =
            stackTrace.indexOf("(") + 1

        private fun getRightBoundary(stackTrace: String): Int =
            stackTrace.indexOf(")")

        private fun isLogClassAppearInStack(logClassFirstAppearIndex: Int) : Boolean =
            logClassFirstAppearIndex != -1

        private fun getLogClassFirstAppearIndex(stackTraceElements: Array<StackTraceElement>) : Int {
            for (i in stackTraceElements.indices) {
                val className = stackTraceElements[i].className
                if (isLogUtilsClass(className).not()) {
                    return i
                }
            }
            return -1
        }

        private fun isLogUtilsClass(className: String): Boolean =
            LogUtils::class.qualifiedName.equals(className)
    }


}