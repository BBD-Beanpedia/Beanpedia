package net.ryan.TimoRefactorLater.Controller;

import net.ryan.TimoRefactorLater.DTO.BeanDTO;
import net.ryan.TimoRefactorLater.Service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

@RestController
public class BeanController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    BeanService beanService;

    @Autowired
    BeanController(BeanService beanService){
        this.beanService = beanService;
    }
    @GetMapping(value = "/getBeans")
    List<BeanDTO> getBeans(){

        return beanService.getBeans();

    }
    @GetMapping(value = "/test")
    public List<Map<String, Object>> test() {
        try {
            String sql = "SELECT * FROM clown";
            return jdbcTemplate.queryForList(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
