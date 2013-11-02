package org.lework.runner.web;

/**
 * 通知类型枚举类
 */
public enum NotificationType {
    INFO, WARNING, ERROR, SUCCESS;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
