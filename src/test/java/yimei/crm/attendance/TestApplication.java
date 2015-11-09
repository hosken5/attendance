package yimei.crm.attendance;

import com.yimei.attendance.Application;
import com.yimei.attendance.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by xiangyang on 15/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class TestApplication {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test01(){
        System.out.println(userRepository.findAllCount("zxy"));
    }
}
