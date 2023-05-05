import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class GachaService implements InitializingBean {
    final private Random random = new Random();

    @Value("${opsim.gacha.weight}")
    private Integer gachaMaxWeight;

    @Autowired
    private GachaDao gachaDao;

    private Integer batchSize = 10;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public Waifu singleWish() {

    }

    public ArrayList<Waifu> batchWish() {

    }

}