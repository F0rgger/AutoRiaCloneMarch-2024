package com.autoria.autoriaplatform.exception;

public class PermissionNotFoundException extends RuntimeException {
    public PermissionNotFoundException(String permissionName) {
        super("Permission not found: " + permissionName);
    }
}