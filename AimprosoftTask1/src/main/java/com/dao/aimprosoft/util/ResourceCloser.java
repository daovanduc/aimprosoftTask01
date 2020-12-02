package com.dao.aimprosoft.util;

public class ResourceCloser {
    public static void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable resource : autoCloseables) {
            if (resource != null){
                try {
                    resource.close();
                } catch (Exception e) {
                    throw new RuntimeException("AutoClosable exceptions");
                }
            }
        }
    }
}
