<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Answering question</title>
<link href='https://fonts.googleapis.com/css?family=Nunito:400,300,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="./style.css">
</head>
<html>
<body>
<center>


    <!--<center>-->
    <div class='container' ng-cloak ng-app="chatApp">
        <h1>Please enter the question</h1>
        <div class='chatbox' ng-controller="MessageCtrl as chatMessage">


            <div class="chatbox__messages-left">
                <div class="chatbox__messages__user-message--ind-message">
                    <p class="name">{{"Your question: "}}</p>
                    <br/>
                    <p class="message"><% String question =(String)request.getAttribute("question");
                     out.println(question);
                     %></p>
                </div>
            </div>
            <div class="chatbox__messages_right">
                <div class="chatbox__messages__user-message--ind-message">
                    <p class="name">{{"Our answer: "}}</p>
                    <br/>
                    <p class="message"><% String answer =(String)request.getAttribute("answer");
                        out.println(answer);
                    %></p>
                </div>
            </div>


            <form method="post" action="AskQuestion">
                <input name="LIST" type="text" placeholder="Enter your question here.       ">
                <!--        <br><br>-->
                <!--        <input type="submit">-->

            </form>
        </div>
    </div>
    <!--</center>-->
    <script src='https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.14/angular.min.js'></script>
    <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="script.js"></script>

</body>

</html>