package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> myLoggerObjectProvider;
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest httpServletRequest) {
//        MyLogger myLogger = myLoggerObjectProvider.getObject();
        System.out.println("myLogger = " + myLogger);
        String requestUrl = httpServletRequest.getRequestURL().toString();
        myLogger.setRequestUrl(requestUrl);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
