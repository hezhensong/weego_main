import com.weego.main.dto.PersonDto;
import com.weego.main.service.PersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liuniandxx on 16-3-21.
 */
public class PersonServiceTest extends BaseTest{
    @Autowired
    private PersonService personService;

    @Test
    public void testGetPersonDetail() {
        String personId = "56e3c63460f7fef1690001c5";
        PersonDto dto = personService.getPersonDetail(personId);

        System.out.println(dto.toString());
    }
}
