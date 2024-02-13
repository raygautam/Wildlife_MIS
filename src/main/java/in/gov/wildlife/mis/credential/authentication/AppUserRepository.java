package in.gov.wildlife.mis.credential.authentication;

import in.gov.wildlife.mis.domian.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUserNameAndIsActive(String userName,Boolean isActive);

    @Query(value = "UPDATE app_users SET failed_attempt = ?1 WHERE user_name = ?2",nativeQuery = true)
    @Modifying
    public void updateFailedAttempts(int failAttempts, String email);

    @Modifying
    @Query(value = "update app_users_roles set roles_id = ?1 where app_users_id = ?2 and roles_id = ?3",nativeQuery = true)
    public void updateRole(Long roleId,Long appUserId, Long existingId);

//    @Modifying
//    @Query(value = "insert into app_users_roles(app_users_id,roles_id) values(?1,?2)",nativeQuery = true)
//    public void insertRole(Long appUsersId, Long RoleId);
}