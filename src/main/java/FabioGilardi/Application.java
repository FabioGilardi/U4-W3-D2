package FabioGilardi;


import FabioGilardi.dao.EventsDAO;
import FabioGilardi.entities.Events;
import FabioGilardi.exceptions.NotFoundException;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("U4-W3-D2");
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
//      FAKER
        Faker faker = new Faker();

//        CREAZIONE ENTITY MANAGER
        EntityManager em = emf.createEntityManager();
        EventsDAO eventsDAO = new EventsDAO(em);

//        POPOLAMENTO DB
//        for (int i = 0; i < 20; i++) {
//            Random random = new Random();
//            int randomeEventType = random.nextInt(0, 2);
//            List<EventType> typeList = new ArrayList<>();
//            typeList.add(EventType.PUBLIC);
//            typeList.add(EventType.PRIVATE);
//            int randomPartecipants = random.nextInt(10, 101);
//            eventsDAO.saveOnDB(new Events(faker.esports().event(), LocalDate.now(), faker.esports().game(), typeList.get(randomeEventType), randomPartecipants));
//        }

//        LETTURA EVENTI
        try {
            Events event1 = eventsDAO.findById(4);
            System.out.println(event1);
            Events event2 = eventsDAO.findById(11);
            System.out.println(event2);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

//        RIMOZIONE EVENTI
        try {
            eventsDAO.deletFromDB(20);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

        em.close();
        emf.close();
    }
}
