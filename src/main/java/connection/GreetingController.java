package connection;

import connection.Entity.Message;
import connection.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller // связующий элемент между моделью и видом
public class GreetingController {
    @Autowired //автоматическая привязка сервиса к укзанной переменной типа репо и установка связи с бд
    private MessageRepo messageRepo;
    @GetMapping("/greeting") // контролер модуль который по пути greeting слушает запросы пользователя и возвр какие то данные
    // в тукущем примере он будет возвращать файл шаблона приветсвия (greeting.mustache) метод GET выдергивает данные от польз.из url
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
         // метоод ожидает на вход параметр имя ( этот параметр не обязательный  required = false, и дефолтное значение у него мирр)
        model.put("name", name); // тут мы храним данные для дальнейшего возврата пользователю
        return "greeting"; // возвращаем имя файла спинговому контейнеру которое мы хотим отобразить
    }
    @GetMapping  // мепирование // если не указывать функцию в параметре то отображаться будет шаблон main
    public String main(Map<String, Object> model) {
       // Iterable<Message> messages = messageRepo.findAll(); // подключаем репозирии с данными

        Object messages1 = model.put("messages",  messageRepo.findAll()); //в map складываю то что вернулсь мне от пользователя в main.mustache
        //(это отображается как лист в {{#messages}})

        return "main";
    }

    @PostMapping // методы запрса выдергивает данные из формы (см main.mustache <form method="post">) postMapping к form method ="post",
     // антотация @RequestParam берет значения из блока post по аналогичным именам переменнах в
    public String add(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag);

        messageRepo.save(message);

        Iterable<Message> messages = messageRepo.findAll(); // метод findAll = возвращает Iterable

        model.put("messages",messages);

        return "main";
    }
    @PostMapping("filter")
     public String filter (@RequestParam String filter, Map <String,Object> model){ // то что мы принемаем из блока post filter
        Iterable<Message> messages;
           if(filter!=null&!filter.isEmpty()){
        messages = messageRepo.findByTag(filter);}
           else {
            messages = messageRepo.findAll();}
             model.put("messages", messages);
                 return "main";
    }

}