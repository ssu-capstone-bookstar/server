package book.book.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;


    @Transactional(readOnly = true)
    public Map<Long, List<String>> getTop4ImagesMapByBookCollectionId(List<Long> bookCollectionIds) {
        List<Image> images = imageRepository.findAllByBookCollectionIds(bookCollectionIds);

        return images.stream()
                .collect(Collectors.groupingBy(
                        Image::getDomainId,
                        Collectors.collectingAndThen(
                                Collectors.toList(), // value를 List<Image>로 만들고
                                list -> list.stream().
                                        limit(4).
                                        map(Image::getPath).
                                        toList() // 4개로 List<Image> 길이를 제한한다
                        )
                ));
    }
}
