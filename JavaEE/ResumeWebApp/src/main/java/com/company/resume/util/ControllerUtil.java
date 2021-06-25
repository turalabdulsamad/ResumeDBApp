package com.company.resume.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerUtil {
    public static void errorPage(HttpServletResponse response, Exception ex){
        ex.printStackTrace();
        try {
            response.sendRedirect("error.jsp?msg="+ex.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
