package com.softdesign.school.utils;

import android.util.Log;

public class Lg {


    /**
     * Префикс
     */
    private static final String PREFIX = "Logger";

    /**
     * Максимальный буффер
     */
    public static final int LOGCAT_BUFFER_SIZE = 3000;

    /**
     *
     * @return bool
     */
    private static boolean shouldLog() {
        // TODO: сделать конфиг и константу логирования
        // return BuildConfig.IS_LOGCAT_LOGGER_ENABLED;
        return true;
    }

    /**
     * Лог info
     *
     * @param tag Тег дляиндификации лога
     * @param msg Сообщение лога
     */
    public static void i(String tag, String msg) {
        wrapper(Log.INFO, tag, msg);
    }

    /**
     * Лог приоритет: Ошибк
     *
     * @param tag Тег для индификации лога
     * @param msg Сообщение лога
     */
    public static void e(String tag, String msg) {
        wrapper(Log.ERROR, tag, msg);
    }

    /**
     * Лог приоритет: Предупреждение
     *
     * @param tag Тег для индификации лога
     * @param msg Сообщение лога
     */
    public static void w(String tag, String msg) {
        wrapper(Log.WARN, tag, msg);
    }

    /**
     * Лог приоритет: Подробный
     *
     * @param tag Тег для индификации лога
     * @param msg Сообщение лога
     */
    public static void v(String tag, String msg) {
        wrapper(Log.VERBOSE, tag, msg);
    }

    /**
     * Лог приоритет: Дебаг
     *
     * @param tag Тег для индификации лога
     * @param msg Сообщение лога
     */
    public static void d(String tag, String msg) {
        wrapper(Log.DEBUG, tag, msg);
    }

    /**
     * Лог приоритет: Утверждение
     *
     * @param tag Тег для индификации лога
     * @param msg Сообщение лога
     */
    public static void a(String tag, String msg) {
        wrapper(Log.ASSERT, tag, msg);
    }

    /**
     * Универсальный метод логирования
     *
     * @param priority Константа приоритета
     * @param tag      Тег для индификации лога
     * @param msg      Сообщение лога
     */
    public static void wrapper(Integer priority, String tag, String msg) {
        if (shouldLog()) {
            String str = msg;

            while (str.length() > LOGCAT_BUFFER_SIZE) {
                String substr = str.substring(0, LOGCAT_BUFFER_SIZE);
                str = substr.substring(LOGCAT_BUFFER_SIZE);
                log(priority, PREFIX + tag, substr);
            }

            log(priority, PREFIX + tag, str);
        }
    }

    /**
     * Вызов системного класса {@link android.util.Log}
     *
     * @param priority Константа приоритета
     * @param tag      Тег для индификации лога
     * @param msg      Сообщение лога
     */
    private static void log(Integer priority, String tag, String msg) {
        switch (priority) {
            case Log.VERBOSE:
                Log.v(tag, msg);
                break;
            case Log.DEBUG:
                Log.d(tag, msg);
                break;
            case Log.INFO:
                Log.i(tag, msg);
                break;
            case Log.WARN:
                Log.w(tag, msg);
                break;
            case Log.ERROR:
                Log.e(tag, msg);
                break;
            case Log.ASSERT:
                Log.println(Log.ASSERT, tag, msg);
                break;
        }
    }
}