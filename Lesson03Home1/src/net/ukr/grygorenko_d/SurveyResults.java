package net.ukr.grygorenko_d;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class SurveyResults extends  HttpServlet{
    Map<String,String> map = new HashMap<String,String>();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionOne = "Do you like prog.kiev.ua courses?";
        String questionTwo = "Will you recommend them to your friends?";

        map.put("First name", req.getParameter("firstname"));
        map.put("Last name", req.getParameter("lastname"));
        map.put("Age", req.getParameter("age"));
        map.put(questionOne, req.getParameter("like"));
        map.put(questionTwo, req.getParameter("recommend"));

        PrintWriter pw = resp.getWriter();
        pw.println("<!DOCTYPE html>");
        pw.println("<html><head><title>Results</title></head>");
        String template = "<body><h2>Results for user %s %s, %s:</h2>";
        pw.println(String.format(template, map.get("First name"), map.get("Last name"), map.get("Age") ));
        pw.println("<br>" + questionOne + " : <strong>" + map.get(questionOne) + "</strong></br>");
        pw.println("<br>" + questionTwo + " : <strong>" + map.get(questionTwo) + "</strong></br>");
        pw.println("<h3>Thank you for using our SimpleSurvey!</h3></body></html>");




    }

}
