package de.feedhelfer.app.repository;

import de.feedhelfer.app.entity.RSSFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by cernota on 23.12.15.
 */
@Repository
@Transactional
public interface FeedRepository extends JpaRepository<RSSFeed, Long> {

    @Modifying
    @Query("UPDATE RSSFeed r SET r.lastread = :lastread WHERE r.id = :feedId")
    int updateLastread(@Param("lastread") String lastread, @Param("feedId") long feedId);

}
