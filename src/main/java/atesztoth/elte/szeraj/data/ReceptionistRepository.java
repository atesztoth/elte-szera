package atesztoth.elte.szeraj.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface ReceptionistRepository extends JpaRepository<Receptionist, Integer> { }
