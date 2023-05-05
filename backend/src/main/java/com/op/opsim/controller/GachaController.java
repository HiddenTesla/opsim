import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/gacha")
public class GachaController {

    @Autowired
    private GachaService gachaService;

    @Autowired
    private GachaDao gachaDao;

    @PostMapping(path = {"", "/"})
    public ResponseEntity<Object> wish() {
        try {
            Waifu waifu = gachaService.singleWish();
            //TODO: Record wish and calculate pity for single user
            return new ResponseEntity<>(waifu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/ten")
    public ResponseEntity<Object> batchWish() {
        try {
            ArrayList<Waifu> results = gachaService.batchWish();
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
