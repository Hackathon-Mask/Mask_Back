package meister.hackaton.maskserver.domain.curiousity.repository

import meister.hackaton.maskserver.domain.curiousity.model.Curiousity
import meister.hackaton.maskserver.domain.curiousity.model.CuriousityId
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CuriousityRepository : CrudRepository<Curiousity, CuriousityId> {
}