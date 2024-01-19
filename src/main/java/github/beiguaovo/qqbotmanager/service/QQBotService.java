package github.beiguaovo.qqbotmanager.service;

import jakarta.servlet.http.HttpServletRequest;

public interface QQBotService {
    void QQBotEventHandle(HttpServletRequest request);
}
