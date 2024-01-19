package github.beiguaovo.qqbotmanager.service;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class QQBotServiceImpl implements QQBotService{
    @Override
    public void QQBotEventHandle(HttpServletRequest request) {
        JSONObject jsonParam = this.getJSONParam(request);
        log.info("接受参数为:{}",jsonParam.toString()!=null ? "SUCCESS" : "FAILED");
        if ("message".equals(jsonParam.getString("post_type")) && "group".equals(jsonParam.getString("message_type"))){
            String message = jsonParam.getString("message");
            log.info("群消息：{}",jsonParam);
        }
    }

    public JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParma = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line=br.readLine())!=null){
                sb.append(line);
            }
            jsonParma = JSONObject.parseObject(sb.toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return jsonParma;
    }
}
