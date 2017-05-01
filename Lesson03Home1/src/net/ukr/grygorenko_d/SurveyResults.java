package net.ukr.grygorenko_d;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet (name = "SurveyResults", urlPatterns = "/result")
public class SurveyResults extends  HttpServlet{
    Map<String,String> map = new HashMap<String,String>();
    String questionOne = "Do you like prog.kiev.ua courses?";
    String questionTwo = "Will you recommend them to your friends?";
    String answer1;
    String answer2;

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs = req.getSession();
        Statistic st = (Statistic) hs.getAttribute("stat");
        if (st == null) {
            st = Statistic.getInstance();
            hs.setAttribute("stat", st);
        }

        processAnswers(req, st);
        PrintWriter pw = resp.getWriter();
        showResult(pw);
        showStatistic(pw, st);
        pw.close();
    }

    public void processAnswers(HttpServletRequest req, Statistic st){
        map.put("First name", req.getParameter("firstname"));
        map.put("Last name", req.getParameter("lastname"));
        map.put("Age", req.getParameter("age"));
        answer1 = req.getParameter("like");
        answer2 = req.getParameter("recommend");
        map.put(questionOne, answer1);
        if(answer1.equals("yes")){
            st.addVote("q1Yes");
        }else{
            st.addVote("q1No");
        }
        map.put(questionTwo, answer2);
        if(answer2.equals("yes")){
            st.addVote("q2Yes");
        }else{
            st.addVote("q2No");
        }
        st.addVote("Total");
    }

    public void showResult(PrintWriter pw){
        pw.println("<!DOCTYPE html>");
        pw.println("<html><head><title>Results</title></head>");
        String template = "<body><h2>Results for user %s %s, %s:</h2>";
        pw.println(String.format(template, map.get("First name"), map.get("Last name"), map.get("Age") ));
        pw.println("<br>" + questionOne + " : <strong>" + map.get(questionOne) + "</strong></br>");
        pw.println("<br>" + questionTwo + " : <strong>" + map.get(questionTwo) + "</strong></br>");
        pw.println("<h3>Thank you for using our SimpleSurvey!</h3>");
        pw.println("<br/>");
    }

    public void showStatistic(PrintWriter pw, Statistic st){
        String template = "<body><h2>Current votes statistic (%d users voted):</h2>";
        pw.println(String.format(template, st.getVote("Total")));
        pw.println(questionOne + " : ");
        pw.println("<br><strong>Yes: </strong>" + st.getVote("q1Yes") + " times.");
        pw.println("<br><strong>No: </strong>" + st.getVote("q1No") + " times.");
        pw.println("<br>" +questionTwo +  " : ");
        pw.println("<br><strong>Yes: </strong>" + st.getVote("q2Yes") + " times.");
        pw.println("<br><strong>No: </strong>" + st.getVote("q2No") + " times.");
        pw.println("</body></html>");
    }

}
