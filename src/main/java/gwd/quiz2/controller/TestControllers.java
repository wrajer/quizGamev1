package gwd.quiz2.controller;

import gwd.quiz2.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope(value = "session")
@Controller
public class TestControllers {

    private User user; //dla zmiennych klasy jest rowne 0, nie
    // widzimy construktora bo jest ona w fabry i tworzy czesto zmienna statyczną, czego nie chcemy


    @ResponseBody //gdy nie chce się robić html
    @GetMapping("/tests/{id}/play")
    public String play () {
        user.addPoint();
        return "" + user.getNumberOfPoints();

    }

}

