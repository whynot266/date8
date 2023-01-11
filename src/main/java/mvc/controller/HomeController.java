package mvc.controller;

import mvc.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import mvc.repository.BookRepository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class HomeController {
    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String listBooks(Model model){
        List<BookEntity> bookEntityList= (List<BookEntity>) bookRepository.findAll();
        model.addAttribute("list", bookEntityList);
        return "home";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam(name = "searchInput") String searchInput, Model model){
        List<BookEntity> resultList;
        if(searchInput.isEmpty()){
            resultList= (List<BookEntity>) bookRepository.findAll();
        }else{
            resultList= bookRepository.findByNameOrAuthor(searchInput, searchInput);
        }
        model.addAttribute("list", resultList);
        return "home";
    }


}
