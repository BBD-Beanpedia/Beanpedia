package net.ryan.TimoRefactorLater.Controller;

import net.ryan.TimoRefactorLater.DTO.BeanDTO;
import net.ryan.TimoRefactorLater.Service.BeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class BeanController {
    BeanService beanService;

    @Autowired
    private JdbcTemplate jdbcTemplate;
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
        String sql = "SELECT * FROM \"BasicBeanInformation\"";
        return jdbcTemplate.queryForList(sql);
    }

}
