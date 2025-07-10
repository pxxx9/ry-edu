package org.dromara.system.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 上传资源类型枚举
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    IMAGE("image", "图片", "image-bucket"),
    DOCUMENT("document", "文档", "doc-bucket"),
    VIDEO("video", "视频", "video-bucket"),
    AUDIO("audio", "音频", "audio-bucket"),
    AVATAR("avatar", "头像", "avatar-bucket"),
    DEFAULT("default", "其他", "default-bucket");

    /**
     * 前端传的资源类型标识
     */
    private final String code;

    /**
     * 类型中文名称
     */
    private final String label;

    /**
     * 对应的 MinIO 桶名
     */
    private final String bucket;

    /**
     * 通过 code 获取对应枚举类型
     */
    public static ResourceTypeEnum fromCode(String code) {
        for (ResourceTypeEnum type : values()) {
            if (type.code.equalsIgnoreCase(code)) {
                return type;
            }
        }
        throw new IllegalArgumentException("未知资源类型: " + code);
    }
}

