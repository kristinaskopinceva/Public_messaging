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
public class GreetingController {// контролер модуль который по пути greeting слушает запросы пользователя и возвр какие то данные
    @Autowired //автоматическая привязка сервиса к укзанной переменной типа репо и установка связи с бд
    private MessageRepo messageRepo;
   /* @GetMapping("/")
    // анотация - ярлык RequestParam, которые выдергивает данные по укзаны полям от польз.из url
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
         // метоод ожидает на вход параметр имя ( required = false, и дефолтное значение у него World - эти поля не обяз.)
        model.put("name", name); // тут мы храним данные для дальнейшего возврата пользователю
        return "greeting"; // возвращаем имя файла-шаблона спинговому контейнеру которое мы хотим отобразить
    }*/
    @GetMapping("/")
     public String mainPage( Map<String,Object> model){
       return "greeting" ;
    }
    @GetMapping("/main")
    public String main(Map<String, Object> model) {
       // Iterable<Message> messages = messageRepo.findAll(); // подключаем репозирии с данными
          Object messages1 = model.put("messages",  messageRepo.findAll()); //в map складываю то что вернулсь мне от пользователя в main.mustache
        //(это отображается как лист в {{#messages}})

        return "main";
    }

    @PostMapping("/main") // методы запрса выдергивает данные из формы (см main.mustache <form method="post">) postMapping к form method ="post",
     // антотация @RequestParam берет значения из блока post по аналогичным именам переменнах в
    public String add(@RequestParam String text, @RequestParam String tag) {
        Map<String, Object> model =null;
        Message message = new Message(text, tag); //  складываем данные от пользователя в переменные класса сущности Message

        messageRepo.save(message);//сохраняем в БД информацию , которую выдернули RequestParam

        Iterable<Message> messages = messageRepo.findAll(); //  берем информацию из репозиторя (БД)

        model.put("messages",messages);// кладем в модель

        return "main"; // отдали пользователю
    }
    @PostMapping("filter")
     public String filter (@RequestParam String filter, Map <String,Object> model){ // то что мы принемаем из блока post filter
        Iterable<Message> messages;
           if(filter!=null&!filter.isEmpty()){
        messages = messageRepo.findByTag(filter);} // CrudRepository передаем текст из поля filter
           else {
            messages = messageRepo.findAll();}
             model.put("messages", messages);
                 return "main";
    }

}