package gwd.quiz2.controller;

import gwd.quiz2.model.Question;
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

@Scope(value = "session")
@Controller
public class AdminController {

    @Autowired
    private TestRepository testRepository;
    private boolean ifNewTest = true;
    private Test test = new Test();
    private Integer questionNumber = 1;
//private Integer questionNumber;


    // ---------------------------------
    @GetMapping("/addtest")
    public String addtest(ModelMap modelMap) {
        modelMap.put("questionnumber", questionNumber);
        return "addtest";
    }

    // ---------------------------------


    @GetMapping("/addtesttest")
    public String addtestDB(@RequestParam(required = false) String name,
                            @RequestParam(required = false) String content,
                            @RequestParam(required = false) boolean correct,
                            @RequestParam(required = false) boolean optionSaveClose,
                            ModelMap modelMap) {
        //gdy tylko 1 pytanie i koniec
        if (test.getName() == null) {
            test.setName(name);
        }
        test.addQuestion(new Question(content, correct));
        questionNumber++;
        modelMap.put("questionnumber", questionNumber);
        modelMap.put("name", test.getName());
        modelMap.put("disabled", true);

        if (optionSaveClose) {
            testRepository.save(test); //przekazanie do bazy danych poczatkowych
            return "index";
        }

        return "addtest";
    }


    // ---------------------------------
    @GetMapping("/alltests")
    public String alltests(ModelMap modelMap) {

        modelMap.put("tests", testRepository.findAll());

        return "alltests";
    }

    // ---------------------------------


}

