package meister.hackaton.maskserver.domain.user.repositiory

import meister.hackaton.maskserver.domain.user.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun findUserById(id: Long): User?

}