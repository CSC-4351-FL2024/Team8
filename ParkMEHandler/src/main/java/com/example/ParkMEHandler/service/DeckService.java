@Service
public class DeckService{
    private final DeckRepository deckRepository;

    @Autowired 
    public DeckService (DeckRepository deckRepository) {
        this.deckRepository = deckRepository;
    }

}

