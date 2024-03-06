package backend.mbti;

import backend.mbti.domain.member.Member;
import backend.mbti.exception.AppException;
import backend.mbti.exception.ErrorCode;
import backend.mbti.sign.SignRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.apache.commons.io.FilenameUtils.getFullPath;

@AllArgsConstructor
public class FileStore {
    private final SignRepository signRepository;

    @Value("${file.upload-dir}")
    private String filePath;
    public String getFullPath(String filename) {
        return filePath + filename;
    }

    public void upload(String username, MultipartFile multipartFile) throws IOException {
        Member member = signRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));

        String fileName = storeFile(multipartFile);
        member.getProfileImage().setOriginalFileName(multipartFile.getName());
        member.getProfileImage().setStoreFileName(fileName);
        signRepository.save(member);
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String storeFileName = UUID.randomUUID() + fileExtension;

        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return storeFileName;
    }
}
