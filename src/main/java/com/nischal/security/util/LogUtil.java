package com.nischal.security.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtil {

    public static void exception(Exception ex) {
        log.error("[Exception]: " + ex.getMessage());
    }

    public static void exception(String message) {
        log.error("[Exception]: " + message);
    }

}
