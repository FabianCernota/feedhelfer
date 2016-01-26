package de.feedhelfer.app.repository;

import de.feedhelfer.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by cernota on 26.12.15.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
}
