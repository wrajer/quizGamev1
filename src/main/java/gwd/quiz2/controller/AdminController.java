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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        test = new Test();
        questionNumber = 1;
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
            modelMap.put("tests", testRepository.findAll());
            return "alltests";
        }

        return "addtest";
    }


    // --------------------------------- wyswietlanie listy testów
    @GetMapping("/alltests")
    public String alltests(ModelMap modelMap) {

        modelMap.put("tests", testRepository.findAll());

        return "alltests";
    }

    // --------------------------------- usuwanie elementów


    @GetMapping("tests/{id}/delete")
    public String delete(@PathVariable Integer id, ModelMap modelMap) {

        Test test = testRepository.findById(id).get();
        modelMap.put("message", ("Usunieto test " + test.getName() + " z bazy danych"));
        testRepository.deleteById(id);
        modelMap.put("tests", testRepository.findAll());

        return "alltests";
    }
}

//5)Dodać admina,jakieś przykładowe hasło i login.
//6)Zapisywanie danych użytkownika email i nazwa do bazy danych oraz wyników poszczególnych użytkowników(same wyniki).
//7)Dodać możliwość wyświetlania poszczególnych odpowiedzi użytkowników.