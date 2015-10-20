package edu.utfpr.projetoweb.repositories;

import edu.utfpr.projetoweb.entities.PostEntity;
import java.util.List;
import javax.persistence.Query;
import static org.torpedoquery.jpa.Torpedo.*;


public class PostRepository extends Repository<PostEntity>{

    private static class PostHolder {
        public static PostRepository INSTANCE = new PostRepository();
    }
    public static PostRepository getInstance() {
        return PostHolder.INSTANCE;
    }
    private PostRepository(){
        super(PostEntity.class);
    }

    public List<PostEntity> findAllbyTitle(String title){
        PostEntity from = from(PostEntity.class);
        where(from.getTitle()).like().any(title);
        List<PostEntity> result = select(from).list(em);
        return result;
    }
    
    public List<PostEntity> findAllbyCategory(String category){
        PostEntity from = from(PostEntity.class);
        where(from.getCategory()).like().any(category);
        List<PostEntity> result = select(from).list(em);
        return result;
    }
    

        public List<PostEntity> getPostsbyLikes(int pageNumber){
               String query = String.format(
                  "from %s order by likes desc",
                  PostEntity.class.getSimpleName());
                Query q = em.createQuery(query, PostEntity.class).setFirstResult(pageNumber * 30).setMaxResults(30);      
        return q.getResultList();
    }
}
