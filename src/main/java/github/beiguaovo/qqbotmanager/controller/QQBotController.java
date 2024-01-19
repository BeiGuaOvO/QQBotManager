package github.beiguaovo.qqbotmanager.controller;

import github.beiguaovo.qqbotmanager.service.QQBotService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class QQBotController {
    @Resource
    private QQBotService qqBotService;

    @PostMapping
    public void QQBotEvent(HttpServletRequest request){
        qqBotService.QQBotEventHandle(request);
    }
}
