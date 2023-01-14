package springbootemployeemana01.demo.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    // preHandle() 在请求方法之前执行,返回值为boolean类型,true表示请求继续执行,false表示请求结束,postHandle()在请求处理完成,dispatcherServlet返回视图后执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //登录成功之后, 拿到用户的 session 信息
        Object loginUser = request.getSession().getAttribute("loginUser");

        if (loginUser == null) { //进行权限判断
            request.setAttribute("msg", "没有权限, 请先登录");
            // 请求的分发, 退回到登录界面
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }
}
