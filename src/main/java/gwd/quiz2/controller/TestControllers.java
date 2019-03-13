package gwd.quiz2.controller;

import gwd.quiz2.model.Test;
import gwd.quiz2.model.User;
import gwd.quiz2.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value = "session")
@Controller
public class TestControllers {


    private User user = new User(); //dla zmiennych klasy jest rowne 0, nie
    // widzimy construktora bo jest ona w fabry i tworzy czesto zmienna statyczną, czego nie chcemy

    @Autowired
    private TestRepository testRepository;

    //dla testu nr pytania
    private Integer questionNumber;

    //@ResponseBody //gdy nie chce się robić html


    @GetMapping("/tests/{id}/play")
    public String play(@PathVariable Integer id, ModelMap modelMap) {
        questionNumber = 0;
        Test test = testRepository.findById(id).get(); //wyjęcie z bazy danych obiektu pytania
        modelMap.addAttribute("test", test);
        modelMap.addAttribute("question", test.getQuestionList().get(questionNumber));
//ja w swojej miałem jescze ++
      //  user = new User();
      //  questionNumber=0;
        return "test";
    }


//controller michala
@GetMapping("/tests/{id}/continue")
public String continueTest(
        @RequestParam(required = false)
                boolean correct, @PathVariable Integer id, ModelMap modelMap) {
    Test test = testRepository.findById(id).get();
    if ( test.getQuestionList().get(questionNumber).getCorrect() == correct){
        user.addPoint();
    }
    questionNumber++;
    if ( questionNumber < test.getQuestionList().size() ){
        modelMap.put("test",test);
        modelMap.put("question",test.getQuestionList().get(questionNumber));
    }
    else{
        modelMap.put("test",test);
        modelMap.put("message","Koniec gry! Twoja liczba punktów to: "
                +user.getNumberOfPoints());
    }
    return "test";
}


}

