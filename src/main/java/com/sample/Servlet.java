package com.sample;

//import com.sample.model.LiquorType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


@WebServlet(
        name = "askingQuestion",
        urlPatterns = "/AskQuestion"
)
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String input_question = req.getParameter("LIST");

 //       AnsweringService answeringService = new AnsweringService();


        input_question=input_question.toLowerCase(); //turn string ot lowercase, easy to compare
        String answering = Question_Parsing.question_parsing(input_question);


        //String answering = answeringService.getAvailableBrands(input_question);

        req.setAttribute("answer", answering);
        req.setAttribute("question", input_question);
        RequestDispatcher view = req.getRequestDispatcher("result.jsp");
        view.forward(req, resp);

    }
}
