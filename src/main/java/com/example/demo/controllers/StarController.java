package com.example.demo.controllers;

import com.example.demo.models.Star;
import com.example.demo.repository.StarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/star")
public class StarController {

    @Autowired
    StarRepository starRepository;

    @GetMapping("/")
    public String index(Model model) {
        Iterable<Star> starIterable = starRepository.findAll();
        model.addAttribute("star_list", starIterable);
        return "star/index";
    }

//    @GetMapping("/add")
//    public String AddView(){
//        return "star/star-add";
//    }
//
//    @PostMapping("/add")
//    public String AddStar(
//            @RequestParam(name = "name") String name,
//            @RequestParam(name = "class") String class_star,
//            @RequestParam(name = "lumen") int lumen
//    ){
//        Star new_star = new Star(name, class_star, lumen);
//        starRepository.save(new_star);
//        return "redirect:/star/";
//    }

    @GetMapping("/filter")
    public String filter(@RequestParam(name = "name") String name_star,
                         Model model) {
        List<Star> starList = starRepository.findByName(name_star);
        model.addAttribute("star_list", starList);

        return "star/index";
    }

    @GetMapping("/filtercontains")
    public String filterContains(@RequestParam(name = "name") String name_star,
                         Model model) {
        List<Star> starList = starRepository.findByNameContains(name_star);
        model.addAttribute("star_list", starList);

        return "star/index";
    }

    @GetMapping("/detail/{id}")
    public String detailStar(
            @PathVariable Long id,
            Model model
    ) {
        Star star_obj = starRepository.findById(id).orElseThrow();
        model.addAttribute("one_star", star_obj);

        return "star/info";
    }

    @GetMapping("'/detail/{id}/del")
    public String delStar(@PathVariable Long id,
                          Model model) {
        Star star_obj = starRepository.findById(id).orElseThrow();
        starRepository.delete(star_obj);

//        starRepository.deleteById(id);

        return "redirect:/star/";
    }

    @GetMapping("/details/{id}/upd")
    public String updateView(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("object",
                starRepository.findById(id).orElseThrow());
        return "star/update";
    }

    @PostMapping("/details/{id}/upd")
    public String updateView(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam(name="class") String class_star,
            @RequestParam Integer lumen
    ) {
        Star star = starRepository.findById(id).orElseThrow();

        star.setName(name);
        star.setClass_star(class_star);
        star.setLumen(lumen);

        starRepository.save(star);

        return "redirect:/star/detail/" + star.getUID();
    }

    @GetMapping("/add")
    public String addView(Star star) {
        return "star/star-add";
    }

    @PostMapping("/add")
    public String AddFlower(@Valid Star star, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "star/star-add";
        }

        return "redirect:/star/";
    }
}
