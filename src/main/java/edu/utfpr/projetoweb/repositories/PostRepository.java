package edu.utfpr.projetoweb.repositories;

import edu.utfpr.projetoweb.entities.PostEntity;
import edu.utfpr.projetoweb.entities.UserEntity;
import java.util.List;
import javax.persistence.Query;
import static org.torpedoquery.jpa.Torpedo.*;

public class PostRepository extends Repository<PostEntity> {

    private static class PostHolder {

        public static PostRepository INSTANCE = new PostRepository();
    }

    public static PostRepository getInstance() {
        return PostHolder.INSTANCE;
    }

    private PostRepository() {
        super(PostEntity.class);
    }

    public List<PostEntity> findAllbyTitle(String title) {
        PostEntity from = from(PostEntity.class);
        where(from.getTitle()).like().any(title);
        List<PostEntity> result = select(from).list(em);
        return result;
    }

    public List<PostEntity> findAllbyCategory(String category) {
        PostEntity from = from(PostEntity.class);
        where(from.getCategory()).like().any(category);
        List<PostEntity> result = select(from).list(em);
        return result;
    }

    public List<PostEntity> findbyTitle(String title) {
        PostEntity from = from(PostEntity.class);
        where(from.getTitle()).eq(title);
        List<PostEntity> result = select(from).list(em);
        return result;
    }

    public List<PostEntity> getPostsbyLikes(int pageNumber) {
        String query = String.format(
                "from %s order by likes desc",
                PostEntity.class.getSimpleName());
        Query q = em.createQuery(query, PostEntity.class);
        int offset = pageNumber * 30;
        q.setFirstResult(offset);
        q.setMaxResults(offset + 30);
        return q.getResultList();
    }
    public List<PostEntity> getPostsbyLikesASC(int pageNumber) {
        String query = String.format(
                "from %s order by likes asc",
                PostEntity.class.getSimpleName());
        Query q = em.createQuery(query, PostEntity.class);
        int offset = pageNumber * 30;
        q.setFirstResult(offset);
        q.setMaxResults(offset + 30);
        return q.getResultList();
    }

    public List<PostEntity> getNewPosts(int currentPostId) {
        String query = String.format(
                "from %s where id > %d order by id asc",
                PostEntity.class.getSimpleName(),
                currentPostId);
        Query q = em.createQuery(query, PostEntity.class);
        return q.getResultList();
    }
    public List<PostEntity> getNewPosts() {
        String query = String.format(
                "from %s order by id desc",
                PostEntity.class.getSimpleName());
        Query q = em.createQuery(query, PostEntity.class);
        q.setMaxResults(20);
        return q.getResultList();
    }
    public List<PostEntity> getPostsbyUser(UserEntity user) {
        String query = String.format(
                "from %s where userid=%d order by likes desc",
                PostEntity.class.getSimpleName(),
                user.getId());
        Query q = em.createQuery(query, PostEntity.class);
        return q.getResultList();
    }

    public PostEntity findbyImgURL(String imgURL) {
        PostEntity from = from(PostEntity.class);
        where(from.getImgURL()).eq(imgURL);
        List<PostEntity> result = select(from).list(em);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }

    public boolean postExists(String imgURL) {
        PostEntity post = findbyImgURL(imgURL);
        return post != null;
    }

    @Override
    public boolean save(PostEntity post) {
        if (postExists(post.getImgURL())) {
            return false;
        } else {
            super.save(post);
            return true;
        }
    }

    public List<PostEntity> getPostsbyCategory(String category, int pageNumber) {
        //select id,category,title from postentity where category='funny'
        String query = String.format(
                "FROM %s WHERE category like \'%s\' order by likes desc",
                PostEntity.class.getSimpleName(),
                category);
        Query q = em.createQuery(query, PostEntity.class);
        int offset = pageNumber * 30;
        q.setFirstResult(offset);
        q.setMaxResults(offset + 30);
        return q.getResultList();
    }

}
