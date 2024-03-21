package edu.matc.persistence;
import com.mysql.cj.Session;
import edu.matc.entity.Race;
import edu.matc.entity.TeamRaces;
import edu.matc.entity.Teams;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This class' purpose is to test the functionality of this application
 */
class TeamRacesTest {

    private GenericDao dao;

    /**
     * Triggers before everything else
     */
    @BeforeEach
    void setUp() {

        Database database = Database.getInstance();
        dao = new GenericDao(TeamRaces.class);
    }

    @Test
    void testTeamRace() {

        Race race = new Race("example", "2324", LocalDate.now());
        Teams team = new Teams("example");

        TeamRaces teamRace = new TeamRaces();
        teamRace.setRace(race);
        teamRace.setTeam(team);
        teamRace.setLate_penalty(2);

        assertNotNull(teamRace.getRace());
        assertNotNull(teamRace.getTeam());
    }
}
