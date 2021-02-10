package class101.foo.io;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequiredArgsConstructor
public class PostCacheService {

    private final PostRepository postRepository;
    private Page<Post> firstPostPage;

    @Scheduled(cron = "* * * * * *")
    public void updateFirstPostPage() {
        Pageable pageable = PageRequest.of(0, 20, Sort.by("id").descending());
        firstPostPage = postRepository.findAll(pageable);
    }

    public Page<Post> getFirstPostPage() {
        return this.firstPostPage;
    }


}
