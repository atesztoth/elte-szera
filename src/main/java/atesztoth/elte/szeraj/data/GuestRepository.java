package atesztoth.elte.szeraj.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GuestRepository extends JpaRepository<Guest, Integer> {
    public Guest findByUsername(String username);
}
