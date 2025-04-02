package com.global.chatbox.error;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        Map<String, Object> customError = new LinkedHashMap<>();

        String message = (String) errorAttributes.get("message");

        if (message == null || message.startsWith("No message available")) {
            message = (String) errorAttributes.get("error");
        }

        customError.put("message", message != null ? message : "Unknown error");
        customError.put("data", null);

        return customError;
    }
}