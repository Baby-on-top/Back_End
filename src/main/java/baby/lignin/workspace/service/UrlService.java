package baby.lignin.workspace.service;

import baby.lignin.workspace.entity.UrlEntity;
import baby.lignin.workspace.repository.UrlRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import baby.lignin.workspace.model.request.UrlLongRequest;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UrlService {

    private final BaseConversion conversion;
    private final UrlRepository urlRepository;

    public String convertToShortUrl(UrlLongRequest request) {
        var url = new UrlEntity();
        url.setLongUrl(request.getLongUrl());
        url.setExpiresDate(request.getExpiresDate());
        url.setCreatedDate(new Date());
        System.out.println("url = " + url.getLongUrl());
        var entity = urlRepository.save(url);

        return conversion.encode(entity.getId());
    }

    public String getOriginalUrl(String shortUrl) {
        var id = conversion.decode(shortUrl);
        var entity = urlRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no entity with " + shortUrl));

        if (entity.getExpiresDate() != null && entity.getExpiresDate().before(new Date())){
            urlRepository.delete(entity);
            throw new EntityNotFoundException("Link expired!");
        }
        return entity.getLongUrl();
    }
}