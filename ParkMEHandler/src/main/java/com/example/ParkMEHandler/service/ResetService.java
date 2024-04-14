import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;

@Service
public class ResetService {

    @Autowired
    private DeckRepository deckRepository; 
    @Autowired
    private UserRepository userRepository;

    //runs everyday at 12am 
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void resetUserBookingsAndDecks() {
        deckRepository.deleteAllInBatch(); //delete all rows in deck table
        
        //deckRepository.resetAllDeckAvailability(15); 
        
        //i should not have to reset all deck since all rows are deleted. 
        //instead i think i have to remove user.bookingtime and user.deckbooked??...
        userRepository.clearParkingInformation();

    }
}
