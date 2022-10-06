package meister.hackaton.maskserver.domain.user.repositiory

import meister.hackaton.maskserver.domain.tag.model.Tag
import meister.hackaton.maskserver.domain.user.model.School
import meister.hackaton.maskserver.domain.user.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun findUserById(id: Long): User?

    fun findUserByEmail(email: String): User?

    fun existsByEmail(email: String): Boolean

    fun findUsersByNameContainingAndSchoolOrderByName(keyword: String?, school: School): List<User>

    fun findUsersByMajorTag(majorTag: Tag): List<User>

}