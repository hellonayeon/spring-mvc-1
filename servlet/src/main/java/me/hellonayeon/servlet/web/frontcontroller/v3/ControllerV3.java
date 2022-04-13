package me.hellonayeon.servlet.web.frontcontroller.v3;

import me.hellonayeon.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);
}
