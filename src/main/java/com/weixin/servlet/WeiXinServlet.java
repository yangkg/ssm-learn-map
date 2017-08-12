package com.weixin.servlet;

import com.weixin.util.CheckUtil;
import com.weixin.util.MessageUtil;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "WeiXinServlet")
public class WeiXinServlet extends HttpServlet {

    /**
     * 消息的接收和响应
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter responseWriter = response.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String responseXml = null;
            if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
                if ("1".equals(content)) {
                    responseXml = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenue());
                } else if ("2".equals(content)) {
                    responseXml = MessageUtil.initNewsMessage(toUserName, fromUserName);
                } else if ("3".equals(content)) {
                    responseXml = MessageUtil.initImageMessage(toUserName, fromUserName);
                } else if ("?".equals(content) || "？".equals(content)) {
                    responseXml = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menueText());
                }
            } else if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
                String eventType = map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
                    responseXml = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menueText());
                }

            }

            responseWriter.print(responseXml);
            System.out.println(responseXml);


        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            responseWriter.close();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }

    }

}
