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
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value = "session")
@Controller
public class TestControllers {


    private User user; //dla zmiennych klasy jest rowne 0, nie
    // widzimy construktora bo jest ona w fabry i tworzy czesto zmienna statyczną, czego nie chcemy

    @Autowired
    private TestRepository testRepository;

    //@ResponseBody //gdy nie chce się robić html
    @GetMapping("/tests/{id}/play")
    public String play (@PathVariable Integer id, ModelMap modelMap) {
        Test test = testRepository.findById(id).get(); //wyjęcie z bazy danych obiektu pytania

        modelMap.addAttribute("test", test);
        modelMap.addAttribute("question", test.getQuestionList().get(0));

//        user.addPoint();
//        return "" + user.getNumberOfPoints();

        return "test";

    }

}

