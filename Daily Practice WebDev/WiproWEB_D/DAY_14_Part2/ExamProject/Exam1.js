
NoOfQuestions = 5;// Edit this value before adding questions 
Questions = Array(NoOfQuestions);
Answers = new Array(NoOfQuestions);
Choosed = false;
for (var i = 0 ; i < NoOfQuestions ; i++)
{
    Answers[i] = new Array(4);
}
CorrectAnswers = new Array(NoOfQuestions);

QuestionNo = 0;
Marks = 0;
OptionChoosed = 0;
Answer = 5;

function AnswerChoosed(Ans)
{
	Answer = Ans;
	Choosed = true;
}



function load()
{
	LoadQuestion();
}

function LoadQuestion()
{
	document.getElementById("Question").innerHTML = "(" + (QuestionNo + 1)+") : " + Questions[QuestionNo];
	document.getElementById("Option0").innerHTML = "(a) " + Answers[QuestionNo][0];
	document.getElementById("Option1").innerHTML = "(b) " + Answers[QuestionNo][1];
	document.getElementById("Option2").innerHTML = "(c) " + Answers[QuestionNo][2];
	document.getElementById("Option3").innerHTML = "(d) " + Answers[QuestionNo][3];		
	document.getElementById("Opt1").checked = false;	
	document.getElementById("Opt2").checked = false;	
	document.getElementById("Opt3").checked = false;	
	document.getElementById("Opt4").checked = false;
	Answer = 5;
}


function NextQuestion()
{      
	if(Answer == CorrectAnswers[QuestionNo])
	{
		Marks++;
	}
	if(Choosed)
	{
   	if(QuestionNo < NoOfQuestions - 1)
   	{	
   		QuestionNo++;
   		LoadQuestion();
   	}
   	else
   	{
   		alert("End of Examination \n"+"Marks Are : "+Marks+"");
   	}
   	Choosed = false;
	}
	else
	{
       alert("No option choosed yet.... ");
	}
}

Questions[0] = "The headmaster of Hogwarts during Harry’s school years is";
Answers[0][0] = "Severus Snape";
Answers[0][1] = "Minerva McGonagall";
Answers[0][2] = "Albus Dumbledore";
Answers[0][3] = "Horace Slughorn";
CorrectAnswers[0] = 3;


Questions[1] = "The house known for bravery and courage is";
Answers[1][0] = "Ravenclaw";
Answers[1][1] = "Hufflepuff";
Answers[1][2] = "Gryffindor";
Answers[1][3] = "Slytherin";
CorrectAnswers[1] = 3;


Questions[2] = "The character known as 'The Boy Who Lived' is";
Answers[2][0] = "Harry Potter";
Answers[2][1] = "Ron Weasley";
Answers[2][2] = "Draco Malfoy";
Answers[2][3] = "Neville Longbottom";
CorrectAnswers[2] = 1;


Questions[3] = "The dark wizard feared by all is";
Answers[3][0] = "Gellert Grindelwald";
Answers[3][1] = "Lord Voldemort";
Answers[3][2] = "Lucius Malfoy";
Answers[3][3] = "Sirius Black";
CorrectAnswers[3] = 2;


Questions[4] = "The magical sport played on broomsticks is";
Answers[4][0] = "Wizards Tennis";
Answers[4][1] = "Quidditch";
Answers[4][2] = "Broom Polo";
Answers[4][3] = "Skyball";
CorrectAnswers[4] = 2;
