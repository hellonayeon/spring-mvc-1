package me.hellonayeon.servlet.web.frontcontroller.v5;

import me.hellonayeon.servlet.web.frontcontroller.ModelView;
import me.hellonayeon.servlet.web.frontcontroller.MyView;
import me.hellonayeon.servlet.web.frontcontroller.v3.ControllerV3;
import me.hellonayeon.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import me.hellonayeon.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import me.hellonayeon.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import me.hellonayeon.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import me.hellonayeon.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import me.hellonayeon.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import me.hellonayeon.servlet.web.frontcontroller.v5.adapter.ControllerV3AdapterHandler;
import me.hellonayeon.servlet.web.frontcontroller.v5.adapter.ControllerV4AdapterHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private Map<String, Object> handlerMappingMap = new HashMap<>();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3AdapterHandler());
        handlerAdapters.add(new ControllerV4AdapterHandler());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // MemberFormControllerV3
        Object handler = getHandler(request); // handler == controller

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // ControllerV3AdapterHandler
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName();
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter is not founded ! handler = " + handler);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

}
