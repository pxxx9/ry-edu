package org.dromara.system.domain;

public class ResourceTypeHolder {
    private static final ThreadLocal<ResourceTypeEnum> context = new ThreadLocal<>();

    public static void set(ResourceTypeEnum type) {
        context.set(type);
    }

    public static ResourceTypeEnum get() {
        return context.get();
    }

    public static void clear() {
        context.remove();
    }
}
